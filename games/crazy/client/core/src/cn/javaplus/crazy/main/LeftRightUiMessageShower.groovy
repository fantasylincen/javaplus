package cn.javaplus.crazy.main;

import cn.javaplus.crazy.D;

public class LeftRightUiMessageShower {

	public void showMessage(IPlayerUI r, String message) {
		if (message.equals(D.WAIT_JDZ)) {
			r.getMessageBox().setText("wait for him jdz");
		} else if (message.equals(D.WAIT_QDZ)) {
			r.getMessageBox().setText("wait for him qdz");
		} else if (message.equals(D.JDZ)) {
			r.getJiaoDiZhu().setVisible(true);
		} else if (message.equals(D.BJDZ)) {
			r.getBuJiao().setVisible(true);
		} else if (message.equals(D.QDZ)) {
			r.getQiangDiZhu().setVisible(true);
		} else if (message.equals(D.BQDZ)) {
			r.getBuQiang().setVisible(true);
		} else {
			r.getMessageBox().setText("unknown message:" + message);
		}
	}
}
