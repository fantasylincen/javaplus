/**
 * 
 */
package experiment;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author liukun
 * 2012-8-20
 * 
 * 通过url下载配置文件
 * 
 */
public class HttpDownLoad {
	
	private static int		BUFFER_SIZE = 8096;//缓冲区大小  
	private static String 	ZIP_URL = "http://www.sina.com";//配置文件所在的地址 
	private static String 	ZIP_FILE = "d:\\ZIP.htm";//配置文件所在的地址 
	
	public static void downloadCfgFile() throws IOException{
		FileOutputStream fos = null;  
        BufferedInputStream bis = null;  
        HttpURLConnection httpUrl = null;  
        URL url = null;  
        byte[] buf = new byte[BUFFER_SIZE];  
        int size = 0;  
  
        //建立链接  
        url = new URL( ZIP_URL );  
        httpUrl = (HttpURLConnection) url.openConnection();  
        //连接指定的资源  
        httpUrl.connect();  
        //获取网络输入流  
        bis = new BufferedInputStream(httpUrl.getInputStream());  
        //建立文件  
       // File file = new File(ZIP_FILE);//配置文件所在的地址 );  
//        if(!file.exists()) {  
//            if(!file.getParentFile().exists()) {  
//                file.getParentFile().mkdirs();  
//            }  
//            file.createNewFile();  
//        }  
        fos = new FileOutputStream(ZIP_FILE);  
  
//        if (this.DEBUG)  
//            System.out.println("正在获取链接[" + destUrl + "]的内容...\n将其保存为文件["  
//                    + fileName + "]");  
  
        //保存文件  
        while ((size = bis.read(buf)) != -1)  
            fos.write(buf, 0, size);  
  
        fos.close();  
        bis.close();  
        httpUrl.disconnect();   
		
	}
	public static void main(String[] args) throws IOException {
		downloadCfgFile();
	}

}
