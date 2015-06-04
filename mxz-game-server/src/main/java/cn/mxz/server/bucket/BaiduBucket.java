package cn.mxz.server.bucket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;

public class BaiduBucket {

	/**
	 * 读取百度Bucket中的配置信息
	 * 
	 * @param path
	 * @return
	 */
	public static Properties getProperties(String bucket, String path) {
		String key = bucket + "/" + path;
		return load(key);
	}

	private static Properties load(String path) {
		InputStream is = null;
		try {
			java.util.Properties p = new java.util.Properties();
			URL url = new URL("http://bcs.duapp.com/" + path);
			is = url.openStream();
			p.load(is);
			return new PropertiesImpl(p);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(is);
		}
	}

	private static String loadText(String path) {
		InputStream is = null;
		try {
			URL url = new URL("http://bcs.duapp.com/" + path);
			return Util.File.getContent(url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(is);
		}
	}

	private static void close(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
	}

	static String host = "bcs.duapp.com";
	static String accessKey = "0h3Zk1KPg6TFHor3LWihcksv";
	static String secretKey = "fedEIZkTjTvhfVps7xzbp9MCh5YGnxiE";

	public static void put(String bucket, String fileName, Object data) {
		BCSCredentials credentials = new BCSCredentials(accessKey, secretKey);
		BaiduBCS baiduBCS = new BaiduBCS(credentials, host);
		baiduBCS.setDefaultEncoding("UTF-8");

		File file = createTmpFile(data.toString());

		PutObjectRequest request = new PutObjectRequest(bucket, "/" + fileName,
				file);
		ObjectMetadata metadata = new ObjectMetadata();
		request.setMetadata(metadata);
		BaiduBCSResponse<ObjectMetadata> response = baiduBCS.putObject(request);
		ObjectMetadata objectMetadata = response.getResult();
		Log.d("x-bs-request-id: " + response.getRequestId());
		Log.d(objectMetadata);
	}

	private static File createTmpFile(String data) {
		try {
			File file = File.createTempFile("java-sdk-", ".txt");
			file.deleteOnExit();

			Writer writer = new OutputStreamWriter(new FileOutputStream(file));
			writer.write(data);
			writer.close();
			return file;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getText(String bucket, String fileName) {
		String key = bucket + "/" + fileName;
		return loadText(key);
	}
}
