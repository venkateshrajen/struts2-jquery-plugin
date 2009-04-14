package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.simple.AbstractTagHandler;


public class BaseHandler extends AbstractTagHandler {  
    
    /* (non-Javadoc)
     * @see org.apache.struts2.views.java.simple.AbstractTagHandler#start(java.lang.String, org.apache.struts2.views.java.Attributes)
     */
    @SuppressWarnings("unchecked")
	@Override
    public void start(String name, Attributes a) throws IOException {
        
    	Map params = context.getParameters();
    	        
        a.addIfExists("id", params.get("id"));
    	String cssClass = a.get("class");
    	cssClass = cssClass == null ? "_struts2_jquery_bound" : cssClass + " _struts2_jquery_bound";
        a.addIfExists("hideTopics", params.get("hideTopics"));
        a.addIfExists("showTopics", params.get("showTopics"));
        a.addIfExists("removeTopics", params.get("removeTopics"));
    	        
        super.start(name, a);
    }
}
