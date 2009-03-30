package org.apache.struts2.jquery.views.java;

import java.io.IOException;

public class SubmitTagSerializer extends ActionTagSerializer {

	public void end(String name) throws IOException {

		super.end(name);
    	
    	String id = jqueryAttributes.get("id");
	    
	    if(id != null) {
			
	    	writer.write("\n<script type='text/javascript'>");

	    	writer.write("\n\t var submit = $('#" + id + "');");
	    	writer.write("\n\t submit.attr('type','button');");
	    	writer.write("\n\t submit.removeAttr('name');");
	    	
	    	writer.write("\n </script>");
	    }
    }	
}
