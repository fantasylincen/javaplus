package cn.javaplus.crazy.deploy;

/**
 * 部署规则
 * @author 林岑
 *
 */
public interface DeployRule {

	void pack();

	void upload();

	void backup();

	void start();

	void stop();
}
