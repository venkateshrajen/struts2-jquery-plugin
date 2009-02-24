package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.struts2.jquery.components.Tab;
import org.apache.struts2.jquery.components.TabbedPane;
import org.apache.struts2.views.java.Attributes;

public class TabbedPaneTagSerializer extends AbstractJQueryTagSerializer {

	 public void start(String name, Attributes attrs) throws IOException {
       
        super.start(name, attrs);
		writer.write("\n\t<ul>");
    }
	 
	@SuppressWarnings("unchecked")
	public void end(String name) throws IOException {
        
		writer.write("\n\t</ul>\n");
		
		super.end(name);
		
    	Map params = context.getParameters();
    	jqueryAttributes.addIfExists("selected", params.get("selected"));
    	jqueryAttributes.addIfExists("iscache", params.get("iscache"));
    	jqueryAttributes.addIfExists("disabled", params.get("disabled"));
    	
	    String id = jqueryAttributes.get("id");
	    
	    boolean prevOption = false;
	    if(id != null) {
			
		    writer.write("\n<script type='text/javascript'>");

	    	writer.write("\n\t$.subscribeHandler('reloadTab', function(event, data){");
	    	writer.write("\n\t\t$(\"#" + id + "\").tabs('load', event.data);");
	    	writer.write("\n\t});");

	    	writer.write("\n\t$.subscribeHandler('selectTab', function(event, data){");
	    	writer.write("\n\t\t$(\"#" + id + "\").tabs('select', event.data);");
	    	writer.write("\n\t});");

	    	writer.write("\n\t$.subscribeHandler('disableTab', function(event, data){");
	    	writer.write("\n\t\t$(\"#" + id + "\").tabs('disable', event.data);");
	    	writer.write("\n\t});");

	    	writer.write("\n\t$.subscribeHandler('enableTab', function(event, data){");
	    	writer.write("\n\t\t$(\"#" + id + "\").tabs('enable', event.data);");
	    	writer.write("\n\t});");

	    	writer.write("\n\t$.subscribeHandler('removeTab', function(event, data){");
	    	writer.write("\n\t\t$(\"#" + id + "\").tabs('remove', event.data);");
	    	writer.write("\n\t});");
	        
	        writer.write("\n</script>");
	        
	        	        
	    	writer.write("\n<script type='text/javascript'>");
		    
	    	writer.write("\n\tvar $tabs = $(\"#" + id + "\").tabs({");
	    	
	    	if(jqueryAttributes.get("selected") != null) {

		    	writer.write("selected: " + jqueryAttributes.get("selected"));
		    	prevOption = true;
	    	}

	    	if(jqueryAttributes.get("iscache") != null) {

		    	writer.write((prevOption == true ? "," : "") + " cache: " + jqueryAttributes.get("iscache"));	
		    	prevOption = true;
	    	}

	    	if(jqueryAttributes.get("disabled") != null) {

	    		StringTokenizer disabledIds = new StringTokenizer(jqueryAttributes.get("disabled"),",");
	    		
	    		StringBuilder idArray = new StringBuilder("[");
	    		
	    		boolean prevId = false;
	    		while(disabledIds.hasMoreTokens()){
	    			
	    			String disabledId = disabledIds.nextToken();
	    			
	    			try{ 
	    				
	    				idArray.append((prevId == true ? "," : "") + Integer.parseInt(disabledId));
	    				prevId = true;
	    				
	    			}catch(NumberFormatException ne){}
	    		}
	    		
	    		idArray.append("]");
	    		
		    	writer.write((prevOption == true ? "," : "") + " disabled: " + idArray.toString());	
	    	}

	    	writer.write("});");

	    	/*
	    	if (jqueryAttributes.containsKey("reloadTopics")) {
	        	StringTokenizer topics = new StringTokenizer(jqueryAttributes.get("reloadTopics"),",");
	        	while(topics.hasMoreTokens()){
	            	writer.write("\n$('#" + id + "').subscribe('" + topics.nextToken() + "','reload');");
	        	}
	        }
	    	*/

			List<Tab> tabs  = ((TabbedPane)context.getTag()).getTabs();
			for (int i = 0 ; i < tabs.size(); ++i) {
				  
				Tab tab = tabs.get(i);
				
				if(tab.getReloadTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getReloadTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','reloadTab', " + i + ");");
		        	}
				}
				  
				if(tab.getEnableTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getEnableTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','enableTab', " + i + ");");
		        	}
				}
				  
				if(tab.getDisableTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getDisableTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','disableTab', " + i + ");");
		        	}
				}
				  
				if(tab.getSelectTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getSelectTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','selectTab', " + i + ");");
		        	}
				}
				  
				if(tab.getRemoveTopics() != null) {
					
					StringTokenizer topics = new StringTokenizer(tab.getRemoveTopics(),",");
		        	while(topics.hasMoreTokens()){
		            	writer.write("\n\t$tabs.subscribe('" + topics.nextToken() + "','removeTab', " + i + ");");
		        	}
				}
			}
			
	        writer.write("\n</script>");
	    }
    }
}
