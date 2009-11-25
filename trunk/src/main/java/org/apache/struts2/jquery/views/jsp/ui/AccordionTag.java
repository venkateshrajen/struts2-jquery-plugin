package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Accordion;
import org.apache.struts2.jquery.components.AccordionItem;

import com.opensymphony.xwork2.util.ValueStack;

public class AccordionTag extends AbstractInteractiveTag {

	private static final long serialVersionUID = -5675557693230197992L;
	
	protected String active;				//Selector for the active element. Set to false to display none at start. Needs collapsible: true.
	protected String autoHeight;			//If set, the highest content part is used as height reference for all other parts. Provides more consistent animations
	protected String clearStyle;			//If set, clears height and overflow styles after finishing animations. This enables accordions to work with dynamic content. Won't work together with autoHeight.
	protected String collapsible;			//Whether all the sections can be closed at once. Allows collapsing the active section by the triggering event (click is the default).
	protected String fillSpace;				//If set, the accordion completely fills the height of the parent element. Overrides autoheight.
	protected String headerClass;			//The class to assign to the header element div
	protected String options;				//Additional widget options
    
	public AccordionTag() {
		super();
	}
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Accordion(stack, req, res);
		return bean;
	}
	
	public void addItem(AccordionItem item){
		if(this.component != null) {
			((Accordion)component).addItem(item);
		}
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();

        Accordion accordion = (Accordion) component;
        accordion.setActive(active);
        accordion.setAutoHeight(autoHeight);
        accordion.setClearStyle(clearStyle);
        accordion.setCollapsible(collapsible);
        accordion.setFillSpace(fillSpace);
        accordion.setHeaderClass(headerClass);
        accordion.setOptions(options);
        
    }

	public void setActive(String active) {
		this.active = active;
	}
	
	public void setAutoHeight(String autoHeight) {
		this.autoHeight = autoHeight;
	}
	
	public void setClearStyle(String clearStyle) {
		this.clearStyle = clearStyle;
	}
	
	public void setCollapsible(String collapsible) {
		this.collapsible = collapsible;
	}
	
	public void setFillSpace(String fillSpace) {
		this.fillSpace = fillSpace;
	}
	
	public void setHeaderClass(String headerClass) {
		this.headerClass = headerClass;
	}
		
    public void setOptions(String options) {
        this.options = options;
    }
}
