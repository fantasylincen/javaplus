package org.hhhhhh.fqzs.core;

public class SecChangeEvent {

	private int oldSec;
	private int newSec;

	public SecChangeEvent(int oldSec, int newSec) {
		this.oldSec = oldSec;
		this.newSec = newSec;
	}

	public int getOldSec() {
		return oldSec;
	}

	public int getNewSec() {
		return newSec;
	}

}
