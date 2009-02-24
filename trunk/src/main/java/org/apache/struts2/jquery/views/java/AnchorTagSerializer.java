package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class AnchorTagSerializer extends AbstractJQueryTagSerializer {

	@SuppressWarnings("unchecked")
	public void end(String name) throws IOException {

		super.end(name);
		
    	Map params = context.getParameters();
    	jqueryAttributes.addIfExists("href", params.get("href"));
    	jqueryAttributes.addIfExists("targets", params.get("targets"));
    	jqueryAttributes.addIfExists("formId", params.get("formId"));

	    String id = jqueryAttributes.get("id");
	    String href = jqueryAttributes.get("href");
	    
        //execute load logic
        if (id != null && href != null) { 

	    	writer.write("\n<script>");

	    	String reloadTopic = "__topic_jquery_reload_" + id;
	    	
	    	writer.write("\n\t$('#" + id + "').createTopic('" + reloadTopic + "');");	
			
    		//subscribe all targets to this link's reload topic
	    	for (Iterator<String> targets = getUniqueStrings(jqueryAttributes.get("targets")).iterator(); targets.hasNext();) {
	    		
	    		writer.write("\n\t$('#" + targets.next() + "').subscribe('" + reloadTopic + "','reload'");	
			}
	    	
	    	//bind reload topic to click event
	    	String formId = jqueryAttributes.get("formId");
	    	
	    	writer.write("\n\t$('#" + id + ").publishOnEvent('click', '" + reloadTopic + "'"  + ( formId != null ? "$('#" + formId + "').serialize()" : "") + ");");

	    	//add aditional on=click handlers
	    	writer.write("\n\t$('#" + id + ").bind('click', function(event){");
	    	
	    		
	    	writer.write("\n\t});");
	    	
       
	    	writer.write("\n</script>");
        }
    }	
}
