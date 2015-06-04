package cn.mxz.task.achieve.tasks;

import java.util.List;

public class MethodImpl implements Method {

	private String	name;
	private String	returnType;
	private List<String>	args;
	private String	doc;

	public MethodImpl(String name, String returnType, List<String> args, String doc) {
		this.name = name;
		this.returnType = returnType;
		this.args = args;
		this.doc = doc;
	}

	@Override
	public String getDoc() {
		return doc;
	}

	@Override
	public String getReturnType() {
		return returnType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<String> getArgs() {
		return args;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((args == null) ? 0 : args.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((returnType == null) ? 0 : returnType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MethodImpl other = (MethodImpl) obj;
		if (args == null) {
			if (other.args != null)
				return false;
		} else if (!args.equals(other.args))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (returnType == null) {
			if (other.returnType != null)
				return false;
		} else if (!returnType.equals(other.returnType))
			return false;
		return true;
	}



}
