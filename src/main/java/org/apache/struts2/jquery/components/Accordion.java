package org.apache.struts2.jquery.components;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="accordion", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.AccordionTag", 
		description="Renders an accordion menu with accordion items",
		allowDynamicAttributes=true)
public class Accordion extends AbstractInteractive  {

    public static final String TEMPLATE = "accordion";
    public static final String TEMPLATE_CLOSE = "accordion-close";

	protected String active;				//Selector for the active element. Set to false to display none at start. Needs collapsible: true.
	protected String autoHeight;			//If set, the highest content part is used as height reference for all other parts. Provides more consistent animations
	protected String clearStyle;			//If set, clears height and overflow styles after finishing animations. This enables accordions to work with dynamic content. Won't work together with autoHeight.
	protected String collapsible;			//Whether all the sections can be closed at once. Allows collapsing the active section by the triggering event (click is the default).
	protected String fillSpace;				//If set, the accordion completely fills the height of the parent element. Overrides autoheight.
	protected String headerClass;			//The class to assign to the header element div
	protected String options;				//Additional widget options

    protected List<AccordionItem> items;
    
    public Accordion(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        
    	super(stack, request, response);

    	items = new ArrayList<AccordionItem>();
    }

    public void addItem(AccordionItem item){
    	
    	if(item != null) {
    		item.setHeaderClass(headerClass);
    	}
    	
    	items.add(item);
    }
    
    @Override
    public void evaluateExtraParams() {
        
        super.evaluateExtraParams();
        
        if (active != null)
            addParameter("active", findString(active));
        if (autoHeight != null)
            addParameter("autoHeight", findValue(autoHeight, Boolean.class));
        if (clearStyle != null)
            addParameter("clearStyle", findValue(clearStyle, Boolean.class));
        if (collapsible != null)
            addParameter("collapsible", findValue(collapsible, Boolean.class));
        if (fillSpace != null)
            addParameter("fillSpace", findValue(fillSpace, Boolean.class));
        if (headerClass != null)
            addParameter("headerClass", findString(headerClass));
       
        if (this.options != null) {
            addParameter("options", findString(this.options));
        }
    }

	public List<AccordionItem> getItems() {
		return items;
	}
        
	@Override
	public String getDefaultOpenTemplate() {
        return TEMPLATE;
	}
	
	@Override
	protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
	}
	

    @StrutsTagAttribute(description = "The name of a variable (or a javascript map) that contains additional options to be passed to the Dialog widget")
    public void setOptions(String options) {
        this.options = options;
    }

    @StrutsTagAttribute(name="active", type="Boolean", defaultValue="true", description = "Selector for the active element. Set to false to display none at start. Needs collapsible: true.")
    public void setActive(String active) {
		this.active = active;
	}

    @StrutsTagAttribute(name="autoHeight", type="Boolean", defaultValue="true", description = "If set, the highest content part is used as height reference for all other parts. Provides more consistent animations")
	public void setAutoHeight(String autoHeight) {
		this.autoHeight = autoHeight;
	}

    @StrutsTagAttribute(name="clearStyle", type="Boolean", defaultValue="false", description = "If set, clears height and overflow styles after finishing animations. This enables accordions to work with dynamic content. Won't work together with autoHeight.")
	public void setClearStyle(String clearStyle) {
		this.clearStyle = clearStyle;
	}

    @StrutsTagAttribute(name="collapsible", type="Boolean", defaultValue="false", description = "Whether all the sections can be closed at once. Allows collapsing the active section by the triggering event (click is the default).")
	public void setCollapsible(String collapsible) {
		this.collapsible = collapsible;
	}

    @StrutsTagAttribute(name="headerClass", type="String", defaultValue="", description = "The class to assign to the header element div")
	public void setHeaderClass(String headerClass) {
		this.headerClass = headerClass;
	}

    @StrutsTagAttribute(name="fillSpace", type="Boolean", defaultValue="false", description = "If set, the accordion completely fills the height of the parent element. Overrides autoheight.")
	public void setFillSpace(String fillSpace) {
		this.fillSpace = fillSpace;
	}
}
