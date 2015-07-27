package cn.javaplus.baidu.bucket.uploader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.FileWithRelativePath;
import cn.javaplus.web.WebContentFethcer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.model.X_BS_ACL;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;

/**
 * Hello world!
 * 
 */
public class App {

	public static class FileData {
		String md5;
		String name;

		public FileData(Object object) {
			JSONObject o = (JSONObject) object;
			md5 = o.getString("md5");
			name = o.getString("name");
		}

		public String getMd5() {
			return md5;
		}

		public String getName() {
			return name;
		}
	}

	private static final String VERSION_FILE = "files.json";

	static String host;
	static String accessKey;
	static String secretKey;
	private static BaiduBCS baiduBCS;

	public static void main(String[] args) {
		List<JSONObject> configs = getConfigs();

		for (JSONObject config : configs) {
			List<FileData> remoteFiles = getRemoteFiles(config);
			updateFilesMd5(config);
			uploadFiles(config, remoteFiles);
		}
	}

	private static List<FileData> getRemoteFiles(JSONObject config) {
		String h = "http://" + host + "/" + config.getString("baiduBucketName")  + "/files.json";
		
		Log.d("正在获取文件列表:" + h);
		String list = WebContentFethcer.get("utf8", h);
		Log.d("获取文件列表成功:" + h);
		
		JSONArray obj = JSON.parseObject(list).getJSONArray("files");
		
		ArrayList<FileData> ls = Lists.newArrayList();
		for (Object object : obj) {
			ls.add(new FileData(object));
		}
		return ls;
	}

	private static List<JSONObject> getConfigs() {
		URL resource = Resources.getResource("config.json");
		JSONObject obj = JSON.parseObject(Util.File.getContent(resource));

		host = obj.getString("host");
		accessKey = obj.getString("accessKey");
		secretKey = obj.getString("secretKey");

		
		JSONArray array = obj.getJSONArray("uploads");
		ArrayList<JSONObject> ls = Lists.newArrayList();
		for (Object object : array) {
			ls.add((JSONObject) object);
		}
		return ls;
	}

	private static void uploadFiles(JSONObject config,
			List<FileData> remoteFiles) {
		initBaiduBucket();
		String dir = config.getString("localDir");
		List<FileWithRelativePath> files = Util.File
				.getFileWithRelativePaths(dir);
		for (FileWithRelativePath file : files) {
			if (md5Changed(file, remoteFiles)) {
				Log.d("upload", file.getName());
				upload(file, config);
				Log.d("upload over", file.getName());
			}
		}
	}

	private static boolean md5Changed(FileWithRelativePath file,
			List<FileData> remoteFiles) {
		
//		if(file.getName().equals(VERSION_FILE))
//			return false;
		
		String md5 = Util.Secure.md5(Util.File.getBytes(file.getFile()));

		FileData fileData = getData(file.getRelativePath(), remoteFiles);
		if (fileData == null)
			return true;

		String m = fileData.getMd5();
		return !m.equals(md5);
	}

	private static FileData getData(String relativePath,
			List<FileData> remoteFiles) {
		for (FileData fileData : remoteFiles) {
			if (fileData.getName().equals(relativePath)) {
				return fileData;
			}
		}
		return null;
	}

	private static void initBaiduBucket() {
		BCSCredentials credentials = new BCSCredentials(accessKey, secretKey);
		baiduBCS = new BaiduBCS(credentials, host);
		baiduBCS.setDefaultEncoding("UTF-8");
	}

	private static void upload(FileWithRelativePath file, JSONObject config) {
		String name = file.getName();
		if (name.contains(" ")) {
			throw new RuntimeException("文件名中不能有空格:" + name);
		}
		String baiduBucketName = config.getString("baiduBucketName");
		String path = "/" + config.getString("bucketDir")
				+ file.getRelativePath();
		PutObjectRequest request = new PutObjectRequest(baiduBucketName, path,
				file.getFile());
		BaiduBCSResponse<ObjectMetadata> response = baiduBCS.putObject(request);
		ObjectMetadata objectMetadata = response.getResult();
		Log.d("x-bs-request-id: " + response.getRequestId());
		Log.d(objectMetadata);
		X_BS_ACL t = X_BS_ACL.PublicReadWrite;
		baiduBCS.putObjectPolicy(baiduBucketName, path, t);
	}

	private static void updateFilesMd5(JSONObject config) {
		List<FileWithRelativePath> files = Util.File
				.getFileWithRelativePaths(config.getString("localDir"));
		String vp = getVersionFilePath(config);
		JSONArray o = new JSONArray();
		for (FileWithRelativePath file : files) {
			if (!file.getRelativePath().equals(VERSION_FILE))
				putMd5(file, o);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("files", o);
		Util.File.write(vp, jsonObject.toJSONString());
	}

	private static void putMd5(FileWithRelativePath file, JSONArray o) {
		String name = file.getRelativePath();
		byte[] content = Util.File.getBytes(file.getFile());
		String md5 = Util.Secure.md5(content);
		JSONObject jo = new JSONObject();
		jo.put("name", name);
		jo.put("md5", md5);
		jo.put("size", content.length);
		o.add(jo);
	}

	private static String getVersionFilePath(JSONObject config) {
		String p = config.getString("localDir") + VERSION_FILE;
		ensureExist(p);
		return p;
	}

	private static void ensureExist(String p) {
		File f = new File(p);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
