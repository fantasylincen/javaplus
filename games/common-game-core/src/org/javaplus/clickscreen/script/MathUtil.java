package org.javaplus.clickscreen.script;

public class MathUtil {
	public double sin(double a) {
		return Math.sin(a);
	}

	public double cos(double a) {
		return Math.cos(a);
	}

	public double tan(double a) {
		return Math.tan(a);
	}

	public double asin(double a) {
		return Math.asin(a);
	}

	public double acos(double a) {
		return Math.acos(a);
	}

	public double atan(double a) {
		return Math.atan(a);
	}

	public double toRadians(double angdeg) {
		return Math.toRadians(angdeg);
	}

	public double toDegrees(double angrad) {
		return Math.toDegrees(angrad);
	}

	public double exp(double a) {
		return Math.exp(a);
	}

	public double log(double a) {
		return Math.log(a);
	}

	public double log10(double a) {
		return Math.log10(a);
	}

	public double sqrt(double a) {
		return Math.sqrt(a);
	}

	public double ceil(double a) {
		return Math.ceil(a);
	}

	public double floor(double a) {
		return Math.floor(a);
	}

	public double atan2(double y, double x) {
		return StrictMath.atan2(y, x);
	}

	public double pow(double a, double b) {
		return StrictMath.pow(a, b);
	}

	public int abs(int a) {
		return (a < 0) ? -a : a;
	}

	public long abs(long a) {
		return (a < 0) ? -a : a;
	}

	public float abs(float a) {
		return (a <= 0.0F) ? 0.0F - a : a;
	}

	public double abs(double a) {
		return (a <= 0.0D) ? 0.0D - a : a;
	}

	public int max(int a, int b) {
		return (a >= b) ? a : b;
	}

	public long max(long a, long b) {
		return (a >= b) ? a : b;
	}

	public float max(float a, float b) {
		return Math.max(a, b);
	}

	public double max(double a, double b) {
		return Math.max(a, b);
	}

	public int min(int a, int b) {
		return (a <= b) ? a : b;
	}

	public long min(long a, long b) {
		return (a <= b) ? a : b;
	}

	public float min(float a, float b) {
		return Math.min(a, b);
	}

	public double min(double a, double b) {
		return Math.min(a, b);
	}

	public double sinh(double x) {
		return StrictMath.sinh(x);
	}

	public double cosh(double x) {
		return StrictMath.cosh(x);
	}

	public double tanh(double x) {
		return StrictMath.tanh(x);
	}

}
