package cn.vgame.b.physical;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;
import cn.vgame.b.account.Role;
import cn.vgame.b.bag.Bag;
import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.MissionDataDto;
import cn.vgame.b.gen.dto.MongoGen.MongoMap;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;
import cn.vgame.b.result.ErrorResult;
import cn.vgame.b.turntable.GetBagAction.BagItem;
import cn.vgame.share.KeyValue;
import cn.vgame.share.Xml;

/**
 * 
 */
public class Physical {

	private final Role role;

	public Physical(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}


	public int getCount(int id) {
		RoleDto dto = role.getDto();
		MongoMap<MissionDataDto> data = dto.getMissionData();
		MissionDataDto d = data.get(id + "");
		return d.getStar();
	}

	/**
	 * 获取当前体力数，存储到数据库中
	 */

	public void setPhysical(int physical){
		RoleDto dto = role.getDto();
        dto.setPhysical(physical);
		Daos.getRoleDao().save(dto);

	}
	/**
	 * 获取当前体力数，根据时间进行体力恢复
	 * 
	 */
	public int getPhysicalforTime() {
		RoleDto dto = role.getDto();
        int physical=dto.getPhysical();
        Timer timer=new Timer();
		timer.schedule(new addPhysicalTime(), 1000, 600000);
      
		if(physical>30){
			physical=30;
		}
		return physical;
		
	}
	
	/**
	 * 每6分钟加一个体力
	 * @author Administrator
	 *
	 */
	public class addPhysicalTime extends TimerTask{
		RoleDto dto = role.getDto();
		int physical=dto.getPhysical();
		
		public void run() {
			physical=physical+1;
		}
	}

}
