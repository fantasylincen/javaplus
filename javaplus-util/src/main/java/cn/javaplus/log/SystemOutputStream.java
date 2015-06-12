package cn.javaplus.log;

public class SystemOutputStream implements IPrintStream {

	@Override
	public void println(Object obj) {
		System.out.println(obj);
	}

}