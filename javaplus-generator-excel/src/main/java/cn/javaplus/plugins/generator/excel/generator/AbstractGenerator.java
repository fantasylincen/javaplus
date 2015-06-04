/**
 * 扜生成器的父类，构造方法中实现了读取property文件和导入java 模版的功
 * @author dengzongrong
 * @version 1.1
 * @date 2011-10-09
 */
package cn.javaplus.plugins.generator.excel.generator;

import java.net.URL;

import cn.javaplus.util.Util;

public abstract class AbstractGenerator {

	protected String	temp	= new String();

//	public AbstractGenerator(String tempName) {
//
//		String name = "/templet/" + tempName;
//
//		URL url = Class.class.getResource(name);
//
//		this.temp = Util.File.getContent(url);
//	}
}
