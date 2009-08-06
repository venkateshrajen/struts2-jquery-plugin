package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.util.ContainUtil;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="textarea", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.TextAreaTag", 
		description="Render input field of type textarea providing content from remote call via AJAX",
		allowDynamicAttributes=true)
public class TextArea extends AbstractContainer implements Input {

    public static final String TEMPLATE = "textarea";
    public static final String TEMPLATE_CLOSE = "textarea-close";

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
		
    public TextArea(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
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
	
	@StrutsTagAttribute(name="enableTopics", description = "A comma delimited list of topics that will enable this element", type = "String", defaultValue = "")
    public void setEnableTopics(String enableTopics) {
		inputDelegate.setEnableTopics(enableTopics);
	}

	@StrutsTagAttribute(name="disableTopics", description = "A comma delimited list of topics that will disable this element", type = "String", defaultValue = "")
    public void setDisableTopics(String disableTopics) {
		inputDelegate.setDisableTopics(disableTopics);
	}	

	// Handled by AbstracContainer 
	//public void setElementIds(String elementIds)

	// Handled by AbstracContainer 
	//public void setFormIds(String formIds)
}
