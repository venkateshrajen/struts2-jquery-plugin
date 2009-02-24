package org.apache.struts2.jquery.components;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ClosingUIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="tabbedPane", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.TabbedPaneTag", description="Renders a tabbed pane in which the tabs contents are loaded via remote AJAX")
public class TabbedPane extends ClosingUIBean { //implements JQueryBean {

    public static final String TEMPLATE = "tabbedpane";
    public static final String TEMPLATE_CLOSE = "tabbedpane-close";

    protected Integer selected;				//The zero-based index of the tab to be selected initially
	protected Boolean iscache;				//Whether or not to cache remote tabs content, e.g. load only once or with every click.
    protected String disabled;				//Comma delimited list of zero-based indexes of tabs 
    
    protected List<Tab> tabs;

	//private DelegateJQueryBean delegate;
	
    public TabbedPane(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        
    	super(stack, request, response);
        
    	/*
        delegate = new DelegateJQueryBean(stack);
        delegate.setStack(this.stack);
        delegate.setParameters(this.parameters);
        */
        
        tabs = new ArrayList<Tab>();
    }

    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();

        if (selected != null)
            addParameter("selected", selected);
        if (iscache != null)
            addParameter("iscache", iscache);
        if (disabled != null)
            addParameter("disabled", findString(disabled));
    }
    
    public void addTab(Tab tab){
    	tabs.add(tab);
    }
    
	@Override
	public String getDefaultOpenTemplate() {
        return TEMPLATE;
	}

	@Override
	protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
	}
    
    public String getTheme() {
        //return delegate.getTheme();
        return JQueryBean.JQUERY_THEME;

    }
	
    @StrutsTagAttribute(name="selected", description="The zero-based index of the tab that is initially selected")
	public void setSelected(Integer selected) {
		this.selected = selected;
	}

    @StrutsTagAttribute(name="selected", description="A boolean indicating whether or not to cache remote tabs content, e.g. load only once or with every click.")
	public void setIscache(Boolean iscache) {
		this.iscache = iscache;
	}

    @StrutsTagAttribute(name="selected", description="A comma delimited list of zero-based indexes of tabs that are initially disabled")
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public List<Tab> getTabs() {
		return tabs;
	}
    
	/*
    @StrutsTagAttribute(name="alwaysPublishTopics", description="Topics that are published before load, after load and on error", type="String", defaultValue="")
    public void setAlwaysPublishTopics(String alwaysPublishTopics) {
    	delegate.setAlwaysPublishTopics(alwaysPublishTopics);
	}

	@StrutsTagAttribute(name="beforePublishTopics", description="Topics that are published before a load", type="String", defaultValue="")
    public void setBeforePublishTopics(String beforePublishTopics) {
    	delegate.setBeforePublishTopics(beforePublishTopics);
	}

	@StrutsTagAttribute(name="afterPublishTopics", description="Topics that are published before after load", type="String", defaultValue="")
    public void setAfterPublishTopics(String afterPublishTopics) {
    	delegate.setAfterPublishTopics(afterPublishTopics);
	}

	@StrutsTagAttribute(name="errorPublishTopics", description="Topics that are published on a load error", type="String", defaultValue="")
    public void setErrorPublishTopics(String errorPublishTopics) {
    	delegate.setErrorPublishTopics(errorPublishTopics);
	}

	@StrutsTagAttribute(name="indicatorId", description="Id of element that will be displayed during load and hidden afterwards", type="String", defaultValue="")
    public void setIndicatorId(String indicatorId) {
    	delegate.setIndicatorId(indicatorId);
	}
	*/
}
