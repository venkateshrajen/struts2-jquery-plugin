package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Input;
import org.apache.struts2.jquery.components.TextArea;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class TextAreaTag extends AbstractContainerTag implements Input {
		
	private AbstractInputTag inputDelegate = new AbstractInputTag(){};
    
	public TextAreaTag() {
		super();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new TextArea(stack, req, res);
		return bean;
	}

	@Override
    protected void populateParams() {
		
		inputDelegate.setComponent(this.component);
		inputDelegate.populateParams();
		
		super.populateParams();
		
    }
		
	//need to apply both to container base class and input delegate class
	public void setReloadTopics(String reloadTopics) {
		super.setReloadTopics(reloadTopics);
		inputDelegate.setReloadTopics(reloadTopics);
	}

	//need to apply both to container base class and input delegate class
	public void setSrc(String src) {
		super.setSrc(src);
		inputDelegate.setSrc(src);
	}
	
	public void setBlurTopics(String blurTopics) {
		inputDelegate.setBlurTopics(blurTopics);
	}

	public void setFocusTopics(String focusTopics) {
		inputDelegate.setFocusTopics(focusTopics);
	}

	public void setOnBlurTopics(String onBlurTopics) {
		inputDelegate.setOnBlurTopics(onBlurTopics);
	}

	public void setOnChangeTopics(String onChangeTopics) {
		inputDelegate.setOnChangeTopics(onChangeTopics);
	}

	public void setOnFocusTopics(String onFocusTopics) {
		inputDelegate.setOnFocusTopics(onFocusTopics);
	}

	public void setDisableTopics(String disableTopics) {
		inputDelegate.setDisableTopics(disableTopics);
	}

	public void setEnableTopics(String enableTopics) {
		inputDelegate.setEnableTopics(enableTopics);
	}

	public void setFormIds(String formIds) {
		inputDelegate.setFormIds(formIds);
	}

	public void setElementIds(String elementIds) {
		inputDelegate.setElementIds(elementIds);
	}
}