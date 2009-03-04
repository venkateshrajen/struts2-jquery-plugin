package org.apache.struts2.jquery.components;

import org.apache.struts2.views.annotations.StrutsTagAttribute;

public interface Base {

	public static final String JQUERY_THEME = "jquery";

	/**  TOPIC LISTENERS **/
	
	@StrutsTagAttribute(name="hideTopics", description = "A comma delimited list of topics that will hide (display: none) this element", type = "String", defaultValue = "")
	public void setHideTopics(String hideTopics);

	@StrutsTagAttribute(name="showTopics", description = "A comma delimited list of topics that will show (display: block) this element", type = "String", defaultValue = "")
	public void setShowTopics(String showTopics);

	@StrutsTagAttribute(name="removeTopics", description = "A comma delimited list of topics that will remove this element from the dom", type = "String", defaultValue = "")
	public void setRemoveTopics(String removeTopics);

	/**  TOPIC PUBLISHERS **/
	
	/**  SPECIAL **/
}