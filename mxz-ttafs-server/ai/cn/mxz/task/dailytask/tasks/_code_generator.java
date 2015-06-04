package cn.mxz.task.dailytask.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cn.mxz.DailyTaskTemplet;
import cn.mxz.DailyTaskTempletConfig;


public class _code_generator {

	public static void main(String[] args) throws IOException {

		DailyTaskTempletConfig.load();
		List<Integer> keys = DailyTaskTempletConfig.getKeys();

		for (Integer id : keys) {

			createFile(id);
		}
	}

	@SuppressWarnings("resource")
	private static void createFile(Integer id) throws IOException {

		DailyTaskTemplet temp = DailyTaskTempletConfig.get(id);

		String code = temp.getCode()
				.replaceAll("\\[newline\\]", "\r")
				.replaceAll("\\&gt;", ">")
				.replaceAll("\\&lt;", "<")
				;

//		&gt;
//		&gt;
		String name = System.getProperty("user.dir") + "/ai/" + _code_generator.class.getName();

		name = name.replaceAll("\\.", "/");

		name = name.replaceAll(_code_generator.class.getSimpleName(), "");

		name = name + "T" + id + ".java";

		name = name.replaceAll("/", "\\\\");


		File f = new File(name);

		if(f.exists()) {

			f.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(f);

		if(code.isEmpty()) {
			code = "error";
		}
		fileWriter.write(code);

		fileWriter.flush();
	}
}
