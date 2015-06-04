package db.domain;import java.util.Arrays;public class UserPiecesBagImpl implements UserPiecesBag , 
				UserItem
			, 
				DBBag
			{	@Key	private String uname;	private int capacity;	public UserPiecesBagImpl() {	}	public UserPiecesBagImpl(UserPiecesBag c) {		this.uname = c.getUname();		this.capacity = c.getCapacity();	}	@Override	public String tableName() {		return "user_pieces_bag";	}	public void setUname(String uname) {		this.uname = uname;	}	public String getUname() {		return this.uname;	}public void addUname(String uname) {		this.uname += uname;}	public void setCapacity(int capacity) {		this.capacity = capacity;	}	public int getCapacity() {		return this.capacity;	}public void addCapacity(int capacity) {		this.capacity += capacity;}}