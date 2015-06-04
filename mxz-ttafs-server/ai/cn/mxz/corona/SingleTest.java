package cn.mxz.corona;

public enum SingleTest {



	INSTANCE(){

		@Override
		void doSomething(){
			System.out.println( "do something!" );

		}

	};
	abstract void doSomething();

}
