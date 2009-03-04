package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


public abstract class InteractiveTagSerializer extends BaseTagSerializer {

    @SuppressWarnings("unchecked")
	public void end(String name) throws IOException {

		super.end(name);
		
    	Map params = context.getParameters();
    	jqueryAttributes.addIfExists("enableTopics", params.get("enableTopics"));
    	jqueryAttributes.addIfExists("disableTopics", params.get("disableTopics"));
    	String id = jqueryAttributes.get("id");
	    
	    if(id != null) {
			
	    	writer.write("\n<script type='text/javascript'>");
		    
	    	//subscribe the hide handler to all hide topics 
        	for (Iterator<String> topics = getUniqueStrings(jqueryAttributes.get("enableTopics")).iterator(); topics.hasNext();) {
        		
        		writer.write("\n\t$('#" + id + "').subscribe('" + topics.next() + "','_strut2_jquery_enable');");			
			}

        	//subscribe the show handler to all show topics 
        	for (Iterator<String> topics = getUniqueStrings(jqueryAttributes.get("disableTopics")).iterator(); topics.hasNext();) {
        		
        		writer.write("\n\t$('#" + id + "').subscribe('" + topics.next() + "','_strut2_jquery_disable');");			
			}
        		       
	        writer.write("\n</script>");
	    }
    }
}
