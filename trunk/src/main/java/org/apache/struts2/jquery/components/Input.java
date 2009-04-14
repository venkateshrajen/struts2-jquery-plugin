package org.apache.struts2.jquery.components;

import org.apache.struts2.views.annotations.StrutsTagAttribute;

public interface Input extends Interactive {

	/**  TOPIC LISTENERS **/
	@StrutsTagAttribute(name="reloadTopics", description="A comma delimited list of topics that will cause this element to reload its contents", type="String", defaultValue="")
	public void setReloadTopics(String reloadTopics);
	
	@StrutsTagAttribute(name="focusTopics", description="A comma delimited list of topics that will cause this element to focus", type="String", defaultValue="")
	public void setFocusTopics(String focusTopics);
	
	@StrutsTagAttribute(name="blurTopics", description="A comma delimited list of topics that will cause this element to blur", type="String", defaultValue="")
	public void setBlurTopics(String blurTopics);
	
	
	/**  TOPIC PUBLISHERS **/
	@StrutsTagAttribute(name="onChangeTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnChangeTopics(String onChangeTopics);
	
	@StrutsTagAttribute(name="onFocusTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnFocusTopics(String onFocusTopics);
	
	@StrutsTagAttribute(name="onBlurTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnBlurTopics(String onBlurTopics);
	
	/**  SPECIAL **/
	@StrutsTagAttribute(name="src", description="The url to be use to retrieve this element's contents", type="String", defaultValue="", required=true)
	public void setSrc(String src);

	@StrutsTagAttribute(name="elementIds", description="A comma delimited list of form elements that should be individually serialized and sent with the input's load request", type="String", defaultValue="", required=false)
	public void setElementIds(String elementIds);

	@StrutsTagAttribute(name="formIds", description="A comma delimited list of forms that should be serialized and sent with the input's load request", type="String", defaultValue="", required=false)
	public void setFormIds(String formIds);
}