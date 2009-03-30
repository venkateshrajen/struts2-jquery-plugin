package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="submit", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.SubmitTag", 
		description="Render HTML button allowing loading result into ajax target",
		allowDynamicAttributes=true)
public class Submit extends AbstractAction {

	private static final long serialVersionUID = -1728246656600445144L;
	public static final String OPEN_TEMPLATE = "submit";
	public static final String TEMPLATE = "submit-close";
	
    public Submit(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }
    
    public void evaluateParams() {
    	
        if ((key == null) && (value == null)) {
            value = "Submit";
        }

        if (((key != null)) && (value == null)) {
            this.value = "%{getText('"+key +"')}";
        }
        
        super.evaluateParams();
    }
    
    public void evaluateExtraParams() {

        super.evaluateExtraParams();

        addParameter("type", "button");
    }
    
    public String getDefaultOpenTemplate() {
        return OPEN_TEMPLATE;
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
}
