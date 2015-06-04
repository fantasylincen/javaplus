package game.util;

import game.events.EventBase;
import game.events.IEvent;
import game.events.EventDescrip;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.List;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 动态加载所有的背包
 * 
 * @author liukun 
 * 
 */

public class PackageUtil {
	/**
	 * 从包package中获取所有的Class信息
	 * 
	 * @param pack		包路径,类似于game.packages.BasePacket
	 * @return
	 */
	public static List<Class<?>> getClasses( String pack ) {

		// 第一个class类的集合
		List<Class<?>> classes = new ArrayList<Class<?>>();

		boolean recursive = true;// 是否循环迭代

		String packageName = pack;// 获取包的名字 并进行替换
		String packageDirName = packageName.replace( '.', '/' );// 定义一个枚举的集合 并进行循环来处理这个目录下的things

		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources( packageDirName );
			while( dirs.hasMoreElements() ) {

				URL url = dirs.nextElement();
				String protocol = url.getProtocol();// 得到协议的名称

				if( "file".equals( protocol ) ) {// 如果是以文件的形式保存在服务器上
					System.err.println("file类型的扫描");
					String filePath = URLDecoder.decode( url.getFile(), "UTF-8" );// 获取包的物理路径
					findAndAddClassesInPackageByFile( packageName, filePath, recursive, classes );// 以文件的方式扫描整个包下的文件并添加到集合中
					
				} else if ( "jar".equals( protocol ) ) {

					System.err.println("jar类型的扫描");
					JarFile jar;
					try {

						jar = ( (JarURLConnection) url.openConnection() ).getJarFile();// 从此jar包 得到一个枚举类

						Enumeration<JarEntry> entries = jar.entries();// 同样的进行循环迭代

						while (entries.hasMoreElements()) {

							JarEntry entry = entries.nextElement();
							String name = entry.getName();

							if (name.charAt(0) == '/') {// 如果是以/开头的
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf( '/' );
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace( '/', '.' );
								}
								// 如果可以迭代下去 并且是一个包
								if( (idx != -1) || recursive ) {
									// 如果是一个.class文件 而且不是目录
									if( name.endsWith(".class")	&& !entry.isDirectory() ){										
										String className = name.substring( packageName.length() + 1, name.length() - 6 );// 去掉后面的".class" 获取真正的类名
										try {
											// 添加到classes
											classes.add( Class.forName(packageName + '.' + className ) );
										} catch (ClassNotFoundException e) {											
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						// log.error("在扫描用户定义视图时从jar包获取文件出错");
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile( String packageName,	String packagePath, final boolean recursive, List<Class<?>> classes) {
		
		File dir = new File( packagePath );//获取此包的目录 建立一个File

		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}

		File[] dirfiles = dir.listFiles();
		for (File file : dirfiles) {
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile( packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes );
			} 
			else {
				String className = file.getName().substring( 0, file.getName().length() - 6);// 如果是java类文件, 去掉后面的.class 只留下类名
				try {
					// classes.add(Class.forName(packageName + '.' +
					// className));
					// 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
					classes.add( Thread.currentThread().getContextClassLoader().loadClass( packageName + '.' + className ) );
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 打印所有的包情况，方便查询
	 */
	public static void printAllPakcets( EventBase[] packages ) {
		Formatter f = new Formatter(System.out);
		f.format("%-15s %-127s %-150s %n", "包号", "类别", "功能说明");
		f.format("%-15s %-127s %-150s %n", "－－", "－－", "－－－－");
		for ( EventBase ap : packages ) {
			if( ap != null ){
				Class<?> c = ap.getClass();
				EventDescrip desc = c.getAnnotation(EventDescrip.class);
				String s = null;
				s = (desc == null) ? "" : desc.desc();
				f.format("%-8s %-50s %-150s %n", ap.getEventId(), c.getName(),	s );
			}
		}
		f.close();
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException, IOException {
		EventBase p1 = null;
		// String p = "PacketTest";
		String s = "game.packages";// 包文件夹
		List<Class<?>> list = getClasses(s);
		int max = 1000;
		final EventBase[] packets = new EventBase[max];// 不存在0号包

		// 生成所有包的实例数组，供后面调用
		for (Class<?> c : list) {
			if (!c.isInterface() && !c.getName().contains("Base")) {

				p1 = (EventBase) c.newInstance();
				// System.out.println( c.getName() + " ：" + p1.getPacketNo() );

				int packetNo = p1.getEventId();
				EventBase ip = packets[packetNo];
				if( ip == null ) {
					packets[packetNo] = p1;

				} else {
					System.out.println(packetNo + " 重复了");
				}
			}
		}

		System.out.println("---------------------------------------------------");
		for (int i = 0; i < 100; i++) {
			int packetNo = new Random().nextInt( packets.length );
			IEvent ap = packets[packetNo];
			System.out.print(i + ":\t");
			if (ap == null) {
				System.out.println(packetNo + "：不存在对应的包号");
			} else {
				ap.run(null, null);//传null，有些包可能会出错

				if (ap.getEventId() == 4) {
					// ((ShowBattle)ap).run( null, true, 234 );
				}
			}

		}
		System.out.println("---------------------------------------------------");

		printAllPakcets(packets);
		
	}
}