package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Base;
import org.apache.struts2.views.jsp.ui.AbstractClosingTag;

import com.opensymphony.xwork2.util.ValueStack;

public abstract class AbstractBaseTag extends AbstractClosingTag implements Base {

	private static final long serialVersionUID = -2470372343852770832L;

	protected String hideTopics;
	protected String showTopics;
	protected String removeTopics;

    protected void populateParams() {
    	        
    	super.populateParams();
    	
    	Base jQuery = (Base) component;
    	jQuery.setHideTopics(hideTopics);
        jQuery.setShowTopics(showTopics);
        jQuery.setRemoveTopics(removeTopics);
    }
    
    @Override
    public int doEndTag() throws JspException {
    	int result = super.doEndTag();
    	
    	setDisabled(null); //Seems necessary to prevent caching of attribute state by pooled/cached tags. weird? 
    	
    	return result;
    }
    
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return component;
	}
	
	protected void setComponent(Component component){
		this.component = component;
	}

	public void setHideTopics(String hideTopics) {
		this.hideTopics = hideTopics;
	}

	public void setShowTopics(String showTopics) {
		this.showTopics = showTopics;
	}

	public void setRemoveTopics(String removeTopics) {
		this.removeTopics = removeTopics;
	}	
}
