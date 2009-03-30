package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.struts2.jquery.components.Tab;
import org.apache.struts2.jquery.components.TabbedPane;
import org.apache.struts2.views.java.Attributes;

public class TabbedPaneTagSerializer extends BaseTagSerializer {

	 public void start(String name, Attributes attrs) throws IOException {
       
        super.start(name, attrs);
		writer.write("\n\t<ul>");
    }
	 
	@SuppressWarnings("unchecked")
	public void end(String name) throws IOException {
        
		writer.write("\n\t</ul>\n");
		
		super.end(name);
		
    	Map params = context.getParameters();
    	jqueryAttributes.addIfExists("isCache", params.get("isCache"));
    	jqueryAttributes.addIfExists("selected", params.get("selected"));
    	
	    String id = jqueryAttributes.get("id");
	    
	    if(id != null) {
				        
	    	writer.write("\n<script type='text/javascript'>");
		    
	    	//instantiate the tabbed pane
	    	writer.write("\n\tvar $tabs = $(\"#" + id + "\").tabs({" + (jqueryAttributes.get("isCache") != null ? " cache: " + jqueryAttributes.get("isCache") : "") 
	    			+ (jqueryAttributes.get("selected") != null ? " selected: " + jqueryAttributes.get("selected") : "") + "})");
	    		
	    	List<Tab> tabs  = ((TabbedPane)context.getTag()).getTabs();
			
			for (int i = 0 ; i < tabs.size(); ++i) {
				  
				Tab tab = tabs.get(i);
				
				if(tab.getReloadTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getReloadTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','_struts2_jquery_reloadTab', " + i + ");");
		        	}
				}
				  
				if(tab.getEnableTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getEnableTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','_struts2_jquery_enableTab', " + i + ");");
		        	}
				}
				  
				if(tab.getDisableTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getDisableTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','_struts2_jquery_disableTab', " + i + ");");
		        	}
				}
				  
				if(tab.getFocusTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getFocusTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','_struts2_jquery_selectTab', " + i + ");");
		        	}
				}
				  
				if(tab.getRemoveTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getRemoveTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','_struts2_jquery_removeTab', " + i + ");");
		        	}
				}
				  
				if(tab.getRemoveTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getHideTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','_struts2_jquery_hideTab', " + i + ");");
		        	}
				}
				  
				if(tab.getRemoveTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getShowTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','_struts2_jquery_showTab', " + i + ");");
		        	}
				}
				  
				if(tab.getIsDisabled() != null && Boolean.valueOf(tab.getIsDisabled()) == true) {
					writer.write("\n\t$tabs.tabs('disable'," + i + ");");
				}
				  
				if(tab.getIsSelected() != null && Boolean.valueOf(tab.getIsSelected()) == true) {
					writer.write("\n\t$tabs.tabs('select'," + i + ");");
				}
			}
			
	        writer.write("\n</script>");
	        
	    }
    }
}