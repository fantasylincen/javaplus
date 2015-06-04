package cn.javaplus.plugins.generator.protocol.config;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodBackImpl implements MethodBack {

	private String			c;
	private String			m;
	private GeneratorConfig	config;
	private List<String>	args;

	/**
	 * <back>text</back>
	 * 
	 * @param text
	 *            text back节点里面的完整内容
	 * @param config
	 */
	public MethodBackImpl(String text, GeneratorConfig config) {
		String[] ss = text.replaceAll("\\(.*\\)", "").split("\\.");
		String className = ss[0].trim();
		String methodName = ss[1].trim();

		this.config = config;

		args = buildArgs(text);

		c = className;
		m = methodName;
	}

	/**
	 * 
	 * @param text
	 *            back节点里面的完整内容
	 * @return
	 */
	private List<String> buildArgs(String text) {

		Pattern p = Pattern.compile("\\(.*\\)");
		Matcher m = p.matcher(text);

		List<String> ls = new ArrayList<String>();

		boolean find = m.find();
		if (find) {
			text = m.group();
			text = text.replaceAll("\\(", "");
			text = text.replaceAll("\\)", "");

			String[] ss = text.split(",");
			for (int i = 0; i < ss.length; i++) {
				ss[i] = ss[i].trim();
				ls.add(ss[i]);
			}
		}
		return ls;
	}

	@Override
	public IInterface getClazz() {
		return config.getInterface(c);
	}

	@Override
	public IMethod getMethod() {
		return getClazz().getMethod(m);
	}

	@Override
	public List<String> getArgs() {
		return args;
	}

}
