package cn.javaplus.build;

import java.util.List;

public class ErrorEvent {

	private List<String> errors;

	public ErrorEvent(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}
}