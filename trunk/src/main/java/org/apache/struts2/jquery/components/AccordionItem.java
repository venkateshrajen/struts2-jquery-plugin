package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="accordionItem", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.AccordionItemTag", 
		description="Render a menu item within an accordion menu",
		allowDynamicAttributes=true)
public class AccordionItem extends AbstractContainer {

    public static final String TEMPLATE = "accordionitem";
    public static final String TEMPLATE_CLOSE = "accordionitem-close";

    private String isActive;		//set to true to make this the initial active menu item (only one item should have this)
    private String cache;			//If set to true and the contents are remotely loaded ('src' provided), contents will only be loaded once. Otherwise contents will be reloaded each time the item is expanded (default=false)
    private String lazyLoad;		//If set to true and the contents are remotely loaded ('src' provided), contents will not be loaded at first until the item is expanded (default=false). 
    private String headerClass;		//The class to add to the header element
   
    public AccordionItem(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    @Override
    public void evaluateExtraParams() {
        
        super.evaluateExtraParams();

        if (isActive != null)
            addParameter("isActive", findValue(isActive, Boolean.class));
        if (title != null)
            addParameter("title", findString(title));
        if(headerClass != null)
        	addParameter("headerClass", findString(headerClass));
        if (cache != null)
            addParameter("cache", findValue(cache, Boolean.class));
        if (lazyLoad != null)
            addParameter("lazyLoad", findValue(lazyLoad, Boolean.class));
    }

    @Override
    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }
    
    @StrutsTagAttribute(name="isActive", description="A boolean indicating whether this tab is the initial active menu item (only one item should have this set to true)")
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
    @StrutsTagAttribute(name="lazyLoad", type="Boolean", defaultValue="false", 
    		description = "If set to true and the contents are remotely loaded ('src' provided), contents will only be loaded once. " +
    				"Otherwise contents will be reloaded each time the item is expanded")
	public void setLazyLoad(String lazyLoad) {
		this.lazyLoad = lazyLoad;
	}
	
	@StrutsTagAttribute(name="cache", type="Boolean", defaultValue="false", description = "If set to true and the contents are remotely " +
			"loaded ('src' provided), contents will not be loaded at first until the item is expanded")
	public void setCache(String cache) {
		this.cache = cache;
	}
    
	@StrutsTagAttribute(defaultValue="false", description = "The class to add to the header element")
	public void setHeaderClass(String headerClass) {
		this.headerClass = headerClass;
	}
	
	public String getLazyLoad() {
		return lazyLoad;
	}
	
	public String getCache() {
		return cache;
	}
    
    public String getIsActive() {
		return isActive;
	}
    
	public String getShowTopics() {
		return this.showTopics;
	}

	public String getHideTopics() {
		return this.hideTopics;
	}

	public String getRemoveTopics() {
		return this.removeTopics;
	}

	public String getReloadTopics() {
		return this.reloadTopics;
	}

	public String getOnAlwaysTopics() {
		return onAlwaysTopics;
	}

	public String getOnBeforeTopics() {
		return onBeforeTopics;
	}

	public String getOnCompleteTopics() {
		return onCompleteTopics;
	}

	public String getOnErrorTopics() {
		return onErrorTopics;
	}

	public String getOnSuccessTopics() {
		return onSuccessTopics;
	}

	public String getSrc() {
		return src;
	}

	public String getLoadingText() {
		return loadingText;
	}

	public String getErrorText() {
		return errorText;
	}

	public String getIndicatorId() {
		return indicatorId;
	}

	public String getFormIds() {
		return formIds;
	}

	public String getElementIds() {
		return elementIds;
	}
}
