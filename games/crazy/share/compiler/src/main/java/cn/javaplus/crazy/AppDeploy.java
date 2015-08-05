package cn.javaplus.crazy;

import cn.javaplus.build.maven.Maven;
import cn.javaplus.crazy.deploy.Deploy;
import cn.javaplus.crazy.deploy.GateDeployRule;
import cn.javaplus.crazy.deploy.ServerDeployRule;

/**
 * 部署
 * 
 * @author 林岑
 */
public class AppDeploy {
	public static void main(String[] args) throws Exception {

		Deploy deploy = new Deploy();
		installDependencis();

		deploy.deploy(new GateDeployRule());// 部署网关
		deploy.deploy(new ServerDeployRule());// 部署逻辑服务器
	}

	private static void installDependencis() {
		Maven maven = new Maven();
		maven.install("git/javaplus/javaplus-util");
		maven.install("git/games/javaplus-game-crazy/share/share");
		maven.install("git/games/javaplus-game-crazy/share/server");
	}
}
