package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.util.ContainUtil;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="textfield", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.TextFieldTag", 
		description="Render input field of type text providing content from remote call via AJAX",
		allowDynamicAttributes=true)
public class TextField extends AbstractContainer implements Input {

    public static final String TEMPLATE = "text";
    public static final String TEMPLATE_CLOSE = "text-close";

    private AbstractInput inputDelegate = new AbstractInput(stack, request, response){

		@Override
		public String getDefaultOpenTemplate() {
			return TEMPLATE;
		}

		@Override
		protected String getDefaultTemplate() {
			return TEMPLATE_CLOSE;
		}    	
    };
		
    public TextField(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    public boolean contains(Object obj1, Object obj2) {
        return ContainUtil.contains(obj1, obj2);
    }
    
	@Override
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();
        inputDelegate.setParameters(this.parameters);
        inputDelegate.evaluateExtraParams();
    }

    @Override
    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }

    @StrutsTagAttribute(name="focusTopics", description="A comma delimited list of topics that will cause this element to focus", type="String", defaultValue="")
	public void setFocusTopics(String focusTopics) {
		inputDelegate.setFocusTopics(focusTopics);
	}

    @StrutsTagAttribute(name="blurTopics", description="A comma delimited list of topics that will cause this element to blur", type="String", defaultValue="")
	public void setBlurTopics(String blurTopics) {
		inputDelegate.setBlurTopics(blurTopics);
	}

	@StrutsTagAttribute(name="reloadTopics", description="A comma delimited list of topics that will cause this element to reload its contents", type="String", defaultValue="")
	public void setReloadTopics(String reloadTopics) {
		this.reloadTopics = reloadTopics;
	}

	@StrutsTagAttribute(name="onBlurTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnBlurTopics(String onBlurTopics) {
		inputDelegate.setOnBlurTopics(onBlurTopics);
	}

	@StrutsTagAttribute(name="onChangeTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnChangeTopics(String onChangeTopics) {
		inputDelegate.setOnChangeTopics(onChangeTopics);
	}

	@StrutsTagAttribute(name="onFocusTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnFocusTopics(String onFocusTopics) {
		inputDelegate.setOnFocusTopics(onFocusTopics);
	}

	@StrutsTagAttribute(name="elementIds", description="A comma delimited list of form elements that should be individually serialized and sent with the input load request. " +
			"Input element must have a 'name' attribute and will be serialized as <name>=<value>", type="String", defaultValue="", required=false)
	public void setElementIds(String elementIds){
		inputDelegate.setElementIds(elementIds);
	}

	@StrutsTagAttribute(name="formIds", description="A comma delimited list of forms that should be serialized and sent with the input load request", type="String", defaultValue="", required=false)
	public void setFormIds(String formIds){
		inputDelegate.setFormIds(formIds);
	}
	
	@StrutsTagAttribute(name="enableTopics", description = "A comma delimited list of topics that will enable this element", type = "String", defaultValue = "")
    public void setEnableTopics(String enableTopics) {
		inputDelegate.setEnableTopics(enableTopics);
	}

	@StrutsTagAttribute(name="disableTopics", description = "A comma delimited list of topics that will disable this element", type = "String", defaultValue = "")
    public void setDisableTopics(String disableTopics) {
		inputDelegate.setDisableTopics(disableTopics);
	}	
}
