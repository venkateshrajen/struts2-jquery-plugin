package org.apache.struts2.jquery.views.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.views.java.DefaultTagHandlerFactory;
import org.apache.struts2.views.java.DefaultTheme;
import org.apache.struts2.views.java.TagHandlerFactory;
import org.apache.struts2.views.java.XHTMLTagSerializer;
import org.apache.struts2.views.java.simple.ScriptingEventsHandler;

public class JQueryTheme extends DefaultTheme {
	
    @SuppressWarnings("serial")
	public JQueryTheme() {
    	
        setHandlerFactories(new HashMap<String, List<TagHandlerFactory>>() {
		{
            put("head", new FactoryList(HeadTagSerializer.class));
            
            put("div", new FactoryList(DivHandler.class, ContainerHandler.class, BaseHandler.class, XHTMLTagSerializer.class));
            put("div-close", new FactoryList(DivHandler.CloseHandler.class, JQueryTagSerializer.class));

            put("select", new FactoryList(SelectHandler.class, ScriptingEventsHandler.class, ContainerHandler.class, InputHandler.class, InteractiveHandler.class, BaseHandler.class, XHTMLTagSerializer.class));
            put("select-close", new FactoryList(SelectHandler.CloseHandler.class, SelectTagSerializer.class));
            
            put("a", new FactoryList(AnchorHandler.class, ScriptingEventsHandler.class, ActionHandler.class, InteractiveHandler.class, BaseHandler.class,  XHTMLTagSerializer.class));
            put("a-close", new FactoryList(AnchorHandler.CloseHandler.class, JQueryTagSerializer.class));
            
            put("submit", new FactoryList(SubmitHandler.class, ScriptingEventsHandler.class, ActionHandler.class, InteractiveHandler.class, BaseHandler.class, XHTMLTagSerializer.class));
            put("submit-close", new FactoryList(SubmitHandler.CloseHandler.class, JQueryTagSerializer.class));

            put("tab", new FactoryList(TabHandler.class, InputHandler.class, InteractiveHandler.class, BaseHandler.class, TabTagSerializer.class));
            put("tab-close", new FactoryList(TabHandler.CloseHandler.class, TabTagSerializer.class));
            
            put("tabbedpane", new FactoryList(TabbedPaneHandler.class,  BaseHandler.class, TabbedPaneTagSerializer.class));
            put("tabbedpane-close", new FactoryList(TabbedPaneHandler.CloseHandler.class,  TabbedPaneTagSerializer.class));
            
            put("form", new FactoryList(FormHandler.class, ScriptingEventsHandler.class, BaseHandler.class, XHTMLTagSerializer.class));
            put("form-close", new FactoryList(FormHandler.CloseHandler.class, JQueryTagSerializer.class));
            
            put("dialog", new FactoryList(DialogHandler.class, ScriptingEventsHandler.class, ContainerHandler.class, InteractiveHandler.class, BaseHandler.class, XHTMLTagSerializer.class));
            put("dialog-close", new FactoryList(DialogHandler.CloseHandler.class, JQueryTagSerializer.class));
            
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