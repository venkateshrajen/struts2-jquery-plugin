package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.TagGenerator;

public class FormHandler extends org.apache.struts2.views.java.simple.FormHandler implements TagGenerator {
	
	public void generate() throws IOException {
        
    	//Map<String, Object> params = context.getParameters();
        //Attributes attrs = new Attributes();
        
        super.generate();
    }
	
    @SuppressWarnings("unchecked")
	@Override
    public void start(String name, Attributes a) throws IOException {
        
    	Map params = context.getParameters();
        a.addIfExists("onSubmitTopics", params.get("onSubmitTopics"));
        a.addIfExists("submitTopics", params.get("submitTopics"));
        a.addIfExists("targetId", params.get("targetId"));
    	
        super.start(name, a);
    }

    public static class CloseHandler extends org.apache.struts2.views.java.simple.FormHandler.CloseHandler implements TagGenerator {
        public void generate() throws IOException {
            super.generate();
        }
    }
}
