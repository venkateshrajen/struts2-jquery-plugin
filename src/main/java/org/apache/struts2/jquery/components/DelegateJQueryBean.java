package org.apache.struts2.jquery.components;

import java.util.Map;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

public class DelegateJQueryBean extends Component implements JQueryBean {

    protected String indicatorId;				//Id of element that will be displayed during load and hidden afterwards
    protected String alwaysPublishTopics;		//topics that are published before load, after load and on error
    protected String beforePublishTopics;		//topics that are published before a load
    protected String afterPublishTopics;		//topics that are published before after load
    protected String errorPublishTopics;		//topics that are published on a load error
   
	public DelegateJQueryBean(ValueStack stack) {
		super(stack);
	}
    
    public void evaluateExtraParams() {
    	
        if (alwaysPublishTopics != null)
            addParameter("alwaysPublishTopics", findString(alwaysPublishTopics));
        if (beforePublishTopics != null)
            addParameter("beforePublishTopics", findString(beforePublishTopics));
        if (afterPublishTopics != null)
            addParameter("afterPublishTopics", findString(afterPublishTopics));
        if (errorPublishTopics != null)
            addParameter("errorPublishTopics", findString(errorPublishTopics));
        if (indicatorId != null)
            addParameter("indicatorId", findString(indicatorId));
    }
    
    public String getTheme() {
    	return JQueryBean.JQUERY_THEME;
    }

    protected void setStack(ValueStack stack){
    	this.stack = stack;
    }

    @SuppressWarnings("unchecked")
	protected void setParameters(Map parameters){
    	this.parameters = parameters;
    }
    
	/* (non-Javadoc)
	 * @see com.apposit.ui.tag.struts.jquery.JQueryBean#setAlwaysPublishTopics(java.lang.String)
	 */
	@StrutsTagAttribute(description="Topics that are published before load, after load and on error", type="String", defaultValue="")
    public void setAlwaysPublishTopics(String alwaysPublishTopics) {
		this.alwaysPublishTopics = alwaysPublishTopics;
	}

	/* (non-Javadoc)
	 * @see com.apposit.ui.tag.struts.jquery.JQueryBean#setBeforePublishTopics(java.lang.String)
	 */
	@StrutsTagAttribute(description="Topics that are published before a load", type="String", defaultValue="")
    public void setBeforePublishTopics(String beforePublishTopics) {
		this.beforePublishTopics = beforePublishTopics;
	}

	/* (non-Javadoc)
	 * @see com.apposit.ui.tag.struts.jquery.JQueryBean#setAfterPublishTopics(java.lang.String)
	 */
	@StrutsTagAttribute(description="Topics that are published before after load", type="String", defaultValue="")
    public void setAfterPublishTopics(String afterPublishTopics) {
		this.afterPublishTopics = afterPublishTopics;
	}
    
	/* (non-Javadoc)
	 * @see com.apposit.ui.tag.struts.jquery.JQueryBean#setErrorPublishTopics(java.lang.String)
	 */
	@StrutsTagAttribute(description="Topics that are published on a load error", type="String", defaultValue="")
    public void setErrorPublishTopics(String errorPublishTopics) {
		this.errorPublishTopics = errorPublishTopics;
	}

	/* (non-Javadoc)
	 * @see com.apposit.ui.tag.struts.jquery.JQueryBean#setIndicatorId(java.lang.String)
	 */
	@StrutsTagAttribute(description="Id of element that will be displayed during load and hidden afterwards", type="String", defaultValue="")
    public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}
}
