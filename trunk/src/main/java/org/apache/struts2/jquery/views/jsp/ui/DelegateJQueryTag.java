package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.JQueryBean;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class DelegateJQueryTag extends ComponentTagSupport implements JQueryTag {

	private static final long serialVersionUID = -2470372343852770832L;
	
    protected String alwaysPublishTopics;		//topics that are published before load, after load and on error
    protected String beforePublishTopics;		//topics that are published before a load
    protected String afterPublishTopics;		//topics that are published before after load
    protected String errorPublishTopics;		//topics that are published on a load error
    protected String indicatorId;				//Id of element that will be displayed during load and hidden afterwards

    //protected String preload;
    //protected String executeScripts;
    //protected String handler;
    //protected String formFilter;
    //protected String showErrorTransportText;
    //protected String highlightColor;
    //protected String highlightDuration;
    //protected String separateScripts;
    //protected String transport;
    //protected String parseContent;
    //protected String updateFreq;
    //protected String autoStart;
    //protected String delay;
    //protected String startTimerListenTopics;
    //protected String stopTimerListenTopics;
    //protected String refreshOnShow;
    //protected String closable;

    protected void populateParams() {
    	        
    	JQueryBean jQuery = (JQueryBean) component;
    	jQuery.setAlwaysPublishTopics(alwaysPublishTopics);
        jQuery.setBeforePublishTopics(beforePublishTopics);
        jQuery.setAfterPublishTopics(afterPublishTopics);
        jQuery.setErrorPublishTopics(errorPublishTopics);
        jQuery.setIndicatorId(indicatorId);
    }

    protected void setComponent(Component component){
    	this.component = component;
    }
    
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return component;
	}
	
	/* (non-Javadoc)
	 * @see com.apposit.ui.tag.struts.jquery.JQueryTag#setAlwaysPublishTopics(java.lang.String)
	 */
	public void setAlwaysPublishTopics(String alwaysPublishTopics) {
		this.alwaysPublishTopics = alwaysPublishTopics;
	}

	/* (non-Javadoc)
	 * @see com.apposit.ui.tag.struts.jquery.JQueryTag#setBeforePublishTopics(java.lang.String)
	 */
	public void setBeforePublishTopics(String beforePublishTopics) {
		this.beforePublishTopics = beforePublishTopics;
	}

	/* (non-Javadoc)
	 * @see com.apposit.ui.tag.struts.jquery.JQueryTag#setAfterPublishTopics(java.lang.String)
	 */
	public void setAfterPublishTopics(String afterPublishTopics) {
		this.afterPublishTopics = afterPublishTopics;
	}
	
	/* (non-Javadoc)
	 * @see com.apposit.ui.tag.struts.jquery.JQueryTag#setErrorPublishTopics(java.lang.String)
	 */
	public void setErrorPublishTopics(String errorPublishTopics) {
		this.errorPublishTopics = errorPublishTopics;
	}
	
	/* (non-Javadoc)
	 * @see com.apposit.ui.tag.struts.jquery.JQueryTag#setIndicatorId(java.lang.String)
	 */
	public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}
}
