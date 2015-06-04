package cn.mxz.listeners.tianming;

import java.util.List;

import message.S;
import cn.mxz.ExclusiveTemplet;
import cn.mxz.ExclusiveTempletConfig;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.equipment.TianMingChangeEvent;
import cn.mxz.formation.AdditionType;

/**
 * 当天命增加了, 提示
 * 
 * @author 林岑
 * 
 */
public class ShowTianMingAddListener implements Listener<TianMingChangeEvent> {

	@Override
	public void onEvent(TianMingChangeEvent e) {
		City user = e.getUser();

		List<Integer> as = e.getEquipmentTianMingAdd();
		as.addAll(e.getHeroTianMingAdd());
		as.addAll(e.getSkillTianMingAdd());

		for (Integer id : as) {

			ExclusiveTemplet temp = ExclusiveTempletConfig.get(id);
			if (temp.getJudge() == 0) {
				continue;
			}
			try {
				AdditionType type = AdditionType.fromNum(temp.getNatureType());
				String text = type.getText();
				String percent = (int) (temp.getNaturePar() * 100) + "%";
				user.getMessageSender().send(S.S60220, temp.getName(), percent,
						text);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
