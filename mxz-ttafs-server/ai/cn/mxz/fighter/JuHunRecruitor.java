package cn.mxz.fighter;

import java.util.Iterator;
import java.util.List;

import cn.javaplus.util.Util.Random;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.JuHunRecruitTemplet;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.spirite.SpiriteManager;

class JuHunRecruitor {

	private City	city;
	private JuHunRecruitTemplet	temp;

	JuHunRecruitor(City city, JuHunRecruitTemplet temp) {
		this.city = city;
		this.temp = temp;
	}


	int recruit() {
		check();
		int id = random();
		reduce();
		SpiriteManager manager = city.getSpiriteManager();
		manager.add(id);
		return id;
	}


	private void reduce() {
		city.getPlayer().reduce(PlayerProperty.JU_HUN, temp.getNeed());
	}


	private void check() {
		int need = temp.getNeed();
		city.getChecker().checkPlayerProperty(PlayerProperty.JU_HUN, need);
	}


	private int random() {
		int step = randomStep();
		List<FighterTemplet> temps = FighterTempletConfig.findByStep(step);
		filter(temps);
		return cn.javaplus.util.Util.Collection.getRandomOne(temps).getId();
	}


	private void filter(List<FighterTemplet> temps) {
		Iterator<FighterTemplet> it = temps.iterator();
		while (it.hasNext()) {
			FighterTemplet ft = (FighterTemplet) it.next();
			if(!ft.getGodType().equals(temp.getGodBank())) {
				it.remove();
			}
		}
	}


	private int randomStep() {
		int [] w = getW();
		int index = Random.getRandomIndex(w);
		return getStep(index);
	}


	private int getStep(int index) {

		String steps = temp.getSteps().trim();
		String[] split = steps.split("\\|");
		String s1 = split[index];
		String[] s2 = s1.split(",");
		return new Integer(s2[0].trim());
	}


	private int[] getW() {

		String steps = temp.getSteps().trim();
		String[] split = steps.split("\\|");
		int [] a = new int [split.length] ;
		for (int i = 0; i < a.length; i++) {
			String temp = split[i];
			a [i] = new Integer(temp.split(",")[1].trim());
		}
		return a;
	}

//	public static void main(String[] args) {
//		JuHunRecruitor j = new JuHunRecruitor(null, JuHunRecruitTempletConfig.get(2));
//		for (int i = 0; i < 1000; i++) {
//			int randomStep = j.randomStep();
//			System.out.println(randomStep);
//		}
//
//	}
}
