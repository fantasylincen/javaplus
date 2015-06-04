package cn.javaplus.util;

import java.net.URL;

public class Resources {

	  public static URL getResource(String resourceName) {
	    URL url = Resources.class.getClassLoader().getResource(resourceName);
	    if(url == null) {
	    	throw new IllegalArgumentException(resourceName);
	    }
	    return url;
	  }
}
