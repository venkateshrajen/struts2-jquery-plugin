(function($){
	
	/**
	 * Base Tag logic
	 */
	
	//Register handler to hide an element
	$.subscribeHandler('_struts2_jquery_hide', function(event, data) {
		
		$(this).hide();
	});

	//Register handler to show an element
	$.subscribeHandler('_struts2_jquery_show', function(event, data) {
		
		$(this).show();
	});

	//Register handler to remove an element
	$.subscribeHandler('_struts2_jquery_remove', function(event, data) {
		
		$(this).remove();
	});
	

	/**
	 * Interactive Tag logic
	 */
	
	//Register handler to hide an element
	$.subscribeHandler('_struts2_jquery_enable', function(event, data) {
		
		$(this).attr("disabled","false");
	});

	//Register handler to show an element
	$.subscribeHandler('_struts2_jquery_disable', function(event, data) {

		$(this).attr("disabled","disabled");
	});
	

	/**
	 * Input Tag logic
	 */	
	
	//Register handler to focus an input
	$.subscribeHandler('_struts2_jquery_focus',  function(event, data) {
		$(this).focus();
	});

	//Register handler to focus an input
	$.subscribeHandler('_struts2_jquery_blur',  function(event, data) {
		$(this).blur();
	});
	
	
	/**
	 * Container Tag logic
	 */

	//Register handler to load a container
	$.subscribeHandler('_struts2_jquery_container_load', function(event, data) {

		var container = $(event.target);
		
		//need to also make use of original attributes registered with the container (suc as onCompleteTopics)
		var attributes = container[0].attributes;
		var options = {};
		for(var i = 0; i < attributes.length; i++) {
			options[attributes[i].name.toLowerCase()] = attributes[i].value;
		}
		
		$.extend(options,event.data);
		$.extend(options,data);

		//Show indicator element (if any)
		if(options) {

			var indicatorId = options.indicatorid;
			if(indicatorId) { $('#' + indicatorId).show(); }
	
			var onAlwaysTopics = options.onalwaystopics;
			
	    	//publish all 'before' and 'always' topics
			if(onAlwaysTopics) {  
				var topics = onAlwaysTopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					container.publish(topics[i], container);
				}
			}
			
			if(options.onbeforetopics) {  
				var topics = onAlwaysTopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					container.publish(topics[i], container);
				}
			}
	    	
	    	//Set pre-loading text (if any)
			if(options.loadingtext) { container.html(options.loadingtext); }
			    				
			var onSuccessTopics = options.onsuccesstopics;
			
			options.success = function (data, textStatus) {
								
				if(indicatorId) { $('#' + indicatorId).hide(); }
				
				container.html(data);
						
				if(onSuccessTopics) {			  
					var topics = onSuccessTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						container.publish(topics[i], container);
					}
				}
				if(onAlwaysTopics) {
					var topics = onAlwaysTopics.split(',');  
					for ( var i = 0; i < topics.length; i++) {
						container.publish(topics[i], container);
					}
				}
			}
				
			var onCompleteTopics = options.oncompletetopics;
			options.complete = function (xhr, textStatus, errorThrown) {
	
				if(indicatorId) { $('#' + indicatorId).hide(); }
				
				if(xhr.status == 404) {
					
					container.html(xhr.responseText);
				}
				
				if(onCompleteTopics) {			  
					var topics = onCompleteTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						container.publish(topics[i], container);
					}
				}
				if(onAlwaysTopics) {  
					var topics = onAlwaysTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						container.publish(topics[i], container);
					}
				}
			}
			
			var onErrorTopics = options.onerrortopics;
			options.error = function (XMLHttpRequest, textStatus, errorThrown) {

				if(options.errortext) { container.html(options.errortext); }
				
				if(onErrorTopics) {			
					var topics = onErrorTopics.split(',');  
					for ( var i = 0; i < topics.length; i++) {
						container.publish(topics[i], container);
					}
				}
				if(onAlwaysTopics) {  
					var topics = onAlwaysTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						container.publish(topics[i], container);
					}
				}
			}
			
			//serialize forms & elements
			var serializeData;
			
			var formIds = options.formids;
			if(formIds) {
						
				var forms = formIds.split(',');  
				for ( var i = 0; i < forms.length; i++) {
					serializeData = (serializeData ? (serializeData + "&") : "") + $("#" + forms[i]).serialize();
				}
			}    		

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
			}     	
			$.extend(options,{data: serializeData});	
			
			//if reloadtopics exist, need to reset reload topics with new options
			if(options.reloadtopics) {			  
				var topics = options.reloadtopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					container.unsubscribe(topics[i]);
					container.subscribe(topics[i], '_struts2_jquery_container_load', options);
				}
			}

			//load container using ajax
			if(options.src) {
				
				options.type = "POST";
				options.url = options.src;
			
				$.ajax(options);
			
			}
		}
	});

	
	/**
	 * Action Tag logic
	 */

	//Register handler to execute action with no target
	$.subscribeHandler('_struts2_jquery_action_request', function(event, data) {

		var action = $(event.target);
		var options = event.data;
		$.extend(options,data);

		//Show indicator element (if any)
		if(options) {
				
			if(options.indicatorid) { $('#' + options.indicatorid).show(); }
				    				
			var indicatorId = options.indicatorid;
			var onSuccessTopics = options.onsuccesstopics;
			
			options.success = function (data, textStatus) {
								
				if(indicatorId) { $('#' + indicatorId).hide(); }
										
				if(onSuccessTopics) {			  
					var topics = onSuccessTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						action.publish(topics[i], action);
					}
				}
			}
				
			var onCompleteTopics = options.oncompletetopics;
			options.complete = function (xhr, textStatus, errorThrown) {
	
				if(indicatorId) { $('#' + indicatorId).hide(); }
								
				if(onCompleteTopics) {			  
					var topics = onCompleteTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						action.publish(topics[i], action);
					}
				}
			}
			
			var onErrorTopics = options.onerrortopics;
			options.error = function (XMLHttpRequest, textStatus, errorThrown) {
				
				if(onErrorTopics) {			
					var topics = onErrorTopics.split(',');  
					for ( var i = 0; i < topics.length; i++) {
						action.publish(topics[i], action);
					}
				}
			}
			
		    //serialize forms
			var formIds = options.formids;
			if(formIds) {
				
				var formData;
						
				var forms = formIds.split(',');  
				for ( var i = 0; i < forms.length; i++) {
					formData = (formData ? "&" : "") + $("#" + forms[i]).serialize();
				}
				
				$.extend(options,{data: formData});
			}    
					
			//execute request using ajax
			if(options.src) {
				
				options.type = "POST";
				options.url = options.src;
			
				$.ajax(options);
			
			}
		}
	});
		

	/**
	 * Dialog Tag logic
	 */
	
	//Register handler to open a dialog
	$.subscribeHandler('_struts2_jquery_dialog_open', function(event, data) {
		
		$(this).dialog('open');
	});

	//Register handler to cloadse a dialog
	$.subscribeHandler('_struts2_jquery_dialog_close', function(event, data) {
		
		$(this).dialog('close');
	});

	//Register handler to remove/destroy a dialog
	$.subscribeHandler('_struts2_jquery_dialog_destroy', function(event, data) {
		
		$(this).dialog('destroy');
	});

	//Register handler to enable a dialog
	$.subscribeHandler('_struts2_jquery_dialog_enable', function(event, data) {
		
		$(this).dialog('enable');
	});

	//Register handler to disable a dialog
	$.subscribeHandler('_struts2_jquery_dialog_disable', function(event, data) {
		
		$(this).dialog('disable');
	});
		
	
	/**
	 * Tabbed Pane Tag logic
	 */
	
	//Register handler to reload a tab
	$.subscribeHandler('_struts2_jquery_reloadTab',  function(event, data) {
		$(this).tabs('load', event.data);
	});
	
	//Register handler to select a tab
	$.subscribeHandler('_struts2_jquery_selectTab',  function(event, data) {
		$(this).tabs('select', event.data);
	});
	
	//Register handler to disable a tab
	$.subscribeHandler('_struts2_jquery_disableTab',  function(event, data) {
		$(this).tabs('disable', event.data);
	});
	
	//Register handler to enable a tab
	$.subscribeHandler('_struts2_jquery_enableTab',  function(event, data) {
		$(this).tabs('enable', event.data);
	});
	
	//Register handler to remove a tab
	$.subscribeHandler('_struts2_jquery_removeTab',  function(event, data) {
		$(this).tabs('remove', event.data);
	});
	
	//Register handler to show a tab
	$.subscribeHandler('_struts2_jquery_showTab',  function(event, data) {
		$(this).tabs('show', event.data);
	});
	
	//Register handler to hide a tab
	$.subscribeHandler('_struts2_jquery_hideTab', function(event, data) {
		$(this).tabs('hide', event.data);
	});

	/**
	 * Select Tag logic
	 */	
	
	//Register handler to load an input element
	$.subscribeHandler('_struts2_jquery_select_load', function(event, data) {

		var input = $(event.target);
		
		//need to also make use of original attributes registered with the input (such as elementIds)
		var attributes = input[0].attributes;
		var options = {};
		for(var i = 0; i < attributes.length; i++) {
			options[attributes[i].name.toLowerCase()] = attributes[i].value;
		}
		$.extend(options,data);

		if(options) {

			//Show indicator element (if any)
			var indicatorId = options.indicatorid;
			if(indicatorId) { $('#' + indicatorId).show(); }

	    	//Set pre-loading text (if any)
			if(options.loadingtext) { input.txt(options.loadingtext); }
				
			var onAlwaysTopics = options.onalwaystopics;
			
	    	//publish all 'before' and 'always' topics
			if(onAlwaysTopics) {  
				var topics = onAlwaysTopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					input.publish(topics[i], input);
				}
			}
			
			if(options.onbeforetopics) {  
				var topics = options.onbeforetopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					input.publish(topics[i], input);
				}
			}
			    				
			var onSuccessTopics = options.onsuccesstopics;
			options.success = function (data, textStatus) {
								
				if(indicatorId) { $('#' + indicatorId).hide(); }
				
				input[0].length = 0;
				                 
				if(typeof(data) == "object" || $.isArray(data)) {
					
					var i = -1;
					
					if(options.headerkey && options.headervalue) {
						var option = document.createElement("option");
						option.value = options.headerkey;
						option.text = options.headervalue;
						
						if(options.value == options.headervalue) {
							option.selected = true;
						}
						
						input[0].options[++i] = option;
					}
					
					if(options.emptyoption) {
						input[0].options[++i] = document.createElement("option");
					}
					
					for (var key in data) {
						
						var option = document.createElement("option");
						option.value = key;
						option.text = data[key];

						if(options.value == option.value) {
							option.selected = true;
						}
						
						input[0].options[++i] = option;
					}		
				}		        
		        
				if(onSuccessTopics) {			  
					var topics = onSuccessTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						input.publish(topics[i], input);
					}
				}
				if(onAlwaysTopics) {
					var topics = onAlwaysTopics.split(',');  
					for ( var i = 0; i < topics.length; i++) {
						input.publish(topics[i], input);
					}
				}
			}
				
			var onCompleteTopics = options.oncompletetopics;
			options.complete = function (xhr, textStatus, errorThrown) {
	
				if(indicatorId) { $('#' + indicatorId).hide(); }
								
				if(onCompleteTopics) {			  
					var topics = onCompleteTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						input.publish(topics[i], input);
					}
				}
				if(onAlwaysTopics) {  
					var topics = onAlwaysTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						input.publish(topics[i], input);
					}
				}
			}
			
			var onErrorTopics = options.onerrortopics;
			options.error = function (XMLHttpRequest, textStatus, errorThrown) {

				if(options.errortext) { container.html(options.errortext); }
				
				if(onErrorTopics) {			
					var topics = onErrorTopics.split(',');  
					for ( var i = 0; i < topics.length; i++) {
						input.publish(topics[i], input);
					}
				}
				if(onAlwaysTopics) {  
					var topics = onAlwaysTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						input.publish(topics[i], input);
					}
				}
			}
			
		    //serialize forms & elements
			var serializeData;
			
			var formIds = options.formids;
			if(formIds) {
						
				var forms = formIds.split(',');  
				for ( var i = 0; i < forms.length; i++) {
					serializeData = (serializeData ? (serializeData + "&") : "") + $("#" + forms[i]).serialize();
				}
			}    		

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
			}    
			$.extend(options,{data: serializeData});			
			
	    	//load input using ajax
			if(options.src) {
				
				options.type = "GET";
				options.url = options.src;
				options.dataType = "json";
			
				$.ajax(options);
			
			}
		}
	});
	
	
	/**
	 * Form Tag logic
	 */	
	
	//Register handler to submit a form element
	$.subscribeHandler('_struts2_jquery_form_submit', function(event, data) {
		
		var form = $(event.target);
		form.submit();
	});
	
	
	/**
	 * STRUTS2 JQUERY BINDINGS
	 */
	_struts2_jquery = {
			
		bind: function(el) {
			
			if(el) {
				var $el = $(el);
				el = $el[0];
				var attributes = el.attributes;
				var options = {};
				
				//attributes names are sometimes returned all lower/upper case so we need to force to a case for uniformity
				for(var i = 0; i < attributes.length; i++) {
					options[attributes[i].name.toLowerCase()] = attributes[i].value;
				}
				
				var tag = el.tagName.toLowerCase();
				
				if(tag == "div") {
			
					if(el.className.indexOf("_struts2_jquery_class_tabbedpane") >= 0) {
						tag = "tabbedpane";
					} else if(el.className.indexOf("_struts2_jquery_class_dialog") >= 0) {
						tag = "dialog";
					}
				} 
				
				this[tag]($el, options);
			}
		},
		
		base:  function($elem, options){
		
			if(options.hidetopics) {			  
				var topics = options.hidetopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_hide',options);
				}
			}

			if(options.showtopics) {			  
				var topics = options.showtopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_show',options);
				}
			}

			if(options.removetopics) {			  
				var topics = options.removetopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_remove',options);
				}
			}
		},
		
		interactive:  function($elem, options){

			if(options.enabletopics) {			  
				var topics = options.enabletopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_enable',options);
				}
			}

			if(options.disabletopics) {			  
				var topics = options.disabletopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_disable',options);
				}
			}
		},
		
		container:  function($elem, loadHandlerName, options){

			if(options.reloadtopics) {			  
				var topics = options.reloadtopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i], loadHandlerName, options);
				}
			}
		},
	
		input:  function($elem, loadHandlerName, options){
			
			if(options.reloadtopics) {			  
				var topics = options.reloadtopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i], loadHandlerName, options);
				}
			}
			
			if(options.focustopics) {			  
				var topics = options.focustopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_focus',options);
				}
			}	
			
			if(options.blurtopics) {			  
				var topics = options.blurtopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_blur',options);
				}
			}	
			
			//bind change event to onChange topics
			if(options.onchangetopics) {  
				var topics = options.onchangetopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.publishOnEvent('change',topics[i]);
				}
			}	
			
			//bind focus event to onFocus topics
			if(options.onfocustopics) {  
				var topics = options.onfocustopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.publishOnEvent('focus',topics[i]);
				}
			}	
			
			//bind blur event to onBlur topics
			if(options.onblurtopics) {  
				var topics = options.onblurtopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.publishOnEvent('blur',topics[i]);
				}
			}
		},
			
		action: function($elem, containerLoadHandlerName, linkLoadHandlerName, options){

	    	if($elem.attr('href')) { $elem.attr('href','#'); }
	    	
	    	//if($elem.attr('href')) { $elem.removeAttr('href'); $elem.css('cursor','pointer'); }
	    		
	    	//bind change event to onClick topics
			if(options.onclicktopics) {  
				var topics = options.onclicktopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.publishOnEvent('click',topics[i]);	
				}
			}	
			
	    	var actionTopic = '_struts2_jquery_topic_' + options.id;
	    	var href = options.href;

    		//subscribe all targets to this action's custom execute topic
	    	if(options.targets) {  
	    		
	    		//target subscription needs to be done after document load in case element exists in the dom AFTER the current action object 
	    		$(function() {
					var topics = options.targets.split(',');
					for ( var i = 0; i < topics.length; i++) {
						var target = topics[i];
						if('#tab' == target) {
							$elem.closest('.ui-tabs-panel').subscribe(actionTopic, containerLoadHandlerName, options);
			    		} else {
			    			$('#' + target).subscribe(actionTopic, containerLoadHandlerName, options);
			    		}
					}
	    		});
	    		
			} else {   // if no targets, then the action can still execute ajax request and will handle itself (no loading result into container
					
				//bind custom action topic to click event and process
		    	if(href != null || options.oncompletetopics || options.onsuccesstopics || options.onerrortopics) {

		    		$elem.subscribe(actionTopic, linkLoadHandlerName, options);
		    	}
			}

	    	options.src = href;
	    	if(options.validate) { options.enableJSONValidation = 'true'; }
	    	$elem.publishOnEvent('click', actionTopic);
       
		},
			
		select: function($elem, options){

			var loadHandlerName = '_struts2_jquery_select_load';
			
			this.base($elem, options);
			this.interactive($elem, options);
			//this.container($elem, options);  (reloadTopics already implemented by input)
			this.input($elem, loadHandlerName, options);

	    	//load select using ajax
			if(options.src) {
				$elem.bind('struts2_jquery_topic_load', null, _subscribe_handlers[loadHandlerName]);
				$elem.trigger('struts2_jquery_topic_load', options);
				//_subscribe_handlers[loadHandlerName]($.Event({type: 'struts2_jquery_topic_load', target: $elem[0]}),options);
			}
		},
		
		div: function($elem, options){

			var loadHandlerName = '_struts2_jquery_container_load';
			
			this.base($elem, options);
			this.container($elem, loadHandlerName, options);

	    	//load div using ajax
			if(options.src) {
				$elem.bind('struts2_jquery_topic_load', null, _subscribe_handlers[loadHandlerName]);
				$elem.trigger('struts2_jquery_topic_load', options);
				//_subscribe_handlers[loadHandlerName]($.Event({type: 'struts2_jquery_topic_load', target: $elem[0]}),options);
			}
		},
		
		form: function($elem, options){

			var submitHandlerName = '_struts2_jquery_form_submit';
			var containerLoadHandlerName = '_struts2_jquery_container_load';
			
			this.base($elem, options);

			//bind submit event to onSubmit topics
			if(options.onsubmittopics) {  
				var topics = options.onsubmittopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.publishOnEvent('submit',topics[i]);
				}
			}	
			
			if(options.submittopics) {			  
				var topics = options.submittopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					
					var targetId = options.targetid;
					if(targetId) {
						options.src = options.action;
						options.formids = options.id;
						if('#tab' == targetId) {
							$elem.closest('.ui-tabs-panel').subscribe(topics[i], containerLoadHandlerName, options);
			    		} else {
			    			$('#' + targetId).subscribe(topics[i], containerLoadHandlerName, options);
			    		}
					} else {

						$elem.subscribe(topics[i], submitHandlerName, options);
					}	
				}

			}
		},
		
		a: function($elem, options){
			
			var linkLoadHandlerName = '_struts2_jquery_action_request';
			var containerLoadHandlerName = '_struts2_jquery_container_load';
			
			this.base($elem, options);
			this.interactive($elem, options);
			this.action($elem, containerLoadHandlerName, linkLoadHandlerName, options);
			
		},
		
		button: function($elem, options){
			
			var linkLoadHandlerName = '_struts2_jquery_action_request';
			var containerLoadHandlerName = '_struts2_jquery_container_load';
			
			this.base($elem, options);
			//	this.container($elem, containerLoadHandlerName, options);
			this.interactive($elem, options);
			this.action($elem, containerLoadHandlerName, linkLoadHandlerName, options);

			$elem.attr('type','button');  //remove - covered by SubmitHandler?
			$elem.removeAttr('name');
		},
		
		dialog: function($elem, options){
			
			if(options.hidetopics) {			  
				var topics = options.hidetopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_dialog_close',options);
				}
			}

			if(options.showtopics) {			  
				var topics = options.showtopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_dialog_open',options);
				}
			}

			if(options.removetopics) {			  
				var topics = options.removetopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_dialog_destroy',options);
				}
			}
			
			if(options.enabletopics) {			  
				var topics = options.enabletopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_dialog_enable',options);
				}
			}

			if(options.disabletopics) {			  
				var topics = options.disabletopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					$elem.subscribe(topics[i],'_struts2_jquery_dialog_disable',options);
				}
			}
			
			var parameters = {};
			parameters.autoOpen = false;
			parameters.modal = (options.modal ? options.modal : false);
			parameters.resizable = (options.resizable ? options.resizable : true);
			if(options.height) { parameters.height = options.height; }
			if(options.width) { parameters.height = options.width; }
			if(options.position) { parameters.position = options.position; }
			
			if(options.title) { $elem.attr("title", options.title); }
			if(options.data) {  $elem.data = options.data; }	
			
			if(options.buttons) {
				
				parameters.buttons = {};
				
				var buttontopics;
				if(options.buttontopics) {
					buttontopics = options.buttontopics.split(',');
				} else {
					buttontopics = [];
				}
				
				var $dialog = $elem;  //used for closure
				$dialog.data('buttonTopics',{});  //used for closure
								
				var buttons = options.buttons.split(',');
				for ( var i = 0; i < buttons.length; i++) {
					var button = buttons[i];
					var topic = buttontopics[i];
					if(buttontopics.length >= i+1) {
						$dialog.data('buttonTopics')[button] = topic;  
						parameters.buttons[button] = function(event) { 
							$elem.publish($dialog.data('buttonTopics')[event.target.innerHTML], $dialog) 
						};
					} else {
						parameters.buttons[button] = function(event) {};
					}
				}
			}

			$elem.css("display", "none");
			
			if(options.src) {
				
				$elem.bind('dialogopen', function(event, ui) {

					var loadHandlerName = '_struts2_jquery_container_load';
					
					var $dialogContent = $(".ui-dialog-content",$elem);
					$dialogContent.bind('struts2_jquery_topic_load', null, _subscribe_handlers[loadHandlerName]);
					$dialogContent.trigger('struts2_jquery_topic_load', options);
					
					//$(".ui-dialog-content",$elem).load(options.src);
				});
			}			
				
			$elem.dialog(parameters);
			
			//move id from dialog to dialog contents
			$(".ui-dialog-content",$elem).attr("id",$elem.attr("id"));
			$elem.removeAttr("id");
		},
		
		tabbedpane: function($elem, options){
			
	    	//instantiate the tabbed pane
	    	var $tabs = $elem.tabs({ cache: (options.iscache || false),  selected: options.selected});
	    	
	    	("a",$tabs).each( function(tabIndex, el){
	    			
	    		$tab = $(el);
	    		
	    		if($tab.attr("isdisabled")){
					$tabs.tabs('disable', tabIndex);
	    		}
	    		
	    		if($tab.attr("isselected")){
					$tabs.tabs('select', tabIndex);
	    		}
	    		
	    		var hideTopics = $tab.attr("hidetopics");
				if(hideTopics) {			  
					var topics = hideTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.subscribe(topics[i],'_struts2_jquery_hideTab',tabIndex);
					}
				}

	    		var showTopics = $tab.attr("showtopics");
				if(showTopics) {			  
					var topics = showTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.subscribe(topics[i],'_struts2_jquery_showTab',tabIndex);
					}
				}

	    		var removeTopics = $tab.attr("removetopics");
				if(removeTopics) {			  
					var topics = removeTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.subscribe(topics[i],'_struts2_jquery_removeTab',tabIndex);
					}
				}

	    		var reloadTopics = $tab.attr("reloadtopics");
				if(reloadTopics) {			  
					var topics = reloadTopic.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.subscribe(topics[i],'_struts2_jquery_reloadTab',tabIndex);
					}
				}

	    		var focusTopics = $tab.attr("focustopics");
				if(focusTopics) {			  
					var topics = focusTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.subscribe(topics[i],'_struts2_jquery_selectTab',tabIndex);
					}
				}	

	    		var blurTopics = $tab.attr("blurtopics");
				if(options.blurtopics) {			  
					var topics = blurTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.subscribe(topics[i],'_struts2_jquery_blur',tabIndex);
					}
				}	

	    		var enableTopics = $tab.attr("enabletopics");
				if(enableTopics) {			  
					var topics = enableTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.subscribe(topics[i],'_struts2_jquery_enableTab',tabIndex);
					}
				}

	    		var disableTopics = $tab.attr("disabletopics");
				if(disableTopics) {			  
					var topics = disableTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.subscribe(topics[i],'_struts2_jquery_disableTab',tabIndex);
					}
				}

	    		var onChangeTopics = $tab.attr("onchangetopics");
				if(onChangeTopics) {  
					var topics = onChangeTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.publishOnEvent('change',topics[i]);
					}
				}	

	    		var onFocusTopics = $tab.attr("onfocustopics");
				if(onFocusTopics) {  
					var topics = onFocusTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.publishOnEvent('focus',topics[i]);
					}
				}	

	    		var onBlurTopics = $tab.attr("onblurtopics");
				if(onBlurTopics) {  
					var topics = onBlurTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						$tabs.publishOnEvent('blur',topics[i]);
					}
				}
	    	});
		}
	};		
	
})(jQuery);