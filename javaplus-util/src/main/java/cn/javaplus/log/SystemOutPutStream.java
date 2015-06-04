package cn.javaplus.log;

public class SystemOutPutStream implements IPrintStream {

	@Override
	public void println(Object obj) {
		System.out.println(obj);
	}

}