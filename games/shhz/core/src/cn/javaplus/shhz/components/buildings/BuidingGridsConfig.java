package cn.javaplus.shhz.components.buildings;

import java.io.File;
import java.util.List;

import cn.javaplus.shhz.assets.Assets;
import cn.javaplus.shhz.collections.Lists;
import cn.javaplus.shhz.random.Fetcher;
import cn.javaplus.shhz.util.Util;
import cn.javaplus.shhz.util.VectorInt;

import com.badlogic.gdx.files.FileHandle;

public class BuidingGridsConfig {

	private List<VectorInt> positions;

	private static final String TAIL = ".mapgrid";

	private Building building;

	public BuidingGridsConfig(Building building) {
		this.building = building;
		FileHandle f = Assets.getFile(building.getPng() + TAIL);
		load(f.readString());
	}

	public void load(String readString) {
		positions = Lists.newArrayList();
		String[] ps = readString.split("\\|");
		for (String xy : ps) {
			if (xy.trim().isEmpty()) {
				continue;
			}
			String[] ss = xy.split(",");

			int x = new Integer(ss[0].trim());
			int y = new Integer(ss[1].trim());
			positions.add(new VectorInt(x, y));
		}
	}

	@Override
	public String toString() {
		Fetcher<VectorInt, String> fetcher = new Fetcher<VectorInt, String>() {

			@Override
			public String get(VectorInt t) {
				return t.getX() + "," + t.getY();
			}
		};

		return Util.Collection.linkWith("|", positions, fetcher);
	}

	/**
	 * dx = gridX - buildingX
	 */
	public List<VectorInt> getPositions() {
		return positions;
	}

	/**
	 * 
	 * @param dx
	 *            gridX - buildingX
	 * @param dy
	 */
	public void changeStatus(int dx, int dy) {
		for (VectorInt v : this.positions) {
			if (dx == v.getX() && dy == v.getY()) {
				positions.remove(v);
				v.set(dx, dy);
				return;
			}
		}
		positions.add(new VectorInt(dx, dy));
	}

	public void save() {
		String filePath = "../android/assets/" + building.getPng() + TAIL;
		File file = new File(filePath);
		Util.File.write(file, toString());
	}

}
