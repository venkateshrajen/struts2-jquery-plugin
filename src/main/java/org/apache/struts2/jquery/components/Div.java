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
	protected String draggableOptions;		//jQuery draggable options
	protected String droppable;				//If the dialog should be a droppable container or not (true/false)
	protected String droppableOptions;		//jQuery draggable options
	protected String resizable;				//If the dialog should be resizable or not (true/false)
	protected String resizableOptions;		//jQuery resizable options
	protected String sortable;				//If the div represents a container of sortable items 
	protected String sortableOptions;		//jQuery sortable options
	
	protected String onSortableUpdateTopics;	//topics that are published when a user stops sorting and a contained item in a sortable div has changed position 
	protected String onSortableStartTopics;		//topics that are published when a user starts sorting in a sortable div
	protected String onSortableSortTopics;		//topics that are published when during sorting in a sortable div
	protected String onSortableStopTopics;		//topics that are published when a user stops sorting in a sortable div
	protected String onSortableReceiveTopics;	//topics that are published when a connected sortable list has received an item from another list.
	protected String onSortableRemoveTopics;	//topics that are published when a sortable item has been dragged out from the list and into another.
	
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
        if (sortable != null)
            addParameter("sortable", findValue(sortable, Boolean.class));
        if (sortableOptions != null)
            addParameter("sortableOptions", findString(sortableOptions));
        if (onSortableUpdateTopics != null)
            addParameter("onSortableUpdateTopics", findString(onSortableUpdateTopics));
        if (onSortableStartTopics != null)
            addParameter("onSortableStartTopics", findString(onSortableStartTopics));
        if (onSortableSortTopics != null)
            addParameter("onSortableSortTopics", findString(onSortableSortTopics));
        if (onSortableStopTopics != null)
            addParameter("onSortableStopTopics", findString(onSortableStopTopics));
        if (onSortableReceiveTopics != null)
            addParameter("onSortableReceiveTopics", findString(onSortableReceiveTopics));
        if (onSortableRemoveTopics != null)
            addParameter("onSortableRemoveTopics", findString(onSortableRemoveTopics));
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

    @StrutsTagAttribute(name="draggableOptions", description="Additional jQuery draggable options to pass to the draggable container", type="String")
	public void setDraggableOptions(String draggableOptions) {
		this.draggableOptions = draggableOptions;
	}

    @StrutsTagAttribute(name="droppable", description="If the dialog should be a valid droppable container or not (true/false)", type="Boolean")
	public void setDroppable(String droppable) {
		this.droppable = droppable;
	}

    @StrutsTagAttribute(name="droppableOptions", description="Additional jQuery droppable options to pass to the droppable container", type="String")
	public void setDroppableOptions(String droppableOptions) {
		this.droppableOptions = droppableOptions;
	}

    @StrutsTagAttribute(name="resizable", description="If the dialog should be resizable or not (true/false)", type="Boolean")
	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

    @StrutsTagAttribute(name="resizableOptions", description="Additional jQuery resizable options to pass to the droppable container", type="String")
	public void setResizableOptions(String resizableOptions) {
		this.resizableOptions = resizableOptions;
	}

    @StrutsTagAttribute(name="sortable", description="If the div represents a container of sortable items or not (true/false)", type="Boolean")
    public void setSortable(String sortable) {
		this.sortable = sortable;
	}

    @StrutsTagAttribute(name="sortableOptions", description="Additional jQuery sortable options to pass to the sortable container", type="String")
    public void setSortableOptions(String sortableOptions) {
		this.sortableOptions = sortableOptions;
	}
    
    @StrutsTagAttribute(name="onSortableReceiveTopics", description="Topics that are published when a connected sortable list has received an item from another list.", type="String")
    public void setOnSortableReceiveTopics(String onSortableReceiveTopics) {
		this.onSortableReceiveTopics = onSortableReceiveTopics;
	}

    @StrutsTagAttribute(name="onSortableRemoveTopics", description="Topics that are published when a sortable item has been dragged out from the list and into another.", type="String")
    public void setOnSortableRemoveTopics(String onSortableRemoveTopics) {
		this.onSortableRemoveTopics = onSortableRemoveTopics;
	}

    @StrutsTagAttribute(name="onSortableSortTopics", description="Topics that are published when during sorting in a sortable div", type="String")
    public void setOnSortableSortTopics(String onSortableSortTopics) {
		this.onSortableSortTopics = onSortableSortTopics;
	}

    @StrutsTagAttribute(name="onSortableStartTopics", description="Topics that are published when a user starts sorting in a sortable div", type="String")
    public void setOnSortableStartTopics(String onSortableStartTopics) {
		this.onSortableStartTopics = onSortableStartTopics;
	}

    @StrutsTagAttribute(name="onSortableStopTopics", description="Topics that are published when a user stops sorting in a sortable div", type="String")
    public void setOnSortableStopTopics(String onSortableStopTopics) {
		this.onSortableStopTopics = onSortableStopTopics;
	}

    @StrutsTagAttribute(name="onSortableUpdateTopics", description="Topics that are published when a user stops sorting and a contained item in a sortable div has changed position", type="String")
    public void setOnSortableUpdateTopics(String onSortableUpdateTopics) {
		this.onSortableUpdateTopics = onSortableUpdateTopics;
	}
}
