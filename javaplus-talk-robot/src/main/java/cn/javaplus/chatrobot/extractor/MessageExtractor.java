package cn.javaplus.chatrobot.extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javaplus.log.Debuger;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 
 * 提取语言中的参数
 * 
 * 比如  : 
 * 			 输入1:		student1,student2
 *           输入2:      今天    student1   把   student2   打了
 *           输入3:      今天小明把小张打了
 *           返回:        <student1:小明><student2:小张>  
 * 
 * 
 * 				如果语意不符, 报错
 * 比如:
 * 
 *           输入1:      今天 [student1] 把 [student2] 打了
 *           输入2:      今天小明把小张打了五次
 *                 报错
 * 
 * @param message
 * @return
 */
// * 输入1: student1,student2
// * 输入2: 今天 student1 把 student2 打了
// * 输入3: 今天小明把小张打了
// * 返回: <student1:小明><student2:小张>
//
// MessageExtractor message = new MessageExtractor();
// message.defineArg("student1");
// message.defineArg("student2");
// message.defineFormat("今天 student1 把 student2 打了");
// message.input("今天 student1 把 student2 打了");
// Result r = message.getResult();
//  r == <student1:小明><student2:小张>
public class MessageExtractor {

	public class MyIndex {

		private String name;
		private int index;

		public MyIndex(String name, int index) {
			this.name = name;
			this.index = index;
		}
		
		public String getName() {
			return name;
		}
		
		public int getIndex() {
			return index;
		}

	}

	List<Arg> args = Lists.newArrayList();
	private String format;
	
	/**
	 * 定义一个参数
	 */
	public void defineArg(Arg arg) {
		args.add(arg);
	}
	
	/**
	 * 定义一个参数
	 * @param argName 参数名
	 * @param argPattern 该参数必须匹配的正则表达式
	 */
	public void defineArg(String argName, String argPattern) {
		defineArg(new Arg(argName, argPattern));
	}
	
	/**
	 * 定义一个参数, 该参数默认匹配 (.*)
	 * @param argName 参数名
	 */
	public void defineArg(String argName) {
		defineArg(new Arg(argName, ".*"));
	}

	/**
	 * 定义输入的 语言的格式
	 */
	public void defineFormat(String format) {
		this.format = Util.Str.toSBC(format);
	}


	public Map<String, String> getResult(String input) {

		if(args.isEmpty()) {
			return Maps.newHashMap();
		}
		
		input = Util.Str.toSBC(input);
		

		if(input.isEmpty()) {
			throw new MessageExtractorException("输入不能为空");
		}
		
		List<String> ls = splitFormatByArgs();
		
		List<String> argList = getArgs(ls);
		List<String> unArgList = getUnArgList(ls, argList);
		List<String> valueList = getValues(unArgList, input);


		if(argList.size() != valueList.size()) {
			throw new MessageExtractorException("无法匹配 input 和 format  --  input = " + input + "   format = " + format + "        --   解析结果:"   + argList + " -- " +  valueList);
		}
		
		HashMap<String, String> map = Maps.newHashMap();
		for (int i = 0; i < argList.size(); i++) {
			String key = argList.get(i);
			String value = valueList.get(i);
			checkMatches(key, value);
			
			value = Util.Str.toDBC(value);
			map.put(key, value);
		}
		
		
		return map;
	}

	private List<String> getUnArgList(List<String> all, List<String> argList) {
		ArrayList<String> a = Lists.newArrayList(all);
		a.removeAll(argList);
		return a;
	}

	private void checkMatches(String key, String value) {
		String regex = getRegex(key);
		if(!value.matches(regex)) {
			throw new MessageExtractorException("[   " + value + "  ] 必须要满足:  " + regex);
		}
	}

	private String getRegex(String key) {
		for (Arg a : args) {
			if(key.equals(a.getName())) {
				return a.getRegex();
			}
		}
		throw new NullPointerException("无法找到" + key + "所对应的正则表达式!");
	}

	/**
	 * @param ls  非参数拆分段
	 * @param input
	 * @return
	 */
	private List<String> getValues(List<String> ls, String input) {
		String r = new String(input);
		String sp = "akasaldkfjlaskdjfhaglnsdlf;ajpuqwroipq";
		
		for (String string : ls) {
			String temp = new String(r);
			r = r.replaceAll(string, sp);
			if(temp.equals(r)) {
				throw new MessageExtractorException("未找到: [" + string + "]");
			}
		}
		
		r = r.replaceAll("(" + sp + ")+", sp);
		String[] split = r.split(sp);
		List<String> lis = Lists.newArrayList();
		for (String string : split) {
			if(!string.isEmpty()) {
				lis.add(string);
			}
		}
		return lis;
	}

	private List<String> getArgs(List<String> ls) {
		List<String> as = Lists.newArrayList();
		for (String string : ls) {
			if(isArg(string)) {
				as.add(string);
			}
		}
		return as;
	}

	private boolean isArg(String string) {
		for (Arg a : this.args) {
			if(a.getName().equals(string)) {
				return true;
			}
		}
		return false;
	}

	// * 输入1: student1,student2
	// * 输入2: 今天 student1 把 student2 打了
	// * 输入3: 今天小明把小张打了
	// * 返回: <student1:小明><student2:小张>
	/**
	 * 将format分解成     [今天] [student1] [把] [student2] [打了]
	 * @return
	 */
	private List<String> splitFormatByArgs() {

		String string = new String(format);
		List<String> ls = Lists.newArrayList();
		while(true) {
			if(string.isEmpty()) {
				break;
			}
			MyIndex index = findFirstSplitIndex(string);
			if(index == null) {
				ls.add(string);
				break;
			} else {
				int i = index.getIndex();
				String a = string.substring(0, i);
				string = string.substring(i, string.length()).replaceFirst(index.getName(), "");
				if(!a.isEmpty()) {
					ls.add(a);
				}
				ls.add(index.getName());
			}
		}
		return ls;
	}

	private MyIndex findFirstSplitIndex(String string) {
		int length = string.length();
		for (int i = 0; i < length; i++) {
			String substring = string.substring(i, length);
			Arg a = getStartArg(substring);
			if(a != null) {
				return new MyIndex(a.getName(), i);
			}
		}
		return null;
	}

	private Arg getStartArg(String substring) {
		for (Arg a : args) {
			String name = a.getName();
			if(substring.startsWith(name)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * 判断是否匹配的上
	 * @param input
	 * @return
	 */
	public boolean matches(String input) {
		if(args.isEmpty()) {
			input = Util.Str.toSBC(input);
			format = Util.Str.toSBC(format);
			return input.equals(format);
		}
		try {
			getResult(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		MessageExtractor e = new MessageExtractor();
		e.defineArg("serverId");
		e.defineArg("userId");
		e.defineFormat("serverIduserId");
		Map<String, String> result = e.getResult("5区lc1");
		Debuger.debug("MessageExtractor.main() " + result);
	}
}
