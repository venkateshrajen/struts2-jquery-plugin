package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Anchor;

import com.opensymphony.xwork2.util.ValueStack;

public class AnchorTag extends AbstractActionTag {
	
	private static final long serialVersionUID = -2130063104541861261L;
				
	public AnchorTag() {
		super();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Anchor(stack, req, res);
		return bean;
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();

       //Anchor anchor = (Anchor) component;
    }
}
