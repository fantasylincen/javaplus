package cn.mxz.cornucopia;

public class CornucopiaFactory {

	public static Cornucopia getCornucopia(String id) {

		CornucopiaImpl c = new CornucopiaImpl(id);


		return c;
	}

}
