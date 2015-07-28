package org.javaplus.game.common.cache;

import java.util.ArrayList;
import java.util.List;

import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.properties.GameProperties;
import org.javaplus.game.common.util.Lists;
import org.javaplus.game.common.util.Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class ChangedFileList {

	private ArrayList<FileUpdate> list;

	public class FileUpdate {

		int size;
		String name;

		public String getName() {
			return name;
		}

		public int getSize() {
			return size;
		}
	}

	public ChangedFileList(JSONObject object) {
		list = Lists.newArrayList();
		JSONArray arr = object.getJSONArray("files");


		for (Object obj : arr) {
			JSONObject o = (JSONObject) obj;
			String name = o.getString("name");
			FileHandle h = Gdx.files.local(name);
			String md5 = o.getString("md5");
			int size = o.getInteger("size");

			Log.d(name, md5, size);

			if (!h.exists() || !md5Equal(h, md5)) {
				add(name, size);
				Log.d("---------->file changed", name);
			}
		}
	}

	private void add(String name, int size) {
		FileUpdate e = new FileUpdate();
		e.name = name;
		e.size = size;
		list.add(e);
	}

	private boolean md5Equal(FileHandle h, String md5) {
		byte[] data = h.readBytes();
		String md5Old = Util.Secure.md5(data);
		return md5.equals(md5Old);
	}

	public int getCount() {
		return list.size();
	}

	public int getKB() {
		return getB() / 1024;
	}

	private int getB() {
		int a = 0;
		for (FileUpdate f : list) {
			a += f.size;
		}
		return a;
	}

	public List<FileUpdate> getAll() {
		return list;
	}
}
