package cn.javaplus.game.defender.json;

import org.json.JSONException;
import org.json.JSONObject;

public class  Frame {

	private int x;
	private int y;
	private int width;
	private int height;
	private boolean rotated;


	Frame (JSONObject o) {
		
		try {
			
			 JSONObject frame = o.getJSONObject("frame");
			
			x = frame.getInt("x");
			y = frame.getInt("y");
			width = frame.getInt("w");
			height = frame.getInt("h");
			rotated = o.getBoolean("rotated");
			
		} catch (JSONException e) {
		}
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public boolean isRotated() {
		return rotated;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public void setRotated(boolean rotated) {
		this.rotated = rotated;
	}
	
	
}
