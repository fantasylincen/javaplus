package cn.mxz.newpvp;


//注意最大数量  15  
public enum PvpPlayerProperties {
	SCORE,
	BE_KOWTOW_TIMES,
	BUY_TIMES,
	PVP_WINNING_STREAK_COUNT,
	IS_UP,
	BEST_RANK,
	WAR_SITUATION_ID, 
	@Deprecated
	AAA,
	CURRENT_WIN_TIMES_IN_DAN_ID,
	
	/**最后一次更新身价缓存的时间*/
	LAST_UPDATE_SHEN_JIA_CACHE_SEC;
	
	public int toNum() {
		return ordinal();
	}

}
