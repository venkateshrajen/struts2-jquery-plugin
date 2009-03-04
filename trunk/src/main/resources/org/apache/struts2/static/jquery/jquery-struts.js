(function($){
	
	/**
	 * Base Tag logic
	 */
	
	//Register handler to hide an element
	$.subscribeHandler('_strut2_jquery_hide', function(event, data) {
		
		$(this).hide();
	});

	//Register handler to show an element
	$.subscribeHandler('_strut2_jquery_show', function(event, data) {
		
		$(this).show();
	});

	//Register handler to remove an element
	$.subscribeHandler('_strut2_jquery_remove', function(event, data) {
		
		$(this).remove();
	});
	

	/**
	 * Interactive Tag logic
	 */
	
	//Register handler to hide an element
	$.subscribeHandler('_strut2_jquery_enable', function(event, data) {
		
		$(this).attr("disabled","false");
	});

	//Register handler to show an element
	$.subscribeHandler('_strut2_jquery_disable', function(event, data) {

		$(this).attr("disabled","disabled");
	});
	
	
	/**
	 * Container Tag logic
	 */

	//Register handler to load a tag
	$.subscribeHandler('_strut2_jquery_load', function(event, data) {

		var container = $(event.target);
		var options = event.data;
		
		//Show indicator element (if any)
		if(options.indicatorId) { $('#' + options.indicatorId).show(); }

		var onAlwaysTopics = options.onAlwaysTopics;
		
    	//publish all 'before' and 'always' topics
		if(onAlwaysTopics) {  
			var topics = onAlwaysTopics.split(',');
			for ( var i = 0; i < topics; i++) {
				container.publish(topics[i], data);
			}
		}
		
		if(options.onBeforeTopics) {  
			var topics = onAlwaysTopics.split(',');
			for ( var i = 0; i < topics; i++) {
				container.publish(topics[i], data);
			}
		}
    	
    	//Set pre-loading text (if any)
		if(options.loadingText) { container.html(options.loadingText); }
		    	
    	//load container using ajax
		options.type = "POST";
		
		var onSuccessTopics = options.onSuccessTopics;
		
		options.success = function (data, textStatus) {
			
			container.html(data);
			var topics = onSuccessTopics.split(',');
			if(onSuccessTopics) {			  
				for ( var i = 0; i < topics; i++) {
					container.publish(topics[i]);
				}
			}
			if(onAlwaysTopics) {
				var topics = onAlwaysTopics.split(',');  
				for ( var i = 0; i < topics; i++) {
					container.publish(topics[i]);
				}
			}
		}
			
		var indicatorId = options.indicatorId;
		var onCompleteTopics = options.onCompleteTopics;
		options.complete = function (XMLHttpRequest, textStatus, errorThrown) {

			if(indicatorId) { $('#' + indicatorId).hide(); }
			
			if(onCompleteTopics) {			  
				var topics = onCompleteTopics.split(',');
				for ( var i = 0; i < topics; i++) {
					container.publish(topics[i]);
				}
			}
			if(onAlwaysTopics) {  
				var topics = onAlwaysTopics.split(',');
				for ( var i = 0; i < topics; i++) {
					container.publish(topics[i]);
				}
			}
		}
		
		var onErrorTopics = options.onErrorTopics;
		options.error = function (XMLHttpRequest, textStatus, errorThrown) {
			
			if(onErrorTopics) {			
				var topics = onErrorTopics.split(',');  
				for ( var i = 0; i < topics; i++) {
					container.publish(topics[i]);
				}
			}
			if(onAlwaysTopics) {  
				var topics = onAlwaysTopics.split(',');
				for ( var i = 0; i < topics; i++) {
					container.publish(topics[i]);
				}
			}
		}
				
		$.ajax(options);
	});
		
	/**
	 * Tabbed Pane Tag logic
	 */
	
	//Register handler to reload a tab
	$.subscribeHandler('_strut2_jquery_reloadTab',  function(event, data) {
		$(this).tabs('load', event.data);
	});
	
	//Register handler to select a tab
	$.subscribeHandler('_strut2_jquery_selectTab',  function(event, data) {
		$(this).tabs('select', event.data);
	});
	
	//Register handler to disable a tab
	$.subscribeHandler('_strut2_jquery_disableTab',  function(event, data) {
		$(this).tabs('disable', event.data);
	});
	
	//Register handler to enable a tab
	$.subscribeHandler('_strut2_jquery_enableTab',  function(event, data) {
		$(this).tabs('enable', event.data);
	});
	
	//Register handler to remove a tab
	$.subscribeHandler('_strut2_jquery_removeTab',  function(event, data) {
		$(this).tabs('remove', event.data);
	});
	
	//Register handler to show a tab
	$.subscribeHandler('_strut2_jquery_showTab',  function(event, data) {
		$(this).tabs('show', event.data);
	});
	
	//Register handler to hide a tab
	$.subscribeHandler('_strut2_jquery_hideTab', function(event, data) {
		$(this).tabs('hide', event.data);
	});

	/**
	 * Action Pane Tag logic
	 */
	

	
	//Register functions to execute ajax load on container 
	/*
	$.fn.extend({
						
		ajax : function(options) {
			
			var self = this;
			var success = options['success'];
			
			options['success'] = function (data, textStatus) {
				
				self.html(data);
				
				if(success && typeof(success) == 'function') {
					success(data, textStatus);	 //TODO - test
				}
			}
			
			$.ajax(options);
			
			return this;
		}
	});
	*/
	
})(jQuery);
