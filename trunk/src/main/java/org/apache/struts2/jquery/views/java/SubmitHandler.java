package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.views.java.TagGenerator;

public class SubmitHandler extends org.apache.struts2.views.java.simple.SubmitHandler implements TagGenerator {
	
	@SuppressWarnings("unchecked")
	public void generate() throws IOException {
        
        //Attributes attrs = new Attributes();
    	Map<String, Object> params = context.getParameters();
    	params.put("type", "button");
        
        super.generate();
    }

    public static class CloseHandler extends org.apache.struts2.views.java.simple.SubmitHandler.CloseHandler implements TagGenerator {
        public void generate() throws IOException {
            super.generate();
        }
    }
}
