package cn.javaplus.build.file;

import java.io.File;
import java.io.IOException;

import cn.javaplus.log.Log;

public class CopyFile {

	public void copy(String src, String dst) {

		File srcf = new File(src);
		File dstf = new File(dst);

		if (!dstf.exists()) {
			try {
				dstf.createNewFile();
			} catch (IOException e) {
				System.err.println("无法拷贝至:" + dstf + " 请检查路径");
				return;
			}
		}

		Output out = new Output(dstf);
		Input in = new Input(srcf);
		while (true) {
			byte[] data = in.readNext(10240);
			if (data.length <= 0) {
				break;
			}
			out.write(data);
		}
		out.flush();

		in.close();
		out.close();

		Log.d("copy file:", src, dst);
	}

}
