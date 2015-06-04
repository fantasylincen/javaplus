//package cn.mxz.json;
//
//import java.io.IOException;
//
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//
//import com.alibaba.fastjson.JSON;
//
//public class JsonTest {
//	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
//		MyData m = new MyDataImpl();
//		Object json = JSON.toJSON(m);
//		System.out.println(json);
//
//
////		ObjectMapper ma = new ObjectMapper();
////		MyData v = ma.readValue(json + "", MyData.class);
////		System.out.println(v);
//
//		Class<?> c = Class.forName("cn.mxz.Test");
//		System.out.println(c);
//
////		Compiler.
//	}
//}
