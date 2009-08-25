package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="form", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.FormTag", 
		description="Render HTML form providing dynamic submission and ajax loading",
		allowDynamicAttributes=true)
public class Form extends AbstractBase {

    public static final String TEMPLATE = "form";
    public static final String TEMPLATE_CLOSE = "form-close";

	protected String onSubmitTopics;		//topics that will be published when the form is submitted
	
	protected String submitTopics;			//topics that will cause form to submit
	
	protected String targetId;				//id of container element into which to load results of form submission 
    protected String action;
    protected String onsubmit;
    protected String onreset;
    protected String method;
    protected String validate;
    protected String focusElement;

	
    public Form(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    @Override
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();

        if (submitTopics != null)
            addParameter("submitTopics", findString(submitTopics));
        if (targetId != null)
            addParameter("targetId", findString(targetId));
        if (onSubmitTopics != null)
            addParameter("onSubmitTopics", findString(onSubmitTopics));
        if (action != null)
            addParameter("action", findString(action));
        if (onsubmit != null)
            addParameter("onsubmit", findString(onsubmit));
        if (onreset != null)
            addParameter("onreset", findString(onreset));
        if (method != null)
            addParameter("method", findString(method));
        if (validate != null)
            addParameter("validate", findValue(validate, Boolean.class));
        if (focusElement != null)
            addParameter("focusElement", findString(focusElement));
    }

    @Override
    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }
    

	/**  TOPIC LISTENERS **/
	@StrutsTagAttribute(name="submitTopics", description="A comma delimited list of topics that will cause this form to submit", type="String", defaultValue="")
	public void setSubmitTopics(String submitTopics){
		this.submitTopics = submitTopics;
	}

	/**  TOPIC PUBLISHERS **/
	@StrutsTagAttribute(name="onSubmitTopics", description="A comma delimited list of topics that will be published when the form is submitted", type="String", defaultValue="")
	public void setOnSubmitTopics(String onSubmitTopics){
		this.onSubmitTopics = onSubmitTopics;
	}
	
	/**  SPECIAL **/
	@StrutsTagAttribute(name="targetId", description="Id of container element into which to load results of form submission ", type="String", defaultValue="")
	public void setTargetId(String targetId){
		this.targetId = targetId;
	}

    @StrutsTagAttribute(description="HTML onsubmit attribute")
    public void setOnsubmit(String onsubmit) {
        this.onsubmit = onsubmit;
    }

    @StrutsTagAttribute(description="HTML onreset attribute")
    public void setOnreset(String onreset) {
        this.onreset = onreset;
    }

    @StrutsTagAttribute(description="Set action name to submit to, without .action suffix", defaultValue="current action")
    public void setAction(String action) {
        this.action = action;
    }

    @StrutsTagAttribute(description="HTML form method attribute")
    public void setMethod(String method) {
        this.method = method;
    }

    @StrutsTagAttribute(description="Whether client side/remote validation should be performed.", type="Boolean", defaultValue="false")
    public void setValidate(String validate) {
        this.validate = validate;
    }

    @StrutsTagAttribute(description="Id of element that will receive the focus when page loads.")
    public void setFocusElement(String focusElement) {
        this.focusElement = focusElement;
    }
}
