package cn.javaplus.database;

/**
 * @author 	林岑
 * @since	2013年4月29日 20:56:49
 */
public class DBProperties {

	private String Drives;
	private String DataCon;
	private String User;
	private String Password;

	public String getDrives() {
		return Drives;
	}

	public void setDrives(String drives) {
		Drives = drives;
	}

	public String getDataCon() {
		return DataCon;
	}

	public void setDataCon(String dataCon) {
		DataCon = dataCon;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public String toString() {
		return "DBProperties [Drives=" + Drives + ", DataCon=" + DataCon + ", User=" + User + ", Password=" + Password + "]";
	}
	
	
}