package com.linekong.platform.protocol.erating.eRatingAGIP.AgipTest;

public class debugTest {

	protected static void debugSet(int op,String log)
	{
		if(op<0)
		{
			System.out.println(log+" return "+op);
			System.exit(0);
		}
	}
}
