package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Tab;
import org.apache.struts2.jquery.components.TabbedPane;
import org.apache.struts2.views.jsp.ui.AbstractClosingTag;

import com.opensymphony.xwork2.util.ValueStack;

public class TabbedPaneTag extends AbstractClosingTag {

	private static final long serialVersionUID = -3809304151441133723L;
	
	//private DelegateJQueryTag delegate;

    protected Integer selected;				//The zero-based index of the tab to be selected initially
	protected Boolean iscache;				//Whether or not to cache remote tabs content, e.g. load only once or with every click.
    protected String disabled;				//Comma delimited list of zero-based indexes of tabs 
    
	public TabbedPaneTag() {
		super();
		//delegate = new DelegateJQueryTag();
	}
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new TabbedPane(stack, req, res);
		//delegate.setComponent(bean);
		return bean;
	}
	
	public void addTab(Tab tab){
		if(this.component != null) {
			((TabbedPane)component).addTab(tab);
		}
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();
        //delegate.populateParams();

        TabbedPane tabbedPane = (TabbedPane) component;
        tabbedPane.setSelected(selected);
        tabbedPane.setIscache(iscache);
        tabbedPane.setDisabled(disabled);
        
    }

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public void setIscache(Boolean iscache) {
		this.iscache = iscache;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
	/*
	public void setAlwaysPublishTopics(String alwaysPublishTopics) {
		delegate.setAlwaysPublishTopics(alwaysPublishTopics);
	}

	public void setBeforePublishTopics(String beforePublishTopics) {
		delegate.setBeforePublishTopics(beforePublishTopics);
	}

	public void setAfterPublishTopics(String afterPublishTopics) {
		delegate.setAfterPublishTopics(afterPublishTopics);
	}
	
	public void setErrorPublishTopics(String errorPublishTopics) {
		delegate.setErrorPublishTopics(errorPublishTopics);
	}
	
	public void setIndicatorId(String indicatorId) {
		delegate.setIndicatorId(indicatorId);
	}
	*/
}
