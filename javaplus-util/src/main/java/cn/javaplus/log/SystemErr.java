package cn.javaplus.log;


public class SystemErr implements Out {
	
	public class SystemErrPutStream implements IPrintStream {

		@Override
		public void println(Object obj) {
			System.err.println(obj);
		}
	}

	IPrintStream err = new SystemErrPutStream();

	@Override
	public void println(String head, Object... message) {
		Log.print(head, err, message);
	}

}