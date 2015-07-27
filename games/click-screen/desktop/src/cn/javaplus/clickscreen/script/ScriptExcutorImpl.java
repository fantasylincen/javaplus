//package cn.javaplus.clickscreen.script;
//
//import javax.script.Invocable;
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//
//public class ScriptExcutorImpl implements ScriptExcutor {
//
//	private ScriptEngine engine;
//
//	public ScriptExcutorImpl() {
//		ScriptEngineManager mgr = new ScriptEngineManager();
//		engine = mgr.getEngineByName("JavaScript");
//	}
//
//	@Override
//	public Object call(String script, String functionName, Object... args) {
//		try {
//			engine.eval(script);
//			Invocable inv = (Invocable) engine;
//			return inv.invokeFunction(functionName);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Override
//	public Integer callInt(String script, String functionName, Object... args) {
//		Object back = call(script, functionName, args);
//		if (back == null)
//			return null;
//		return new Integer(back.toString());
//	}
//
//	@Override
//	public Double callDouble(String script, String functionName, Object... args) {
//		Object back = call(script, functionName, args);
//		if (back == null)
//			return null;
//		return new Double(back.toString());
//	}
//
//	@Override
//	public String callString(String script, String functionName, Object... args) {
//		Object back = call(script, functionName, args);
//		if (back == null)
//			return null;
//		return back.toString();
//	}
//
//}
