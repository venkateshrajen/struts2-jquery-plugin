package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


public abstract class ActionTagSerializer extends InteractiveTagSerializer {

    @SuppressWarnings("unchecked")
	public void end(String name) throws IOException {

		super.end(name);
	    
    	Map params = context.getParameters();
    	jqueryAttributes.addIfExists("targets", params.get("targets"));
    	jqueryAttributes.addIfExists("href", params.get("href"));
    	jqueryAttributes.addIfExists("formIds", params.get("formIds"));
    	jqueryAttributes.addIfExists("validate", params.get("validate"));
    	jqueryAttributes.addIfExists("onClickTopics", params.get("onClickTopics"));
    	
    	String id = jqueryAttributes.get("id");
	    
	    if(id != null) {
			
	    	writer.write("\n<script type='text/javascript'>");

	    	writer.write("\n\t var action = $('#" + id + "');");
	    				
    		//subscribe all targets to this action's custom execute topic
	    	String actionTopic = "__topic_jquery_action_" + id;
	    	for (Iterator<String> targets = getUniqueStrings(jqueryAttributes.get("targets")).iterator(); targets.hasNext();) {
	    		
	    		writer.write("\n\t $('#" + targets.next() + "').subscribe('" + actionTopic + "','_strut2_jquery_load');");	
			}

	    	//register onClick topics to publish on click
	    	for (Iterator<String> topics = getUniqueStrings(jqueryAttributes.get("onClickTopics")).iterator(); topics.hasNext();) {
	    		
	    		writer.write("\n\t action.publishOnEvent('click','" + topics.next() + "');");	
			}
	    		    	
	    	//bind custom action topic to click event and process
	    	String formId = jqueryAttributes.get("formId");
	    	writer.write("\n\t var data = {url : '" + jqueryAttributes.get("href") + "'};");
	    	if(formId != null) { writer.write("\n\t $.extend(data,$('#" + formId + "').serialize());"); }
	    	if(jqueryAttributes.get("validate") != null) { writer.write("\n\t $.extend(data,{struts.enableJSONValidation:'true'});"); }
	    	
	    	writer.write("\n\t action.publishOnEvent('click', '" + actionTopic + "',data);");
       
	    	writer.write("\n </script>");
	    }
    }
}
