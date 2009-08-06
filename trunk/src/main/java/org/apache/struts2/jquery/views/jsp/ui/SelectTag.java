package org.apache.struts2.jquery.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.Input;
import org.apache.struts2.jquery.components.Select;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class SelectTag extends AbstractContainerTag implements Input {
	
	private static final long serialVersionUID = -4839207037830558353L;
	
	private AbstractInputTag inputDelegate = new AbstractInputTag(){};

    private String emptyOption;
	private String headerKey;
    private String headerValue;
    protected String list;
    protected String listKey;
    protected String listValue;
    
	public SelectTag() {
		super();
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		Component bean = new Select(stack, req, res);
		return bean;
	}

	@Override
    protected void populateParams() {
		
		inputDelegate.setComponent(this.component);
		inputDelegate.populateParams();
		
		super.populateParams();
		
        Select select = (Select) component;
        select.setEmptyOption(emptyOption);
        select.setHeaderKey(headerKey);
        select.setHeaderValue(headerValue);
        select.setList(list);
        select.setListKey(listKey);
        select.setListValue(listValue);
    }
	

    public void setEmptyOption(String emptyOption) {
		this.emptyOption = emptyOption;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

    public void setList(String list) {
        this.list = list;
    }

    public void setListKey(String listKey) {
        this.listKey = listKey;
    }

    public void setListValue(String listValue) {
        this.listValue = listValue;
    }
	
	//need to apply both to container base class and input delegate class
	public void setReloadTopics(String reloadTopics) {
		super.setReloadTopics(reloadTopics);
		inputDelegate.setReloadTopics(reloadTopics);
	}

	//need to apply both to container base class and input delegate class
	public void setSrc(String src) {
		super.setSrc(src);
		inputDelegate.setSrc(src);
	}
	
	public void setBlurTopics(String blurTopics) {
		inputDelegate.setBlurTopics(blurTopics);
	}

	public void setFocusTopics(String focusTopics) {
		inputDelegate.setFocusTopics(focusTopics);
	}

	public void setOnBlurTopics(String onBlurTopics) {
		inputDelegate.setOnBlurTopics(onBlurTopics);
	}

	public void setOnChangeTopics(String onChangeTopics) {
		inputDelegate.setOnChangeTopics(onChangeTopics);
	}

	public void setOnFocusTopics(String onFocusTopics) {
		inputDelegate.setOnFocusTopics(onFocusTopics);
	}

	public void setDisableTopics(String disableTopics) {
		inputDelegate.setDisableTopics(disableTopics);
	}

	public void setEnableTopics(String enableTopics) {
		inputDelegate.setEnableTopics(enableTopics);
	}

	public void setFormIds(String formIds) {
		super.setFormIds(formIds);
	}

	public void setElementIds(String elementIds) {
		super.setElementIds(elementIds);
	}
}

