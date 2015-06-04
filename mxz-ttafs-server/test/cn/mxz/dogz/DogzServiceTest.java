package cn.mxz.dogz;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import cn.mxz.handler.DogzService;
import cn.mxz.testbase.TestBaseAccessed;


public class DogzServiceTest extends TestBaseAccessed{

	//获得所以神兽
	@Test
	public void testDogzAll(){

		getService().getDogzAll();
	}

	private DogzService getService() {
		return (DogzService) factory.get("dogzService");
	}

	//神兽顿悟
	@Test
	public void testdunWu(){

		getService().dunWu(800002, false, 1);
	}

	//神兽保护
	@Test
	public void testprotectedDogz(){

		getService().protectedDogz(800002);
	}

//	@Test
//	public void testzxxxx() throws Exception {
//
//		City city = getCity("xf4");
//
//		DogzManager dm = city.getDogzManager();
//
//		FriendManager fm = city.getFriendManager();
//
//
//		long T2 = System.currentTimeMillis();
//
//
//		for (int i = 0; i < 50; i++) {
//			fm.isFriend("lc1");
////			dm.getDogzAll();
//		}
//
//		System.out.println("执行时间:" + (System.currentTimeMillis() - T2));
//	}

//	//神兽喂鱼
//	@Test
//	public void testfeedAll(){
//
////		getService().feedAll("160001|160002|160003",800001);
//	}


		@Test
		public void testTime(){

			SimpleDateFormat df = new SimpleDateFormat("HHmmss");

			String nowTime = df.format(Calendar.getInstance().getTime());

//			String[] split = nowTime.split("\\:");

			System.out.println(Integer.parseInt(nowTime));

		}

}
