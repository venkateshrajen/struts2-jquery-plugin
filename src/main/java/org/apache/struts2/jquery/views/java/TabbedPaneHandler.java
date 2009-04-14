package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.TagGenerator;
import org.apache.struts2.views.java.simple.AbstractTagHandler;

public class TabbedPaneHandler extends AbstractTagHandler implements TagGenerator {
	
	@SuppressWarnings("unchecked")
	public void generate() throws IOException {
        
        Attributes attrs = new Attributes();
    	Map<String, Object> params = context.getParameters();
        attrs.addIfExists("isCache", params.get("isCache"));
        attrs.addIfExists("selected", params.get("selected"));
        attrs.add("class", "_struts2_jquery_class_tabbedpane");

        super.start("div", attrs);
    }

    public static class CloseHandler extends AbstractTagHandler implements TagGenerator {
        public void generate() throws IOException {
            end("div");
        }
    }
}
