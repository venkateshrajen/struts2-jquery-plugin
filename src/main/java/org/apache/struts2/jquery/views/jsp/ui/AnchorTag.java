package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Anchor;

import com.opensymphony.xwork2.util.ValueStack;

public class AnchorTag extends org.apache.struts2.views.jsp.ui.AnchorTag {
	
	private static final long serialVersionUID = -2130063104541861261L;
		
	protected String targets;					//A comma separated list of ajax-enable ids of elements to realod with the contents of this request
    protected String formId;					//Form id for which to serialize all fields during submission for load
   
	private DelegateJQueryTag delegate;
		
	public AnchorTag() {
		super();
		delegate = new DelegateJQueryTag();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Anchor(stack, req, res);
		delegate.setComponent(bean);
		return bean;
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();
        delegate.populateParams();

        Anchor anchor = (Anchor) component;
        anchor.setTargets(targets);
        anchor.setFormId(formId);
       
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
