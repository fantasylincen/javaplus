package cn.mxz.chuangzhen;

import java.util.List;

import cn.mxz.CopterDifficultyTemplet;
import cn.mxz.CopterDifficultyTempletConfig;

public class ChuangZhenUtil {

	/**
	 * 1:力战 2:奋战 3:血战
	 *
	 * @param type
	 * @param player
	 * @return
	 */
	public static CopterDifficultyTemplet getTemplet(int type, ChuangZhenPlayer player) {
		int count = player.getFormationCount();
		List<CopterDifficultyTemplet> all = CopterDifficultyTempletConfig.findByDifficulty(parseType(type));
		for (CopterDifficultyTemplet temp : all) {
			if (temp.getNumber() == count) {
				return temp;
			}
		}
		throw new RuntimeException(count + "," + type);
	}

	/**
	 * 客户端发过来的战斗类型和 配置表里面的战斗类型不一致, 转换一下
	 * @param type
	 * @return
	 */
	private static int parseType(int type) {
		if (type == 1) {
			return 3;
		}
		if (type == 3) {
			return 1;
		}
		return type;
	}
}
