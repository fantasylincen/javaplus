//package com.cnbizmedia.resources;
//
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import javax.servlet.ServletContext;
//
//import org.apache.struts2.ServletActionContext;
//
//@SuppressWarnings("deprecation")
//public class ResourceDir {
//
//	/**
//	 * @param resourcePath
//	 *            相对于WEB-INFO的路径
//	 * @param s
//	 * @return
//	 */
//	public URL get(String resourcePath, ServletContext s) {
//		String path = s.getRealPath("/");
//		try {
//			File f = new File(path + "WEB-INFO" + File.separator + resourcePath);
//			return f.toURL();
//		} catch (MalformedURLException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * @param resourcePath
//	 *            相对于WEB-INFO的路径
//	 * @return
//	 */
//	public URL get(String resourcePath) {
//		String path = ServletActionContext.getServletContext().getRealPath("/");
//		try {
//			File f = new File(path + "WEB-INFO" + File.separator + resourcePath);
//			return f.toURL();
//		} catch (MalformedURLException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//}
