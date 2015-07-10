package cn.vgame.a.turntable.generator;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.turntable.Result;
import cn.vgame.a.turntable.ResultGenerator;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;
import cn.vgame.a.turntable.swt.SwitchAll;
import cn.vgame.share.Xml;

public class ExcelGenerator implements ResultGenerator {

	@Override
	public Result generateReward(SwitchAll switchs) {
		
		randomXNumber = Util.Random.get(1, 6);
		
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("weights");
		List<Row> all = sheet.getAll();
		List<Row> randoms = random(all);
		Result r = new Result();
		r.setResult(randoms);
		return r;
	}
	
	public int getRandomXNumber() {
		return randomXNumber;
	}
	
	private List<Row> random(List<Row> all) {
		ArrayList<Row> ls = Lists.newArrayList();
		Row row = randomFirst(all); // 随机出第一个结果
		ls.add(row);
		new RandomByExcel().randomSongDeng(all, ls, row, randomXNumber); // 如果出鲨鱼, 送灯
		return ls;
	}

	

	private Row randomFirst(List<Row> all) {
		Row row;
		Controller c = Turntable.getInstance().getController();
		if (Turntable.getInstance().getMustGenerateId() > 0) { // 本轮第一个必出
			row = getMust(all);
		} else if (c.isZhengZaiGanShe() && c.isHappen()) { // 如果干涉程序正在运行,
			row = c.randomOne(all); // 则由干涉程序生成结果
		} else {
			row = new RandomByExcel().randomOne(all, randomXNumber);
		}
		return row;
	}
	

	
	/** 随机倍率序号 */
	private int randomXNumber = 1;
	

	private Row getMust(List<Row> all) {
		for (Row row : all) {
			if (row.getInt("id") == Turntable.getInstance().getMustGenerateId())
				return row;
		}
		throw new NullPointerException("row " + Turntable.getInstance().getMustGenerateId()
				+ "not found");
	}
}
