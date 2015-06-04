package cn.javaplus.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * zi
 * @author 林岑
 *
 */
public class AutoDateFormat {


	private static final String	S	= "[-\\./\\|:,'\";#]";

	public Date parse(String source) {

		if(source.matches("[1-2][0-9]{3}" + S + "[0-1][0-9]" + S + "[0-3][0-9] [0-2][0-9]" + S + "[0-5][0-9]")) {
			source = source.replaceAll(S, "-");
			String pattern = "yyyy-MM-dd HH-mm";
			return parse(source, pattern);
		}

		if(source.matches("[1-2][0-9]{3}" + S + "[0-1][0-9]" + S + "[0-3][0-9]")) {
			source = source.replaceAll(S, "-");
			String pattern = "yyyy-MM-dd";
			return parse(source, pattern);
		}

		throw new IllegalArgumentException("无法识别:" + source);
	}

	private Date parse(String source, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(source);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
