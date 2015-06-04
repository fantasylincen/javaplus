package cn.javaplus.cubie.driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.CharBuffer;

public enum Pin {
	PG10("gpio16_pg10", 16), //
	PE6("gpio22_pe6", 22), //
	PD3("gpio29_pd3", 29), //
	PE9("gpio35_pe9", 35), //
	PD7("gpio41_pd7", 41), //
	PD16("gpio48_pd16", 48), //
	PD24("gpio54_pd24", 54), //
	PI11("gpio60_pi11", 60), //
	PH7("gpio67_ph7", 67), //
	PH14("gpio10_ph14", 10), //
	PG9("gpio17_pg9", 17), //
	PE5("gpio23_pe5", 23), //
	PB19("gpio2_pb19", 2), //
	PD12("gpio36_pd12", 36), //
	PD6("gpio42_pd6", 42), //
	PD15("gpio49_pd15", 49), //
	PD26("gpio55_pd26", 55), //
	PI13("gpio61_pi13", 61), //
	PG4("gpio6_pg4", 6), //
	PH15("gpio11_ph15", 11), //
	PG8("gpio18_pg8", 18), //
	PE4("gpio24_pe4", 24), //
	PD2("gpio30_pd2", 30), //
	PD11("gpio37_pd11", 37), //
	PD5("gpio43_pd5", 43), //
	PG6("gpio4_pg6", 4), //
	PD27("gpio56_pd27", 56), //
	PI10("gpio62_pi10", 62), //
	PG1("gpio7_pg1", 7), //
	PI6("gpio12_pi6", 12), //
	PG7("gpio19_pg7", 19), //
	PI9("gpio25_pi9", 25), //
	PD1("gpio31_pd1", 31), //
	PD10("gpio38_pd10", 38), //
	PD20("gpio44_pd20", 44), //
	PD14("gpio50_pd14", 50), //
	PD23("gpio57_pd23", 57), //
	PI12("gpio63_pi12", 63), //
	PG2("gpio8_pg2", 8), //
	PI5("gpio13_pi5", 13), //
	PG3("gpio1_pg3", 1), //
	PI8("gpio26_pi8", 26), //
	PD0("gpio32_pd0", 32), //
	PD9("gpio39_pd9", 39), //
	PD19("gpio45_pd19", 45), //
	PD13("gpio51_pd13", 51), //
	PD22("gpio58_pd22", 58), //
	PB13("gpio64_pb13", 64), //
	PG0("gpio9_pg0", 9), //
	PI4("gpio14_pi4", 14), //
	PE8("gpio20_pe8", 20), //
	PI7("gpio27_pi7", 27), //
	PE11("gpio33_pe11", 33), //
	PB18("gpio3_pb18", 3), //
	PD18("gpio46_pd18", 46), //
	PB2("gpio52_pb2", 52), //
	PD21("gpio59_pd21", 59), //
	PB11("gpio65_pb11", 65), //
	PG11("gpio15_pg11", 15), //
	PE7("gpio21_pe7", 21), //
	PD4("gpio28_pd4", 28), //
	PE10("gpio34_pe10", 34), //
	PD8("gpio40_pd8", 40), //
	PD17("gpio47_pd17", 47), //
	PD25("gpio53_pd25", 53), //
	PG5("gpio5_pg5", 5), //
	PB10("gpio66_pb10", 66);//

	private int number;
	private String pinPath;
	private String valueFile;
	private String dirFile;

	private Pin(String fileName, int number) {
		this.number = number;
		ensureExist();

		pinPath = "/sys/class/gpio/" + fileName + "/";
		valueFile = pinPath + "value";
		dirFile = pinPath + "direction";
	}

	public boolean isIn() {
		BufferedReader in = null;
		FileReader fr = null;
		try {
			fr = new FileReader(dirFile);
			in = new BufferedReader(fr);
			String s = in.readLine();
			return "in".equals(s);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(in);
			Closer.close(fr);
		}
	}

	public void setIn() {

		PrintStream dirOut = null;
		try {
			dirOut = new PrintStream(dirFile);
			dirOut.println("in");
			dirOut.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(dirOut);
		}
	}

	public void setOut() {
		PrintStream dirOut = null;
		try {
			dirOut = new PrintStream(dirFile);
			dirOut.println("out");
			dirOut.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(dirOut);
		}
	}

	private void ensureExist() {
		String path = "/sys/class/gpio/export";
		PrintStream out = null;

		try {
			out = new PrintStream(path);
			out.println(number);
			out.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(out);
		}
	}

	public boolean isOpen() {
		BufferedReader in = null;
		FileReader fr = null;
		try {
			fr = new FileReader(valueFile);
			in = new BufferedReader(fr);
			String s = in.readLine();
			return "1".equals(s);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(in);
			Closer.close(fr);
		}
	}

	public void open() {

		PrintStream out = null;
		try {
			out = new PrintStream(valueFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(out);
		}
		out.println(1);
		out.flush();
	}

	public void close() {
		PrintStream out = null;
		try {
			out = new PrintStream(valueFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(out);
		}
		out.println(0);
		out.flush();
	}

}
