import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;

public class FileUtil {

	@Test
	public void createDirs() {
		String path = "E:\\微云网盘\\来自HUAWEI U9508\\";
		
		for (int i = 2009; i <= 2015; i++) {
			for (int m = 1; m <= 12; m++) {
				String name = i + "年" + m + "月";
				System.out.println(name);
				File f = new File(path + name);
				System.out.println(f.exists());
				if(!f.exists()) {
					f.mkdir();
				}
			}
		}
	}
	
	@Test
	public void showTime() throws JpegProcessingException, FileNotFoundException {
		String path = "E:\\微云网盘\\照片\\[Camera Album]\\2013_11_22_20_23_17.A777.jpg";
		InputStream jpegFile = new FileInputStream(new File(path));
		Metadata m = com.drew.imaging.jpeg.JpegMetadataReader.readMetadata(jpegFile);
	
		Iterable<Directory> it = m.getDirectories();
		for (Directory d : it) {
			Date date = d.getDate(0);
			System.out.println(date);
		}
	}
	
	@Test
	public void rename() throws FileNotFoundException,
			IOException, JpegProcessingException {
		Properties p = new Properties();
		p.load(new FileReader(new File("path.properties")));
		String path = p.getProperty("path");

		String name = p.getProperty("name");

		File f = new File(path);
		String[] list = f.list();
		int i = 1;
		for (String fn : list) {
			File file = new File(path + "/" + fn);
			if(file.isDirectory()) {
				continue;
			}
			String tail = fn.substring(fn.lastIndexOf('.'), fn.length());

			tail = tail.toLowerCase();
			
			Set<String> ts = new HashSet<String>();
			ts.add(".jpg");
			ts.add(".png");
			ts.add(".bmp");

			if (ts.contains(tail)) {

				File fto = new File("E:/微云网盘/照片/" + name + " " + getIndex(i++)
						+ tail);
				if(fto.exists()) {
					throw new RuntimeException("文件已经存在");
				}
				file.renameTo(fto);

				System.out.println(file + " 成功！");
			}
		}

	}

	private static String getIndex(int i) {
		if (i < 10) {
			return "000" + i;
		}
		if (i < 100) {
			return "00" + i;
		}
		if (i < 1000) {
			return "0" + i;
		}
		return i + "";
	}

}
