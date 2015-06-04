package com.taobao.protobuf.popmenu;

import org.eclipse.core.resources.IFile;

public class Location {

	private IFile	file;

	private String	msg;

	private int		line;

	public IFile getFile() {
		return file;
	}

	public void setFile(IFile file) {
		this.file = file;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

}
