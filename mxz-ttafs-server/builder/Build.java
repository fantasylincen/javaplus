import java.util.ArrayList;
import java.util.List;

import cn.mxz.FeedBackTwoTemplet;
import cn.mxz.FormulaTemplet;
import cn.mxz.FormulaTempletConfig;
import cn.mxz.IFormula;
import cn.mxz.generator.formula.ServerXmlPropertiesGenerator;

import com.google.common.collect.Lists;

/**
 * 构建项目
 *
 * @author 林岑
 *
 */
public class Build {

	public static void main(String[] args) throws Exception {

		// 成就任务生成器
		cn.mxz.task.achieve.tasks._code_generator.main(args);

		// 日常任务生成器
		cn.mxz.task.dailytask.tasks._code_generator.main(args);

		// 生成City.java
		cn.mxz.city.CityGenerator.main(args);

		// 消息生成器
		MessageGenerator.generate();
		
//		// 反向输入D.java 到控制台, 请手动将控制台信息复制到 常量表 (excel) 中
//		cn.javaplus.generator.ConstGenerator2.main(args);

		// 常量生成器
		cn.javaplus.generator.ConstGenerator.main(args);

		FormulaTempletConfig.load();
		List<FormulaTemplet> all = FormulaTempletConfig.getAll();
		List<IFormula> a = parse(all);
		// 脚本生成器
		cn.mxz.generator.formula.FormulaGenerator.generate(a);

		//通过JSON服务器配置文件, 导出xml服务器配置文件
		ServerXmlPropertiesGenerator.generate();
		
		FunctionOpenManagerBuilder.build();
		
//		//基本奖励
		BaseRewardBuilder.build();
		
		//活动ID
		ActivityIdBuilder.build();
		
		//道具ID
		PropIdsBuilder.build();

//		PrintServiceMethodsComment.print();// 打印所有Service的方法的意义
		
		System.err.println("警告: 构建项目 记得将所有 IllegalOperationException 中的汉字全部提取出来! 使用:MessageExtract.java" );
		System.err.println("警告: 构建项目 记得将所有 MessageSender.sendText 中的汉字全部提取出来! 使用:MessageExtract.java" );
	}

	private static List<IFormula> parse(List<FormulaTemplet> all) {
		List<IFormula> ls = Lists.newArrayList();
		for (FormulaTemplet f : all) {
			ls.add(new FormulaAdaptor(f));
		}
		return ls;
	}
}
