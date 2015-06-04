package cn.mxz.city;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

public class CityGenerator {

	public static void main(String[] args) throws IOException {

		BufferedReader br = null;

		String line = null;
		try {
			br = new BufferedReader(new FileReader("res/build/modules"));
			String METHOD_TEMP = get("methodtemp");

			StringBuffer FILEDS = new StringBuffer();

			StringBuffer FREE_MEMORY = new StringBuffer();

			StringBuffer GETTERS = new StringBuffer();

			StringBuffer LOAD_ALL = new StringBuffer();
			StringBuffer FREES = new StringBuffer();
			StringBuffer RELOADS = new StringBuffer();



			while ((line = br.readLine()) != null) {
				String[] split = line.split(":");
				String type = split[0];
				String fname = split[1];
				String create = split[2];

				FILEDS.append("	private " + type + " " + fname + ";\r\r");
				FREES.append("	public void free" + Util.Str.firstToUpperCase(fname) + "() {\r");
				FREES.append("		this." + fname + " = null;\r");
				FREES.append("	}\r");


				RELOADS.append("	public void reload" + Util.Str.firstToUpperCase(fname) + "() {\r");
				RELOADS.append("		free" + Util.Str.firstToUpperCase(fname) + "();\r");
				RELOADS.append("		get" + Util.Str.firstToUpperCase(fname) + "();\r");
				RELOADS.append("	}\r");
				
				
				
				
				GETTERS.append(METHOD_TEMP.replaceAll("TYPE", type).replaceAll("UPPER_NAME", Util.Str.firstToUpperCase(fname)).replaceAll("LOWER_NAME", fname)
				.replaceAll("CREATE", create));

				FREE_MEMORY.append("		" + fname + " = null;\r");
				LOAD_ALL.append("		get" + Util.Str.firstToUpperCase(fname) + "();\r");


//				{
//					"name":"player" ,
//					"create":"Player:player:new PlayerImpl(this, dto)"
//				},
//
//
//				System.out.println("	{");
//				System.out.println("		\"name\":\"" + fname +  "\",");
//				System.out.println("		\"create\":\"" + line + "\"");
//				System.out.println("	},");
			}

			writeToFile(FILEDS, FREE_MEMORY, GETTERS, LOAD_ALL, FREES, RELOADS);
		} catch (Exception e) {
			throw new RuntimeException(line);
		} finally {
			Closer.close(br);
		}
	}

	private static void writeToFile(StringBuffer FILEDS, StringBuffer FREE_MEMORY, StringBuffer GETTERS, StringBuffer LOAD_ALL, StringBuffer FREES, StringBuffer RELOADS) throws IOException {
		String f = getCityFile();
		String temp = get("citytemp");
		temp = temp.replaceAll("FILEDS", FILEDS + "");
		temp = temp.replaceAll("GETTERS", GETTERS + "");
		temp = temp.replaceAll("FREE_MEMORY", FREE_MEMORY + "");
		temp = temp.replaceAll("LOAD_ALL", LOAD_ALL + "");
		temp = temp.replaceAll("FREES", FREES + "");
		temp = temp.replaceAll("RELOADS", RELOADS + "");
		temp = temp.replaceAll("CITY_HEAD", getHead());
		Util.File.write(f, temp);
	}

	private static String getHead() {
		String content = Util.File.getContent(getCityFile());
		int index = content.indexOf("{");
		content = content.substring(0, index + 1);
		return content;
	}

	private static String getCityFile() {
		return "ai\\cn\\mxz\\city\\City.java";
	}

	private static String get(String string) {
		InputStream is = null;
		try {
			is = new FileInputStream("res/build/" + string);

			byte[] data = new byte[102400];

			int read = is.read(data);

			data = Arrays.copyOf(data, read);

			return new String(data);
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(is);
		}
	}

}
