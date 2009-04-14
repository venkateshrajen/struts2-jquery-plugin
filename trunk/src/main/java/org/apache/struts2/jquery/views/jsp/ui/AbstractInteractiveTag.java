package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Interactive;

import com.opensymphony.xwork2.util.ValueStack;

public abstract class AbstractInteractiveTag extends AbstractBaseTag implements Interactive {

	private static final long serialVersionUID = 6438949293925673945L;

	protected String enableTopics;			//topics that will enable element
	protected String disableTopics;			//topics that will disable element
	
	public AbstractInteractiveTag() {
		super();
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();

		Interactive interactive = (Interactive) component;
		interactive.setEnableTopics(enableTopics);
		interactive.setDisableTopics(disableTopics);
        
    }
    
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return component;
	}

	public void setEnableTopics(String enableTopics) {
		this.enableTopics = enableTopics;
	}

	public void setDisableTopics(String disableTopics) {
		this.disableTopics = disableTopics;
	}

}
