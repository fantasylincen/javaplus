
package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface LogSnatch {
	
	@Key
	int getId();

	String getUname();

	int getDatatype();

	int getNub();

	String getRobber();

	int getTime();

	int getWarsituationid();

	boolean getIswin();
	
	/**
	 * 是否查看了
	 * @return
	 */
	boolean getIsSaw();
	
	/**
	 * 是否掠夺成功了.
	 * @return
	 */
	boolean isSnatchSuccess();
}
