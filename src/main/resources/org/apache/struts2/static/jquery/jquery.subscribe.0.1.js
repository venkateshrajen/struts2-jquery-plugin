/*
 * Implementation of publish/subcription framework for jQuery
 * Required use of jQuery. Tested with jQuery 1.3 and above
 *
 *
 * Copyright (c) 2008 Eric Chijioke (obinna a-t g mail dot c o m)
 *
 * 
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 */

(function($){
	
	_subscribe_topics = {};
	_subscribe_handlers = {}; 
	
	$.fn.extend({
		
		/**
		 * Creates a new topic without any subscribers. 
		 * Not usually used explicitly 
		 */
		createTopic :  function(topic) {	
		
			if(!_subscribe_topics[topic]) {
				
				_subscribe_topics[topic] = {};
				_subscribe_topics[topic].objects = {};
				_subscribe_topics[topic].objects['__noId__'] = [];
			}
			
			return this;
		},
		
		/**
		 * Destroy an existing topic and unsubscribes all subscribers
		 */
		destroyTopic  :	 function(topic) {	
		
			if(!_subscribe_topics[topic]) {
				
				for(i in _subscribe_topics[topic].objects) {
					
					var object = _subscribe_topics[topic].objects[i];
					
					if($.isArray(object)) {		// handle '__noId__' elements
	
						for(j in object) {
							
							object[j].unbind(topic);
						}
						
					} else {
						
						object.unbind(topic,data);
					}
				}
			}
			
			return this;
		},
		
		/**
		 * Subscribes an object to particular topic with a handler.
		 * When the topic is published, this elements handler will be executed.
		 * 
		 * Parameters:
		 *  -topics- is the string name of the topic
		 *  -handler- is a handler function and is of the form function(event, data), in which the 'this' refers to the element itself.
		 *  		  handler can be a function or can be a string referring to a function previously registered using the $.subscribeHandler() function
		 *  -data- is additional data that is passed to the event handler as event.data when the topic is published
		 */
		subscribe :  function(topic, handler, data) {	
				
			this.createTopic(topic);
			
			if(this.attr('id')) {
				
				_subscribe_topics[topic].objects[this.attr('id')] = this;
				
			} else {
				
				_subscribe_topics[topic].objects['__noId__'].push(this);
			}
			
			if(typeof(handler) == 'function') {
			
				this.bind(topic, data, handler);
			
			} else if(typeof(handler) == 'string' && typeof(_subscribe_handlers[handler]) == 'function') {
				
				this.bind(topic, data, _subscribe_handlers[handler]);
			}
			
			return this;
		},
		
		/**
		 * Remove a subscription of an element to a topic. The element must have an id
		 */
		unsubscribe :  function(topic) {	
				
			if(topic && this.attr('id')) {
				
				if(_subscribe_topics[topic]) {
				
					var object = _subscribe_topics[topic].objects[this.attr('id')];
					
					if(object) {
						this.unbind(topic, handler);
						delete _subscribe_topics[topic].objects[this.attr('id')];
					}
				}
			}
			
			return this;
		},
		
		/**
		 * Publishes a topic (triggers handlers on all topic subscribers)
		 * This ends up calling any subscribed handlers which are functions of the form function (event, data)
		 * where:
		 * 		-event- is a standard jQuery event object
		 *  	-event.data- is the data parameter passed to the subscribe() function when this published topic was subscribed to
		 *  	-data- is the data parameter that was passed to this publish() method
		 */
		publish : function(topic, data) {	
		
			this.createTopic(topic);
				
			for(i in _subscribe_topics[topic].objects) {
				
				var object = _subscribe_topics[topic].objects[i];
				
				if($.isArray(object)) {		// handle '__noId__' elements (if any)

					if(object.length > 0) {
					
						for(j in object) {
							
							object[j].trigger(topic,data);
						}
					}
					
				} else {
					
					object.trigger(topic,data);
				}
			}
			
			return this;
		},
		
		/**
		 * Binds an objects event handler to a publish call
		 * 
		 * Upon the event trigerring, this ends up calling any subscribed handlers which are functions of the form function (event, data)
		 * where:
		 * 		-event- is a standard jQuery event object
		 *  	-event.data- is the data parameter passed to the subscribe() function when this published topic was subscribed to
		 *  	-data- is the data parameter that was passed to this publishOnEvent() method
		 */
		publishOnEvent : function(event, topic, data) {	
		
			this.createTopic(topic);
			
			this.bind(event, data, function (e) {
				
				$(this).publish(topic, e.data);
			});
			
			return this;
		}
	});
	
	/**
	 * Make publish(), createTopic() and destroyTopic() callable without an element context
	 * Usually don't need a context to publish, create or destroy a topic. We will call from the document context
	 */
	$.extend({
		
		subscribeHandler: function(name, handler) { 
			
			if(name && handler && typeof(handler) == "function") {
				
				_subscribe_handlers[name] = handler;
			}
			
			return $();
		},
		
		publish: function(topic, data) { 
			
			return $().publish(topic,data);
		},
		
		createTopic: function(topic) { 
			
			return $().createTopic(topic);
		},
		
		destroyTopic: function(topic) { 
			
			return $().destroyTopic(topic);
		}
		
	});
					
})(jQuery);
