//package cn.javaplus.clickscreen.android;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebSettings.RenderPriority;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import cn.javaplus.clickscreen.script.ScriptExcutor;
//
//@SuppressLint({ "UseValueOf", "SetJavaScriptEnabled" })
//public class AndroidScriptExcutor implements ScriptExcutor {
//
//	private WebView w;
//
//	public AndroidScriptExcutor(Activity activity) {
//		w = new WebView(activity);
//	}
//
//	@SuppressWarnings("deprecation")
//	@Override
//	public Object call(String script, String functionName, Object... args) {
//		WebSettings settings = w.getSettings();
//		settings.setJavaScriptEnabled(true);
//		settings.setRenderPriority(RenderPriority.HIGH);
//		w.setWebChromeClient(new WebChromeClient());
//
//		WebViewClient mWebViewClient = new WebViewClient() {
//
//			@Override
//			public void onPageFinished(WebView webView, String url) {
//				w.loadUrl("javascript:这里就是你要调用的js方法");
//			}
//		};
//
//		w.setWebViewClient(mWebViewClient);
//
//		w.loadUrl("file:///android_asset/page.html");
//		return null;
//	}
//
//	@Override
//	public Integer callInt(String script, String functionName, Object... args) {
//		Object back = call(script, functionName, args);
//		if(back == null)
//			return null;
//		return new Integer(back.toString());
//	}
//
//	@Override
//	public Double callDouble(String script, String functionName, Object... args) {
//		Object back = call(script, functionName, args);
//		if(back == null)
//			return null;
//		return new Double(back.toString());
//	}
//
//	@Override
//	public String callString(String script, String functionName, Object... args) {
//		Object back = call(script, functionName, args);
//		if(back == null)
//			return null;
//		return back.toString();
//	}
//
//}
