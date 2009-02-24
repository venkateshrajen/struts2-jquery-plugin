package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Tab;
import org.apache.struts2.views.jsp.ui.AbstractClosingTag;

import com.opensymphony.xwork2.util.ValueStack;

public class TabTag extends AbstractClosingTag {

	private static final long serialVersionUID = -3841835722558071249L;
	
	protected String href;						//url to load tag contents via AJAX
		
	protected String reloadTopics;				//comma separated list of topics that will cause tag reload

	protected String disableTopics;				//comma separated list of topics that will disable tag

	protected String enableTopics;				//comma separated list of topics that will enable tag
	
	protected String selectTopics;				//comma separated list of topics that will select tag
	
	protected String removeTopics;				//comma separated list of topics that will remove tag
	
	public TabTag() {
		super();
	}
	    
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Tab(stack, req, res);
    }


    protected void populateParams() {
        super.populateParams();
        
        Tab tab = (Tab) component;
        tab.setHref(href);
        tab.setReloadTopics(reloadTopics);
        tab.setDisableTopics(disableTopics);
        tab.setEnableTopics(enableTopics);
        tab.setSelectTopics(selectTopics);
        tab.setRemoveTopics(removeTopics);
    }
    
	@Override
	public int doEndTag() throws JspException {
		
		Tag tabbedPaneTag = this.getParent();
		
		if(!(tabbedPaneTag instanceof TabbedPaneTag)) {
			
			throw new JspTagException("Tab tag must be inside TabbedPane Tag");
		}
		
		((TabbedPaneTag)tabbedPaneTag).addTab((Tab)component);
		
		return super.doEndTag();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getReloadTopics() {
		return reloadTopics;
	}

	public void setReloadTopics(String reloadTopics) {
		this.reloadTopics = reloadTopics;
	}

	public String getDisableTopics() {
		return disableTopics;
	}

	public void setDisableTopics(String disableTopics) {
		this.disableTopics = disableTopics;
	}

	public String getEnableTopics() {
		return enableTopics;
	}

	public void setEnableTopics(String enableTopics) {
		this.enableTopics = enableTopics;
	}

	public String getSelectTopics() {
		return selectTopics;
	}

	public void setSelectTopics(String selectTopics) {
		this.selectTopics = selectTopics;
	}

	public String getRemoveTopics() {
		return removeTopics;
	}

	public void setRemoveTopics(String removeTopics) {
		this.removeTopics = removeTopics;
	}
}
