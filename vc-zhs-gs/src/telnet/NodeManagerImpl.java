package telnet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * XML 命令行 
 * @author DXF
 *
 */
public class NodeManagerImpl  {

	private TextConent 		cutCommand;
	
	private List<String> 	tips	= new ArrayList<String>();
	
	private boolean 		isRun	= false;
	
	public NodeManagerImpl( ) {
		cutCommand		= new TextConent();
	}

	/**
	 * 获取 下一个 命令内容
	 * @return
	 */
	public TextConent curNode() {
		return cutCommand;
	}

	public List<String> getTips(){
		return this.tips;
	}
	
	/**
	 * 添加参数
	 * @param arg
	 * @param jurisdiction 
	 * @throws Exception 
	 */
	public void addArg( String arg, byte jurisdiction ) throws Exception {
		
		isRun					= false;
		tips.clear();
		
		if( !cutCommand.isHave() ){
			
			checkCommand( arg, jurisdiction );
			
			ElementBase base 	= ElementManager.get(arg);
			
			cutCommand.setCommand( arg );
			
			tips.addAll( base.getTips() );
			
			isRun 				= base.isNeedArgs();
		}else{
			
			cutCommand.setArg( arg );
			
			isRun				= true;
		}
	}

	public void run( PrintWriter out, byte jurisdiction ) throws Exception{
		
		if( !isRun ) return;
		
		cutCommand.run( out, jurisdiction );
		
		clear( out );
	}
	
	private void checkCommand( String arg, byte jurisdiction ) throws Exception {
		
		ElementBase base 	= ElementManager.get(arg);
		if( base == null )
			throw new Exception("命令输入错误!");
		if( jurisdiction < base.getJurisdiction() )
			throw new Exception("权限不够,不能使用该命令!");
	}

	public boolean isHave() {
		return cutCommand.isHave();
	}

	private void clear( PrintWriter out ){
		cutCommand.clear( out );
	}
}
