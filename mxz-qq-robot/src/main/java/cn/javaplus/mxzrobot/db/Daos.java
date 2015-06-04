package cn.javaplus.mxzrobot.db;import mongo.gen.MongoGen.CollectionFetcher;public class Daos {	private static CollectionFetcher	fetcher;	public static AbilityDao getAbilityDao() {		return new AbilityDao(fetcher.getCollection("Ability"));	}
	public static AttributeDao getAttributeDao() {		return new AttributeDao(fetcher.getCollection("Attribute"));	}
	public static ChatRecordDao getChatRecordDao() {		return new ChatRecordDao(fetcher.getCollection("ChatRecord"));	}
	public static KeyValueDao getKeyValueDao() {		return new KeyValueDao(fetcher.getCollection("KeyValue"));	}
	public static void setCollectionFetcher(CollectionFetcher fetcher) {		Daos.fetcher = fetcher;	}}