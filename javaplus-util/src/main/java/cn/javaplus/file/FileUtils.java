package cn.javaplus.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import cn.javaplus.util.Closer;




public class FileUtils {

	public static void write(String file, String content) {
		OutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			File f = new File(file);
			if(!f.exists())	{
				int lastIndexOf = file.lastIndexOf("/");
				if(lastIndexOf == -1) {
					lastIndexOf = file.lastIndexOf("\\");
				}
				File path = new File(file.substring(0, lastIndexOf));
				path.mkdirs();
				f.createNewFile();
			}

			fos = new FileOutputStream(f);
			osw = new OutputStreamWriter(fos,"UTF-8");
			osw.write(content);
			osw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Closer.close(fos);
		}
	}
}
