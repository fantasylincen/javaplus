package cn.mxz.mission.templet;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-18
 * Time: 上午10:25
 */
public class MissionPathTemplet {
    private final Multimap<Integer, MissionNodeTemplet> pathMap = LinkedListMultimap.create();//<线路id,线路上的所有节点>
    		     // Multimap<Integer, String> multimap = LinkedListMultimap.create();
	

    public Multimap<Integer, MissionNodeTemplet> getPathMap() {
		return pathMap;
	}

	/**
     * 通过json字符串构造
     * @param jsonStr
     */
    public MissionPathTemplet( String jsonStr ) {
    	//Multimap<Integer,MissionNodeTemplet> temp = LinkedListMultimap.create();
        JSONArray paths = JSON.parseArray( jsonStr );
        int id = 0;//为每个具体的节点赋予一个唯一id，方便从数据库读取
        
        boolean beforeCrossNodeMark = false;
        for (Object o : paths) {
            JSONArray arr1 = JSON.parseArray(o.toString());
            MissionNodeTemplet lastNode = null;
            for (Object o1 : arr1) {
                JSONObject jo = (JSONObject) o1;

                MissionNodeTemplet node = new MissionNodeTemplet( jo );
                //System.out.println( node );

                if( node.getType() == 2 ||  node.getType() == -3){//2:必须有内容的大石头,-3为首节点
                	lastNode = node;
//                	pathTable.put( node.getPath(), node.getIndex(), node );
                	node.setId(id++);
                	//System.out.println( node );
                	if( !beforeCrossNodeMark ){
                		if( isCrossNode(jo) ){
                			beforeCrossNodeMark = true;
                			node.setBeforeCrossNode(false);
                			
                		}else{
                			node.setBeforeCrossNode(true);
                		}
                	}
                	else{
                		node.setBeforeCrossNode(false);
                	}
                	pathMap.put( node.getPath(), node );
                	
                }              
            }
            if( lastNode != null ){
            	lastNode.setLast( true );
            }
        }
         
       // pathMap = temp;
    }
    
    /**
     * 当前节点是否交叉节点
     * @return
     */
    public boolean isCrossNode(JSONObject jo){
    	if( jo.getString("n").split(",").length > 1 ){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    /**
     * 获取路径set
     * @return
     */
    public Set<Integer> getPathSet(){
    	return getPathMap().keySet();    
    	
    }
    
    public Collection<MissionNodeTemplet> getPath( int path ){
    	
    	return getPathMap().get( path );
    }
    

    
    public MissionNodeTemplet getById( int id ){
    	
    	int i = 0;
    	for( MissionNodeTemplet node : pathMap.values() ){
    		if( id == i++ ){
    			return node;
    		}
    	}
//    	List<MissionNodeTemplet> list = (List<MissionNodeTemplet>)pathMap.values();
    	return null;
    }
    

//    public getNodeById( int id ){
//    	for()
//    }
    
    /**
     * 根据支线id，返回相应的主线分叉点在主线list中的下标,
     * 注意：返回的是下标
     * @param branchPath
     * @return
     */
    public int getBifurcation( int branchPath ){
//    	Collection<MissionNodeTemplet> collection = getPathMap();
    	int i = 0;
    	for( MissionNodeTemplet node : pathMap.values() ){
    		//System.out.println(  node.getBranch() );
    		if( node.getBranch() != null && node.getBranch().contains( branchPath ) ){
    			//System.out.println( node );
    			return i;
    		}
    		i++;
    	}
    	return -1;
//    	int i = 0;
//    	for( MissionNodeTemplet node : pathMap.values() ){
//    		if( id == i++ ){
//    			return node;
//    		}
//    	}
    	
    }

	@Override
	public String toString() {
		return "MissionPathTemplet [pathTable=" + getPathMap() + "]";
	}
	
	public static void main(String[] args) {
		Multimap<Integer, String> multimap = LinkedListMultimap.create();
        multimap.put(1, "ba");
        multimap.put(2, "2a");
        multimap.put(4, "4a");
        multimap.put(3, "3a");
        multimap.put(21, "21a");
        multimap.put(12, "12a");
        multimap.put(1, "fd");
        multimap.put(1, "a");

        for (String s : multimap.values() ) {
            System.out.println( s );
        }
        System.out.println( "------------------------------");
        for (int i : multimap.keys() ) {
            System.out.println( i );
        }

        System.out.println( "------------------------------");
        for (int i : multimap.keySet() ) {
            System.out.println( i );
        }

        List<String> list1 = (List<String>) multimap.values();
        System.out.println( "values11111111111111:" + list1.get(4) );
	}
    
}
