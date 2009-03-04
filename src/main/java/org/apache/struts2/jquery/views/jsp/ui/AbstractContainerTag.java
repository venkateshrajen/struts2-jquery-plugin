package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Container;

import com.opensymphony.xwork2.util.ValueStack;

public class AbstractContainerTag extends AbstractBaseTag implements Container {

	private static final long serialVersionUID = -2470372343852770832L;

	protected String reloadTopics;			//topics that will cause div to reload
	
	protected String onAlwaysTopics;		//topics that are published before load, after load and on error
    protected String onBeforeTopics;		//topics that are published before a load
    protected String onCompleteTopics;			//topics that are published before after load is completed
    protected String onErrorTopics;			//topics that are published on a load error
    protected String onSuccessTopics;		//topics that are published on a load error
    
	protected String src;					//The url from which to load the source
    protected String loadingText;			//Text to be displayed during load (will be shown if any provided)
    protected String errorText;				//text to be displayed on load error
    protected String indicatorId;			//Id of element that will be displayed during execution of this element's action and hidden afterwards
	
	public AbstractContainerTag() {
		super();
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();

        Container container = (Container) component;
        container.setReloadTopics(reloadTopics);
        container.setOnAlwaysTopics(onAlwaysTopics);
        container.setOnBeforeTopics(onBeforeTopics);
        container.setOnCompleteTopics(onCompleteTopics);
        container.setOnErrorTopics(onErrorTopics);
        container.setOnSuccessTopics(onSuccessTopics);
        container.setSrc(src);
        container.setLoadingText(loadingText);
        container.setErrorText(errorText);
        container.setIndicatorId(indicatorId);
        
    }
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return component;
	}

	public void setReloadTopics(String reloadTopics) {
		this.reloadTopics = reloadTopics;
	}

	public void setOnAlwaysTopics(String onAlwaysTopics) {
		this.onAlwaysTopics = onAlwaysTopics;
	}

	public void setOnBeforeTopics(String onBeforeTopics) {
		this.onBeforeTopics = onBeforeTopics;
	}

	public void setOnCompleteTopics(String onCompleteTopics) {
		this.onCompleteTopics = onCompleteTopics;
	}

	public void setOnErrorTopics(String onErrorTopics) {
		this.onErrorTopics = onErrorTopics;
	}

	public void setOnSuccessTopics(String onSuccessTopics) {
		this.onSuccessTopics = onSuccessTopics;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public void setLoadingText(String loadingText) {
		this.loadingText = loadingText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}
	
}
