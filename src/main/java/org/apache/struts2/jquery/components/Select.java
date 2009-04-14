package org.apache.struts2.jquery.components;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.util.ContainUtil;
import org.apache.struts2.util.MakeIterator;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="select", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.SelectTag", 
		description="Render HTML select providing content from remote call via AJAX",
		allowDynamicAttributes=true)
public class Select extends AbstractContainer implements Input {

    public static final String TEMPLATE = "select";
    public static final String TEMPLATE_CLOSE = "select-close";

    private String emptyOption;
    private String headerKey;
    private String headerValue;
    protected Object list;
    protected String listKey;
    protected String listValue;

    private AbstractInput inputDelegate = new AbstractInput(stack, request, response){

		@Override
		public String getDefaultOpenTemplate() {
			return TEMPLATE;
		}

		@Override
		protected String getDefaultTemplate() {
			return TEMPLATE_CLOSE;
		}    	
    };
		
    public Select(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    public boolean contains(Object obj1, Object obj2) {
        return ContainUtil.contains(obj1, obj2);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void evaluateExtraParams() {
    	
        super.evaluateExtraParams();
        inputDelegate.setParameters(this.parameters);
        inputDelegate.evaluateExtraParams();
        
        if (emptyOption != null){
        	addParameter("emptyOption", findValue(emptyOption, Boolean.class));
        }
        
        if ((headerKey != null) && (headerValue != null)) {
            addParameter("headerKey", findString(headerKey));
            addParameter("headerValue", findString(headerValue));
        }
        Object value = null;

        if (list == null) {
            list = parameters.get("list");
        }

        if (list instanceof String) {
            value = findValue((String) list);
        } else if (list instanceof Collection) {
            value = list;
        } else if (MakeIterator.isIterable(list)) {
            value = MakeIterator.convert(list);
        }
        if (value == null) {
            value = findValue((list == null)?(String) list:list.toString());
        }

        if (value instanceof Collection) {
            addParameter("list", value);
        } else {
            addParameter("list", MakeIterator.convert(value));
        }

        if (value instanceof Collection) {
            addParameter("listSize", Integer.valueOf(((Collection) value).size()));
        } else if (value instanceof Map) {
            addParameter("listSize", Integer.valueOf(((Map) value).size()));
        } else if (value != null && value.getClass().isArray()) {
            addParameter("listSize", Integer.valueOf(Array.getLength(value)));
        }

        if (listKey != null) {
        	listKey = stripExpressionIfAltSyntax(listKey);
            addParameter("listKey", listKey);
        } else if (value instanceof Map) {
            addParameter("listKey", "key");
        }

        if (listValue != null) {
        	listValue = stripExpressionIfAltSyntax(listValue);
            addParameter("listValue", listValue);
        } else if (value instanceof Map) {
            addParameter("listValue", "value");
        }
    }

    @Override
    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    @Override
    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }

    @StrutsTagAttribute(name="emptyOption", description="Whether or not to add an empty option (--) after the header option", type="String", defaultValue="false")
	public void setEmptyOption(String emptyOption) {
    	this.emptyOption = emptyOption;
	}

    @StrutsTagAttribute(name="headerKey", description="Key for list header option. Must not be empty", type="String", defaultValue="")
	public void setHeaderKey(String headerKey) {
    	this.headerKey = headerKey;
	}

    @StrutsTagAttribute(name="focusTopics", description="Value for list header option. Must not be empty", type="String", defaultValue="")
	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

    @StrutsTagAttribute(name="focusTopics", description="A comma delimited list of topics that will cause this element to focus", type="String", defaultValue="")
	public void setFocusTopics(String focusTopics) {
		inputDelegate.setFocusTopics(focusTopics);
	}

    @StrutsTagAttribute(name="blurTopics", description="A comma delimited list of topics that will cause this element to blur", type="String", defaultValue="")
	public void setBlurTopics(String blurTopics) {
		inputDelegate.setBlurTopics(blurTopics);
	}

	@StrutsTagAttribute(name="reloadTopics", description="A comma delimited list of topics that will cause this element to reload its contents", type="String", defaultValue="")
	public void setReloadTopics(String reloadTopics) {
		this.reloadTopics = reloadTopics;
	}

	@StrutsTagAttribute(name="onBlurTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnBlurTopics(String onBlurTopics) {
		inputDelegate.setOnBlurTopics(onBlurTopics);
	}

	@StrutsTagAttribute(name="onChangeTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnChangeTopics(String onChangeTopics) {
		inputDelegate.setOnChangeTopics(onChangeTopics);
	}

	@StrutsTagAttribute(name="onFocusTopics", description = "A comma delimited list of topics that published when the element value is changed", type="String", defaultValue="")
	public void setOnFocusTopics(String onFocusTopics) {
		inputDelegate.setOnFocusTopics(onFocusTopics);
	}

	@StrutsTagAttribute(name="elementIds", description="A comma delimited list of form elements that should be individually serialized and sent with the input load request. " +
			"Input element must have a 'name' attribute and will be serialized as <name>=<value>", type="String", defaultValue="", required=false)
	public void setElementIds(String elementIds){
		inputDelegate.setElementIds(elementIds);
	}

	@StrutsTagAttribute(name="formIds", description="A comma delimited list of forms that should be serialized and sent with the input load request", type="String", defaultValue="", required=false)
	public void setFormIds(String formIds){
		inputDelegate.setFormIds(formIds);
	}
	
	@StrutsTagAttribute(name="enableTopics", description = "A comma delimited list of topics that will enable this element", type = "String", defaultValue = "")
    public void setEnableTopics(String enableTopics) {
		inputDelegate.setEnableTopics(enableTopics);
	}

	@StrutsTagAttribute(name="disableTopics", description = "A comma delimited list of topics that will disable this element", type = "String", defaultValue = "")
    public void setDisableTopics(String disableTopics) {
		inputDelegate.setDisableTopics(disableTopics);
	}

    @StrutsTagAttribute(description="Iterable source to populate from. If the list is a Map (key, value), the Map key will become the option 'value'" +
                " parameter and the Map value will become the option body. This should not be used together with the 'src' attribute", required=false)
    public void setList(Object list) {
        this.list = list;
    }

    @StrutsTagAttribute(description=" Property of list objects to get field value from", required=false)
    public void setListKey(String listKey) {
        this.listKey = listKey;
    }

    @StrutsTagAttribute(description="Property of list objects to get field content from", required=false)
    public void setListValue(String listValue) {
        this.listValue = listValue;
    }
	
}
