import java.util.Date;

public class LogDatum {
	private String name;
	private Date time;
	private String event;
	private String result;

	public LogDatum(String a, Date b, String c, String d) {
		name = a;
		time = b;
		event = c;
		result = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String toCSV() {
		return name+","+time.toString()+","+event+","+result;
	}
}
