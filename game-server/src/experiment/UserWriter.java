package experiment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class UserWriter implements Writerable {
	// getter setter methods
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	};

	// private fields
	private int number;
	private String id;
	private int age;
	private boolean administrator;
	private String userName;
	private String password;
	private String text;
	private byte[] image;

	@Override
	public void write(DataOutput data) throws IOException {
		data.writeInt(number);
		data.writeUTF(id);
		data.writeInt(age);
		data.writeBoolean(administrator);
		data.writeUTF(userName);
		data.writeUTF(password);
		data.writeUTF(text);
		data.write(image);
		
		byte[] byteArray = new byte[4];
		for (int n = 0; n < 4; n++)
			byteArray[3 - n] = (byte) (number>>> (n * 8));
		
	}

	@Override
	public void read(DataInput di) throws IOException {
		this.number = di.readInt();
		this.id = di.readUTF();
		this.age = di.readInt();
		this.administrator = di.readBoolean();
		this.userName = di.readUTF();
		this.password = di.readUTF();
		this.text = di.readUTF();
		// 读取图片
		this.image = new byte[2048];
		try {
			di.readFully(this.image);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

	public static byte[] in2byte(InputStream is) throws IOException {
		byte[] bs = new byte[1024];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len = -1;
		while ((len = is.read(bs)) != -1) {
			bos.write(bs, 0, len);
		}
		bs = bos.toByteArray();
		return bs;
	}

	public static void copy(InputStream in, OutputStream out)
			throws IOException {
		byte[] buf = new byte[1024];
		while (true) {
			int len = in.read(buf);
			if (len < 0)
				break;
			out.write(buf, 0, len);
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		UserWriter user = new UserWriter();
		user.setUserName("likehua");
		user.setPassword("password");
		user.setId(UUID.randomUUID().toString());
		user.setNumber(123);
		user.setAdministrator(false);
		// 加一段文字
		user.setText("做一个天气预报，可以保存要显示的城市名称 在增加城市页面，我的rms关键操作如下（其中SetCanvas.cityName是一个Vector，用来保存城市名，在执行此段代码之前，我已经把新增加的城市名添加进了这个Vector ");
		// 传入一张图片
		user.setImage( in2byte(new FileInputStream("E:\\Desktop\\003.JPG")) );
		
//		File file = new File("E:\\Desktop\\user.data");
//		DataOutput db = new DataOutputStream( new FileOutputStream( file ) );
//		user.write( db );
		user.write( new DataOutputStream(new FileOutputStream("E:\\Desktop\\user.data")) );
		
		System.out.println("完毕");
		System.out.println("....读取开始...");
		UserWriter u = new UserWriter();
		u.read( new DataInputStream(new FileInputStream("E:\\Desktop\\user.data")) );
		System.out.println(u.getUserName());
		System.out.println(u.getPassword());
		System.out.println(u.isAdministrator());
		System.out.println(u.getText());
		System.out.println( u.getId() );
		// 拿出图片
		copy(new ByteArrayInputStream(u.getImage()), new FileOutputStream("E:\\Desktop\\copy.JPG"));

	}

}
