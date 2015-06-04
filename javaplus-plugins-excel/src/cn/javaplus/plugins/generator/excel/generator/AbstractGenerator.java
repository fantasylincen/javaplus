/**
 * 所有生成器的父类，构造方法中实现了读取property文件和导入java 模版的功能
 * @author dengzongrong
 * @version 1.1
 * @date 2011-10-09
 */
package cn.javaplus.plugins.generator.excel.generator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import _util.Closer;
import _util.Util;
import cn.javaplus.plugins.generator.excel.Activator;

public abstract class AbstractGenerator {

	protected String		temp	= new String();
	private BufferedReader	in;

	/**
	 * 构造方法，读取property文件和导入java模版
	 * 
	 * @param temp
	 */
	public AbstractGenerator(String temp) {
		String b = null;
		try {

			Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
			String name = "templet/" + temp;

			URL url = bundle.getResource(name);
			URL fileURL = FileLocator.toFileURL(url);
			InputStream is = fileURL.openStream();

			in = new BufferedReader(new InputStreamReader(is));

			while ((b = in.readLine()) != null)
				this.temp += b + "\r";

		} catch (Exception e) {
			throw Util.toRuntimeException(e);
		} finally {
			Closer.close(in);
		}
	}
}
