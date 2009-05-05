package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.template.TemplateRenderingContext;
import org.apache.struts2.views.java.Attributes;
import org.apache.struts2.views.java.TagGenerator;
import org.apache.struts2.views.java.TagHandler;
import org.apache.struts2.views.java.TagSerializer;

public class HeadTagSerializer implements TagGenerator, TagSerializer {

	private final static String  JQUERY_FILE_PREFIX = "jquery-1.3.2";
	private final static String  JQUERY_UI_FILE_PREFIX = "jquery-ui-1.7.1";
	private final static String  JQUERY_SUBSCRIBE_FILE_PREFIX = "jquery.subscribe.1.1";
	private final static String  JQUERY_STRUTS_FILE_PREFIX = "jquery.struts2";

	private final static String  FILE_SUFFIX = ".js";
	private final static String  MIN_FILE_SUFFIX = ".min.js";
	
    protected Writer writer;
    
    protected TemplateRenderingContext context;
    
    public void setup(TemplateRenderingContext context) {
    	this.context = context;
        this.writer = context.getWriter();
    }

    public void characters(String text) throws IOException {}

    public void characters(String text, boolean encode) throws IOException {}

    public void setNext(TagHandler next) {}

    public void end(String name) throws IOException {
        writer.write("</");
        writer.write(name);
        writer.write(">");
    }

    public void generate() throws IOException {
    	
    	 start(null,null);
    }
 
    @SuppressWarnings("unchecked")
	public void start(String name, Attributes attrs) throws IOException {

    	Map params = context.getParameters();
    	
    	String jqueryFile;
    	String jqueryUIFile;
    	String jquerySubscribeFile;
    	String jqueryStrutsFile;

        String basePath = ServletActionContext.getRequest().getContextPath();
        if (basePath == null) { basePath = ""; }
  	  
    	if(params.get("compressed") != null && "false".equals(params.get("compressed").toString())) {
    		
    		jqueryFile = JQUERY_FILE_PREFIX + FILE_SUFFIX; 
        	jqueryUIFile = JQUERY_UI_FILE_PREFIX + FILE_SUFFIX; 
        	jquerySubscribeFile = JQUERY_SUBSCRIBE_FILE_PREFIX + FILE_SUFFIX; 
        	jqueryStrutsFile = JQUERY_STRUTS_FILE_PREFIX + FILE_SUFFIX; 
    	
    	} else {
    		
    		jqueryFile = JQUERY_FILE_PREFIX + MIN_FILE_SUFFIX; 
        	jqueryUIFile = JQUERY_UI_FILE_PREFIX + MIN_FILE_SUFFIX; 
        	jquerySubscribeFile = JQUERY_SUBSCRIBE_FILE_PREFIX + MIN_FILE_SUFFIX; 
        	jqueryStrutsFile = JQUERY_STRUTS_FILE_PREFIX + MIN_FILE_SUFFIX; 
    	}
    	
    	if(params.get("baseRelativePath") != null) {
    		
    		basePath = params.get("baseRelativePath").toString();

        	writer.write("<script language=\"JavaScript\" type=\"text/javascript\" base=\"" + basePath + "\" src=\"" + basePath + "/" + jqueryFile + "\" includeParams=\"none\" encode=\"false\"/></script>");
        	writer.write("<script language=\"JavaScript\" type=\"text/javascript\" base=\"" + basePath + "\" src=\"" + basePath + "/" + jqueryUIFile + "\" includeParams=\"none\" encode=\"false\"/></script>");
        	writer.write("<script language=\"JavaScript\" type=\"text/javascript\" base=\"" + basePath + "\" src=\"" + basePath + "/" + jquerySubscribeFile + "\" includeParams=\"none\" encode=\"false\"/></script>");
        	writer.write("<script language=\"JavaScript\" type=\"text/javascript\" base=\"" + basePath + "\" src=\"" + basePath + "/" + jqueryStrutsFile + "\" includeParams=\"none\" encode=\"false\"/></script>");
    	
    	} else {

        	writer.write("<script language=\"JavaScript\" type=\"text/javascript\" base=\"" + basePath + "\" src=\"" + basePath + "/struts/jquery/" + jqueryFile + "\"/></script>");
        	writer.write("<script language=\"JavaScript\" type=\"text/javascript\" base=\"" + basePath + "\" src=\"" + basePath + "/struts/jquery/" + jqueryUIFile + "\"/></script>");
        	writer.write("<script language=\"JavaScript\" type=\"text/javascript\" base=\"" + basePath + "\" src=\"" + basePath + "/struts/jquery/" + jquerySubscribeFile + "\"/></script>");
        	writer.write("<script language=\"JavaScript\" type=\"text/javascript\" base=\"" + basePath + "\" src=\"" + basePath + "/struts/jquery/" + jqueryStrutsFile + "\"/></script>");
    	}
    	
    	writer.write("<link rel=\"stylesheet\" href=\"" + basePath + "/struts/jquery/theme/ui.all.css\" base=\"" + basePath + "\" type=\"text/css\"/>");
    	writer.flush();
    }
}
