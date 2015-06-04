package cn.mxz.mission.templet;

import java.io.File;
import java.util.Map;

import cn.javaplus.file.FileUtil;
import cn.mxz.util.debuger.SystemLog;

import com.google.common.collect.Maps;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-18
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class   MissionPathCfg {
    private static final String PATH = "res/map";
    private static final Map<Integer, MissionPathTemplet> templets = Maps.newHashMap();


    public static MissionPathTemplet getMapTemplet( int id ){
        return templets.get( id );
    }

    static {
        //init();
    }

    public static void init() {
        File dir = new File( PATH );
        File[] files = dir.listFiles();

        if (files == null)
            return;
        for (File file : files) {
            if (!file.isDirectory()) {
                String fileName = file.getAbsolutePath();
                if( fileName.endsWith(".json") ){
	                String json = FileUtil.getContent(fileName);
	                MissionPathTemplet t = new MissionPathTemplet( json );
	                int key =  getKey(fileName);
//	                System.out.println( t );
	                templets.put( key, t );
//	                System.out.println("处理完毕 " + fileName);
                }

            }
        }
//        System.out.println(Thread.currentThread().getStackTrace()[3].getMethodName());
//        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
//        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
//        System.out.println(Thread.currentThread().getStackTrace()[0].getMethodName());
        SystemLog.debug("MissionPathCfg.getKey() 地图数据处理完毕");
    }

    /**
     * 通过文件名获取key
     * @param fileName      文件名为1.json就应该返回1，
     * @return
     */
    private static int getKey( String fileName ){
        int pos = fileName.lastIndexOf( File.separator  );
        pos++;//过滤掉分隔符\
        String s = fileName.substring( pos, fileName.length() - 5 );//5 for ".json".length()
        return Integer.parseInt( s );
    }

    public static void main(String[] args) {
    	MissionPathCfg.init();
    	for( int i = 1; i < 109; i++){
    		MissionPathTemplet t = MissionPathCfg.getMapTemplet( i );

    		System.out.println( "第" + i + "关的非空白节点数：" + t.getPathMap().size() );
    	}
    }

}
