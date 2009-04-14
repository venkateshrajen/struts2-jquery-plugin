package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.simple.AbstractTagHandler;


public class InteractiveHandler extends AbstractTagHandler {

    /* (non-Javadoc)
     * @see org.apache.struts2.views.java.simple.AbstractTagHandler#start(java.lang.String, org.apache.struts2.views.java.Attributes)
     */
    @SuppressWarnings("unchecked")
	@Override
    public void start(String name, Attributes a) throws IOException {
        
    	Map params = context.getParameters();
        a.addIfExists("enableTopics", params.get("enableTopics"));
        a.addIfExists("disableTopics", params.get("disableTopics"));
    	
        super.start(name, a);
    }
}
