package org.javaplus.game.common.http;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Parameters {

	private Map<String, Parameter> ps;

	public Parameters() {
		ps = new HashMap<String, Parameter>();
	}

	public boolean isEmpty() {
		return ps.isEmpty();
	}

	public Collection<Parameter> getParameters() {
		return ps.values();
	}

	public void put(String key, Object value) {
		Parameter p = new Parameter(key, value);
		ps.put(key, p);
	}

}
