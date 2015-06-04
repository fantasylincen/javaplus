package cn.javaplus.comunication;
//package cn.javaplus.communication;
//
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//
///**
// * 主响应除外的  额外的响应
// * 
// * 比如 客户端 请求使用物品， 当物品使用后， 背包的物品会发生变化
// * 当客户端留有背包缓存的情况下 有两种解决方案：
// *  1.客户端先调用Server.bag.use方法， 再调用Server.bag.getData方法 连续请求两次，以保证客户端的数据和服务器的数据保持同步
// *  2.客户端调用Server.bag。use方法， 同时再服务器端的 bag.use方法上，加上注解：AttachmentResponse("Bag.getData()"),
// *   	此时，当客户端调用Server.bag.use方法时，服务器会给出2个响应，1个是Bag.use响应，一个是Bag.getData响应。
// * 
// * 如果客户端没有对背包数据进行缓存， 那就没有这个问题了。
// * 
// * 建议不使用该注解，客户端自行维护数据的同步，建议使用1方案
// * 
// * @author 林岑
// * @since 2014年2月15日 22:42:41
// */
//@Retention(RetentionPolicy.RUNTIME)
//public @interface AttachmentResponse {
//
//	/**
//	 * 格式： 类名 + 方法名 + 。+ ( + 逗号分隔的参数列表  + )
//	 * 比如 Bag.use("五颗", "还魂丹")
//	 * @return
//	 */
//	String value();
//}
