package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


public abstract class ContainerTagSerializer extends BaseTagSerializer {
    
    @SuppressWarnings("unchecked")
	public void end(String name) throws IOException {

		super.end(name);
		
    	Map params = context.getParameters();
    	jqueryAttributes.addIfExists("reloadTopics", params.get("reloadTopics"));
    	jqueryAttributes.addIfExists("onAlwaysTopics", params.get("onAlwaysTopics"));
    	jqueryAttributes.addIfExists("onBeforeTopics", params.get("onBeforeTopics"));
    	jqueryAttributes.addIfExists("onCompleteTopics", params.get("onCompleteTopics"));
    	jqueryAttributes.addIfExists("onErrorTopics", params.get("onErrorTopics"));
    	jqueryAttributes.addIfExists("onSuccessTopics", params.get("onSuccessTopics"));
    	jqueryAttributes.addIfExists("src", params.get("src"));
    	jqueryAttributes.addIfExists("loadingText", params.get("loadingText"));
    	jqueryAttributes.addIfExists("errorText", params.get("errorText"));
    	jqueryAttributes.addIfExists("indicatorId", params.get("indicatorId"));

	    String id = jqueryAttributes.get("id");
	    
	    if(id != null) {
			
	    	writer.write("\n<script type='text/javascript'>");
		    
		    String src = jqueryAttributes.get("src");
		    
        	writer.write("\n$(function(){");

			writer.write("\n\t var container = $('#" + id + "');");
		
			writer.write("\n\t var data = {};");

			if(src != null) {writer.write("\n\t data.url='" + src + "';"); }
    		if(jqueryAttributes.containsKey("indicatorId")) { writer.write("\n\t data.indicatorId='" + jqueryAttributes.get("indicatorId") + "';"); }
    		if(jqueryAttributes.containsKey("onAlwaysTopics")) { writer.write("\n\t data.onAlwaysTopics='" + jqueryAttributes.get("onAlwaysTopics") + "';"); }
    		if(jqueryAttributes.containsKey("onBeforeTopics")) { writer.write("\n\t data.onBeforeTopics='" + jqueryAttributes.get("onBeforeTopics") + "';"); }
    		if(jqueryAttributes.containsKey("loadingText")) { writer.write("\n\t data.loadingText='" + jqueryAttributes.get("loadingText") + "';"); }
    		if(jqueryAttributes.containsKey("onSuccessTopics")) { writer.write("\n\t data.onSuccessTopics='" + jqueryAttributes.get("onSuccessTopics") + "';"); }
    		if(jqueryAttributes.containsKey("onErrorTopics")) { writer.write("\n\t data.onErrorTopics='" + jqueryAttributes.get("onErrorTopics") + "';"); }
    		if(jqueryAttributes.containsKey("onCompleteTopics")) { writer.write("\n\t data.onCompleteTopics='" + jqueryAttributes.get("onCompleteTopics") + "';"); }

        	//store data for loads
        	writer.write("\n\t\t container.data('load',data);");
        	
        	//subscribe to reload topics
        	for (Iterator<String> topics = getUniqueStrings(jqueryAttributes.get("reloadTopics")).iterator(); topics.hasNext();) {
            	writer.write("\n\t\t container.subscribe('" + topics.next() + "','_struts2_jquery_load');");
        	}	

	        //execute load logic
	        if (src != null) { 
	        	writer.write("\n\t\t container.bind('_struts2_jquery_load', null, _subscribe_handlers['_struts2_jquery_load']);");
	        	writer.write("\n\t\t container.trigger('_struts2_jquery_load');");
	        }
        			        	
	        writer.write("\n});");
	      
	       
	        writer.write("\n</script>");
	    }
    }
}
