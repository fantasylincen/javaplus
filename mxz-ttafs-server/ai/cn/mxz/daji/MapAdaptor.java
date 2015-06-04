package cn.mxz.daji;

import cn.mxz.CustodianMapTemplet;
import cn.mxz.MapTemplet;

public class MapAdaptor implements MapTemplet {

	private CustodianMapTemplet	temp;

	public MapAdaptor(CustodianMapTemplet temp) {
		this.temp = temp;
	}

	public int getId() {
		return temp.getId();
	}

	public String getName() {
		return temp.getName();
	}

	public String getMonsterId() {
		return temp.getMonsterId();
	}

	public int getMonsterGrade() {
		return temp.getMonsterGrade();
	}

	public String getDriveAward() {
		return temp.getDriveAward();
	}

	public int getCoolTime() {
		return temp.getCoolTime();
	}

	public int hashCode() {
		return temp.hashCode();
	}

	public float getWilsonParam() {
		return temp.getWilsonParam();
	}

	public boolean equals(Object obj) {
		return temp.equals(obj);
	}

	public String toString() {
		return temp.toString();
	}

	@Override
	public String getBossDropOut() {
		return "";
	}

	@Override
	public String getLineMonsterDropOut() {
		return "";
	}

	@Override
	public String getLineBossDropOut() {
		return "";
	}

	@Override
	public String getMonsterDropOut() {
		return "";
	}

	@Override
	public int getPlotBegin1() {
		return 0;
	}

	@Override
	public int getPlotBegin2() {
		return 0;
	}

	@Override
	public int getPlotEnd1() {
		return 0;
	}

	@Override
	public int getPlotEnd2() {
		return 0;
	}

	@Override
	public int getIsNew() {
		return 0;
	}

	@Override
	public int getChapterId() {
		return 0;
	}

	@Override
	public int getSceneId() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public float getIgnoreScale() {
		return 0;
	}

	@Override
	public float getBoxScale() {
		return 0;
	}

	@Override
	public float getDemonScale() {
		return 0;
	}

	@Override
	public float getAskScale() {
		return 0;
	}

	@Override
	public String getBossId() {
		return null;
	}

	@Override
	public int getPictype() {
		return 0;
	}

	@Override
	public float getLineWilsonParam() {
		return 0;
	}

	@Override
	public int getMonsterNumber() {
		return 0;
	}

	@Override
	public float getBossParam() {
		return 0;
	}

	@Override
	public float getLineBossParam() {
		return 0;
	}

	@Override
	public int getHurtMin() {
		return temp.getHurtMin();
	}
	

}
