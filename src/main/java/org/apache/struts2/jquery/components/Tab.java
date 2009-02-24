package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ClosingUIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="tab", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.TabTag", description="Render a tab within a tabbed panel for which content can be dynamically loaded via ajax")
public class Tab extends ClosingUIBean {

    public static final String OPEN_TEMPLATE = "tab";
    public static final String TEMPLATE = "tab-close";

	private String href;						//url to load tag contents via AJAX
	private String reloadTopics;				//comma separated list of topics that will cause tag reload
	private String disableTopics;				//comma separated list of topics that will disable tag
	private String enableTopics;				//comma separated list of topics that will enable tag
	private String selectTopics;				//comma separated list of topics that will select tag
	private String removeTopics;				//comma separated list of topics that will remove tag
	
    public Tab(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }
    
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();

        if (href != null)
            addParameter("href", findString(href));
        if (reloadTopics != null)
            addParameter("reloadTopics", findString(reloadTopics));
        if (disableTopics != null)
            addParameter("disableTopics", findString(disableTopics));
        if (enableTopics != null)
            addParameter("enableTopics", findString(enableTopics));
        if (selectTopics != null)
            addParameter("selectTopics", findString(selectTopics));
        if (removeTopics != null)
            addParameter("removeTopics", findString(removeTopics));
    }

    public String getDefaultOpenTemplate() {
        return OPEN_TEMPLATE;
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    public String getTheme() {
        return JQueryBean.JQUERY_THEME;
    }

    @StrutsTagAttribute(name="href", description="The remote url from which the cointents of this tabbed pane will be loaded")
	public void setHref(String href) {
		this.href = href;
	}

    @StrutsTagAttribute(name="reloadTopics", description="A comma-delimited list of topics which will reload this tab")
	public void setReloadTopics(String reloadTopics) {
		this.reloadTopics = reloadTopics;
	}

    @StrutsTagAttribute(name="disableTopics", description="A comma-delimited list of topics which will  disable this tab")
	public void setDisableTopics(String disableTopics) {
		this.disableTopics = disableTopics;
	}

    @StrutsTagAttribute(name="enableTopics", description="A comma-delimited list of topics which will enable this tab")
	public void setEnableTopics(String enableTopics) {
		this.enableTopics = enableTopics;
	}

    @StrutsTagAttribute(name="selectTopics", description="A comma-delimited list of topics which will select this tab")
	public void setSelectTopics(String selectTopics) {
		this.selectTopics = selectTopics;
	}

    @StrutsTagAttribute(name="removeTopics", description="A comma-delimited list of topics which will remove this tab")
	public void setRemoveTopics(String removeTopics) {
		this.removeTopics = removeTopics;
	}

	public String getHref() {
		return href;
	}

	public String getReloadTopics() {
		return reloadTopics;
	}

	public String getDisableTopics() {
		return disableTopics;
	}

	public String getEnableTopics() {
		return enableTopics;
	}

	public String getSelectTopics() {
		return selectTopics;
	}

	public String getRemoveTopics() {
		return removeTopics;
	}
    
}
