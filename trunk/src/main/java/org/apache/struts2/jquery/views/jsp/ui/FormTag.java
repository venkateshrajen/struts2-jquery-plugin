package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Form;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class FormTag extends AbstractBaseTag {

    private String onSubmitTopics;
    private String submitTopics;
    private String targetId;
    protected String action;
    protected String onsubmit;
    protected String onreset;
    protected String method;
    protected String validate;
    protected String focusElement;
    
	public FormTag() {
		super();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Form(stack, req, res);
		return bean;
	}

	@Override
    protected void populateParams() {
				
		super.populateParams();
		
        Form form = (Form) component;
        form.setSubmitTopics(submitTopics);
        form.setTargetId(targetId);
        form.setOnSubmitTopics(onSubmitTopics);
        form.setAction(action);
        form.setOnsubmit(onsubmit);
        form.setOnreset(onreset);
        form.setMethod(method);
        form.setValidate(validate);
        form.setFocusElement(focusElement);
    }
	
    public void setSubmitTopics(String submitTopics) {
		this.submitTopics = submitTopics;
	}
    
    public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public void setOnSubmitTopics(String onSubmitTopics) {
		this.onSubmitTopics = onSubmitTopics;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setOnsubmit(String onsubmit) {
		this.onsubmit = onsubmit;
	}

	public void setOnreset(String onreset) {
		this.onreset = onreset;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public void setFocusElement(String focusElement) {
		this.focusElement = focusElement;
	}
}

