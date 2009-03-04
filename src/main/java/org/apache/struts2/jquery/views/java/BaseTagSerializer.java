package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.struts2.components.template.TemplateRenderingContext;
import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.XHTMLTagSerializer;


public abstract class BaseTagSerializer extends XHTMLTagSerializer {
	
    protected Attributes jqueryAttributes;
    
    protected TemplateRenderingContext context;
    
    protected Writer writer;

    public void setup(TemplateRenderingContext context) {
    	super.setup(context);
        this.context = context;
        writer = context.getWriter();
    }
    
    @SuppressWarnings("unchecked")
	public void end(String name) throws IOException {
    	
    	if(name == null) { throw new IllegalArgumentException("Null tag name."); }

        super.end(name);
    	
    	Map params = context.getParameters();
    	jqueryAttributes = new Attributes();
    	
    	jqueryAttributes.addIfExists("id", params.get("id"));
    	jqueryAttributes.addIfExists("hideTopics", params.get("hideTopics"));
    	jqueryAttributes.addIfExists("showTopics", params.get("showTopics"));
    	jqueryAttributes.addIfExists("removeTopics", params.get("removeTopics"));
        
    	String id = jqueryAttributes.get("id");
	    
	    if(id != null) {
			
	    	writer.write("\n<script type='text/javascript'>");
		    
	    	//subscribe the hide handler to all hide topics 
        	for (Iterator<String> topics = getUniqueStrings(jqueryAttributes.get("hideTopics")).iterator(); topics.hasNext();) {
        		
        		writer.write("\n\t$('#" + id + "').subscribe('" + topics.next() + "','_strut2_jquery_hide');");			
			}

        	//subscribe the show handler to all show topics 
        	for (Iterator<String> topics = getUniqueStrings(jqueryAttributes.get("hideTopics")).iterator(); topics.hasNext();) {
        		
        		writer.write("\n\t$('#" + id + "').subscribe('" + topics.next() + "','_strut2_jquery_show');");			
			}

        	//subscribe the remove handler to all remove topics 
        	for (Iterator<String> topics = getUniqueStrings(jqueryAttributes.get("hideTopics")).iterator(); topics.hasNext();) {
        		
        		writer.write("\n\t$('#" + id + "').subscribe('" + topics.next() + "',_strut2_jquery_remove');");			
			}
        		       
	        writer.write("\n</script>");
	    }
    }

	protected Collection<String> getUniqueStrings(String... topicLists){
		Set<String> s = new HashSet<String>();		
		for (String topicList : topicLists) {
			if(topicList != null) {
				StringTokenizer topics = new StringTokenizer(topicList,",");
	        	while(topics.hasMoreTokens()){
	            	s.add(topics.nextToken());
	        	}
			}
        }
		return s;
	}
}
