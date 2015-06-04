package cn.mxz.friend;
import org.junit.Test;

import cn.mxz.handler.FriendService;
import cn.mxz.testbase.TestBaseAccessed;

public class FriendServiceTest extends TestBaseAccessed {

	public FriendService getService(){
		return (FriendService) factory.get("friendService");
	}


	//自己的好友
	@Test
	public void testFriendListPro(){

		getService().getFriendsAll(1);
	}

	//收到的申请
	@Test
	public void testReceivePro(){

		getService().getReceiveMessage(1);
	}

	//全部的申请
	@Test
	public void testApplyRefuseMessagePro(){

		getService().getApplyRefuseMessage(1);
	}

	//发出的申请
	@Test
	public void testAppayMessageListPro(){

		getService().getApplyMessage(1);
	}

	@Test
	public void testQueryMessageListPro(){

		getService().getQueryMessage("lc",1);
	}
	//发出好友请求
	@Test
	public void testAddRequest(){

		getService().addRequest("xf1");
	}

	//拒绝加其为好友
	@Test
	public void testrefuse(){

		getService().refuse("lc1");
	}
	
	//同意添加好友
	@Test
	public void testaccept(){

		getService().accept("xf1");
	}
	
	//删除好友
	@Test
	public void testremove(){

		getService().remove("ls1");
	}
	
	//取消好友申请cancel
	@Test
	public void testcancel(){

		getService().cancel("lc1");
	}
	
	@Test
	public void testsendMessageToSomeOne(){

		getService().sendMessageToSomeOne("lc1","1111");
	}
}
