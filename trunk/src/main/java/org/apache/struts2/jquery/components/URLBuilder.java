package org.apache.struts2.jquery.components;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.StringUtils;

public class URLBuilder {
	
	private static final String URL_HASH = "#";

	public static String buildRootURL(String url, HttpServletRequest request) {
		
		if(url == null) { return null; }

		if(URL_HASH.equals(url) || url.length() == 0) { return url; }
		
		String contextPath = request.getContextPath();
		
		String finalURL =  url.startsWith("/") ? url : "/" + url;
		
		if(!finalURL.startsWith(contextPath)) {

	        String requestURI = (String) request.getAttribute("struts.request_uri");

	        if (requestURI == null) {
	            
	        	requestURI = (String) request.getAttribute("javax.servlet.forward.request_uri");
	        }

	        if (requestURI != null) {
	        	
	        	finalURL = StringUtils.substringBeforeLast(requestURI, "/") + finalURL;
	        }
		}
		
		return finalURL;
		
	}
}
