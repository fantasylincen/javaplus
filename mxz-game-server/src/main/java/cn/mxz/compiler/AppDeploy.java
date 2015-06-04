package cn.mxz.compiler;

import cn.javaplus.build.deploy.Deploy;
import cn.javaplus.build.maven.Maven;
import cn.javaplus.util.MainArgs;

/**
 * 部署
 * 
 * @author 林岑
 */
public class AppDeploy {
	public static void main(String[] args) throws Exception {

		MainArgs.set("-host fantasylincen.oicp.net -user root -password 258819045");
		
		new CodeCompiler().compiler();

		Deploy deploy = new Deploy();
		installDependencis();
		deploy.deploy(new GameServerDeployRule());// 部署网关
	}

	private static void installDependencis() {
		Maven maven = new Maven();
		maven.install("git/javaplus/javaplus-util");
		maven.install("git/javaplus/javaplus-compile-util");
		maven.install("git/javaplus/javaplus-generator-mongodb-dao");
	}
}
