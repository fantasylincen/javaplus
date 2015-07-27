package cn.javaplus.baidu.bucket.uploader;

import java.io.File;

import cn.javaplus.log.Log;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.model.X_BS_ACL;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;

public class ChangeContentDisposition {
	public static void main(String[] args) {
		String baiduBucketName = "vc-web";
		String path = "/index.html";
		PutObjectRequest request = new PutObjectRequest(baiduBucketName, path,
				new File("E:/360data/重要数据/桌面/index.html"));

		String accessKey = "0h3Zk1KPg6TFHor3LWihcksv";
		String secretKey = "fedEIZkTjTvhfVps7xzbp9MCh5YGnxiE";
		
		BCSCredentials credentials = new BCSCredentials(accessKey, secretKey);
		BaiduBCS baiduBCS = new BaiduBCS(credentials, "bcs.duapp.com");
		baiduBCS.setDefaultEncoding("UTF-8");
		
		
		ObjectMetadata o = new ObjectMetadata();
		o.setContentDisposition("inline;filename=index.html");
		request.setMetadata(o);;
		
		BaiduBCSResponse<ObjectMetadata> response = baiduBCS.putObject(request);
		
		ObjectMetadata objectMetadata = response.getResult();
		
		Log.d("x-bs-request-id: " + response.getRequestId());
		Log.d(objectMetadata);
		X_BS_ACL t = X_BS_ACL.PublicReadWrite;
		baiduBCS.putObjectPolicy(baiduBucketName, path, t);
	}
}
