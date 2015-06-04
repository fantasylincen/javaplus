package cn.javaplus.plugins.generator.protocol.config;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.plugins.util.StringUtils;

public class ClassImpl implements IClass {

	private String			className;

	private List<IMethod>	methods;

	private List<IMethod>	serverToClientMethods;

	private String			tail;

	private InterfaceImpl	interf;

	/**
	 * @param className
	 *            类名
	 * @param tail
	 *            类名后缀
	 */
	public ClassImpl(String className, String tail) {

		this.className = className;

		this.tail = tail;

		methods = new ArrayList<IMethod>();

		serverToClientMethods = new ArrayList<IMethod>();

		this.interf = new InterfaceImpl(className);
	}

	@Override
	public List<IMethod> getMethods() {
		return methods;
	}

	@Override
	public IInterface getInterface() {
		return this.interf;
	}

	@Override
	public String getBeanName() {
		return getInterface().getBeanName();
	}

	@Override
	public void addMethod(IMethod method) {

		methods.add(method);

		interf.addMethod(method);
	}

	@Override
	public void addServerToClientMethod(IMethod method) {

		serverToClientMethods.add(method);

		interf.addMethod(method);
	}

	@Override
	public String getName() {
		return StringUtils.firstToUpperCase(className + tail);
	}

	@Override
	public IMethod getMethod(String methodName) {

		for (IMethod m : methods) {

			if (m.getName().equals(methodName)) {

				return m;
			}
		}

		throw new IllegalArgumentException(methodName + " at " + className + " not found!");
	}

	@Override
	public String toString() {

		return className;
	}

	@Override
	public String getInterfaceName() {

		return getInterface().getName();
	}

	@Override
	public List<IMethod> getServerToClientMethods() {

		return serverToClientMethods;
	}
}
