package game.mail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import define.DefaultCfg;

public class MailPageControl {
	
	// 当前页
	private byte 									curPage;
	
	// 最大页
	private byte 									maxPage;
	
	// 列表总数据
	private List<MailBase> 							map;
	
	public MailPageControl( ConcurrentHashMap<Integer,MailBase> mails ) {
		map				= new ArrayList<MailBase>();
		updata( mails );
	}
	
	/**
	 * 获取最后一个邮件
	 * @return
	 */
	public MailBase getLast() {
		return map.get( map.size() - 1 );
	}
	
	public List<MailBase> getList() {
		return map;
	}
	public byte getCurPage(){
		return curPage;
	}
	public byte getMaxPage(){
		return maxPage;
	}
	public short getCurNum(){
		return (short)map.size();
	}
	
	/**
	 * 根据页数获取 数据
	 * @param page
	 * @return
	 */
	public List<MailBase> get( byte page ){
		List<MailBase> list = new ArrayList<MailBase>();
		
		if( page > maxPage ) page = maxPage;
		if( page < 1 ) page = 1;
		
		curPage		= page;
		
		int index 	= (page - 1) * DefaultCfg.PAGE_MAX;
		for( int i = index; i < map.size() && list.size() < DefaultCfg.PAGE_MAX; i++ )
			list.add( map.get(i) );
		
		return list;
	}
	
	// 刷新数据
	public void updata( ConcurrentHashMap<Integer,MailBase> mails ){
		
		map.clear();
		
		if( mails.isEmpty() ) return;
		
		for( MailBase temp : mails.values() )
			map.add( temp );
		
		curPage			= 1;
		maxPage 		= (byte) ((map.size() - 1) / DefaultCfg.PAGE_MAX);
		
		sort();
	}
	
	// 排序
	private void sort(){
		 Collections.sort( map, comparatorToRead );
	}

	 /** 根据是否已读排序 */
    private static final Comparator<MailBase> comparatorToRead = new Comparator<MailBase>(){
        @Override
        public int compare( MailBase m1, MailBase m2 ) {
        	if( m1.getIsRead() == m2.getIsRead() )
        		return m2.getTime() - m1.getTime();
        	if( !m1.getIsRead() )
        		return -1;
        	if( !m2.getIsRead() )
        		return 1;
            return 0;
        }
    };
    
}
