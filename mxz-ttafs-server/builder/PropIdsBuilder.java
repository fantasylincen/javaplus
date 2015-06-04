import java.util.Set;

import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;
import cn.mxz.prizecenter.PropIdCheck;


public class PropIdsBuilder {

	public static void build() {
		
		Templet t = new Templet(get("res/build/PropIds.temp"));

		setFileds(t);
		
		t.writeToFile("D:/workspace/MobileServer/ai/cn/mxz/prop/PropIds.java");
		
	}



	private static void setFileds(Templet t) {
		
		Set<Integer> all = PropIdCheck.getAllId();
		StringPrinter out = new StringPrinter();
		for (Integer id : all) {
			String name = PropIdCheck.getName(id);
			if(name == null) {
				continue;
			}
			buildField(out, id, name);
		}

		t.set("ID_FILEDS", out.toString());
	}

	private static void buildField(StringPrinter out, Integer id, String name) {
		out.println("		/**");
		out.println("		 * " + name );
		out.println("		 */");
		out.println("		public static final int " + getName(name, id) +  " = " + id + ";");
	}

	private static String getName(String name, Integer id) {
		name = Util.Chinese.getPinYinHump(name);
		name = name.replaceAll("u:", "v");
		return name + "_" + id;
	}

	private static String get(String string) {
		return Util.File.getContent(string);
	}

}
