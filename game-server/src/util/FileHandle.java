package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import define.SystemCfg;

/**
 * 文本处理类
 * @author DXF
 */
public class FileHandle {

     // 路径
     private static String filenameTemp;
     
     /**
      * 创建LOG文件
      * @throws IOException
      */
     public static boolean creatFile( String name ) throws IOException {
       
    	 filenameTemp 	= SystemCfg.LOG_PATH + name + ".log";
         
         File filename 	= new File(filenameTemp);
         
         if ( !filename.exists() ) {
             filename.createNewFile();
             return true;
         }
         
         return false;
     }
     /**
      * 创建TXT文件
      * @throws IOException
      */
     public static boolean creatFileToTxt( String name ) throws IOException {
    	 
    	 filenameTemp 	= name + ".txt";
    	 
    	 File filename 	= new File(filenameTemp);
    	 
    	 if ( !filename.exists() ) {
    		 filename.createNewFile();
    		 return true;
    	 }
    	 
    	 return false;
     }
     
     /**
      * 写文件
      * @param newStr 新内容
      * @param isRepeatAdd 是否重复添加
      * @throws IOException
      */
     public static boolean writeFile( String newStr, boolean isRepeatAdd, boolean isSaveraw ) throws IOException {
       
    	 // 先读取原有文件内容，然后进行写入操作
         boolean flag 			= false;
         String temp 			= "";

         FileInputStream fis 	= null;
         InputStreamReader isr 	= null;
         BufferedReader br 		= null;

         FileOutputStream fos 	= null;
         PrintWriter pw 		= null;
         
         try {
             // 文件路径
             File file 			= new File( filenameTemp );
             StringBuffer buf 	= new StringBuffer();
             
        	 // 将文件读入输入流
             fis 				= new FileInputStream(file);
             isr 				= new InputStreamReader(fis);
             br 				= new BufferedReader(isr);

             // 保存该文件原有的内容
             while ( (temp = br.readLine()) != null ) {
            	 
            	 // 这里判断是否相同
            	 if( isRepeatAdd && temp.equals( newStr ) )
            		 return flag;
            	 
                 buf.append( temp  + "\r\n" );
                 // System.getProperty("line.separator")
                 // 行与行之间的分隔符 相当于“\n”
//                     buf.append( System.getProperty("line.separator") );
             }
         
             buf.append( newStr );

             fos 				= new FileOutputStream(file);
             pw 				= new PrintWriter(fos);
             
             pw.write( buf.toString().toCharArray() );
             pw.flush();
             
             flag = true;
         } catch (IOException e1) {
             throw e1;
         } finally {
             if (pw != null) {
                 pw.close();
             }
             if (fos != null) {
                 fos.close();
             }
             if (br != null) {
                 br.close();
             }
             if (isr != null) {
                 isr.close();
             }
             if (fis != null) {
                 fis.close();
             }
         }
         return flag;
     }

     public static boolean writeFile( List<String> list ){
    	 
    	 // 先读取原有文件内容，然后进行写入操作
         boolean flag 			= false;

         FileOutputStream fos 	= null;
         PrintWriter pw 		= null;
         
         try {
             // 文件路径
             File file 			= new File( filenameTemp );
             StringBuffer buf 	= new StringBuffer();
             
             for( String s : list )
            	 buf.append( s  + "\r\n" );

             fos 				= new FileOutputStream(file);
             pw 				= new PrintWriter(fos);
             
             pw.write( buf.toString().toCharArray() );
             pw.flush();
             
             flag = true;
         } catch (IOException e1) {
         } finally {
             if (pw != null) {
                 pw.close();
             }
         }
         return flag;
     }
     
     /**
      * 读取数据
      */
     public static List<String> readData() {
    	 
    	 List<String> list = new ArrayList<String>();
    	 
         try {
             FileReader read 	= new FileReader(filenameTemp);
             BufferedReader br 	= new BufferedReader(read);
             String row;
             while ((row = br.readLine()) != null) {
            	 list.add( row );
             }
             
             br.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         
         return list;
     }

     public static String readDate() {
         // 定义一个待返回的空字符串
         String strs = "";
         try {
             FileReader read 	= new FileReader(new File(filenameTemp));
             StringBuffer sb 	= new StringBuffer();
             char ch[] 			= new char[1024];
             int d 				= read.read(ch);
             
             while (d != -1) {
            	 
                 String str = new String(ch, 0, d);
                 sb.append(str);
                 d 			= read.read(ch);
             }
             
             String a 	= sb.toString().replaceAll("@@@@@", ",");
             strs 		= a.substring(0, a.length() - 1);
             
             read.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }finally{
        	 
         }
         
         
         return strs;
     }
	
     
     public static void main(String[] args) {
    	 
    	 
//    	 FileTest myFile = new FileTest();
    	 long begin = System.nanoTime();
    	
//         try {
//        	 FileHandle.creatFile( "2013-10-30" );
//        	 
//        	 String temp = FileHandle.readDate();
//        	 
//             for (int i = 1; i < 30; i++) {
//            	 FileHandle.creatFile( "2013-10-" + (i < 10 ? ( "0" + i ) : i ) );
//            	 
//            	 FileHandle.writeFile( temp, false );
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
    	 
         System.out.println("耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒");
     }
     
}
