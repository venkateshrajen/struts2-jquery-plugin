package org.apache.struts2.jquery.components;

import org.apache.struts2.views.annotations.StrutsTagAttribute;

public interface Base {

	/**  TOPIC LISTENERS **/
	
	@StrutsTagAttribute(name="hideTopics", description = "A comma delimited list of topics that will hide (display: none) this element", type = "String", defaultValue = "")
	public void setHideTopics(String hideTopics);

	@StrutsTagAttribute(name="showTopics", description = "A comma delimited list of topics that will show (display: block) this element", type = "String", defaultValue = "")
	public void setShowTopics(String showTopics);

	@StrutsTagAttribute(name="removeTopics", description = "A comma delimited list of topics that will remove this element from the dom", type = "String", defaultValue = "")
	public void setRemoveTopics(String removeTopics);

	/**  TOPIC PUBLISHERS **/
	
	/**  SPECIAL **/

	@StrutsTagAttribute(name="disabled", description = "If this element is disabled", type = "Boolean", defaultValue = "false")
	public void setDisabled(String disabled);

	@StrutsTagAttribute(name="hideDisabled", description = "Fi set to true, when this element is disabled it will not be shwon in the DOM ('display' will be set to 'none'). Defaults to false", type = "Boolean", defaultValue = "false")
	public void setHideDisabled(String hideDisabled);
}