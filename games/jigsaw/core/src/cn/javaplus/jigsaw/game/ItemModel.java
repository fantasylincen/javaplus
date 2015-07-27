package cn.javaplus.jigsaw.game;

import com.alibaba.fastjson.JSON;
import com.badlogic.gdx.graphics.Color;

public class ItemModel implements Comparable<ItemModel> {

	private String name;
	private String score;
	private String rank;
	private String roleId;
	private Color fontColor = Color.WHITE;

	public ItemModel(String name, String score, String rank, String roleId) {
		this.name = name;
		this.score = score;
		this.rank = rank;
		this.roleId = roleId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public String getScore() {
		return score;
	}

	public String getRank() {
		return rank;
	}

	@Override
	public int compareTo(ItemModel o) {
		return new Integer(getRank()) - new Integer(o.getRank());
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public Color getFontColor() {
		return fontColor;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
