package cn.mxz;

public class FighterConfig {

	public static IFighterTemplet get(int t) {
		IFighterTemplet temp = FighterTempletConfig.get(t);
		if (temp != null) {
			return temp;
		}

		temp = CopterMonsterTempletConfig.get(t);
		if (temp != null) {
			return temp;
		}

		DogzTemplet tt = DogzTempletConfig.get(t);
		if (tt != null) {
			temp = new FighterTempletAdaptor(tt);
			return temp;
		}

		return null;
	}

}
