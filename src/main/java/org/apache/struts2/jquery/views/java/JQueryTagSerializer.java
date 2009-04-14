package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.io.Writer;

import org.apache.struts2.components.template.TemplateRenderingContext;
import org.apache.struts2.views.java.XHTMLTagSerializer;


public class JQueryTagSerializer extends XHTMLTagSerializer {  
    
    protected TemplateRenderingContext context;
    
    protected Writer writer;

    public void setup(TemplateRenderingContext context) {
    	super.setup(context);
        this.context = context;
        writer = context.getWriter();
    }
    
	public void end(String name) throws IOException {
    	
        super.end(name);
        
        writeBinding();
    }
	
	protected void writeBinding() throws IOException {
        
    	String id =  context.getParameters().get("id").toString();
    	
    	writer.write("\n<script type='text/javascript'>");
    		writer.write("\n\t_struts2_jquery.bind($('#" + id + "'));");
        writer.write("\n</script>");
	}
}
