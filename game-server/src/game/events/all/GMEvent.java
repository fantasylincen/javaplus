package game.events.all;

import game.events.EventBase;
import game.events.all.gm.YoXiR;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.xsocket.connection.INonBlockingConnection;

import user.UserInfo;
import cn.javaplus.log.Debuger;
import cn.javaplus.security.DES;

public class GMEvent extends EventBase {

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
	}

	public void run(INonBlockingConnection con, ByteBuffer buf) throws IOException {
		int c = buf.capacity();
		byte[] data = new byte[c];
		buf.get(data, 0, c);
		data = Arrays.copyOfRange(data, 2, c);
		String script = new String(data);
		script = DES.decrypt(script);

		YoXiR yoXiR = new YoXiR();
		yoXiR.run(script);
		yoXiR.waitItRunOver();
		String result = yoXiR.getResult();
//		Debuger.debug("YoXiTransformImpl.yoXi()    " + result);
		
		ByteBuffer ret = buildEmptyPackage( Short.MAX_VALUE - 2 );
		byte[] bytes = result.getBytes();
		ret.putShort((short) bytes.length);
		ret.put(bytes);
		sendPackage(con, ret);
	}

}
