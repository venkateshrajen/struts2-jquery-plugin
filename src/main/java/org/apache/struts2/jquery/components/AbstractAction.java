package org.apache.struts2.jquery.components;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

public abstract class AbstractAction extends AbstractInteractive implements Action {

	protected String onClickTopics;		//topics that are published on click
    
	protected String targets;			//The targets into which to load content
    protected String href;				//The url to execute
    protected String formIds;			//the forms
    protected String validate;			//text to be displayed on load error
		

	public AbstractAction(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		
		super(stack, request, response);
	}
	
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();
            	
        if (onClickTopics != null)
            addParameter("onClickTopics", findString(onClickTopics));
        if (targets != null)
            addParameter("targets", findString(targets));
        if (href != null)
            addParameter("href", findString(href));
        if (formIds != null)
            addParameter("formIds", findString(formIds));
        if (validate != null)
            addParameter("validate", findString(validate));
    }

    protected void setStack(ValueStack stack){
    	this.stack = stack;
    }

    @SuppressWarnings("unchecked")
	protected void setParameters(Map parameters){
    	this.parameters = parameters;
    }

    @StrutsTagAttribute(name="href", description="The url to be use when this element is clicked", type="String", defaultValue="", required=true)
	public void setHref(String href) {
		this.href = href;
	}

    @StrutsTagAttribute(name="formIds", description="Comma delimited list of form ids for which to serialize all fields during submission when this element is clicked (if multiple forms have overlapping element names, it is indeterminate which will be used)", type="String", defaultValue="")
	public void setFormIds(String formIds) {
		this.formIds = formIds;
	}

	@StrutsTagAttribute(name="onClickTopics", description = "A comma delimited list of topics that published when the element is clicked", type="String", defaultValue="")
	public void setOnClickTopics(String onClickTopics) {
		this.onClickTopics = onClickTopics;
	}

	@StrutsTagAttribute(name="targets", description="A comma separated list of ids of container elements to load with the contents from the result of this request", type="String", defaultValue="", required=true)
	public void setTargets(String targets) {
		this.targets = targets;
	}

	@StrutsTagAttribute(name="validate", description="Whether to execute validation on this elements of the form(s) provided in the formId attribute (valid values are 'true', 'false', and 'only'). Selecting 'only' will noly validate the form fiellds and not execute the result of this action implied by the href url", type="String", defaultValue="false")
	public void setValidate(String validate) {
		this.validate = validate;
	}
}