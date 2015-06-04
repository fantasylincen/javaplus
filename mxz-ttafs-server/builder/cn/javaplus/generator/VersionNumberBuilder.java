package cn.javaplus.generator;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;



/**
 * 版本号生成器
 * @author 林岑
 *
 */
public class VersionNumberBuilder {



	private IProperties	p;

	private String	fileName;

//	private String	md5Old;
//
//	private String	filesMD5;

	public VersionNumberBuilder() {

		fileName = "res/build_version.properties";
//
		p = Util.Property.getProperties(fileName);
//
//
//		md5Old = p.getProperty("md5");
//
//		FileCamera fc = new FileCamera("D:/workspace/MobileServer", ".class", ".svn-base", ".jar", ".doc", ".txt", ".battle" );
//
//		List<File> fs = fc.getFiles();
//
//		StringBuilder sb = new StringBuilder();
//
//		for (File file : fs) {
//
//			if(file.getName().equals("D.java")) {
//
//				continue;
//			}
//
//			String md5 = fc.getMD5(file);
//
//			sb.append(md5);
//		}
//
//		filesMD5 = Util.Secure.md5(sb.toString());
	}

	public String buildJava() {

		return "		/**\r" +
				"		 * 版本号\r" +
				"		 */\r" +
				"		public static final String VERSION = \"" + getBaseVersion() + "." + getBuildNumber() + "\";\r\r";
	}


	public String buildAs() {
		return "			/**\r" +
				"		 	* 服务器版本号\r" +
				"		 	*/\r" +
				"			public static const SERVER_VERSION:String = \"" + getBaseVersion() + "." + getBuildNumber() + "\";\r\r";

	}

	private String getBuildNumber() {
		return "2";
	}

//	private String getBuildNumber() {
//
//		String text = p.getProperty("version");
//
//		Integer number = new Integer(text);
//
//		if(!filesMD5.equals(md5Old)) {
//
//			number++;
//
//			p.setProperty("md5", filesMD5);
//		}
//
//		p.setProperty("version", number + "");
//
//		try {
//
//			p.list(new PrintStream(new File(fileName)));
//
//		} catch (FileNotFoundException e) {
//
//			throw new RuntimeException(e);
//		}
//
//		return number + "";
//
//	}

	/**
	 * 主版本号 . 子版本号 . 修正版本号
	 *
	 * Major_Version_Number . Minor_Version_Number . Revision_Number
	 */
	private String getBaseVersion() {

		IProperties p = Util.Property.getProperties("res/version.properties");

		return p.getProperty("version").trim();
	}

	public static void main(String[] args) {

		String text = new VersionNumberBuilder().buildJava();

		System.out.println(text);
	}
}
