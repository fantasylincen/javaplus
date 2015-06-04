package cn.mxz.thirdpaty;

import cn.javaplus.util.Closer;


/**
 * 短连接实现
 * @author 林岑
 */
class EratingConnectorImpl1 extends AbstractEratingConnector {

	public EratingConnectorImpl1(String eratingPath) {
		super(eratingPath);
	}


	protected void end() {
		close();
	}

	protected void start() {
		connect();
		bind();
	}

	private void close() {
		Closer.close(socket);
	}

}
