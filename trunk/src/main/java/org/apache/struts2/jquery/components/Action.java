package org.apache.struts2.jquery.components;

import org.apache.struts2.views.annotations.StrutsTagAttribute;

public interface Action extends Interactive {

	/**  TOPIC LISTENERS **/
	
	
	/**  TOPIC PUBLISHERS **/
	@StrutsTagAttribute(name="onClickTopics", description = "A comma delimited list of topics that published when the element is clicked", type="String", defaultValue="")
	public void setOnClickTopics(String onClickTopics);
	
	/**  SPECIAL **/

	@StrutsTagAttribute(name="href", description="The url to be use when this element is clicked", type="String", defaultValue="", required=true)
	public void setHref(String href);

	@StrutsTagAttribute(name="targets", description="A comma separated list of ids of container elements to load with the contents from the result of this request", type="String", defaultValue="", required=true)
	public void setTargets(String targets);

	@StrutsTagAttribute(name="formIds", description="Comma delimited list of form ids for which to serialize all fields during submission when this element is clicked (if multiple forms have overlapping element names, it is indeterminate which will be used)", type="String", defaultValue="")
    public void setFormIds(String formIds);

	@StrutsTagAttribute(name="validate", description="Whether to execute validation on this elements of the form(s) provided in the formId attribute (valid values are 'true', 'false', and 'only'). Selecting 'only' will noly validate the form fiellds and not execute the result of this action implied by the href url", type="String", defaultValue="false")
    public void setValidate(String validate);

}