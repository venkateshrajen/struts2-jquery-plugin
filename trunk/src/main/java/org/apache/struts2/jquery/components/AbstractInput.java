package org.apache.struts2.jquery.components;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

public class AbstractInput extends AbstractInteractive implements Input {

	protected String focusTopics;		//topics that are published on click
	protected String reloadTopics;		//topics that are published on click
	
	protected String onChangeTopics;	//topics that are published on input vlaue change
	protected String onBlurTopics;		//topics that are published on blur
	protected String onFocusTopics;		//topics that are published on focus
    
    protected String src;				//The url from which to load the input contents
		
	public AbstractInput(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		
		super(stack, request, response);
	}
	
    public void evaluateExtraParams() {
        
        super.evaluateExtraParams();
            	
        if (focusTopics != null)
            addParameter("focusTopics", findString(focusTopics));
        if (reloadTopics != null)
            addParameter("reloadTopics", findString(reloadTopics));
        if (onChangeTopics != null)
            addParameter("onChangeTopics", findString(onChangeTopics));
        if (onBlurTopics != null)
            addParameter("onBlurTopics", findString(onBlurTopics));
        if (onFocusTopics != null)
            addParameter("onFocusTopics", findString(onFocusTopics));
        if (src != null)
            addParameter("src", findString(src));
    }
    
    protected void setStack(ValueStack stack){
    	this.stack = stack;
    }

    @SuppressWarnings("unchecked")
	protected void setParameters(Map parameters){
    	this.parameters = parameters;
    }

	@Override
	public String getDefaultOpenTemplate() {
		throw new UnsupportedOperationException("ActionDelegate does not implement getDefaultOpenTemplate(). Must be implemented by first-class component");
	}

	@Override
	protected String getDefaultTemplate() {
		throw new UnsupportedOperationException("ActionDelegate does not implement getDefaultTemplate(). Must be implemented by first-class component");
	}

	@StrutsTagAttribute(name="focusTopics", description="A comma delimited list of topics that will cause this element to focus", type="String", defaultValue="")
	public void setFocusTopics(String focusTopics) {
		this.focusTopics = focusTopics;
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
}