package org.apache.struts2.jquery.components;

import org.apache.struts2.views.annotations.StrutsTagAttribute;

public interface Container extends Base {

	/**  TOPIC LISTENERS **/
	@StrutsTagAttribute(name="reloadTopics", description="A comma delimited list of topics that will cause this element to reload", type="String", defaultValue="")
	public void setReloadTopics(String reloadTopics);
	
	/**  TOPIC PUBLISHERS **/
	@StrutsTagAttribute(name="onAlwaysTopics", description = "A comma delimited list of topics that are always published (before load, after load, on error and on success)", type="String", defaultValue="")
	public void setOnAlwaysTopics(String onAlwaysTopics);

	@StrutsTagAttribute(name="onBeforeTopics", description = "Topics that are published before a load", type="String", defaultValue="")
	public void setOnBeforeTopics(String onBeforeTopics);

	@StrutsTagAttribute(name="onCompleteTopics", description = "Topics that are published before after load is completed", type="String", defaultValue="")
	public void setOnCompleteTopics(String onCompleteTopics);

	@StrutsTagAttribute(name="onErrorTopics", description = "Topics that are published on a load error", type="String", defaultValue="")
	public void setOnErrorTopics(String onErrorTopics);

	@StrutsTagAttribute(name="onSuccessTopics", description = "Topics that are published after a succesful load", type="String", defaultValue="")
	public void setOnSuccessTopics(String onSuccessTopics);
	
	/**  SPECIAL **/
	@StrutsTagAttribute(name="src", description="The url from which to load the container contents", type="String", defaultValue="")
	public void setSrc(String src);

	@StrutsTagAttribute(name="loadingText", description="The text to be displayed during load (will be shown if any provided)", type="String", defaultValue="")
    public void setLoadingText(String loadingText);

	@StrutsTagAttribute(name="errorText", description="The text to be displayed on load error", type="String", defaultValue="false")
    public void setErrorText(String errorText);
	
	@StrutsTagAttribute(name="indicatorId", description="Id of element that will be displayed during execution of this element's action and hidden afterwards", type="String", defaultValue="")
    public void setIndicatorId(String indicatorId);

}