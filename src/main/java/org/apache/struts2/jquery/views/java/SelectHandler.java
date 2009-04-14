package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.TagGenerator;
import org.apache.struts2.views.java.simple.AbstractTagHandler;

public class SelectHandler extends org.apache.struts2.views.java.simple.SelectHandler implements TagGenerator {
	
	public void generate() throws IOException {
        
    	//Map<String, Object> params = context.getParameters();
        //Attributes attrs = new Attributes();
        
        super.generate();
    }
	
    @SuppressWarnings("unchecked")
	@Override
    public void start(String name, Attributes a) throws IOException {
        
    	Map params = context.getParameters();
        a.addIfExists("emptyOption", params.get("emptyOption"));
    	
        super.start(name, a);
    }

    public static class CloseHandler extends AbstractTagHandler implements TagGenerator {
        public void generate() throws IOException {
            end("select");
        }
    }
}
