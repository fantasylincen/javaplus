//package cn.javaplus.twolegs.android;
//
//import android.app.Activity;
//import cn.javaplus.twolegs.script.ScriptManagerAsync;
//
//import com.evgenii.jsevaluator.JsEvaluator;
//import com.evgenii.jsevaluator.interfaces.JsCallback;
//
//public class AndroidScriptManager implements ScriptManagerAsync {
//
//	public class CallBackAdaptor implements JsCallback {
//
//		private ScriptCallBack back;
//
//		public CallBackAdaptor(ScriptCallBack back) {
//			this.back = back;
//		}
//
//		@Override
//		public void onResult(String value) {
//			if (back != null)
//				back.onBack(value);
//		}
//
//	}
//
//	JsEvaluator mJsEvaluator;
//
//	public AndroidScriptManager(Activity activity) {
//		mJsEvaluator = new JsEvaluator(activity);
//	}
//
//	@Override
//	public void run(String script, ScriptCallBack back, Object... args) {
//		mJsEvaluator.callFunction(script, new CallBackAdaptor(back), "run", args);
//	}
//
//}
