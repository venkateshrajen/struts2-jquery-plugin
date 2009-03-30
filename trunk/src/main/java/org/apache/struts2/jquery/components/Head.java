package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="head", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.HeadTag", 
		description="Enables the jquery ajax tags by injecting required dependencies into page",
		allowDynamicAttributes=true)
public class Head extends org.apache.struts2.components.Head {
	
    public static final String TEMPLATE = "head";
    
    private String compressed;			//Use compressed version of jquery  javascript files
    private String baseRelativePath;	//Context relative path of jquery distribution folder
    private String locale;				//Default locale to be used by jQuery, locale name must be specified as in RFC3066
    
    public Head(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();
        
        if (this.compressed != null)
            addParameter("compressed", findValue(this.compressed, Boolean.class));
        if (this.baseRelativePath != null)
            addParameter("baseRelativePath", findString(this.baseRelativePath));
        if (this.locale != null)
            addParameter("locale", findString(this.locale));
    }

    @Override
    public void setTheme(String theme) {
        super.setTheme(theme);
    }
    
    @Override
    public String getTheme() {
        return "jquery";
    }

    @StrutsTagAttribute(name="locale", description="Default locale to be used by jQuery, locale name must be specified as in RFC3066")
    public void setLocale(String locale) {
        this.locale = locale;
    }
    
    @StrutsTagAttribute(name="compressed", description="Use compressed version of jquery  javascript files", defaultValue="true", type="Boolean")
    public void setCompressed(String compressed) {
        this.compressed = compressed;
    }

    @StrutsTagAttribute(name="baseRelativePath", description="Context relative path of jquery distribution folder", defaultValue="/struts/jquery")
    public void setBaseRelativePath(String baseRelativePath) {
        this.baseRelativePath = baseRelativePath;
    }
}
