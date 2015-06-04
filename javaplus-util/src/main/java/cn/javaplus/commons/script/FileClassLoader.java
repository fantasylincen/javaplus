package cn.javaplus.commons.script;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class FileClassLoader extends ClassLoader {

    private  String drive;

    FileClassLoader(String jarFile) {

        drive=jarFile;

    }

     @Override
	public Class<?> findClass(String name)throws ClassNotFoundException

     {

        Class<?> resultClass=null;

        String pathName=name.replace('.','/');

        resultClass=findLoadedClass(name);
        
        if(resultClass!=null)
        	return resultClass;
        	
         byte[] data = loadClassData(pathName);
         
         resultClass=defineClass(name, data, 0, data.length);
        return resultClass;

     }

     private byte[] loadClassData(String name)throws ClassNotFoundException

     {

        InputStream fis = null;

        ByteArrayOutputStream baos=null;

         byte[] data = null;

         try

         {

            URL url=new URL("jar:file:/"+drive+"!/"+name+".class");
            
            
             fis = url.openStream();

             baos = new ByteArrayOutputStream();

             int ch = 0;

             while ((ch = fis.read()) != -1)

             {

                 baos.write(ch);              

             }

             data = baos.toByteArray();
             
         } catch (IOException e){

             throw new ClassNotFoundException("找不到类:"+name);

         }finally{

            try{

                if(baos!=null){

                    baos.close();

                }

                if(fis!=null){

                    fis.close();

                }

            }catch(Exception e){

            }

         }

         
        return data;

     } 
}
