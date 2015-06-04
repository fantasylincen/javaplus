package _util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtils {

	public void write(String file, String content) {
		BufferedOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			File f = new File(file);
			if (!f.exists()) {
				int lastIndexOf = file.lastIndexOf("/");
				if (lastIndexOf == -1) {
					lastIndexOf = file.lastIndexOf("\\");
				}
				File path = new File(file.substring(0, lastIndexOf));
				path.mkdirs();
				f.createNewFile();
			}

			fos = new BufferedOutputStream(new FileOutputStream(f));
			osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(content);
			osw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Closer.close(fos);
		}
	}
}
