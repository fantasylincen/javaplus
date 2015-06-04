package cn.javaplus.collections.relation;

import java.util.HashMap;
import java.util.Map;

/**
 * 关系类型
 * @author 	林岑
 * @since	2012年4月11日 12:37:56
 */
public enum RelationType {
	
	FRIENDLY((short)1),			//友好
	UNFRIENDLY((short)2);		//敌对
	
	private final short value;
	
	RelationType(short value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	private static final Map<Short, RelationType> shortToEnum = new HashMap<Short, RelationType>();
	static {
		for (RelationType a : values()) {
			shortToEnum.put(a.value, a);
		}
	}

	public static RelationType fromShort(short relation_type) {
		return shortToEnum.get(relation_type);
	}
}
