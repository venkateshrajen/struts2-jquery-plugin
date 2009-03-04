package org.apache.struts2.jquery.views.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.views.java.DefaultTagHandlerFactory;
import org.apache.struts2.views.java.DefaultTheme;
import org.apache.struts2.views.java.TagHandlerFactory;
import org.apache.struts2.views.java.XHTMLTagSerializer;
import org.apache.struts2.views.java.simple.ActionErrorHandler;
import org.apache.struts2.views.java.simple.ActionMessageHandler;
import org.apache.struts2.views.java.simple.AnchorHandler;
import org.apache.struts2.views.java.simple.CheckboxHandler;
import org.apache.struts2.views.java.simple.CommonAttributesHandler;
import org.apache.struts2.views.java.simple.DivHandler;
import org.apache.struts2.views.java.simple.EmptyHandler;
import org.apache.struts2.views.java.simple.FieldErrorHandler;
import org.apache.struts2.views.java.simple.FileHandler;
import org.apache.struts2.views.java.simple.FormHandler;
import org.apache.struts2.views.java.simple.HiddenHandler;
import org.apache.struts2.views.java.simple.LabelHandler;
import org.apache.struts2.views.java.simple.PasswordHandler;
import org.apache.struts2.views.java.simple.ResetHandler;
import org.apache.struts2.views.java.simple.ScriptingEventsHandler;
import org.apache.struts2.views.java.simple.SelectHandler;
import org.apache.struts2.views.java.simple.SubmitHandler;
import org.apache.struts2.views.java.simple.TextAreaHandler;
import org.apache.struts2.views.java.simple.TextFieldHandler;
import org.apache.struts2.views.java.simple.TokenHandler;

public class JQueryTheme extends DefaultTheme {
	
    @SuppressWarnings("serial")
	public JQueryTheme() {
    	
        setHandlerFactories(new HashMap<String, List<TagHandlerFactory>>() {
		{
            put("head", new FactoryList(HeadTagSerializer.class));
            
            put("div", new FactoryList(DivHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("div-close", new FactoryList(DivHandler.CloseHandler.class, DivTagSerializer.class));
            
            put("a", new FactoryList(AnchorHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("a-close", new FactoryList(AnchorHandler.CloseHandler.class, AnchorTagSerializer.class));
            
            put("submit", new FactoryList(SubmitHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("submit-close", new FactoryList(SubmitHandler.CloseHandler.class, SubmitTagSerializer.class));

            put("tab", new FactoryList(TabHandler.class, TabTagSerializer.class));
            put("tab-close", new FactoryList(TabHandler.CloseHandler.class, TabTagSerializer.class));
            
            put("tabbedpane", new FactoryList(DivHandler.class, TabbedPaneTagSerializer.class));
            put("tabbedpane-close", new FactoryList(DivHandler.CloseHandler.class, TabbedPaneTagSerializer.class));
            
            put("text", new FactoryList(TextFieldHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("textfield", new FactoryList(TextFieldHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("select", new FactoryList(SelectHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("form", new FactoryList(FormHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("form-close", new FactoryList(FormHandler.CloseHandler.class, XHTMLTagSerializer.class));
            put("checkbox", new FactoryList(CheckboxHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("file", new FactoryList(FileHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("password", new FactoryList(PasswordHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("label", new FactoryList(LabelHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("reset", new FactoryList(ResetHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("textarea", new FactoryList(TextAreaHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, XHTMLTagSerializer.class));
            put("actionerror", new FactoryList(ActionErrorHandler.class, XHTMLTagSerializer.class));
            put("token", new FactoryList(TokenHandler.class, XHTMLTagSerializer.class));
            put("actionmessage", new FactoryList(ActionMessageHandler.class, XHTMLTagSerializer.class));
            put("hidden", new FactoryList(HiddenHandler.class, XHTMLTagSerializer.class));
            put("fielderror", new FactoryList(FieldErrorHandler.class, XHTMLTagSerializer.class));
            put("empty", new FactoryList(EmptyHandler.class, XHTMLTagSerializer.class));
        }});
        
        setName("jquery");
    }
    
    @SuppressWarnings("unchecked")
    public class FactoryList extends ArrayList<TagHandlerFactory> {
    	
    	private static final long serialVersionUID = -1551895041394434032L;

    	public FactoryList(Class... classes) {
            super();
            for (Class cls : classes) {
                add(new DefaultTagHandlerFactory(cls));
            }
        }
    }
}