package org.javaplus.game.common.android;
//package cn.javaplus.twolegs.android;
//
//import org.mozilla.javascript.Context;
//import org.mozilla.javascript.Function;
//import org.mozilla.javascript.Scriptable;
//
//import cn.javaplus.twolegs.log.Log;
//import cn.javaplus.twolegs.script.ScriptManager;
//
//public class RhScriptManager implements ScriptManager {
//
//	@Override
//	public Object run(String script, String methodName, Object... args) {
//		Context cx = Context.enter();
//
//		try {
//			Scriptable scope = cx.initStandardObjects();
//			cx.evaluateString(scope, script, "<cmd>", 1, null);
//
//			Object fObj = scope.get(methodName, scope);
//			if (!(fObj instanceof Function)) {
//				Log.e(" not found method");
//			} else {
//				Function f = (Function) fObj;
//				return f.call(cx, scope, scope, args);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			Context.exit();
//		}
//		return null;
//
//	}
//
//}
