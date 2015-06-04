package com.taobao.protobuf.editors;

/**
 * Constant definitions for plug-in preferences
 */
public class D {

	public static enum Paths {
		PROTOC, JAVA_CODE, AS_CODE, CPP_CODE, PHYTHON_CODE,
	}

	public static class FileName {
		public static final String	PROTOC	= "protoc";
	}

	public static class LanguageArg {
		public static final String	CPP		= "--cpp_out";
		public static final String	JAVA	= "--java_out";
		public static final String	AS		= "--as3_out";
		public static final String	PHYTHON	= "--python_out";
	}

	public static class ASPlugin {

		public static final String	BAT_NAME	= "protoc-gen-as3.bat";
		public static final String	JAR_NAME	= "protoc-gen-as3.jar";
	}

	/**
	 * ºó×ºÃû
	 * 
	 * @author ÁÖá¯
	 */
	public static class Extensions {
		public static final String	PROTO_FILE	= ".p";
	}
}
