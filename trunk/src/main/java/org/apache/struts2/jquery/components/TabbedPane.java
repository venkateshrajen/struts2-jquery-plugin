package org.apache.struts2.jquery.components;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="tabbedPane", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.TabbedPaneTag", description="Renders a tabbed pane in which the tabs contents are loaded via remote AJAX")
public class TabbedPane extends AbstractBase implements Base {

    public static final String TEMPLATE = "tabbedpane";
    public static final String TEMPLATE_CLOSE = "tabbedpane-close";

	protected Boolean isCache;				//Whether or not to cache remote tabs content, e.g. load only once or with every click.
	protected Integer selected;				//The zero-based index of the tab that is selected when the pane first loads.
    
    protected List<Tab> tabs;
	
    public TabbedPane(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        
    	super(stack, request, response);
        
        tabs = new ArrayList<Tab>();
    }

    @Override
    public void evaluateExtraParams() {
        
        super.evaluateExtraParams();

        if (isCache != null)
            addParameter("isCache", isCache);
        if (selected != null)
            addParameter("selected", selected);
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
    	
    @StrutsTagAttribute(name="isCache", description="A boolean indicating whether or not to cache remote tabs content, e.g. load only once or with every click.")
	public void setIsCache(Boolean isCache) {
		this.isCache = isCache;
	}

    @StrutsTagAttribute(name="selected", description="The zero-based index of the tab that is selected when the pane first loads")
	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public List<Tab> getTabs() {
		return tabs;
	}
    
}
