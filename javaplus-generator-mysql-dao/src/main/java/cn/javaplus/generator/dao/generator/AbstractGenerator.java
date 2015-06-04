/**
 * 所有生成器的父类，构造方法中实现了读取property文件和导入java 模版的功能
 * @author dengzongrong
 * @version 1.1
 * @date 2011-10-09
 */
package cn.javaplus.generator.dao.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.dom4j.Element;

import cn.javaplus.generator.dao.loader.PropertiesLoader;

abstract class AbstractGenerator {

	protected String temp = new String();
	private BufferedReader in;

	/**
	 * 获得文档的DB节点
	 * @return
	 */
	private Element getDBElement() {
		return PropertiesLoader.getRoot();
	}

	protected String getDBProperty(String key) {
		return getDBElement().attributeValue(key);
	}


	/**
	 * 构造方法，读取property文件和导入java模版
	 * @param temp
	 */
	AbstractGenerator(String temp) {
		String b = null;
		try {

				in = new BufferedReader(new FileReader(new File(PropertiesLoader.getTempletsPath() + temp)));

			while((b = in.readLine()) != null)
				this.temp += b + "\r";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
