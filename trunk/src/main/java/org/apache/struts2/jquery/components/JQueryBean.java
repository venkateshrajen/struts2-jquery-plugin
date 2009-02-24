package org.apache.struts2.jquery.components;

import org.apache.struts2.views.annotations.StrutsTagAttribute;

public interface JQueryBean {

	public static final String JQUERY_THEME = "jquery";
	
	@StrutsTagAttribute(name="alwaysPublishTopics", description = "Topics that are published before load, after load and on error", type = "String", defaultValue = "")
	public void setAlwaysPublishTopics(String alwaysPublishTopics);

	@StrutsTagAttribute(name="beforePublishTopics", description = "Topics that are published before a load", type = "String", defaultValue = "")
	public void setBeforePublishTopics(String beforePublishTopics);

	@StrutsTagAttribute(name="afterPublishTopics", description = "Topics that are published before after load", type = "String", defaultValue = "")
	public void setAfterPublishTopics(String afterPublishTopics);

	@StrutsTagAttribute(name="errorPublishTopics", description = "Topics that are published on a load error", type = "String", defaultValue = "")
	public void setErrorPublishTopics(String errorPublishTopics);

	@StrutsTagAttribute(name="indicatorId", description = "Id of element that will be displayed during load and hidden afterwards", type = "String", defaultValue = "")
	public void setIndicatorId(String indicatorId);

}