package org.hhhhhh.prophet.account4app;

import org.hhhhhh.prophet.JsonAction;

public class TestAction extends JsonAction {

	public class TestResult {
		public String getA() {
			return a;
		}


		public String getB() {
			return b;
		}


		public String getC() {
			return c;
		}

	}

	String a;
	String b;
	String c;
	
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	@Override
	protected Object exec() {
		return new TestResult();
	}
}