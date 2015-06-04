package cn.mxz.shenmo;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import cn.mxz.base.servertask.ServerTaskImpl;
import cn.mxz.city.City;
import cn.mxz.events.Listener2;
import cn.mxz.events.ZeroClockEvent;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.domain.BossDamageData;
import db.domain.BossData;

public enum ShenmoManager {
	INSTANCE;


	private List<ShenmoItem> 						shenmoList = Lists.newArrayList();
	private List<BossDamageData> 					damageList = Lists.newArrayList();



	public ShenmoItem getShemoItem( int shenmoId ){
		for( ShenmoItem item : shenmoList ){
			if( item.getBossId() == shenmoId ){

				return item;
			}
		}
		return null;
	}



	private ShenmoManager() {

		for( BossData bd : DaoFactory.getBossDataDao().getAll() ){
			ShenmoItem item = new ShenmoItem( bd );


			shenmoList.add(item);
		}

		damageList = DaoFactory.getBossDamageDataDao().getAll();

		ServerTaskImpl.getInstance().addListener(new Listener2() {

			@Override
			public void onEvent(Object e) {
				
				Iterator<ShenmoItem> iterator = shenmoList.iterator();
				while( iterator.hasNext() ){
					ShenmoItem item = iterator.next();
					if( item.isEnd() || item.getBossData().getLevel() == 1 ){//后面一个条件是为引导魔神专门设置的
						DaoFactory.getBossDataDao().delete( item.getBossData() );
						DaoFactory.getBossDamageDataDao().delete( item.getDamageList() );
						damageList.removeAll( item.getDamageList() );
						iterator.remove();
					}
				}
//				for( ShenmoItem item : shenmoList ){
//					
//				}
//				shenmoList.clear();
//				damageList.clear();
//				DaoFactory.getBossDataDao().clear();
//				DaoFactory.getBossDamageDataDao().clear();
			}

			@Override
			public Class<?> getEventListendClass() {
				return ZeroClockEvent.class;
			}
		});
	}


	BossDamageData createBossDamageData( int shenmoId, City user ){
		BossDamageData dto = DaoFactory.getBossDamageDataDao().createDTO();
		dto.setAward( false );
		dto.setBossId( shenmoId );
		dto.setChallengerId( user.getId() );
		dto.setDamage( 0 );
		DaoFactory.getBossDamageDataDao().add( dto );
		damageList.add( dto );
		return dto;
		//getShemoItem(shenmoId).addDamageData(dto);

	}
	List<ShenmoItem> getAll(){
		return shenmoList;
	}

	public ShenmoItem create( City user) {
		if( checkDuplicate(user) ){
			return null;
		}
		BossData bd = new GenShenmo().gen(user);
		bd.setBossId( DaoFactory.nextBossDataBossId() );
		ShenmoItem item = new ShenmoItem(bd);

		int hpMax = item.getFighter().getHpMax();//

		bd.setHp( hpMax );
		shenmoList.add( item );
		DaoFactory.getBossDataDao().add( bd );

		createBossDamageData( bd.getBossId(), user );
		return item;
	}

	private boolean checkDuplicate( City user ) {
		for( ShenmoItem item : shenmoList ){
			if( item.isFounder( user ) && !item.isEnd() ){
				return true;
			}
		}
		return false;
	}

	public void saveToDB(BossDamageData bdd) {
		DaoFactory.getBossDamageDataDao().update(bdd);
	}

	public List<BossDamageData> getBossDamageDataByShenmo(int shenmoId ) {
		List<BossDamageData> list = Lists.newArrayList();
		for( BossDamageData bdd : damageList ){
			if( bdd.getBossId() == shenmoId ){
				list.add( bdd );
			}
		}
		sort( list );
		return list;
	}

	/**
	 * 按伤害值排序
	 * @param list
	 */
	private void sort(List<BossDamageData> list) {
		Collections.sort(list, new Comparator<BossDamageData>() {
            @Override
			public int compare(BossDamageData arg0, BossDamageData arg1) {
                return arg0.getDamage() - arg1.getDamage();
            }
        });


	}

	public static void main(String[] args) {


		
		

	}


}
