/*
 * jquery.struts2.js
 *
 * This is the binding file integrating the jquery jqGrid grid framework with the struts2.jquery.ui framework
 * 
 * See the jQGrid framework at: http://trirand.com
 *
 * Requires use of jQuery. Tested with jQuery 1.3 and above
 *
 * Copyright (c) 2008 Eric Chijioke (obinna a-t g mail dot c o m)
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 *  Release Notes:
 *  
 *  
 *  
 *  jQGrid Usage: 
 *  
 *  TODO:
 */

(function($){
	
	String.prototype.endsWith = function(str) {
		return this.match(str+"$") == str;
	};
	
	Struts2jQuery.widget("grid", function($elem, options){
	
		var loadHandlerName = '_struts2_jquery_grid_load';
		
		Struts2jQuery.base($elem, options);
		Struts2jQuery.container($elem, options, loadHandlerName);
		
		if($elem.jqGrid) {
			
			var gridOptions = {};
	        
			if(options) { 
				
				gridOptions.colModel = eval("(" + options.colmodel + ")");
				gridOptions.sortorder = options.sortascending == "false" ? "desc" : "asc";
				gridOptions.sortname = options.sortcolumn || null;
				gridOptions.rowNum = options.rowsperpage || -1;
				if(options.rowsperpageoptions) {
					gridOptions.rowList = options.rowsperpageoptions.split(',');
				}
				gridOptions.viewrecords = options.showtotal == "true" ? true : false;
				gridOptions.caption = options.caption || null;
				
				if(options.pager == "true") { 
					gridOptions.pager = "#" + options.id + "_pager";
				} else {
					$("#" + options.id + "_pager").hide();
				};
									
				if(options.data && options.data.length > 0) {
					gridOptions.datastr = options.data;
					gridOptions.datatype = 'jsonstring';
					gridOptions.jsonReader = { 
				        root: "rows", 
				        page: "page", 
				        total: "total", 
				        records: "records", 
				        repeatitems: true, 
				        cell: "cell", 
				        id: "id",
				        userdata: "userdata", 
				        subgrid: {root:"rows", repeatitems: true, cell:"cell" } 
				    };
				
				} else {
					
					gridOptions.url = options.src;				
				}
				
				if(options.options) {
			        var userOptionsStr = options.options;
			        var userOptions = window[userOptionsStr];
			        if (!userOptions) {
			        	userOptions = eval ("( " + userOptionsStr + " )" );
			        }
			        $.extend(gridOptions, userOptions);
				}
				
				if(gridOptions.url) { //load grid using ajax
				
					gridOptions.dataType='json';
					gridOptions.mtype='post';
					$elem.trigger('_struts2_jquery_trigger_fetch', [gridOptions,loadHandlerName]);
				
				} else { //load grid with 'local' data 
				
					$elem.jqGrid(gridOptions);
				}
				
				if(options.width) {
					if(options.width.endsWith('%')) {
						var fluidWidth = options.width.substring(0, options.width.length - 1) / 100.0;
						$(window).bind('resize.__struts2_jquery_grid', {gridId:$elem.attr('id')}, function(event) {
							var $grid = $("#" + event.data.gridId);
							$grid.setGridWidth($grid.closest(".ui-jqgrid").parent().width() *  fluidWidth);
							return false;
						}).trigger('resize.__struts2_jquery_grid');						
					} else {
						$elem.setGridWidth(options.width,true);
					}
				}
				
				if(options.height) {
					if(options.height.endsWith('%')) {
						var fluidHeight = options.height.substring(0, options.height.length - 1) / 100.0;						
						$(window).bind('resize.__struts2_jquery_grid',{gridId:$elem.attr('id')}, function(event) {
							var $grid = $("#" + event.data.gridId);
							$grid.setGridHeight($grid.closest(".ui-jqgrid").parent().height() *  fluidHeight);
							return false;
						}).trigger('resize.__struts2_jquery_grid');						
					} else {
						$elem.setGridHeight(options.height,true);
					}
				}
			}
				
		} else {
			
			$elem.text("Struts2-JQuery grid is not enabled. Set the gridEnabled='true' attribute in the head tag first.");
		}
	});
	
	
	//Register handler to load a chart
	$.subscribeHandler('_struts2_jquery_grid_load', function(event, data) {
	
		var grid = $(event.target);
		
		//need to also make use of original attributes registered with the chart (such as onCompleteTopics)
		var attributes = grid[0].attributes;
		var options = {};
		for(var i = 0; i < attributes.length; i++) {
			options[attributes[i].name.toLowerCase()] = attributes[i].value;
		}
		
		$.extend(options,event.data);
		if(data && !data.id) { //we don't want to merge 'options; when passed an element as the data (such as when published from an onsuccesstopic)
			$.extend(options,data);
		}
		
		var isDisabled = false;
		isDisabled = options.disabled == null ? isDisabled : options.disabled;
		isDisabled = grid.attr('disabled') == null ? isDisabled : grid.attr('disabled');
		if(event.originalEvent) {	//means that grid load is being triggered by other action (link button/link click) need to see if that button/link is disabled
			isDisabled = $(event.originalEvent.currentTarget).attr("disabled") == null ? isDisabled : $(event.originalEvent.currentTarget).attr("disabled");
		}
	
		if(isDisabled != true && isDisabled != 'true') {
				
			//Show indicator element (if any)
			var indicatorId = options.indicatorid;
			if(indicatorId) { $('#' + indicatorId).show(); }
	
			//Set pre-loading text (if any)
			if(options.loadingtext) { grid.html(options.loadingtext); }
			
			var onAlwaysTopics = options.onalwaystopics;
			
			//publish all 'before' and 'always' topics
			if(onAlwaysTopics) {  
				var topics = onAlwaysTopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					grid.publish(topics[i], grid);
				}
			}
			
			if(options.onbeforetopics) {  
				var topics = options.onbeforetopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					grid.publish(topics[i], grid);
				}
			}
								
			var onSuccessTopics = options.onsuccesstopics;
			
			options.success = function (data, textStatus) {
	
				grid.data('loaded',true);
				
				if(indicatorId) { $('#' + indicatorId).hide(); }
					
				//TODO: allow using simple maps/strings
				if(typeof(data) == "object" || $.isArray(data)) {
					
					//convert associative-array series data int proper 2D array of data.
					//this is to allow us to pass maps to the JSON result as the series data (JSONResult serializes maps as associative arrays)
					if(typeof(data) == "object") {					
						for(i in data) {						
							var series = data[i];
							if(series && typeof(series.data) == "object" ) {
								var seriesData = series.data;
								var seriesDataArray = [];
								for(x in seriesData) {
									seriesDataArray.push([x,seriesData[x]]);
								}
								series.data = seriesDataArray;
							}
						}						
					}

					$elem.jQGrid(options);
				}		  
						
				if(onSuccessTopics) {			  
					var topics = onSuccessTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						grid.publish(topics[i], grid);
					}
				}
				if(onAlwaysTopics) {
					var topics = onAlwaysTopics.split(',');  
					for ( var i = 0; i < topics.length; i++) {
						grid.publish(topics[i], grid);
					}
				}
			};
				
			var onCompleteTopics = options.oncompletetopics;
			options.complete = function (xhr, textStatus, errorThrown) {
	
				if(indicatorId) { $('#' + indicatorId).hide(); }
				
				if(xhr.status == 404) {
					
					grid.html(xhr.responseText);
				}
				
				if(onCompleteTopics) {			  
					var topics = onCompleteTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						grid.publish(topics[i], container);
					}
				}
				if(onAlwaysTopics) {  
					var topics = onAlwaysTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						grid.publish(topics[i], container);
					}
				}
			};
			
			var onErrorTopics = options.onerrortopics;
			options.error = function (XMLHttpRequest, textStatus, errorThrown) {
	
				if(options.errortext) { grid.html(options.errortext); }
				
				if(onErrorTopics) {			
					var topics = onErrorTopics.split(',');  
					for ( var i = 0; i < topics.length; i++) {
						grid.publish(topics[i], container);
					}
				}
				if(onAlwaysTopics) {  
					var topics = onAlwaysTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						grid.publish(topics[i], container);
					}
				}
			};
			
			//serialize forms & elements
			var serializeData;
			
			var formIds = options.formids;
			if(formIds) {
						
				var forms = formIds.split(',');  
				for ( var i = 0; i < forms.length; i++) {
					serializeData = (serializeData ? (serializeData + "&") : "") + $("#" + forms[i]).serialize();
				}
			};    		
	
			var elementIds = options.elementids;
			if(elementIds) {
						
				var elements = elementIds.split(',');
				for ( var i = 0; i < elements.length; i++) {
					var element = $('#' + elements[i])[0];
					if(element && element.name){
						serializeData = (serializeData ? (serializeData + "&") : "") + element.name + "=" + element.value;
						//serializeData[element.name] = element.value;
					}
				}
			};
			
			if(serializeData && options.validate) {
				serializeData['struts.enableJSONValidation'] = true;
			};
			
			$.extend(options,{data: serializeData});	
			
			//if reloadtopics exist, need to reset reload topics with new options
			if(options.reloadtopics) {			  
				var topics = options.reloadtopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					grid.unsubscribe(topics[i]);
					grid.subscribe(topics[i], '_struts2_jquery_grid_load', options);
				}
			};
	
			//load container using ajax
			if(options.src) {
				
				options.type = "POST";
				options.url = options.src;
				options.dataType = "json";			
				if(!options.data) { options.data = {}; }	//fix 'issue' wherein IIS will reject post without data
				$.ajax(options);
			
			};
		}
	});

})(jQuery);