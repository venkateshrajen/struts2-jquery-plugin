package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.AccordionItem;

import com.opensymphony.xwork2.util.ValueStack;

public class AccordionItemTag extends AbstractContainerTag {

	private static final long serialVersionUID = 5589579304411513754L;

    private String isActive;		//set to true to make this the initial active menu item (only one item should have this)
    private String cache;			//If set to true and the contents are remotely loaded ('src' provided), contents will only be loaded once. Otherwise contents will be reloaded each time the item is expanded (default=false)
    private String lazyLoad;		//If set to true and the contents are remotely loaded ('src' provided), contents will not be loaded at first until the item is expanded (default=false). 
      
	public AccordionItemTag() {
		super();
	}
	    
    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new AccordionItem(stack, req, res);
		return bean;
    }

	@Override
    protected void populateParams() {
        
		super.populateParams();
		
		AccordionItem accordionItem = (AccordionItem) component;
		accordionItem.setIsActive(isActive);
		accordionItem.setLazyLoad(lazyLoad);
		accordionItem.setCache(cache);
	
	}
	
	@Override
	public int doEndTag() throws JspException {
		
		Tag accordionTag = this.getParent();

		while(accordionTag != null) {

			if((accordionTag instanceof AccordionTag)) {
				
				((AccordionTag)accordionTag).addItem((AccordionItem)component);
			
				return super.doEndTag();
			
			} else {
				
				accordionTag = accordionTag.getParent();
			}
		}
		
		throw new JspTagException("AccordionItem tag must be inside Accordion Tag");
	
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public void setLazyLoad(String lazyLoad) {
		this.lazyLoad = lazyLoad;
	}
	
	public void setCache(String cache) {
		this.cache = cache;
	}
}
