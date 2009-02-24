package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="div", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.DivTag", description="Render HTML div providing content from remote call via AJAX")
public class Div extends org.apache.struts2.components.Div implements JQueryBean {

    public static final String TEMPLATE = "div";
    public static final String TEMPLATE_CLOSE = "div-close";
    
	protected String src;						//The url from which to load the source
	protected String reloadTopics;				//topics that will cause div to reload
    protected String loadingText;				//Text to be displayed during load (will be shown if any provided)
    protected String errorText;					//text to be displayed on load error

	private DelegateJQueryBean delegate;
	
    public Div(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
        delegate = new DelegateJQueryBean(stack);
        delegate.setStack(this.stack);
        delegate.setParameters(this.parameters);
    }

    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }
    
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();

        if (src != null)
            addParameter("src", findString(src));
        if (reloadTopics != null)
            addParameter("reloadTopics", findString(reloadTopics));
        if (loadingText != null)
            addParameter("loadingText", findString(loadingText));
        if (errorText != null)
            addParameter("errorText", findString(errorText));
    }
    
    public String getTheme() {
        return delegate.getTheme();
    }
    
    @StrutsTagAttribute(name="src", description="The url from which to load the source", type="String", defaultValue="")
    public void setSrc(String src) {
		this.src = src;
	}

	@StrutsTagAttribute(name="reloadTopics", description="Topics that will cause div to reload", type="String", defaultValue="")
    public void setReloadTopics(String reloadTopics) {
		this.reloadTopics = reloadTopics;
	}

	@StrutsTagAttribute(name="loadingText", description="Text to be displayed during load (will be shown if any provided)", type="String", defaultValue="")
    public void setLoadingText(String loadingText) {
		this.loadingText = loadingText;
	}

	@StrutsTagAttribute(name="errorText", description="text to be displayed on load error", type="String", defaultValue="false")
    public void setErrorText(String errorText) {
		this.errorText = errorText;
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
