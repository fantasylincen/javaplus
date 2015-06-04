package cn.javaplus.plugins.generator.protocol.config;

import java.util.ArrayList;
import java.util.List;

public class InterfaceImpl implements IInterface {

	private String			name;

	private List<IMethod>	methods;

	public InterfaceImpl(String name) {
		this.name = name;
		this.methods = new ArrayList<IMethod>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IMethod getMethod(String methodName) {
		for (IMethod m : methods) {
			if (m.getName().equals(methodName)) {
				return m;
			}
		}
		throw new IllegalArgumentException(methodName + " at " + name + " not found!");
	}

	@Override
	public void addMethod(IMethod method) {
		this.methods.add(method);
	}

	@Override
	public String getBeanName() {
		return firstToLowerCase(name);
	}

	@Override
	public String getInterfaceName() {
		return name;
	}

	public static String firstToLowerCase(String src) {
		return src.replaceFirst(src.substring(0, 1), src.substring(0, 1).toLowerCase());
	}
}
