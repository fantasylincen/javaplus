package experiment;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import deng.xxoo.utils.XOTime;

public class DynamicClassLoader extends ClassLoader {

//	private static DynamicClassLoader cl = null;
//	private static boolean flag = true;
	private InputStream classFile = null;
	private String name = null;

	/**
	 * @param name
	 *            String 类全名
	 * @param url
	 *            URL 类路径
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public DynamicClassLoader(String name, URL url)
			throws FileNotFoundException, IOException {
		super(getSystemClassLoader());

		this.name = name + ".class";
		// 打开URL指定的资源
		URLConnection con = url.openConnection();
		InputStream classIs = con.getInputStream();
		this.classFile = classIs;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte buf[] = new byte[1024];
		// 读取文件流
		for (int i = 0; (i = classIs.read(buf)) != -1;) {
			baos.write(buf, 0, i);
		}
		classIs.close();
		baos.close();

		// 创建新的类对象
		byte[] data = baos.toByteArray();
		defineClass(name, data, 0, data.length);
	}

	/**
	 * 重载 getResourceAsStream() 是为了返回该类的文件流。
	 * 
	 * @return an InputStream of the class bytes, or null
	 */
	public InputStream getResourceAsStream(String resourceName) {
		try {
			if (resourceName.equals(name)) {
				return this.classFile;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public static void main(String[] arguments) throws Exception {
		// "C:\\Documents and Settings\\Administrator\\Workspaces\\MyEclipse7.5\\lhsp\\web\\WEB-INF\\classes"
		// D:\英雄之城\EclipseProject\ZHSWorldServerToXSocket\bin\experiment
		long l1 	= XOTime.currentTimeMillis();
		String name = "experiment.testss";
		URL url 	= new URL("file:/D:/英雄之城/EclipseProject/ZHSWorldServerToXSocket/bin/experiment/testss.class");
		ClassLoader cl = new DynamicClassLoader(name, url);
		Class<?> c = Class.forName(name, false, cl);
		// 实例化
//		Object obj = c.newInstance();
		testInterface i = (testInterface) c.newInstance();
		i.run( 12, 4 );
		System.out.println( XOTime.currentTimeMillis() - l1 );
	}
}
