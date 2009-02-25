package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.struts2.components.template.TemplateRenderingContext;
import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.XHTMLTagSerializer;


public abstract class AbstractJQueryTagSerializer extends XHTMLTagSerializer {

    protected Attributes jqueryAttributes;
    
    protected TemplateRenderingContext context;
    
    protected Writer writer ;

    public void setup(TemplateRenderingContext context) {
    	super.setup(context);
        this.context = context;
        writer = context.getWriter();
    }
    
    @SuppressWarnings("unchecked")
	public void end(String name) throws IOException {
    	
    	if(name == null) { throw new IllegalArgumentException("Null tag name."); }
    	
    	Map params = context.getParameters();
    	jqueryAttributes = new Attributes();
    	
    	jqueryAttributes.addIfExists("id", params.get("id"));
        	
    	jqueryAttributes.addIfExists("alwaysPublishTopics", params.get("alwaysPublishTopics"));
    	jqueryAttributes.addIfExists("beforePublishTopics", params.get("beforePublishTopics"));
    	jqueryAttributes.addIfExists("afterPublishTopics", params.get("afterPublishTopics"));
    	jqueryAttributes.addIfExists("errorPublishTopics", params.get("errorPublishTopics"));
    	jqueryAttributes.addIfExists("formId", params.get("formId"));
    	jqueryAttributes.addIfExists("indicatorId", params.get("indicatorId"));

        super.end(name);
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
