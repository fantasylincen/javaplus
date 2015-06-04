package cn.mxz.gm;

import java.util.Map;

public class GMHandler extends AbstractHandler {


	@Override
	protected String doGet(Map<String, Object> parameters) {
		System.out.println( parameters );
		return "aaaaaaaaaaaaaa";
	}

//	@Override
//	protected String doGet(Map<String, Object> parameters) {
//
//		
//	}

}
