package org.hhhhhh.fqzs.core;

import org.hhhhhh.fqzs.result.GetHistoryResult;
import org.hhhhhh.fqzs.result.Switchs;
import org.hhhhhh.fqzs.result.Xs;

public class TableData {

	long cd;

	public long getCd() {
		return cd;
	}

	public void setCd(long cd) {
		this.cd = cd;
	}

	long caiJin;
	String id;
	long laBa;
	Switchs switchs;
	Xs xs;

	public Switchs getSwitchs() {
		return switchs;
	}

	public void setSwitchs(Switchs switchs) {
		this.switchs = switchs;
	}

	public Xs getXs() {
		return xs;
	}

	public void setXs(Xs xs) {
		this.xs = xs;
	}

	public long getCaiJin() {
		return caiJin;
	}

	public void setCaiJin(long caiJin) {
		this.caiJin = caiJin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getLaBa() {
		return laBa;
	}

	public void setLaBa(long laBa) {
		this.laBa = laBa;
	}

	public GetHistoryResult getHistory() {
		return history;
	}

	public void setHistory(GetHistoryResult history) {
		this.history = history;
	}

	GetHistoryResult history;

}