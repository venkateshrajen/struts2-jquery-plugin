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
	 * Container Tag logic
	 */

	//Register handler to load a container
	$.subscribeHandler('_struts2_jquery_load', function(event, data) {

		var container = $(event.target);
		var options = {};
		options = $.extend(options, container.data('load'),data)

		//Show indicator element (if any)
		if(options) {
				
			if(options.indicatorId) { $('#' + options.indicatorId).show(); }
	
			var onAlwaysTopics = options.onAlwaysTopics;
			
	    	//publish all 'before' and 'always' topics
			if(onAlwaysTopics) {  
				var topics = onAlwaysTopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					container.publish(topics[i], container);
				}
			}
			
			if(options.onBeforeTopics) {  
				var topics = onAlwaysTopics.split(',');
				for ( var i = 0; i < topics.length; i++) {
					container.publish(topics[i], container);
				}
			}
	    	
	    	//Set pre-loading text (if any)
			if(options.loadingText) { container.html(options.loadingText); }
			    	
	    	//load container using ajax
			options.type = "POST";
			
			var indicatorId = options.indicatorId;
			var onSuccessTopics = options.onSuccessTopics;
			
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
				
			var onCompleteTopics = options.onCompleteTopics;
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
			
			var onErrorTopics = options.onErrorTopics;
			options.error = function (XMLHttpRequest, textStatus, errorThrown) {
				
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
			
		    //serialize forms
			var formIds = options.formIds;
			if(formIds) {
				
				var formData;
						
				var forms = formIds.split(',');  
				for ( var i = 0; i < forms.length; i++) {
					formData = (formData ? "&" : "") + $("#" + forms[i]).serialize();
				}
				
				$.extend(options,{data: formData});
			}    		
					
			$.ajax(options);
		}
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
	 * Action Tag logic
	 */

	//Register handler to execute action with no target
	$.subscribeHandler('_struts2_jquery_action', function(event, data) {

		var action = $(event.target);
		var options = data;

		//Show indicator element (if any)
		if(options) {
				
			if(options.indicatorId) { $('#' + options.indicatorId).show(); }
				    	
	    	//execute request using ajax
			options.type = "POST";
			
			var indicatorId = options.indicatorId;
			var onSuccessTopics = options.onSuccessTopics;
			
			options.success = function (data, textStatus) {
								
				if(indicatorId) { $('#' + indicatorId).hide(); }
										
				if(onSuccessTopics) {			  
					var topics = onSuccessTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						action.publish(topics[i], action);
					}
				}
			}
				
			var onCompleteTopics = options.onCompleteTopics;
			options.complete = function (xhr, textStatus, errorThrown) {
	
				if(indicatorId) { $('#' + indicatorId).hide(); }
								
				if(onCompleteTopics) {			  
					var topics = onCompleteTopics.split(',');
					for ( var i = 0; i < topics.length; i++) {
						action.publish(topics[i], action);
					}
				}
			}
			
			var onErrorTopics = options.onErrorTopics;
			options.error = function (XMLHttpRequest, textStatus, errorThrown) {
				
				if(onErrorTopics) {			
					var topics = onErrorTopics.split(',');  
					for ( var i = 0; i < topics.length; i++) {
						action.publish(topics[i], action);
					}
				}
			}
			
		    //serialize forms
			var formIds = options.formIds;
			if(formIds) {
				
				var formData;
						
				var forms = formIds.split(',');  
				for ( var i = 0; i < forms.length; i++) {
					formData = (formData ? "&" : "") + $("#" + forms[i]).serialize();
				}
				
				$.extend(options,{data: formData});
			}    
					
			$.ajax(options);
		}
	});
		
})(jQuery);
