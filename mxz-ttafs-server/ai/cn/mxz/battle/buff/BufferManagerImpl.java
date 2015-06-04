package cn.mxz.battle.buff;


import java.util.Iterator;
import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.BuffTemplet;
import cn.mxz.BuffTempletConfig;
import cn.mxz.battle.Battle;
import cn.mxz.battle.WarSituation;
import cn.mxz.battle.buffer.Buffer;
import cn.mxz.battle.buffer.BufferManager;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

public class BufferManagerImpl implements BufferManager {

	private List<Buffer> buffers;

	public BufferManagerImpl() {

		buffers = Lists.newArrayList();
	}

	@Override
	public boolean canHit() {

		for (Buffer b : getBuffers()) {

			if(!b.canHit()) {

				return false;
			}
		}

		return true;
	}

	@Override
	public List<Buffer> getBuffers() {
		return buffers;
	}

	@Override
	public void clear() {
		getBuffers().clear();
	}

	@Override
	public void add(Buffer buffer) {
		List<Buffer> bs = getBuffers();
		Iterator<Buffer> it = bs.iterator();
		while (it.hasNext()) {
			Buffer b = (Buffer) it.next();
			if(isSameType(b.getId(), buffer.getId())) {
				it.remove();
			}
		}
		bs.add(buffer);
	}

	/**
	 * 两个Buff是不是同种Buff
	 * @param id
	 * @param id2
	 * @return
	 */
	private boolean isSameType(int id, int id2) {
		BuffTemplet temp = BuffTempletConfig.get(id);
		int category1 = temp.getCategory();
		BuffTemplet temp2 = BuffTempletConfig.get(id2);
		int category2 = temp2.getCategory();
		return category1 == category2;

	}

	@Override
	public Attribute getAddition() {

		Attribute a = new AttributeEmpty();

		for (Buffer b : getBuffers()) {

			a = AttributeCalculator.adding(a, b.getAddition());
		}

		return a;
	}

	@Override
	public List<Buffer> newRound(WarSituation situation, int currentRound) {
		Iterator<Buffer> it = buffers.iterator();
		List<Buffer> removes = Lists.newArrayList();
		while (it.hasNext()) {
			Buffer b = it.next();
			if(b.isLose()) {
				Debuger.debug("logic : BufferManagerImpl.newRound() 移除Buff:" + b);
				it.remove();
				removes.add(b);
				continue;
			}
			b.newRound(situation, currentRound);
		}
		return removes;
	}

	@Override
	public void beforeSkill(Battle battle) {
		List<Buffer> bs = getBuffers();
		for (Buffer b : bs) {
			b.beforeSkill(battle);
		}
	}

	@Override
	public boolean isNormalAttackOnly() {
		List<Buffer> bs = getBuffers();
		for (Buffer b : bs) {
			if(b.isNormalAttackOnly()) {
				return true;
			}
		}
		return false;
	}


}