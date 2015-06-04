package cn.mxz.mission.templet;

import java.util.List;

import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Collection;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;

public class MissionNodeTemplet {
	/**
	 * 引导节点的类型
	 */
	private final int guideType;
	/**
	 * 引导节点的参数
	 */
	private int guideArg;

	private int id;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	private final int path;
    private final int index;
    private final int type;
    private boolean isLast;
    
    /**
	 * 是否位于交叉点以前，如果是交叉点，则返回false，支线上的节点永远位于交叉点之后
	 */
    private boolean beforeCrossNode;

    public boolean isLast() {
		return isLast;
	}


	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}


	/**
     * 支线path List
     */
    final List<Integer> nextPath;

    public int getPath() {
		return path;
	}

	public int getIndex() {
		return index;
	}

	public int getType() {
		return type;
	}

	public List<Integer> getBranch() {
		return nextPath;
	}
	
//	/**
//	 * 此节点是否分叉点
//	 * @return
//	 */
//	public boolean hasBranch(){
//		
//	}

	/**
	 * 是否支线
	 * @return
	 */
	public boolean isBranch(){
		return path != 1;
	}


    public MissionNodeTemplet( JSONObject jsonObject) {
        this.path = jsonObject.getInteger("p");
        this.index = jsonObject.getInteger("i");
        this.type = jsonObject.getInteger("o");

//        if(path == 1 && index == 10) {
//        	Debuger.debug("MissionNodeTemplet.MissionNodeTemplet()" + jsonObject);
//        }

        if( jsonObject.getInteger("t") != null ){

        	guideType = jsonObject.getInteger("t");
        }else{
        	guideType = 0;
        }

       //林岑加  2014年1月16日 00:22:32
        if(Sets.newHashSet(1, 2, 4).contains(guideType)) {
        	 String string = jsonObject.getString("v");
             string = string.trim();
             if(string.isEmpty()) {
             	guideArg = 0;
             }

             List<Integer> vv = Util.Collection.getIntegers(string);
             Integer first = Util.Collection.getFirst(vv);
             guideArg = first;
        } else {
        	guideArg = 0;
        }
        String str = jsonObject.getString("n").trim();
        if( str == null || str.isEmpty() || str.equals("0")){
        	this.nextPath = null;
        }else{
        	this.nextPath = Collection.getIntegers( str );
        }
//        this.isLast = isLast;
    }



	@Override
	public String toString() {
		return "MissionNodeTemplet [id=" + id + ", path=" + path + ", index=" + index + ", type=" + type + ", isLast=" + isLast + ", nextPath=" + nextPath + "]";
	}

	/**
	 * @return guideType
	 */
	public int getGuideType() {
		return guideType;
	}


	/**
	 * @return guideArg
	 */
	public int getGuideArg() {
		return guideArg;
	}

	/**
	 * 是否位于交叉点以前，如果是交叉点，则返回false
	 */
	public boolean beforeCrossNode(){
		return beforeCrossNode;
	}

	public void setBeforeCrossNode(boolean b) {
		beforeCrossNode = b;
		
	}


}