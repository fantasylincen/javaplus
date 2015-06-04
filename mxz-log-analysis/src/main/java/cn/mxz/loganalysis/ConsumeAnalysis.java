package cn.mxz.loganalysis;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsumeAnalysis extends Thread {

	public class Prop {

		private int count;
		private int propId;
		private String userId;

		public Prop(String log) {
			Pattern p = Pattern.compile("userId:.+\\|");
			Matcher m = p.matcher(log);
			m.find();
			String group2 = m.group();
			
			userId = group2.replaceAll("userId:", "").replaceAll("\\|", "");
			
			log = log.replaceAll("ShopServiceImpl.buyTool\\(", "");
			log = log.replaceAll("\\).*", "");
			
			String[] split = log.split(",");
			propId = new Integer(split[0]);
			count = new Integer(split[1]);
		}
		public String getRoleId() {
			return userId;
		}

		public int getCount() {
			return count;
		}
		
		public int getPropId() {
			return propId;
		}
	}

	@Override
	public void run() {
		Iterator<LogData> it = new LogDataIterator();

		while (it.hasNext()) {
			LogData n = it.next();
			String log = n.getLog();
			

			//日志格式:    ShopServiceImpl.buyTool(130046,28)|3ms(a)|userId:100929|socketId:149 
			//			100929 购买了28个130046  
			if(log.matches("ShopServiceImpl.buyTool\\([0-9]{5}.+userId:.+")) {
				
				System.out.println(log);
				Prop p = new Prop(log);
				int id = p.getPropId();  //购买的道具
				String roleId = p.getRoleId();
				int count = p.getCount();//购买的数量
				
			}
		}
	}
}
