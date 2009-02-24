package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="a", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.AnchorTag", description="Render HTML anchor allowing loading result into ajax target")
public class Anchor extends org.apache.struts2.components.Anchor implements JQueryBean {

	private static final long serialVersionUID = -1728246656600445144L;

	private String targets;						//A comma separated list of ajax-enable ids of elements to realod with the contents of this request
	private String formId;						//Form id for which to serialize all fields during submission for load

	private DelegateJQueryBean delegate;
	
    public Anchor(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
        delegate = new DelegateJQueryBean(stack);
        delegate.setStack(this.stack);
        delegate.setParameters(this.parameters);
    }
    
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();

        if (targets != null)
            addParameter("targets", findString(targets));
        if (formId != null)
            addParameter("formId", findString(formId));
        
        delegate.evaluateExtraParams();
    }

	@StrutsTagAttribute(name="targets", description="A comma separated list of ajax-enable ids of elements to realod with the contents of this request", type="String", defaultValue="", required=true)
	public void setTargets(String targets) {
		this.targets = targets;
	}

	@StrutsTagAttribute(name="formId", description="Form id for which to serialize all fields during submission for load", type="String", defaultValue="")
    public void setFormId(String formId) {
		this.formId = formId;
	}
    
    public String getTheme() {
        return delegate.getTheme();
    }
    
	@StrutsTagAttribute(name="alwaysPublishTopics", description="Topics that are published before load, after load and on error", type="String", defaultValue="")
    public void setAlwaysPublishTopics(String alwaysPublishTopics) {
    	delegate.setAlwaysPublishTopics(alwaysPublishTopics);
	}

	@StrutsTagAttribute(name="beforePublishTopics", description="Topics that are published before a load", type="String", defaultValue="")
    public void setBeforePublishTopics(String beforePublishTopics) {
    	delegate.setBeforePublishTopics(beforePublishTopics);
	}

	@StrutsTagAttribute(name="afterPublishTopics", description="Topics that are published before after load", type="String", defaultValue="")
    public void setAfterPublishTopics(String afterPublishTopics) {
    	delegate.setAfterPublishTopics(afterPublishTopics);
	}

	@StrutsTagAttribute(name="errorPublishTopics", description="Topics that are published on a load error", type="String", defaultValue="")
    public void setErrorPublishTopics(String errorPublishTopics) {
    	delegate.setErrorPublishTopics(errorPublishTopics);
	}

	@StrutsTagAttribute(name="indicatorId", description="Id of element that will be displayed during load and hidden afterwards", type="String", defaultValue="")
    public void setIndicatorId(String indicatorId) {
    	delegate.setIndicatorId(indicatorId);
	}
}
