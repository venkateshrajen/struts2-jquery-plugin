package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Action;

import com.opensymphony.xwork2.util.ValueStack;

public class AbstractActionTag extends AbstractInteractiveTag implements Action {

	private static final long serialVersionUID = -8151222719613824475L;

	protected String onClickTopics;		//topics that are published on click
    
	protected String targets;			//The targets into which to load content
    protected String href;				//The url to execute
    protected String formIds;			//the forms
    protected String validate;			//text to be displayed on load error
	
	public AbstractActionTag() {
		super();
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();

		Action action = (Action) component;
		action.setOnClickTopics(onClickTopics);
		action.setTargets(targets);
		action.setHref(href);
		action.setFormIds(formIds);
		action.setValidate(validate);
    }
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return component;
	}

	public void setOnClickTopics(String onClickTopics) {
		this.onClickTopics = onClickTopics;
	}

	public void setTargets(String targets) {
		this.targets = targets;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setFormIds(String formIds) {
		this.formIds = formIds;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

}
