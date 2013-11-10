package com.erp.china.demo.util;

import java.io.UnsupportedEncodingException;

import it.sauronsoftware.base64.Base64;
import javax.servlet.ServletRequest;

import org.apache.log4j.Logger;

/**
 * @author Robby B. Setyo
 * 
 * Description: all utility functions
 */
public class Util {
	private static Logger logger = Logger.getLogger(Util.class);
	private static String encodedFlag = "***";

	public static String getParameter(ServletRequest request, String keyName) {
		String value = request.getParameter(keyName);
		if(value!=null && value.startsWith(encodedFlag)) {
			try {
				value = htmlURLDecode(Base64.decode(value.replace(encodedFlag, ""),"UTF-8"));
			} catch (Exception e) {
				logger.debug("Exception in getParameter function", e);
			}
		}
		return value;
	}

	public static String htmlURLDecode(String value) {
    	String decodeValue = "";
		try {
			decodeValue = java.net.URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException uee) {
			logger.debug("UnsupportedEncodingException in htmlURLDecode function", uee);
		}
		return decodeValue;
	}
}