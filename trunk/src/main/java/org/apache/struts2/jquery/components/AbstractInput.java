package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

public abstract class AbstractInput extends AbstractInteractive implements Input {

	protected String focusTopics;		//topics that
	protected String blurTopics;		//topics that 
	protected String reloadTopics;		//topics that
	protected String onChangeTopics;	//topics that are published on input vlaue change
	protected String onBlurTopics;		//topics that are published on blur
	protected String onFocusTopics;		//topics that are published on focus
    
    protected String src;				//The url from which to load the input contents
    protected String formIds;			//Forms that should be serialized and sent with the input load request
    protected String elementIds;		//Form elements that should be individually serialized and sent with the input's load request
		
	public AbstractInput(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		
		super(stack, request, response);
	}
	
    public void evaluateExtraParams() {
        
        super.evaluateExtraParams();
            	
        if (focusTopics != null)
            addParameter("focusTopics", findString(focusTopics));
        if (blurTopics != null)
            addParameter("blurTopics", findString(blurTopics));
        if (reloadTopics != null)
            addParameter("reloadTopics", findString(reloadTopics));
        if (onChangeTopics != null)
            addParameter("onChangeTopics", findString(onChangeTopics));
        if (onBlurTopics != null)
            addParameter("onBlurTopics", findString(onBlurTopics));
        if (onFocusTopics != null)
            addParameter("onFocusTopics", findString(onFocusTopics));
        if (src != null)
            addParameter("src", ensureAttributeSafelyNotEscaped(findString(src)));
        if (formIds != null)
            addParameter("formIds", findString(formIds));
        if (elementIds != null)
            addParameter("elementIds", findString(elementIds));
    }
    
    protected void setStack(ValueStack stack){
    	this.stack = stack;
    }

	@StrutsTagAttribute(name="focusTopics", description="A comma delimited list of topics that will cause this element to focus", type="String", defaultValue="")
	public void setFocusTopics(String focusTopics) {
		this.focusTopics = focusTopics;
	}

	@StrutsTagAttribute(name="blurTopics", description="A comma delimited list of topics that will cause this element to blur", type="String", defaultValue="")
	public void setBlurTopics(String blurTopics) {
		this.focusTopics = blurTopics;
	}

	@StrutsTagAttribute(name="reloadTopics", description="A comma delimited list of topics that will cause this element to reload its contents", type="String", defaultValue="")
	public void setReloadTopics(String reloadTopics) {
		this.reloadTopics = reloadTopics;
	}

	@StrutsTagAttribute(name="onBlurTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnBlurTopics(String onBlurTopics) {
		this.onBlurTopics = onBlurTopics;
	}

	@StrutsTagAttribute(name="onChangeTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnChangeTopics(String onChangeTopics) {
		this.onChangeTopics = onChangeTopics;
	}

	@StrutsTagAttribute(name="onFocusTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnFocusTopics(String onFocusTopics) {
		this.onFocusTopics = onFocusTopics;
	}

	@StrutsTagAttribute(name="src", description="The url to be use to retrieve this element's contents", type="String", defaultValue="", required=true)
	public void setSrc(String src) {
		this.src = src;
	}

	@StrutsTagAttribute(name="elementIds", description="A comma delimited list of form elements that should be individually serialized and sent with the input load request. " +
			"Input element must have a 'name' attribute and will be serialized as <name>=<value>", type="String", defaultValue="", required=false)
	public void setElementIds(String elementIds){
		this.elementIds = elementIds;
	}

	@StrutsTagAttribute(name="formIds", description="A comma delimited list of forms that should be serialized and sent with the input load request", type="String", defaultValue="", required=false)
	public void setFormIds(String formIds){
		this.formIds = formIds;
	}
}