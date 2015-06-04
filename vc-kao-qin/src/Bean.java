import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bean {
	String name;
	String time;
	Date date;

	public Bean(String name, String date, String time) {
		this.name = name;
		this.time = time;
		try {
			this.date = RecordList.sf.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
