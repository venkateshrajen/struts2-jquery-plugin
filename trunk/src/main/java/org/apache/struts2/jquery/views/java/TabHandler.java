package org.apache.struts2.jquery.views.java;

import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.TagGenerator;
import org.apache.struts2.views.java.simple.AbstractTagHandler;

import java.io.IOException;
import java.util.Map;

public class TabHandler extends AbstractTagHandler implements TagGenerator {
	
    @SuppressWarnings("unchecked")
	public void generate() throws IOException {
        Map<String, Object> params = context.getParameters();
        Attributes attrs = new Attributes();

        attrs.addIfExists("name", params.get("name"))
                .addIfExists("id", params.get("id"))
                .addIfExists("class", params.get("cssClass"))
                .addIfExists("style", params.get("cssStyle"))
                .addIfExists("href", params.get("src"), false)
                .addIfExists("title", params.get("title"))
        		.addIfExists("isSelected", params.get("isSelected"))
        		.addIfExists("isDisabled", params.get("isDisabled"));
        
        
        start("a", attrs);
    }

    public static class CloseHandler extends AbstractTagHandler implements TagGenerator {
        public void generate() throws IOException {
            end("a");
        }
    }
}
