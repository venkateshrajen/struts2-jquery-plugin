package org.apache.struts2.jquery.views.java;

import java.io.IOException;

import org.apache.struts2.views.java.TagGenerator;

public class DivHandler extends org.apache.struts2.views.java.simple.DivHandler implements TagGenerator {
	
	public void generate() throws IOException {
        
    	//Map<String, Object> params = context.getParameters();
        //Attributes attrs = new Attributes();
        super.generate();
    }

    public static class CloseHandler extends org.apache.struts2.views.java.simple.DivHandler.CloseHandler implements TagGenerator {
        public void generate() throws IOException {
            super.generate();
        }
    }
}
