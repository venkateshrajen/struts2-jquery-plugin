package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Div;

import com.opensymphony.xwork2.util.ValueStack;

public class DivTag extends org.apache.struts2.views.jsp.ui.DivTag {
	
	private static final long serialVersionUID = -4839207037830558353L;

    protected String src;						//The url from which to load the source
	protected String reloadTopics;				//topics that will cause div to reload
    protected String loadingText;				//Text to be displayed during load (will be shown if any provided)
    protected String errorText;					//text to be displayed on load error

	private DelegateJQueryTag delegate;
	
	public DivTag() {
		super();
		delegate = new DelegateJQueryTag();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Div(stack, req, res);
		delegate.setComponent(bean);
		return bean;
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();
        delegate.populateParams();

        Div div = (Div) component;
        div.setSrc(src);
        div.setReloadTopics(reloadTopics);
        div.setLoadingText(loadingText);
        div.setErrorText(errorText);
        
    }

	public void setSrc(String src) {
		this.src = src;
	}

	public void setReloadTopics(String reloadTopics) {
		this.reloadTopics = reloadTopics;
	}

	public void setLoadingText(String loadingText) {
		this.loadingText = loadingText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
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
