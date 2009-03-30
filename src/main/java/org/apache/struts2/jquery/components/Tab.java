package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="tab", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.TabTag", 
		description="Render a tab within a tabbed panel for which content can be dynamically loaded via ajax",
		allowDynamicAttributes=true)
public class Tab extends AbstractInput {

    public static final String TEMPLATE = "tab";
    public static final String TEMPLATE_CLOSE = "tab-close";

    private String isSelected;					//set to true to make this the default selected tab (only one tab shoudl have this)
    private String isDisabled;					//set to true to make this tab disabled

    public Tab(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    @Override
    public void evaluateExtraParams() {
        
        super.evaluateExtraParams();

        if (isSelected != null)
            addParameter("isSelected", findValue(isSelected, Boolean.class));
        if (isDisabled != null)
            addParameter("isDisabled", findValue(isDisabled, Boolean.class));
    }

    @Override
    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }
    
    @StrutsTagAttribute(name="isSelected", description="A boolean indicating whether this tab is selected at start (only one tab shoudl have the property set to true)")
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

    @StrutsTagAttribute(name="isDisabled", description="A boolean indicating whether this tab is disabled")
	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getDisableTopics() {
		return this.disableTopics;
	}

	public String getEnableTopics() {
		return this.enableTopics;
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

	public String getFocusTopics() {
		return this.focusTopics;
	}

	public String getOnBlurTopics() {
		return this.onBlurTopics;
	}

	public String getOnFocusTopics() {
		return this.onFocusTopics;
	}

	public String getOnChangeTopics() {
		return this.onChangeTopics;
	}

	public String getIsSelected() {
		return this.isSelected;
	}

	public String getIsDisabled() {
		return this.isDisabled;
	}
}
