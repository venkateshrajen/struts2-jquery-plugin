package org.apache.struts2.jquery.components;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

public abstract class AbstractContainer extends AbstractBase implements Container {

	protected String reloadTopics;			//topics that will cause div to reload
	
	protected String onAlwaysTopics;		//topics that are published before load, after load and on error
    protected String onBeforeTopics;		//topics that are published before a load
    protected String onCompleteTopics;			//topics that are published before after load
    protected String onErrorTopics;			//topics that are published on a load error
    protected String onSuccessTopics;		//topics that are published on a load error
    
	protected String src;					//The url from which to load the source
    protected String loadingText;			//Text to be displayed during load (will be shown if any provided)
    protected String errorText;				//text to be displayed on load error
    protected String indicatorId;			//Id of element that will be displayed during execution of this element's action and hidden afterwards
		
	public AbstractContainer(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		
		super(stack, request, response);
	}

    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();
            	
        if (reloadTopics != null)
            addParameter("reloadTopics", findString(reloadTopics));
        if (onBeforeTopics != null)
            addParameter("onBeforeTopics", findString(onBeforeTopics));
        if (onCompleteTopics != null)
            addParameter("onAfterTonCompleteTopicsopics", findString(onCompleteTopics));
        if (onErrorTopics != null)
            addParameter("onErrorTopics", findString(onErrorTopics));
        if (onSuccessTopics != null)
            addParameter("onSuccessTopics", findString(onSuccessTopics));
        if (src != null)
            addParameter("src", findString(src));
        if (loadingText != null)
            addParameter("loadingText", findString(loadingText));
        if (errorText != null)
            addParameter("errorText", findString(errorText));
        if (indicatorId != null)
            addParameter("indicatorId", findString(indicatorId));
    }

    protected void setStack(ValueStack stack){
    	this.stack = stack;
    }

    @SuppressWarnings("unchecked")
	protected void setParameters(Map parameters){
    	this.parameters = parameters;
    }

    @StrutsTagAttribute(name="errorText", description="The text to be displayed on load error", type="String", defaultValue="false")
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

    @StrutsTagAttribute(name="loadingText", description="The text to be displayed during load (will be shown if any provided)", type="String", defaultValue="")
	public void setLoadingText(String loadingText) {
		this.loadingText = loadingText;
	}

    @StrutsTagAttribute(name="onCompleteTopics", description = "Topics that are published before after load is completed", type="String", defaultValue="")
	public void setOnCompleteTopics(String onCompleteTopics) {
		this.onCompleteTopics = onCompleteTopics;
	}

    @StrutsTagAttribute(name="onAlwaysTopics", description = "A comma delimited list of topics that are always published (before load, after load, on error and on success)", type="String", defaultValue="")
	public void setOnAlwaysTopics(String onAlwaysTopics) {
		this.onAlwaysTopics = onAlwaysTopics;
	}

    @StrutsTagAttribute(name="onBeforeTopics", description = "Topics that are published before a load", type="String", defaultValue="")
	public void setOnBeforeTopics(String onBeforeTopics) {
		this.onBeforeTopics = onBeforeTopics;
	}

    @StrutsTagAttribute(name="onErrorTopics", description = "Topics that are published on a load error", type="String", defaultValue="")
	public void setOnErrorTopics(String onErrorTopics) {
		this.onErrorTopics = onErrorTopics;
	}

    @StrutsTagAttribute(name="onSuccessTopics", description = "Topics that are published after a succesful load", type="String", defaultValue="")
	public void setOnSuccessTopics(String onSuccessTopics) {
		this.onSuccessTopics = onSuccessTopics;
	}

	@StrutsTagAttribute(name="reloadTopics", description="A comma delimited list of topics that will cause this element to reload", type="String", defaultValue="")
	public void setReloadTopics(String reloadTopics) {
		this.reloadTopics = reloadTopics;
	}

	@StrutsTagAttribute(name="src", description="The url from which to load the container contents", type="String", defaultValue="")
	public void setSrc(String src) {
		this.src = src;
	}

	@StrutsTagAttribute(name="indicatorId", description="Id of element that will be displayed during execution of this element's action and hidden afterwards", type="String", defaultValue="")
	public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}
}