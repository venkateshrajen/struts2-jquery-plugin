/*
 * jquery.struts2.js
 *
 * This is the binding file integrating the jquery flot charting framework with  the struts2.jquery.ui framework
 * 
 * See the flot framework at: http://code.google.com/p/flot/
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
 *  Flot Usage: 
 *  
 *  See:  http://people.iola.dk/olau/flot/API.txt
 */

(function($){
	
	Struts2jQuery.widget("chart", function($elem, options){
	
		var loadHandlerName = '_struts2_jquery_chart_load';
		
		Struts2jQuery.base($elem, options);
		Struts2jQuery.container($elem, options, loadHandlerName);
		
		if($.plot) {
			
			var flotOptions = {};
			
			if(options) { 
								
				if(options.colors) { 
					flotOptions.colors = options.colors.split(',');
				};
				
				flotOptions.grid = {
					show:  options.grid,
					color: options.gridcolor,
					backgroundColor: options.gridbackground,
					tickColor: options.tickcolor,
					borderWidth: options.borderwidth
				};
				
				if(options.onplotclicktopic && options.onplotclicktopic.length > 0) {
					flotOptions.grid.clickable = true;
					elem.publishOnEvent('plotclick',onplotclicktopic);
				};

				if(options.onplothovertopic && options.onplothovertopic.length > 0) {
					flotOptions.grid.hoverable = true;
					elem.publishOnEvent('plothover',onplothovertopic);
				};
				
				flotOptions.series = {
					lines: { show: options.line == "true" },
					points: { show: options.point == "true" },
					bars: { show: options.bar == "true" },
					pie: { show: options.pie == "true" },
					stack: { show: options.stacked == "true" }
				};
				
				flotOptions.legend = {
					position: options.legendposition || "se",
					show: options.showlegend == "true",
					backgroundColor: options.legendcolor,
					backgroundOpacity: options.legendopacity
				};
				
				flotOptions.xaxis = {
					mode: options.xaxis1time ? "time" : null,
					min: options.xaxis1min ? options.xaxis1min : null,
					max: options.xaxis1max ? options.xaxis1max : null
				};
				flotOptions.yaxis = {
					mode: options.yaxis1time ? "time" : null,
					min: options.yaxis1min ? options.yaxis1min : null,
					max: options.yaxis1max ? options.yaxis1max : null
				};
				flotOptions.x2axis = {
					mode: options.xaxis2time ? "time" : null,
					min: options.xaxis2min ? options.xaxis2min : null,
					max: options.xaxis2max ? options.xaxis2max : null
				};
				flotOptions.y2axis = {
					mode: options.yaxis2time ? "time" : null,
					min: options.yaxis2min ? options.yaxis2min : null,
					max: options.yaxis2max ? options.yaxis2max : null
				};
								
				var data = eval(options.data) || [];
				var lines = options.lines ? options.lines.split(',') : [];
				var points = options.points ? options.points.split(',') : [];
				var bars = options.bars ? options.bars.split(',') : [];
				var labels = options.labels ? options.labels.split(',') : [];
				var seriesClickTopics =	options.seriesclicktopics ? options.seriesclicktopics.split(',') : [];
				var seriesHoverTopics =	options.serieshovertopics ? options.serieshovertopics.split(',') : [];
								
				for(i = 0; i < data.length; i++){
					var series = data[i];
					if(labels.length > i) { series.label = labels[i]; }
					if(lines.length > i && lines[i] == "true") { series.lines = {show: true}; }
					if(points.length > i && points[i] == "true") { series.points = {show: true}; }
					if(bars.length > i && bars[i] == "true") { series.bars = {show: true}; }

					if(seriesClickTopics.length > i) { 
						series.clickable = true; 
						elem.publishOnEvent('plotclick',seriesClickTopics[i]);
					}

					if(seriesHoverTopics.length > i) { 
						series.hoverable = true; 
						elem.publishOnEvent('plothover',seriesHoverTopics[i]);
					}
				}
				
				var container = $elem.parent().width($elem.width());

				if(options.xaxis1label && options.xaxis1label.length > 0) {
					container.css({'position': 'relative'});
					var label = $("<div class='_struts2_jquery_chart_label_x1'/>").text(options.xaxis1label);
					container.append(label);
				}
				
				if(options.yaxis1label && options.yaxis1label.length > 0) {
					container.css({'position': 'relative'});
					var label = $("<div class='_struts2_jquery_chart_label_y1'/>").css({'text-align': 'center', 'position': 'absolute', 'left': 0, 'top': 0}).text(options.yaxis1label);
					container.prepend(label).css({'padding-left': (label.height() + 3)});
					label.flipv().css('top', ($elem.height()/2) - (label.height()/2));
				} 

				if(options.xaxis2label && options.xaxis2label.length > 0) {
					var label = $("<div class='_struts2_jquery_chart_label_x2'/>").css({'text-align': 'center'}).text(options.xaxis2label);
					container.prepend(label);
				}
				
				if(options.yaxis2label && options.yaxis2label.length > 0) {
					container.css({'position': 'relative'});
					var label = $("<div class='_struts2_jquery_chart_label_y2'/>").css({'position': 'absolute', 'right': 0, 'top': 0}).text(options.yaxis2label);
					container.append(label).css({'margin-right': label.height() + 3});
					label.flipv().css({'top': (container.height()/2) - (label.height()/2)});
				} 				
				
				flotOptions.src = options.src;
				flotOptions.pollmillis = options.pollmillis;
					
				if(options.options) {
			        var userOptionsStr = options.options;
			        var userOptions = window[userOptionsStr];
			        if (!userOptions) {
			        	userOptions = eval ("( " + userOptionsStr + " )" );
			        }
			        $.extend(flotOptions, userOptions);
				}
			}
			
			if(!($elem[0].height || $elem[0].style.height )) {
				$elem.css('height',$elem.parent().height());
			}
			
			if(!($elem[0].width || $elem[0].style.width )) {
				$elem.css('width','auto');
			}
			
			//execute the widget function
			$.plot($elem, data, flotOptions);

	    	//load chart using ajax
			if(flotOptions.src) {
	    		
				$elem.trigger('_struts2_jquery_trigger_fetch', [flotOptions,loadHandlerName]);
			}
	
		} else {
			
			$elem.text("Struts2-JQuery charting is not enabled. Set the chartingEnabled='true' attribute in the head tag first.");
		}
	});
	
	
	//Register handler to load a chart
	$.subscribeHandler('_struts2_jquery_chart_load', function(event, data) {
	
		var chart = $(event.target);
		
		//need to also make use of original attributes registered with the chart (such as onCompleteTopics)
		var attributes = chart[0].attributes;
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
		isDisabled = chart.attr('disabled') == null ? isDisabled : chart.attr('disabled');
		if(event.originalEvent) {	//means that chart load is being triggered by other action (link button/link click) need to see if that button/link is disabled
			isDisabled = $(event.originalEvent.currentTarget).attr("disabled") == null ? isDisabled : $(event.originalEvent.currentTarget).attr("disabled");
		}
	
		if(isDisabled != true && isDisabled != 'true') {
				
			//Show indicator element (if any)
			var indicatorId = options.indicatorid;
			if(indicatorId) { $('#' + indicatorId).show(); }
	
			//Set pre-loading text (if any)
			if(options.loadingtext) { chart.html(options.loadingtext); }
			
			var onAlwaysTopics = options.onalwaystopics;
			
			//publish all 'before' and 'always' topics
			if(onAlwaysTopics) {  
				var topics = onAlwaysTopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					chart.publish(topics[i], chart);
				}
			}
			
			if(options.onbeforetopics) {  
				var topics = options.onbeforetopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					chart.publish(topics[i], chart);
				}
			}
								
			var onSuccessTopics = options.onsuccesstopics;
			
			options.success = function (data, textStatus) {
	
				chart.data('loaded',true);
				
				if(indicatorId) { $('#' + indicatorId).hide(); }
					
				//TODO: allow using simple maps/strings
				if(typeof(data) == "object" || $.isArray(data)) {
					
					//chart.setData(data);
					//chart.setupGrid();	//maybe? expensive to do all the time?
					//chart.draw();
					
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

					$.plot(chart, data, options);
				}		  
						
				if(onSuccessTopics) {			  
					var topics = onSuccessTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						chart.publish(topics[i], chart);
					}
				}
				if(onAlwaysTopics) {
					var topics = onAlwaysTopics.split(',');  
					for ( var i = 0; i < topics.length; i++) {
						chart.publish(topics[i], chart);
					}
				}
			};
				
			var onCompleteTopics = options.oncompletetopics;
			options.complete = function (xhr, textStatus, errorThrown) {
	
				if(indicatorId) { $('#' + indicatorId).hide(); }
				
				if(xhr.status == 404) {
					
					chart.html(xhr.responseText);
				}
				
				if(onCompleteTopics) {			  
					var topics = onCompleteTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						chart.publish(topics[i], container);
					}
				}
				if(onAlwaysTopics) {  
					var topics = onAlwaysTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						chart.publish(topics[i], container);
					}
				}
			};
			
			var onErrorTopics = options.onerrortopics;
			options.error = function (XMLHttpRequest, textStatus, errorThrown) {
	
				if(options.errortext) { chart.html(options.errortext); }
				
				if(onErrorTopics) {			
					var topics = onErrorTopics.split(',');  
					for ( var i = 0; i < topics.length; i++) {
						chart.publish(topics[i], container);
					}
				}
				if(onAlwaysTopics) {  
					var topics = onAlwaysTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						chart.publish(topics[i], container);
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
					chart.unsubscribe(topics[i]);
					chart.subscribe(topics[i], '_struts2_jquery_chart_load', options);
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