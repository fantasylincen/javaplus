package org.hhhhhh.fqzs.result;

public class RollEndEvent {
	private Roller roller;

	public RollEndEvent(Roller roller) {
		this.roller = roller;
	}

	public Roller getRoller() {
		return roller;
	}

	public void setRoller(Roller roller) {
		this.roller = roller;
	}
}
