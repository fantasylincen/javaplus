package cn.mxz.bossbattle.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.mxz.bossbattle.BattleInfo;
import cn.mxz.bossbattle.BossBattleActivity;
import cn.mxz.bossbattle.IBossUI1;
import cn.mxz.testbase.TestBaseAccessed;

import com.alibaba.fastjson.JSON;

public class BossUI1ImplTest extends TestBaseAccessed {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		BattleInfo info = BossBattleActivity.INSTANCE.getBattleInfo();
		IBossUI1 data = new BossUI1Impl( info, user );	
		Object json;
		try {
			json = JSON.toJSON(data);
			System.out.println("BossUI1Impl.main()" + json);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
