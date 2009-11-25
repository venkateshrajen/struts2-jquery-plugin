package org.apache.struts2.jquery.components;

import java.util.ArrayList;
import java.util.List;

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
    private String uiTheme;				//The jquery ui theme to use or root-relative path to custom theme css
    private String version;				//A version suffix to append at the end of the included scrtip and css files to ensure refreshed browser caches
    
    private static final List<String> uiThemes;
    
    static {
    	
    	uiThemes = new ArrayList<String>(26);
    			
			uiThemes.add("base");
			uiThemes.add("black-tie");
			uiThemes.add("blitzer");
			uiThemes.add("cupertino");
			uiThemes.add("dark-hive");
			uiThemes.add("dot-luv");
			uiThemes.add("eggplant");
			uiThemes.add("excite-bike");
			uiThemes.add("flick");
			uiThemes.add("hot-sneaks");
			uiThemes.add("humanity");
			uiThemes.add("le-frog");
			uiThemes.add("mint-choc");
			uiThemes.add("overcast");
			uiThemes.add("pepper-grinder");
			uiThemes.add("redmond");
			uiThemes.add("smoothness");
			uiThemes.add("south-street");
			uiThemes.add("start");
			uiThemes.add("sunny");
			uiThemes.add("swanky-purse");
			uiThemes.add("trontastic");
			uiThemes.add("ui-darkness");
			uiThemes.add("ui-lightness");
			uiThemes.add("vader");
    }
    
    public static final List<String> getUiThemes(){
    	
    	return uiThemes;
    }
    
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
        if (this.uiTheme != null)
            addParameter("uiTheme", findString(this.uiTheme));
        if (this.baseRelativePath != null)
            addParameter("baseRelativePath", findString(this.baseRelativePath));
        if (this.locale != null)
            addParameter("locale", findString(this.locale));
        if (this.version != null)
            addParameter("version", findString(this.version));
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

    @StrutsTagAttribute(name="uiTheme", description="Name of provided ui theme or webapp-root-relative path to custom theme css", defaultValue="base")
    public void setUiTheme(String uiTheme) {
        this.uiTheme = uiTheme;
    }

    @StrutsTagAttribute(name="baseRelativePath", description="Context relative path of jquery distribution folder", defaultValue="/struts/jquery")
    public void setBaseRelativePath(String baseRelativePath) {
        this.baseRelativePath = baseRelativePath;
    }

    @StrutsTagAttribute(name="version", description="A version suffix to append at the end of the included scrtip and css files to ensure refreshed browser caches")
    public void setVersion(String version) {
		this.version = version;
	}
}
