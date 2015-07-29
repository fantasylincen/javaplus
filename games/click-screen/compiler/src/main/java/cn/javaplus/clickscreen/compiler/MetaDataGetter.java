package cn.javaplus.clickscreen.compiler;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MetaDataGetter {

	private static List<Element> metadatas;

	public static String get(Object key) {

		if (metadatas == null)
			initMetas();

		for (Element e : metadatas) {
			String name = e.attributeValue("name");
			if (name.equals(key.toString())) {
				return e.attributeValue("value");
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private static void initMetas() {
		// <manifest xmlns:android="http://schemas.android.com/apk/res/android"
		// package="cn.javaplus.twolegs"
		// android:versionCode="2"
		// android:versionName="1.0.2" >
		//
		// <application
		// android:allowBackup="true"
		// android:icon="@drawable/ic_launcher"
		// android:label="@string/app_name"
		// android:theme="@style/GdxTheme" >
		//
		// <!-- 万普 -->
		// <meta-data
		try {
			File inputXml = new File("../android/AndroidManifest.xml");
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputXml);
			Element employees = document.getRootElement();
			Element application = employees.element("application");
			metadatas = application.elements("meta-data");
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
