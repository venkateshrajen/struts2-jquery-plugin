package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Submit;

import com.opensymphony.xwork2.util.ValueStack;

public class SubmitTag extends org.apache.struts2.views.jsp.ui.SubmitTag {
	
	private static final long serialVersionUID = -2130063104541861261L;
		    
	protected String targets;					//A comma separated list of ajax-enable ids of elements to realod with the contents of this request
    protected String formId;					//Form id for which to serialize all fields during submission for load
   
	private DelegateJQueryTag delegate;
		
	public SubmitTag() {
		super();
		delegate = new DelegateJQueryTag();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Submit(stack, req, res);
		delegate.setComponent(bean);
		return bean;
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();
        delegate.populateParams();

        Submit submit = (Submit) component;
        submit.setTargets(targets);
        submit.setFormId(formId);
       
    }

	public void setTargets(String targets) {
		this.targets = targets;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	public void setAlwaysPublishTopics(String alwaysPublishTopics) {
		delegate.setAlwaysPublishTopics(alwaysPublishTopics);
	}

	public void setBeforePublishTopics(String beforePublishTopics) {
		delegate.setBeforePublishTopics(beforePublishTopics);
	}

	public void setAfterPublishTopics(String afterPublishTopics) {
		delegate.setAfterPublishTopics(afterPublishTopics);
	}
	
	public void setErrorPublishTopics(String errorPublishTopics) {
		delegate.setErrorPublishTopics(errorPublishTopics);
	}
	
	public void setIndicatorId(String indicatorId) {
		delegate.setIndicatorId(indicatorId);
	}
}
