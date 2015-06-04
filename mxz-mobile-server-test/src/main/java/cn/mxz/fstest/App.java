package cn.mxz.fstest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;



public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream(new File("D:\\workspace\\MobileServerTest\\src\\xxx")));
		
		Collection<Object> values = p.values();
		Set<Object> ks = p.keySet();
		for (Object object : ks) {
			
			System.out.println(object + p.getProperty(object.toString()));
		}
	}
}
