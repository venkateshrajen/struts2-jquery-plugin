package org.apache.struts2.jquery.views.java;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class DivTagSerializer extends AbstractJQueryTagSerializer {

	@SuppressWarnings("unchecked")
	public void end(String name) throws IOException {
        
		super.end(name);
		
    	Map params = context.getParameters();
    	jqueryAttributes.addIfExists("src", params.get("src"));
    	jqueryAttributes.addIfExists("reloadTopics", params.get("reloadTopics"));
    	jqueryAttributes.addIfExists("loadingText", params.get("loadingText"));
    	jqueryAttributes.addIfExists("errorText", params.get("errorText"));
    	
	    String id = jqueryAttributes.get("id");
	    
	    if(id != null) {
			
	    	writer.write("\n<script type='text/javascript'>");
		    
		    String src = jqueryAttributes.get("src");
		    
	        //execute load logic
	        if (src != null) { 
	
	        	//publish all 'before' and 'always' topics
	        	for (Iterator<String> topics = getUniqueStrings(jqueryAttributes.get("alwaysPublishTopics"),
	        							jqueryAttributes.get("beforePublishTopics")).iterator(); topics.hasNext();) {
	        		
	        		writer.write("\n$('#" + id + "').publish('" + topics.next() + "');");					
				}
	        	
	        	//Set pre-loading text (if any)
	        	if(jqueryAttributes.containsKey("loadingText")) { writer.write("\n$('#" + id + "').html('" + jqueryAttributes.get("loadingText") + "');"); }

	        	//Show indicator element (if any)
	        	if(jqueryAttributes.containsKey("indicatorId")) { writer.write("\n$('#" + jqueryAttributes.get("indicatorId") + "').show();"); }
	        		
	        	String onSuccess =  getOnSuccessFunction(id);
	        	String onError =  getOnErrorFunction(id);
	        	String onComplete =  getOnCompleteFunction(id);
	        		        	
	        	//ajax load div contents
	        	writer.write("\n\t$(function() {");
	        		
		        	writer.write("\n\t$.ajax({");
	        		writer.write("\n\t\t\ttype: 'GET',");
	    			writer.write("\n\t\t\turl: '" + src + "',");
	    			writer.write("\n\t\t\tsuccess: function(data, textStatus) {"); 
	    				writer.write("\n\t\t\t\t$('#" + id + "').html(data);");
	    				if(onSuccess != null ) { 
	    					writer.write("\n\t\t\t\tvar onSuccess = " + onSuccess );
	    					writer.write("\n\t\t\t\tonSuccess(data,textStatus)");
	    				}
	    			writer.write("\n\t\t\t}");
	    			if(onError != null ) { writer.write("\n\t\t\t, error: " + onError + ","); }
	    			if(onComplete != null ) { writer.write("\n\t\t\t, complete: " + onComplete); }
				    writer.write("\n\t\t});");
			    
	        		/*
		        	writer.write("\n\t\t$('#" + id + "').ajax({");
		        		writer.write("\n\t\t\ttype: 'GET',");
	        			writer.write("\n\t\t\turl: '" + src + "',");
	        			if(onSuccess != null ) { writer.write("\n\t\t\tsuccess: " + onSuccess + ","); }
	        			if(onError != null ) { writer.write("\n\t\t\terror: " + onError + ","); }
	        			if(onComplete != null ) { writer.write("\n\t\t\tcomplete: " + onComplete); }
				    writer.write("\n\t\t});");
				    
				    */
	        	
				    //process reload logic
			        //store reload url
				    writer.write("\n\t\t$('#" + id + "').data('url','" + src  + "');");	
			        
				    //store reload success function
				    if(onSuccess != null ) {  writer.write("\n\t\t$('#" + id + "').data('onsuccess', " + onSuccess + ");");	}
	
				    //store reload error function
				    if(onError != null ) {  writer.write("\n\t\t$('#" + id + "').data('onerror', " + onError + ");");	}
	
				    //store reload completion function
				    if(onComplete != null ) { writer.write("\n\t\t$('#" + id + "').data('oncomplete', " + onComplete + ");");	}
				    
			        //subscribe to reload topics
			        if (jqueryAttributes.containsKey("reloadTopics")) {
			        	StringTokenizer topics = new StringTokenizer(jqueryAttributes.get("reloadTopics"),",");
			        	while(topics.hasMoreTokens()){
			            	writer.write("\n\t\t$('#" + id + "').subscribe('" + topics.nextToken() + "','reload');");
			        	}
			        }

				writer.write("\n\t});");
	        }
	       
	        writer.write("\n</script>");
	    }
    }
	
	private String getOnSuccessFunction(String id){
		
		Collection<String> successTopics =  getUniqueStrings(jqueryAttributes.get("alwaysPublishTopics"), jqueryAttributes.get("afterPublishTopics"));
		
		if(successTopics.isEmpty()) { return null; }
			
		StringBuilder sb = new StringBuilder("function(data, textStatus) { ");
    			
			//publish all 'after' and 'always' topics after loading complete
			for (Iterator<String> topics = successTopics.iterator(); topics.hasNext();) {
				sb.append("\n\t\t\t\t$('#" + id + "').publish('" + topics.next() + "',data);");	
			}
       
		sb.append("\n\t\t\t}");
		
		return sb.toString();

	}

	private String getOnErrorFunction(String id){

		Collection<String> errorTopics =  getUniqueStrings(jqueryAttributes.get("alwaysPublishTopics"), jqueryAttributes.get("errorPublishTopics"));

		if(errorTopics.isEmpty() && !jqueryAttributes.containsKey("errorText")) { return null; }
		
		StringBuilder sb = new StringBuilder("function(XMLHttpRequest, textStatus, errorThrown) { ");

			//Set error text (if any)
			if(jqueryAttributes.containsKey("errorText")) { sb.append("\n\t\t\t\t$('#" + id + "').html('" + jqueryAttributes.get("errorText") + "');"); }

			//publish all 'error' and 'always' topics after loading complete
			for (Iterator<String> topics = errorTopics.iterator(); topics.hasNext();) {
				sb.append("\n\t\t\t\t$('#" + id + "').publish('" + topics.next() + "',textStatus);");	
			}
	        
		sb.append("\n\t\t\t}");
		
		return sb.toString();
	}
	
	private String getOnCompleteFunction(String id){

		Collection<String> completeTopics =  getUniqueStrings(jqueryAttributes.get("alwaysPublishTopics"), jqueryAttributes.get("afterPublishTopics"));

		if(completeTopics.isEmpty() && !jqueryAttributes.containsKey("indicatorId")) { return null; }
			
		StringBuilder sb = new StringBuilder("function(XMLHttpRequest, textStatus, errorThrown) { ");

        	//Hide indicator element (if any)
			if(jqueryAttributes.containsKey("indicatorId")) { sb.append("\n\t\t\t$('#" + jqueryAttributes.get("indicatorId") + "').hide();"); }

			//publish all 'error' and 'always' topics after loading complete
			for (Iterator<String> topics = completeTopics.iterator(); topics.hasNext();) {
				sb.append("\n\t\t\t\t$('#" + id + "').publish('" + topics.next() + "',textStatus);");	
			}
			
        sb.append("\n\t\t\t}");
		
		return sb.toString();
	}
}
