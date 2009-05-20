package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.simple.AbstractTagHandler;


public class ContainerHandler extends AbstractTagHandler {

    /* (non-Javadoc)
     * @see org.apache.struts2.views.java.simple.AbstractTagHandler#start(java.lang.String, org.apache.struts2.views.java.Attributes)
     */
    @SuppressWarnings("unchecked")
	@Override
    public void start(String name, Attributes a) throws IOException {
        
    	Map params = context.getParameters();

    	a.addIfExists("indicatorId", params.get("indicatorId"));
        a.addIfExists("onAlwaysTopics", params.get("onAlwaysTopics"));
        a.addIfExists("onBeforeTopics", params.get("onBeforeTopics"));
        a.addIfExists("loadingText", params.get("loadingText"));
        a.addIfExists("onSuccessTopics", params.get("onSuccessTopics"));
        a.addIfExists("onErrorTopics", params.get("onErrorTopics"));
        a.addIfExists("onCompleteTopics", params.get("onCompleteTopics"));
        a.addIfExists("errorText", params.get("errorText"));
        a.addIfExists("src", params.get("src"));
        a.addIfExists("reloadTopics", params.get("reloadTopics"));
        a.addIfExists("formIds", params.get("formIds"));
        a.addIfExists("elementIds", params.get("elementIds"));
    	
        super.start(name, a);
    }
}
