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
}

