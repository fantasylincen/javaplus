package cn.javaplus.log;


public class SystemOut implements Out {

	IPrintStream out = new SystemOutPutStream();

	@Override
	public void println(String head, Object... message) {
		Log.print(head, out, message);
	}

}