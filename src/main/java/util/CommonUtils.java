package util;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {
	public static String getUrl(HttpServletRequest request) {
	    return request.getRequestURL().toString() + "?" + request.getQueryString();
	}
}
