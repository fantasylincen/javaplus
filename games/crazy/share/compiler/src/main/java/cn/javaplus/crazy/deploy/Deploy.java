package cn.javaplus.crazy.deploy;

public class Deploy {

	public void deploy(DeployRule rule) {
		rule.pack();
		rule.upload();
		rule.backup();
		rule.stop();
		rule.start();
	}
}
