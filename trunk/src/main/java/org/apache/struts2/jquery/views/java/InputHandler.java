package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.simple.AbstractTagHandler;


public class InputHandler extends AbstractTagHandler {

    @SuppressWarnings("unchecked")
	@Override
    public void start(String name, Attributes a) throws IOException {
        
    	Map params = context.getParameters();
        a.addIfExists("src", params.get("src"));
        a.addIfExists("elementIds", params.get("elementIds"));
        a.addIfExists("formIds", params.get("formIds"));
        a.addIfExists("reloadTopics", params.get("reloadTopics"));
        a.addIfExists("focusTopics", params.get("focusTopics"));
        a.addIfExists("blurTopics", params.get("blurTopics"));
        a.addIfExists("onChangeTopics", params.get("onChangeTopics"));
        a.addIfExists("onFocusTopics", params.get("onFocusTopics"));
        a.addIfExists("onBlurTopics", params.get("onmouseover"));
                
        super.start(name, a);
    }
}
