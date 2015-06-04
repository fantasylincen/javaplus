package cn.javaplus.plugins.generator.protocol.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import _util.Closer;
import _util.StringUtil;
import _util.Util;
import cn.javaplus.plugins.generator.protocol.Activator;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;
import cn.javaplus.plugins.generator.protocol.util.Store;

public class GeneratorConfigImpl implements GeneratorConfig {

	private List<IClass>		clazzs;

	private Map<String, String>	javaTypeMap;

	private Map<String, String>	cache;

	public GeneratorConfigImpl() {

		cache = new HashMap<String, String>();

		clazzs = new ArrayList<IClass>();

		File input = new File(Store.getString(Paths.XML));

		List<String> all = new ArrayList<String>();

		Collections.addAll(all, input.list());

		sortByIndexDefine(all);

		for (String file : all) {

			if (!file.endsWith(".xml")) {

				continue;
			}

			try {

				readXml(new File(Store.getString(Paths.XML) + File.separator + file));

			} catch (MalformedURLException e) {

				throw Util.toRuntimeException(e);

			} catch (DocumentException e) {

				throw Util.toRuntimeException(e);
			}
		}

	}

	private void sortByIndexDefine(List<String> all) {

		File f = new File(Store.getString(Paths.XML) + File.separator + "_index.define");

		final Map<String, Integer> vs = getIndexDefine(f);

		Collections.sort(all, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {

				Integer v1 = vs.get(o1);

				Integer v2 = vs.get(o2);

				v1 = v1 != null ? v1 : Integer.MAX_VALUE;

				v2 = v2 != null ? v2 : Integer.MAX_VALUE;

				return v1 - v2;
			}

		});
	}

	private Map<String, Integer> getIndexDefine(File f) {

		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader(f));

			Map<String, Integer> map = new HashMap<String, Integer>();

			int i = 0;

			while (true) {

				String fileName = br.readLine();

				if (fileName == null) {

					break;
				}

				map.put(fileName, i++);
			}

			return map;

		} catch (FileNotFoundException e) {

			throw new RuntimeException(e);

		} catch (IOException e) {

			throw new RuntimeException(e);

		} finally {

			Closer.close(br);
		}
	}

	private void readXml(File file) throws MalformedURLException, DocumentException {

		SAXReader saxReader = new SAXReader();

		InputStreamReader is = null;

		try {

			is = new InputStreamReader(new FileInputStream(file), "utf8");
			Document document = saxReader.read(is);
			Element e = document.getRootElement();

			String className = e.elementTextTrim("name");

			IClass clazz = buildClass(className, e);

			add(clazz);

			MethodIDManager.nextClass();

		} catch (Exception e1) {
			throw Util.toRuntimeException(e1);
		} finally {
			Closer.close(is);
		}

	}

	@SuppressWarnings("unchecked")
	private IClass buildClass(String className, Element e) {

		List<Element> methods = e.elements("method");

		IClass clazz = new ClassImpl(className, "Impl");

		for (Element m : methods) {

			IMethod method = buildMethod(className, m);

			clazz.addMethod(method);

			MethodIDManager.nextMethod();
		}

		methods = e.elements("server_to_client_method");

		for (Element m : methods) {

			IMethod method = buildMethod(className, m);

			clazz.addServerToClientMethod(method);

			MethodIDManager.nextMethod();
		}

		return clazz;
	}

	private IMethod buildMethod(String className, Element m) {

		String methodDoc = m.elementTextTrim("doc");

		String returnDoc = m.elementTextTrim("return");

		String id = m.elementTextTrim("id");

		List<String> argsDoc = readArgs(m);

		List<String> backs = readBacks(m);

		Element compress = m.element("compress");

		String methodText = m.getTextTrim();

		long mId = id != null ? new Long(id) : MethodIDManager.getMethodId();

		MethodImpl method = new MethodImpl(methodText, methodDoc, returnDoc, argsDoc, backs, mId, this);

		if (compress != null) {

			method.setCompressType(compress.getTextTrim());
		}

		return method;
	}

	private List<String> readBacks(Element m) {
		return readStrings(m, "back");
	}

	private void add(IClass clazz) {
		this.clazzs.add(clazz);
	}

	@SuppressWarnings("unchecked")
	private List<String> readStrings(Element m, String node) {

		List<String> args = new ArrayList<String>();

		List<Element> as = m.elements(node);

		for (Element element : as) {

			args.add(element.getTextTrim());
		}

		return args;
	}

	private List<String> readArgs(Element m) {
		return readStrings(m, "args");
	}

	@Override
	public List<IClass> getClazzs() {
		return clazzs;
	}

	@Override
	public String getTemplet(String templetFileName) {

		String temp = cache.get(templetFileName);

		if (temp != null) {
			return temp;
		}

		BufferedReader in = null;
		String b = null;
		StringBuilder sb = new StringBuilder();
		try {

			Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
			String name = "templet/" + templetFileName + ".temp";

			URL url = bundle.getResource(name);
			URL fileURL = FileLocator.toFileURL(url);
			InputStream is = fileURL.openStream();

			in = new BufferedReader(new InputStreamReader(is));

			while ((b = in.readLine()) != null)
				sb.append(b + "\r");

			String string = sb.toString();

			cache.put(templetFileName, string);

			return string;
		} catch (Exception e) {
			throw Util.toRuntimeException(e);
		} finally {
			Closer.close(in);
		}
	}

	@Override
	public FileUtil getFileUtil(String filePath) {
		return new FileUtilImpl(filePath);
	}

	@Override
	public IInterface getInterface(String className) {

		for (IClass c : clazzs) {

			if (c.getInterface().getName().equals(className)) {

				return c.getInterface();
			}
		}

		throw new IllegalArgumentException("xxxxxx" + className + "xxxxxxxx");
	}

	@Override
	public String getJavaPackage(String typeSimple) {
		if (javaTypeMap == null) {
			readFromPFile();
		}
		String string = this.javaTypeMap.get(typeSimple);
		if (string != null) {
			return string;
		}
		return typeSimple;
	}

	@Override
	public boolean contains(String typeSimple) {
		if (javaTypeMap == null) {
			readFromPFile();
		}
		return javaTypeMap.containsKey(typeSimple);
	}

	private void readFromPFile() {
		javaTypeMap = new HashMap<String, String>();
		File f = new File(Store.getString(Paths.XML));
		BufferedReader bufferedReader = null;
		try {
			List<File> ps = getPs(f);
			for (File file : ps) {
				String content = _util.FileUtil.getContent(file);
				findAndPut(content, file);
			}

		} finally {
			Closer.close(bufferedReader);
		}
	}

	private List<File> getPs(File f) {
		List<File> ls = new ArrayList<File>();
		String[] list = f.list();
		for (String string : list) {
			if (string.endsWith(".p")) {
				ls.add(_util.FileUtil.getFile(f, string));
			}
		}
		return ls;
	}

	private void findAndPut(String content, File file) {
		Pattern pack = Pattern.compile("package [A-Za-z\\.]+;");
		Pattern clazz = Pattern.compile("message [A-Za-z ]+\\{");
		Matcher p = pack.matcher(content);
		Matcher c = clazz.matcher(content);

		p.find();

		String group = p.group();
		String pName = group.replace("package ", "").replace(";", "");

		while (c.find()) {
			String g = c.group();
			String cName = g.replace("message", "").replace("{", "").trim();
			String name = file.getName();
			String replace = name.replace(".p", "P");
			String f = StringUtil.firstToUpperCase(replace);

			f = StringUtil.parseAfter_ToUpperCase(f);


			String path = pName + "." + f + "." + cName;
			if (javaTypeMap.containsKey(cName)) {
				throw new RuntimeException("不存在! " + cName);
			}
			javaTypeMap.put(cName, path);
		}
	}
}
