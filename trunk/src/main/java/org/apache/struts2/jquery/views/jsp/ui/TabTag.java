package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Tab;

import com.opensymphony.xwork2.util.ValueStack;

public class TabTag extends AbstractInputTag {

	private static final long serialVersionUID = -3841835722558071249L;

    private Boolean isSelected;					//set to true to make this the default selected tab (only one tab shoudl have this)
    private Boolean isDisabled;					//set to true to make this tab disabled
    
	public TabTag() {
		super();
	}
	    
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Tab(stack, req, res);
		return bean;
    }

	@Override
    protected void populateParams() {
        
		super.populateParams();
		
		Tab tab = (Tab) component;
		tab.setIsSelected(isSelected);
		tab.setIsDisabled(isDisabled);
	
	}
	
	@Override
	public int doEndTag() throws JspException {
		
		Tag tabbedPaneTag = this.getParent();
		
		if(!(tabbedPaneTag instanceof TabbedPaneTag)) {
			
			throw new JspTagException("Tab tag must be inside TabbedPane Tag");
		}
		
		((TabbedPaneTag)tabbedPaneTag).addTab((Tab)component);
		
		return super.doEndTag();
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
}
