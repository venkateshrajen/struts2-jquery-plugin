package org.apache.struts2.jquery.views;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.jquery.views.freemarker.tags.JQueryModels;
import org.apache.struts2.views.TagLibrary;

import com.opensymphony.xwork2.util.ValueStack;

public class JQueryTagLibrary implements TagLibrary {

    public Object getFreemarkerModels(ValueStack stack, HttpServletRequest req,
            HttpServletResponse res) {
        
        return new JQueryModels(stack, req, res);
    }

    @SuppressWarnings("unchecked")
	public List<Class> getVelocityDirectiveClasses() {
        
    	return new ArrayList<Class>();
    }

}