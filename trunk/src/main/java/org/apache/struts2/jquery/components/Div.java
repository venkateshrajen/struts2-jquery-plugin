package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="div", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.DivTag", 
		description="Render HTML div providing content from remote call via AJAX",
		allowDynamicAttributes=true)
public class Div extends AbstractContainer {

    public static final String TEMPLATE = "div";
    public static final String TEMPLATE_CLOSE = "div-close";

    protected String draggable;				//If the dialog should be draggable or not (true/false)
	protected String draggableOptions;		//IjQuery draggable options
	protected String droppable;				//If the dialog should be a droppable container or not (true/false)
	protected String droppableOptions;		//jQuery draggable options
	protected String resizable;				//If the dialog should be resizable or not (true/false)
	protected String resizableOptions;		//jQuery resizable options
	
    public Div(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    @Override
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();

        if (draggable != null)
            addParameter("draggable", findValue(draggable, Boolean.class));
        if (draggableOptions != null)
            addParameter("draggableOptions", findString(draggableOptions));
        if (droppable != null)
            addParameter("droppable", findValue(droppable, Boolean.class));
        if (droppableOptions != null)
            addParameter("droppableOptions", findString(droppableOptions));
        if (resizable != null)
            addParameter("resizable", findValue(resizable, Boolean.class));
        if (resizableOptions != null)
            addParameter("resizableOptions", findString(resizableOptions));
    }

    @Override
    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }

    @StrutsTagAttribute(name="draggable", description="If the dialog should be draggable or not (true/false)", type="Boolean")
	public void setDraggable(String draggable) {
		this.draggable = draggable;
	}

    @StrutsTagAttribute(name="draggableOptions", description="Additional jQuery draggable options to pass to the draggable container", type="Boolean")
	public void setDraggableOptions(String draggableOptions) {
		this.draggableOptions = draggableOptions;
	}

    @StrutsTagAttribute(name="droppable", description="If the dialog should be a valid droppable container or not (true/false)", type="Boolean")
	public void setDroppable(String droppable) {
		this.droppable = droppable;
	}

    @StrutsTagAttribute(name="droppableOptions", description="Additional jQuery droppable options to pass to the droppable container", type="Boolean")
	public void setDroppableOptions(String droppableOptions) {
		this.droppableOptions = droppableOptions;
	}

    @StrutsTagAttribute(name="resizable", description="If the dialog should be resizable or not (true/false)", type="Boolean")
	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

    @StrutsTagAttribute(name="resizableOptions", description="Additional jQuery resizable options to pass to the droppable container", type="Boolean")
	public void setResizableOptions(String resizableOptions) {
		this.resizableOptions = resizableOptions;
	}
}
