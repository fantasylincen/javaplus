package org.javaplus.clickscreen.script;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class RhinoScriptExcutor implements ScriptExcutor {

	private Object app;
	private Object os;

	public RhinoScriptExcutor(Object app, Object os) {
		this.app = app;
		this.os = os;
	}

	@Override
	public Object call(String script, String functionName, Object... args) {

		Context rhino = Context.enter();
		rhino.setOptimizationLevel(-1);

		try {

			Scriptable scope = rhino.initStandardObjects();
			ClassLoader lod = getClass().getClassLoader();
			Object loader = Context.javaToJS(lod, scope);

			ScriptableObject.putProperty(scope, "app", app);
			ScriptableObject.putProperty(scope, "os", os);
			ScriptableObject.putProperty(scope, "classLoader", loader);
			
			rhino.evaluateString(scope, script, "RhinoScript", 1, null);

			Object object = scope.get(functionName, scope);
			Function function = (Function) object;
			Object result = function.call(rhino, scope, scope, args);

			if (result instanceof String) {
				return (String) result;
			} else if (result instanceof NativeJavaObject) {
				return (String) ((NativeJavaObject) result)
						.getDefaultValue(String.class);
			} else if (result instanceof NativeObject) {
				return (String) ((NativeObject) result)
						.getDefaultValue(String.class);
			}

			return result.toString();
		} finally {
			Context.exit();
		}
	}

	@Override
	public Integer callInt(String script, String functionName, Object... args) {
		Object back = call(script, functionName, args);
		if (back == null)
			return null;
		return new Integer(back.toString());
	}

	@Override
	public Double callDouble(String script, String functionName, Object... args) {
		Object back = call(script, functionName, args);
		if (back == null)
			return null;
		return new Double(back.toString());
	}

	@Override
	public String callString(String script, String functionName, Object... args) {
		Object back = call(script, functionName, args);
		if (back == null)
			return null;
		return back.toString();
	}

}
