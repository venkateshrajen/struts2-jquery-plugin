package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Head;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * @see Head
 */
public class HeadTag extends AbstractUITag {

    private static final long serialVersionUID = 6876765769175246030L;

    private String compressed;
    private String baseRelativePath;
    private String locale;
    private String uiTheme;
    private String version;
    
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Head(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();
        
        Head head = (Head) component;
        head.setCompressed(compressed);
        head.setBaseRelativePath(baseRelativePath);
        head.setLocale(locale);
        head.setUiTheme(uiTheme);
        head.setVersion(version);
    }

    public void setBaseRelativePath(String baseRelativePath) {
        this.baseRelativePath = baseRelativePath;
    }

    public void setCompressed(String compressed) {
        this.compressed = compressed;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

	public void setUiTheme(String uiTheme) {
		this.uiTheme = uiTheme;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
}
