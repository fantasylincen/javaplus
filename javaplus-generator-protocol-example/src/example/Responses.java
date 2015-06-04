package example;

import cn.javaplus.comunication.ProtocolUser;
import cn.javaplus.comunication.Responser;

public class Responses {

	private Responser	responser;

	public Responses(ProtocolUser user) {
		responser = new Responser(user);
	}

}
