package cn.mxz.gm;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import cn.mxz.prizecenter.PropIdCheck;

/**
 * 把道具id转为名字，用户客户端查询
 * @author Administrator
 *
 */
public class ExcelFile  extends AbstractHandler {
	String ret;
	
	ExcelFile(){
		StringBuilder buf = new StringBuilder();
		String dir = "D:\\workspace\\InternetFiles\\策划资料\\手机数值";
		File file = new File( dir );
		
		File[] files = file.listFiles();
		
		buf.append("var excelName = [");
        if( files != null ) {
            for( File f : files ) {

                if( f.isHidden() || !f.canRead() ) {
                    continue;
                }

                String name = f.getName();
            	buf.append("\"").append( name).append("\",");
            	
            }
        }
        if( buf.length() > 17 ){//"var excelName = ["  17个字符
//        	buf.substring( 0, buf.length() - 1 );
        	buf.deleteCharAt( buf.length() -1 );
		}
        buf.append("];");
		ret = buf.toString();		
		//ret = "var ss = [\"Deluxe Bicycle\", \"Super Deluxe Trampoline\", \"Super Duper Scooter\"];";
		//ret = "{\"options\": [\"Option 1\",\"Option 2\",\"Option 3\",\"Option 4\",\"Option 5\"]}";
		//137409&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;丙级九彩游梦服卷轴",
	}
	
	@Override
	protected String doGet(Map<String, Object> parameters) {
//		System.out.println( "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
		return ret;
	}


}
