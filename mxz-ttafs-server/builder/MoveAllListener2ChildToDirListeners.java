import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.util.Util;


/**
 * 将所有Listener2 的子类移动到listners源文件夹下
 * @author 林岑
 *
 */
public class MoveAllListener2ChildToDirListeners {

	public static void move() {
		List<File> files = Util.File.getFiles("ai");
		for (File file : files) {
			String name = file.getName();
			if(!name.endsWith(".java")) {
				continue;
			}
			
			Pattern c = Pattern.compile("class .{1,20} implements Listener[1-2]");
			String content = Util.File.getContent(file);
			Matcher m = c.matcher(content);
			
			if(m.find()) {
				System.out.println(m.group());
				move(file);
			}
		}
	}

	private static void move(File file) {
		try {
			String path = file.getCanonicalPath();
			path = path.replaceAll("D:\\\\workspace\\\\MobileServer\\\\ai\\\\", "D:\\\\workspace\\\\MobileServer\\\\listeners\\\\");
			File file2 = new File(path);
			file.renameTo(file2);
			System.out.println("移动文件:" + file + "     到:     " + file2);
		} catch (IOException e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

}
