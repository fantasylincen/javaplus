package cn.javaplus.plugins.generator.protocol.config;

import java.util.List;

public interface IClass extends IBeanAble {

	List<IMethod> getMethods();

	List<IMethod> getServerToClientMethods();

	IInterface getInterface();

	void addMethod(IMethod method);

	IMethod getMethod(String methodName);

	void addServerToClientMethod(IMethod method);
}
