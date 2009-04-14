package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.simple.AbstractTagHandler;


public class ActionHandler extends AbstractTagHandler {

    @SuppressWarnings("unchecked")
	@Override
    public void start(String name, Attributes a) throws IOException {
        
    	Map params = context.getParameters();
        a.addIfExists("targets", params.get("targets"));
        a.addIfExists("href", params.get("href"));
        a.addIfExists("formIds", params.get("formIds"));
        a.addIfExists("validate", params.get("validate"));
        a.addIfExists("onClickTopics", params.get("onClickTopics"));
        a.addIfExists("indicatorId", params.get("indicatorId"));
        a.addIfExists("loadingText", params.get("loadingText"));
        a.addIfExists("onCompleteTopics", params.get("onCompleteTopics"));
        a.addIfExists("onSuccessTopics", params.get("onSuccessTopics"));
        a.addIfExists("onErrorTopics", params.get("onErrorTopics"));
        a.addIfExists("elementIds", params.get("elementIds"));
                
        super.start(name, a);
    }
}
