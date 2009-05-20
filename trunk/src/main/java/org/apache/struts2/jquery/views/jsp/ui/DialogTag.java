package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Dialog;
import org.apache.struts2.jquery.components.Interactive;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class DialogTag extends AbstractContainerTag implements Interactive {

	private static final long serialVersionUID = -3809304151441133723L;

	private AbstractInteractiveTag interactiveDelegate = new AbstractInteractiveTag(){};
	
	protected String buttons;				//A comma delimited list of button names (in order of appearance, in same order as buttonTopics) 
	protected String buttonTopics;			//A comma delimited list of topics to be published when buttons are clicked (in same order as button names)
	protected String title;					//The dialog title
	protected String modal;					//Should the dialog be shown as a modal dialog or not (true/false)
	protected String draggable;				//If the dialog should be draggable or not (true/false)
	protected String resizable;				//If the dialog should be resizable or not (true/false)
	protected String height;				//The height of the dialog
	protected String width;					//The width of the dialog
	protected String position;				//The starting position of the dialog. Possible values: 'center', 'left', 'right', 'top', 'bottom', or an array containing a coordinate pair (in pixel offset from top left of viewport) or the possible string values (e.g. ['right','top'] for top right corner)
	protected String data;					//Additional data (in the form of "key1=value1,key2=value2,..." to be provided to the dialog for use in button click topic handlers

	public DialogTag() {
		super();
	}
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Dialog(stack, req, res);
		return bean;
	}
	
	@Override
    protected void populateParams() {

		interactiveDelegate.setComponent(this.component);
		interactiveDelegate.populateParams();
		
		super.populateParams();

        Dialog dialog = (Dialog) component;
        dialog.setButtons(buttons);
        dialog.setButtonTopics(buttonTopics);
        dialog.setTitle(title);
        dialog.setModal(modal);
        dialog.setDraggable(draggable);
        dialog.setResizable(resizable);
        dialog.setHeight(height);
        dialog.setWidth(width);
        dialog.setPosition(position);
        dialog.setData(data);        
    }

	public void setDisableTopics(String disableTopics) {
		interactiveDelegate.setDisableTopics(disableTopics);
	}

	public void setEnableTopics(String enableTopics) {
		interactiveDelegate.setEnableTopics(enableTopics);
	} 
		
	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	public void setButtonTopics(String buttonTopics) {
		this.buttonTopics = buttonTopics;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setModal(String modal) {
		this.modal = modal;
	}

	public void setDraggable(String draggable) {
		this.draggable = draggable;
	}

	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setData(String data) {
		this.data = data;
	}
}
