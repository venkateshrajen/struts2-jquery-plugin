package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Input;

import com.opensymphony.xwork2.util.ValueStack;

public class AbstractInputTag extends AbstractInteractiveTag implements Input {

	private static final long serialVersionUID = -3898765360950367754L;
	
	protected String focusTopics;		//topics that are published on click
	protected String reloadTopics;		//topics that are published on click
	
	protected String onChangeTopics;	//topics that are published on input vlaue change
	protected String onBlurTopics;		//topics that are published on blur
	protected String onFocusTopics;		//topics that are published on focus
    
    protected String src;				//The url from which to load the input contents
    	
	public AbstractInputTag() {
		super();
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();

		Input input = (Input) component;
		input.setFocusTopics(focusTopics);
		input.setReloadTopics(reloadTopics);
		input.setOnChangeTopics(onChangeTopics);
		input.setOnBlurTopics(onBlurTopics);
		input.setOnFocusTopics(onFocusTopics);
		input.setSrc(src);
    }
	    
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return component;
	}

	public void setFocusTopics(String focusTopics) {
		this.focusTopics = focusTopics;
	}

	public void setReloadTopics(String reloadTopics) {
		this.reloadTopics = reloadTopics;
	}

	public void setOnChangeTopics(String onChangeTopics) {
		this.onChangeTopics = onChangeTopics;
	}

	public void setOnBlurTopics(String onBlurTopics) {
		this.onBlurTopics = onBlurTopics;
	}

	public void setOnFocusTopics(String onFocusTopics) {
		this.onFocusTopics = onFocusTopics;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
