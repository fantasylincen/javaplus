package cn.javaplus.collections.relation;

/**
 * 该类表示两个商盟的关系.
 * @author 	林岑
 * @since	2012年4月11日 12:41:51
 *
 */
public class RelationBean {
	public final String commerceName1;
	public final String commerceName2;
	public final RelationType relationType;
	
	public RelationBean(String commerceName1, String commerceName2, RelationType relationType) {
		this.commerceName1 = commerceName1;
		this.commerceName2 = commerceName2;
		this.relationType = relationType;
	}
	
	@Override
	public String toString() {
		return commerceName1 +"和" + commerceName2 + "的关系是:" + relationType;
	}
}
