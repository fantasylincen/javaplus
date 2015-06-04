package cn.javaplus.build.code;
//package cn.javaplus.crazy;
//
//import java.io.File;
//
//import cn.javaplus.util.Util;
//
//public class UisDuplicator {
//
//	public void duplicate() {
//		String path = "../../client/ui/Resources/uis/";
//
//		File dst = new File(path);
//		dst.delete();
//		dst.mkdir();
//
//		copy("../../client/ui/Json/", path);
//	}
//
//	private void copy(String src, String dst) {
//		File f = new File(src);
//		File[] fs = f.listFiles();
//		for (File file : fs) {
//			String content = Util.File.getContent(file);
//			Util.File.write(new File(dst + "" + file.getName()), content);
//		}
//	}
//
//}
