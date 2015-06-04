package cn.javaplus.build.deploy;

/**
 * 部署规则
 * @author 林岑
 *
 */
public interface DeployRule {

	void pack();

	void upload();

	void start();

	void stop();
}
