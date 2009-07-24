package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringEscapeUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="dialog", tldTagClass="org.apache.struts2.jquery.views.jsp.ui.DialogTag", 
		description="Renders a dialog which is displayed over contents",
		allowDynamicAttributes=true)
public class Dialog extends AbstractContainer implements Interactive {

    public static final String TEMPLATE = "dialog";
    public static final String TEMPLATE_CLOSE = "dialog-close";

	protected String buttons;				//A comma delimited list of button names (in order of appearance, in same order as buttonTopics) 
	protected String buttonTopics;			//A comma delimited list of topics to be published when buttons are clicked (in same order as button names)
	protected String title;					//The dialog title
	protected String modal;					//Should the dialog be shown as a modal dialog or not (true/false)
	protected String draggable;				//If the dialog should be draggable or not (true/false)
	protected String resizable;				//If the dialog should be resizable or not (true/false)
	protected String height;				//The height of the dialog
	protected String width;					//The width of the dialog
	protected String position;				//The starting position of the dialog. Possible values: 'center', 'left', 'right', 'top', 'bottom', or an array containing a coordinate pair (in pixel offset from top left of viewport) or the possible string values (e.g. ['right','top'] for top right corner)
	protected String data;					//Additional data (in the form of "key1=value1,key2=value2,..." to be provided to the dialog for use in button click topic handlers
	protected String options;			//Additional widget options
	 
    private AbstractInteractive interactiveDelegate = new AbstractInteractive(stack, request, response){

		@Override
		public String getDefaultOpenTemplate() {
			return TEMPLATE;
		}

		@Override
		protected String getDefaultTemplate() {
			return TEMPLATE_CLOSE;
		}    	
    };
    
    public Dialog(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        
    	super(stack, request, response);
    }

    @Override
    public void evaluateExtraParams() {
        
        super.evaluateExtraParams();
        interactiveDelegate.setParameters(this.parameters);
        interactiveDelegate.evaluateExtraParams();

        if (buttons != null)
            addParameter("buttons", findString(buttons));
        if (buttonTopics != null)
            addParameter("buttonTopics", findString(buttonTopics));
        if (title != null)
            addParameter("title", findString(title));
        if (modal != null)
            addParameter("modal", findValue(modal, Boolean.class));
        if (draggable != null)
            addParameter("draggable", findValue(draggable, Boolean.class));
        if (resizable != null)
            addParameter("resizable", findValue(resizable, Boolean.class));
        if (height != null)
            addParameter("height", findString(height));
        if (width != null)
            addParameter("width", findString(width));
        if (position != null)
            addParameter("position", findString(position));
        if (data != null)
            addParameter("data", findString(data));
        if (options != null) {
            String ops = findString(this.options);
            if (StringUtils.isNotEmpty(ops))
                addParameter("options", StringEscapeUtils.escapeJavaScript(ops));
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
	
    @StrutsTagAttribute(name="buttons", description="A comma delimited list of button names (in order of appearance, in same order as buttonTopics)")
	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

    @StrutsTagAttribute(name="buttonTopics", description="A comma delimited list of topics to be published when buttons are clicked (in same order as button names)")
	public void setButtonTopics(String buttonTopics) {
		this.buttonTopics = buttonTopics;
	}

    @StrutsTagAttribute(name="title", description="The dialog title")
	public void setTitle(String title) {
		this.title = title;
	}

    @StrutsTagAttribute(name="modal", description="Should the dialog be shown as a modal dialog or not (true/false)", type="Boolean")
	public void setModal(String modal) {
		this.modal = modal;
	}

    @StrutsTagAttribute(name="draggable", description="If the dialog shoudl be draggable or not (true/false)", type="Boolean")
	public void setDraggable(String draggable) {
		this.draggable = draggable;
	}

    @StrutsTagAttribute(name="resizable", description="If the dialog should be resizable or not (true/false)", type="Boolean")
	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

    @StrutsTagAttribute(name="height", description="The height of the dialog")
	public void setHeight(String height) {
		this.height = height;
	}

    @StrutsTagAttribute(name="width", description="The width of the dialog")
	public void setWidth(String width) {
		this.width = width;
	}

    @StrutsTagAttribute(name="position", description="The starting position of the dialog. Possible values: 'center', 'left', 'right', 'top', " +
    		"'bottom', or an array containing a coordinate pair (in pixel offset from top left of viewport) or the possible string values (e.g. " +
    		"['right','top'] for top right corner)")
	public void setPosition(String position) {
		this.position = position;
	} 
    
    @StrutsTagAttribute(name="data", description="Additional data (in the form of 'key1=value1,key2=value2,...' to be provided to the dialog for use in button click topic handlers")
	public void setData(String data) {
		this.data = data;
	}

	@StrutsTagAttribute(name="disableTopics", description = "A comma delimited list of topics that will disable this element", type = "String", defaultValue = "")
	public void setDisableTopics(String disableTopics) {
		interactiveDelegate.setDisableTopics(disableTopics);
	}

	@StrutsTagAttribute(name="enableTopics", description = "A comma delimited list of topics that will enable this element", type = "String", defaultValue = "")
	public void setEnableTopics(String enableTopics) {
		interactiveDelegate.setEnableTopics(enableTopics);
	} 

    @StrutsTagAttribute(description = "The name of a variable (or a javascript map) that contains additional options to be passed to the Dialog widget")
    public void setOptions(String options) {
        this.options = options;
    }
}
