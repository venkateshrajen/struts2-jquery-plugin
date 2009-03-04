package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Div;

import com.opensymphony.xwork2.util.ValueStack;

public class DivTag extends AbstractContainerTag {
	
	private static final long serialVersionUID = -4839207037830558353L;
	
	public DivTag() {
		super();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Div(stack, req, res);
		return bean;
	}

	@Override
    protected void populateParams() {
        
		super.populateParams();

        //Div div = (Div) component;
    }
}

