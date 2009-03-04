package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="a", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.AnchorTag", description="Render HTML anchor allowing loading result into ajax target")
public class Anchor extends AbstractAction {

	private static final long serialVersionUID = -1728246656600445144L;
	public static final String OPEN_TEMPLATE = "a";
	public static final String TEMPLATE = "a-close";


    public Anchor(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }
    
    public void evaluateExtraParams() {
        
        super.evaluateExtraParams();
    }
    
    public String getDefaultOpenTemplate() {
        return OPEN_TEMPLATE;
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
    
}
