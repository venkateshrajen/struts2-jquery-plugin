package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Div;

import com.opensymphony.xwork2.util.ValueStack;

public class DivTag extends AbstractContainerTag {
	
	private static final long serialVersionUID = -4839207037830558353L;
	
	protected String draggable;				//If the dialog should be draggable or not (true/false)
	protected String draggableOptions;		//IjQuery draggable options
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
	
	
	public DivTag() {
		super();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Div(stack, req, res);
		return bean;
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();

        Div div = (Div) component;

		div.setDraggable(draggable);
		div.setDraggableOptions(draggableOptions);
		div.setDroppable(droppable);
		div.setDroppableOptions(droppableOptions);
        div.setResizable(resizable);
		div.setResizableOptions(resizableOptions);
        div.setSortable(sortable);
		div.setSortableOptions(sortableOptions);
		div.setOnSortableReceiveTopics(onSortableReceiveTopics);
		div.setOnSortableRemoveTopics(onSortableRemoveTopics);
		div.setOnSortableSortTopics(onSortableSortTopics);
		div.setOnSortableStartTopics(onSortableStartTopics);
		div.setOnSortableStopTopics(onSortableStopTopics);
		div.setOnSortableUpdateTopics(onSortableUpdateTopics);
		
    }


	public void setDraggable(String draggable) {
		this.draggable = draggable;
	}

	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

	public void setDraggableOptions(String draggableOptions) {
		this.draggableOptions = draggableOptions;
	}

	public void setDroppable(String droppable) {
		this.droppable = droppable;
	}

	public void setDroppableOptions(String droppableOptions) {
		this.droppableOptions = droppableOptions;
	}

	public void setResizableOptions(String resizableOptions) {
		this.resizableOptions = resizableOptions;
	}
	
	public void setSortable(String sortable) {
		this.sortable = sortable;
	}
	
	public void setSortableOptions(String sortableOptions) {
		this.sortableOptions = sortableOptions;
	}
    
    public void setOnSortableReceiveTopics(String onSortableReceiveTopics) {
		this.onSortableReceiveTopics = onSortableReceiveTopics;
	}

    public void setOnSortableRemoveTopics(String onSortableRemoveTopics) {
		this.onSortableRemoveTopics = onSortableRemoveTopics;
	}

    public void setOnSortableSortTopics(String onSortableSortTopics) {
		this.onSortableSortTopics = onSortableSortTopics;
	}

    public void setOnSortableStartTopics(String onSortableStartTopics) {
		this.onSortableStartTopics = onSortableStartTopics;
	}

    public void setOnSortableStopTopics(String onSortableStopTopics) {
		this.onSortableStopTopics = onSortableStopTopics;
	}

     public void setOnSortableUpdateTopics(String onSortableUpdateTopics) {
		this.onSortableUpdateTopics = onSortableUpdateTopics;
	}
}

