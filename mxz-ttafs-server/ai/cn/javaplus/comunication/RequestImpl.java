package cn.javaplus.comunication;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class RequestImpl implements Request {

	private String className;
	private String methodName;
	private List<Object> args;

	@SuppressWarnings("unchecked")
	public RequestImpl(JSONObject data) {
		className = (String) data.get("className");
		methodName = (String) data.get("methodName");
		args = (List<Object>) data.get("args");
	}
	
	@Override
	public String getClassName() {
		return className;
	}
	
	@Override
	public String getMethodName() {
		return methodName;
	}
	
	@Override
	public Object[] getArgsArray() {
		Object[] os = new Object[args.size()];
		for (int i = 0; i < os.length; i++) {
			os[i] = args.get(i);
		}
		return os;
	}
}
