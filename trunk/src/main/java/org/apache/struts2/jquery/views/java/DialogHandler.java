package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.TagGenerator;
import org.apache.struts2.views.java.simple.AbstractTagHandler;

public class DialogHandler extends AbstractTagHandler implements TagGenerator {
	
	@SuppressWarnings("unchecked")
	public void generate() throws IOException {
        
        Attributes attrs = new Attributes();
    	Map<String, Object> params = context.getParameters();
        attrs.addIfExists("buttons", params.get("buttons"));
        attrs.addIfExists("buttonTopics", params.get("buttonTopics"));
        attrs.addIfExists("title", params.get("title"));
        attrs.addIfExists("modal", params.get("modal"));
        attrs.addIfExists("draggable", params.get("draggable"));
        attrs.addIfExists("resizable", params.get("resizable"));
        attrs.addIfExists("height", params.get("height"));
        attrs.addIfExists("width", params.get("width"));
        attrs.addIfExists("position", params.get("position"));
        attrs.addIfExists("data", params.get("data"));
        attrs.add("class", "_struts2_jquery_class_dialog");
        attrs.add("style", "display: none");

        super.start("div", attrs);
    }

    public static class CloseHandler extends AbstractTagHandler implements TagGenerator {
        public void generate() throws IOException {
            end("div");
        }
    }
}
