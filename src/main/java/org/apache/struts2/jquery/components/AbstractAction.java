package org.apache.struts2.jquery.components;

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
	protected String indicatorId;		//If loading content into a target, Id of element that will be displayed during loading and hidden afterwards
	protected String loadingText;		//If loading content into a target, The text to be displayed during load
	protected String onCompleteTopics;
	protected String onSuccessTopics;
	protected String onErrorTopics;
    protected String errorText;				//text to be displayed on load error
    protected String errorElementId;		//the id of the element in to which to put the error text
	
    protected String elementIds;		//Form elements that should be individually serialized and sent with the input's load request
		
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
        	addParameter("href", ensureAttributeSafelyNotEscaped(URLBuilder.buildRootURL(findString(href), request)));
        if (formIds != null)
            addParameter("formIds", findString(formIds));
        if (validate != null)
            addParameter("validate", findString(validate));
        if (indicatorId != null)
            addParameter("indicatorId", findString(indicatorId));
        if (loadingText != null)
            addParameter("loadingText", findString(loadingText));
        if (onCompleteTopics != null)
            addParameter("onCompleteTopics", findString(onCompleteTopics));
        if (onSuccessTopics != null)
            addParameter("onSuccessTopics", findString(onSuccessTopics));
        if (onErrorTopics != null)
            addParameter("onErrorTopics", findString(onErrorTopics));   
        if (elementIds != null)
            addParameter("elementIds", findString(elementIds));
        if (errorText != null)
            addParameter("errorText", findString(errorText));
        if (errorElementId != null)
            addParameter("errorElementId", findString(errorElementId));
    }

    protected void setStack(ValueStack stack){
    	this.stack = stack;
    }

    @StrutsTagAttribute(name="href", description="The url to be use when this element is clicked", type="String", defaultValue="")
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

	@StrutsTagAttribute(name="targets", description="A comma separated list of ids of container elements to load with the contents from the result of this request", type="String", defaultValue="")
	public void setTargets(String targets) {
		this.targets = targets;
	}

	@StrutsTagAttribute(name="validate", description="Whether to execute validation on this elements of the form(s) provided in the formId attribute (valid values are 'true', 'false', and 'only'). Selecting 'only' will noly validate the form fiellds and not execute the result of this action implied by the href url", type="String", defaultValue="false")
	public void setValidate(String validate) {
		this.validate = validate;
	}
	
	@StrutsTagAttribute(name="indicatorId", description="If loading content into a target, Id of element that will be displayed during loading and hidden afterwards (will override settings for the target container)", type="String", defaultValue="")
    public void setIndicatorId(String indicatorId){
		this.indicatorId = indicatorId;
	}
	
	@StrutsTagAttribute(name="loadingText", description="If loading content into a target, The text to be displayed during load (will be shown if any provided, will override settings for the target container)", type="String", defaultValue="")
    public void setLoadingText(String loadingText){
		this.loadingText = loadingText;
		
	}

    @StrutsTagAttribute(name="errorText", description="The text to be displayed on load error. If 'errorElement' is provided, " +
			"this will display the error in the elemtn (if existing), if not, it will display the error as the contents of this container", type="String", defaultValue="false")
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	
	@StrutsTagAttribute(name="errorElementId", description="This should provide the id of the element into which the error text will be placed when an error ocurrs loading the container. If 'errorTest' is provided, that  wil be used, otherwise the ajax error message text wil be used.", type="String", defaultValue="false")
    public void setErrorElementId(String errorElementId){
		this.errorElementId = errorElementId;
	}
	

	@StrutsTagAttribute(name="onCompleteTopics", description = "A comma delimited list of topics that published when the element ajax request is completed (will override settings for a target container if provided)", type="String", defaultValue="")
	public void setOnCompleteTopics(String onCompleteTopics){
		this.onCompleteTopics = onCompleteTopics;
	}

	@StrutsTagAttribute(name="onSuccessTopics", description = "A comma delimited list of topics that published when the element ajax request is completed successfully  (will override settings for a target container if provided)", type="String", defaultValue="")
	public void setOnSuccessTopics(String onSuccessTopics){
		this.onSuccessTopics = onSuccessTopics;
	}

	@StrutsTagAttribute(name="onErrorTopics", description = "A comma delimited list of topics that published when the element ajax request returns an error (will override settings for a target container if provided)", type="String", defaultValue="")
	public void setOnErrorTopics(String onErrorTopics){
		this.onErrorTopics = onErrorTopics;
	}

	@StrutsTagAttribute(name="elementIds", description="A comma delimited list of form elements that should be individually serialized and sent with the input load request. " +
			"Input element must have a 'name' attribute and will be serialized as <name>=<value>", type="String", defaultValue="", required=false)
	public void setElementIds(String elementIds){
		this.elementIds = elementIds;
	}
}