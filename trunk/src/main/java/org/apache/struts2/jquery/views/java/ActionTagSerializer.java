package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


public abstract class ActionTagSerializer extends InteractiveTagSerializer {

	private static final String TAB_PANE_TARGET = "#tab";
	
    @SuppressWarnings("unchecked")
	public void end(String name) throws IOException {

		super.end(name);
	    
    	Map params = context.getParameters();
    	jqueryAttributes.addIfExists("targets", params.get("targets"));
    	jqueryAttributes.addIfExists("href", params.get("href"));
    	jqueryAttributes.addIfExists("formIds", params.get("formIds"));
    	jqueryAttributes.addIfExists("validate", params.get("validate"));
    	jqueryAttributes.addIfExists("onClickTopics", params.get("onClickTopics"));
    	jqueryAttributes.addIfExists("indicatorId", params.get("indicatorId"));
    	jqueryAttributes.addIfExists("loadingText", params.get("loadingText"));
    	jqueryAttributes.addIfExists("onCompleteTopics", params.get("onCompleteTopics"));
    	jqueryAttributes.addIfExists("onSuccessTopics", params.get("onSuccessTopics"));
    	jqueryAttributes.addIfExists("onErrorTopics", params.get("onErrorTopics"));
    	
    	String id = jqueryAttributes.get("id");
	    
	    if(id != null) {
			
	    	writer.write("\n<script type='text/javascript'>");

	    	writer.write("\n\t var action = $('#" + id + "');");
	    	writer.write("\n\t if(action.attr('href')) { action.attr('href','#'); }");
	    	//writer.write("\n\t if(action.attr('href')) { action.removeAttr('href'); action.css('cursor','pointer'); }");
	    		
	    	//register onClick topics to publish on click
	    	for (Iterator<String> topics = getUniqueStrings(jqueryAttributes.get("onClickTopics")).iterator(); topics.hasNext();) {
	    		
	    		writer.write("\n\t action.publishOnEvent('click','" + topics.next() + "');");	
			}

	    	String actionTopic = "__topic_jquery_action_" + id;

    		//subscribe all targets to this action's custom execute topic
	    	Collection<String> targets = getUniqueStrings(jqueryAttributes.get("targets"));
	    	
	    	String formId = jqueryAttributes.get("formId");
	    	String href = jqueryAttributes.get("href");
	    	
	    	if(!targets.isEmpty()) {
	    	
	    		//target subscription need to be done after document load in case element existis in the dom AFTER the current action object 
		    	writer.write("\n\t $(function() {");
		    	
			    	for (Iterator<String> targetIter = targets.iterator(); targetIter.hasNext();) {
			    		
			    		String target = targetIter.next();
			    		
			    		//add integration with tabbedPane framework to execute action within tab
			    		if(ActionTagSerializer.TAB_PANE_TARGET.equals(target)) {
			    			writer.write("\n\t\t action.closest('.ui-tabs-panel').subscribe('" + actionTopic + "','_struts2_jquery_load');");
			    		} else {
			    			writer.write("\n\t\t $('#" + target + "').subscribe('" + actionTopic + "','_struts2_jquery_load');");
			    		}
					}
		    	
		    	writer.write("\n\t });");
	    	
	    	} else { //if no targets, then the action can still execute ajax request and will handle itself (no loading result into container)
	    		
	    		//bind custom action topic to click event and process
		    	if(href != null || jqueryAttributes.get("onCompleteTopics") != null || jqueryAttributes.get("onSuccessTopics") != null || jqueryAttributes.get("onErrorTopics") != null ) {

		    		writer.write("\n\t\t action.subscribe('" + actionTopic + "','_struts2_jquery_action');");
		    	}
	    	}
	    	
	    	writer.write("\n\t var data = {};");
	    	if(href != null) { 

	    		writer.write("\n\t data.url = '" + jqueryAttributes.get("href") + "';");
	    	}
	    	    
	    	if(formId != null) { writer.write("\n\t $.extend(data,$('#" + formId + "').serialize());"); }

	    	if(jqueryAttributes.get("formIds") != null) { writer.write("\n\t $.extend(data,{formIds:'" + jqueryAttributes.get("formIds") + "'});"); }
	    	if(jqueryAttributes.get("validate") != null) { writer.write("\n\t $.extend(data,{struts.enableJSONValidation:'true'});"); }
	    	if(jqueryAttributes.get("indicatorId") != null) { writer.write("\n\t $.extend(data,{indicatorId:'" + jqueryAttributes.get("indicatorId") + "'});"); }
	    	if(jqueryAttributes.get("loadingText") != null) { writer.write("\n\t $.extend(data,{loadingText:'" + jqueryAttributes.get("loadingText") + "'});"); }
	    	if(jqueryAttributes.get("onCompleteTopics") != null) { writer.write("\n\t $.extend(data,{onCompleteTopics:'" + jqueryAttributes.get("onCompleteTopics") + "'});"); }
	    	if(jqueryAttributes.get("onSuccessTopics") != null) { writer.write("\n\t $.extend(data,{onSuccessTopics:'" + jqueryAttributes.get("onSuccessTopics") + "'});"); }
	    	if(jqueryAttributes.get("onErrorTopics") != null) { writer.write("\n\t $.extend(data,{onErrorTopics:'" + jqueryAttributes.get("onErrorTopics") + "'});"); }
	    	writer.write("\n\t action.publishOnEvent('click', '" + actionTopic + "',data);");
       
	    	writer.write("\n </script>");
	    }
    }
}
