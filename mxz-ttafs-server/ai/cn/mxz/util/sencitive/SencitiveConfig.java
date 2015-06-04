package cn.mxz.util.sencitive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;

import cn.javaplus.util.Closer;

import com.google.common.collect.Lists;

/**
 * 敏感词配置
 * @author 	林岑
 * @time	2013-5-7
 */
public class SencitiveConfig {
	
	private static Collection<String> allCanNotUse;
	
	/**
	 * 是否是敏感词
	 * @param k
	 * @return
	 */
	public static boolean isSencitive(String k) {
		return allCanNotUse.contains(k);
	}
	
	/**
	 * 判断改名字是否可以使用(是否不包含敏感词)
	 * @param name
	 * @return
	 */
	public static boolean canUse(String name) {
		
		for (String s : allCanNotUse) {
			if(name.contains(s)) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void init() {

		allCanNotUse = Lists.newArrayList();
		
		final File f = new File("res/words_outlaw.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			
			while(true) {
				final String line = br.readLine();
				if(line == null) {
					break;
				}
				if(line.trim().isEmpty()) {
					continue;
				}
				allCanNotUse.add(line);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(br);
		}
	}
	
}
