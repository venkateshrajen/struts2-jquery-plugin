(function($){
	
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
}