package cn.mxz.script;
import static java.lang.Math.*;
import java.util.List;
import cn.javaplus.util.Util;
public class Script extends ScriptBase {
	public static class MoShen {
		/**
		 * 
		 * @param a0 魔神等级
		 * @param a1 玩家等级
		 */
		public static double getShouHun (float a0, float a1) {
		
			float x=a0/a1;
			if(x>1.5){
			     x=1.5f;
			}
			if(x<0.5){
			     x=0.5f;
			}
			return x;
		}
	}

	public static class ChuangZhen {
		/**
		 * 
		 * @param a0 当前闯关层数
		 */
		public static int getLowestHarm (int a0) {
		
			return (int)(a0*a0/5+Util.Random.get(50, 100));
		}
	}

	public static class ShangHai {
		/**
		 * 
		 * @param a0 玩家等级
		 */
		public static int getConst (int a0) {
		
			return a0*30;
		}
	}

	public static class Boss {
		/**
		 * 
		 * @param a0 实际在线人数
		 */
		public static int getPrestige (int a0) {
		
			int x=200000-(100-a0)*2000;
			if(x<20000){
			x=20000;
			}
			return x;
		}
	}

	public static class Dogz {
		/**
		 * 神兽升级概率计算公式
		 * @param a0 基础概率
		 * @param a1 当前升级所差魂
		 * @param a2 升级所需魂
		 */
		public static double getLevelUpProbability (float a0, int a1, int a2) {
		
			return a0 + 0.1 - 0.5 * a1 / a2;
		}
		/**
		 * 
		 * @param a0 神兽等级
		 * @param a1 神兽基础属性
		 */
		public static int getDogzProperty (int a0, int a1) {
		
			return (int)(a1* 0.2*a0+650);
		}
		/**
		 * 
		 * @param a0 神兽等级
		 * @param a1 敌人等级
		 * @param a2 特殊效果触发基础几率
		 */
		public static double getTriggerOdds (float a0, float a1, float a2) {
		
			float x=a2-(a1-a0)*0.01f;
			if(a0>=a1){
			  x=0.5f;
			}
			if(a0<a1){
			  x=max(x,0.05f);
			}
			
			return x;
		}
	}

	public static class XunXian {
		/**
		 * 
		 * @param a0 当日十次寻仙次数
		 */
		public static double getDiscount (int a0) {
		
			return max(0.9-a0*0,0.5);
		}
	}

	public static class Pvp {
		/**
		 * 胜利获得的胜点
		 * @param a0 敌人仙位
		 * @param a1 自己仙位
		 */
		public static double getWinPointsWin (int a0, int a1) {
		
			int x=(int)(25+(a0-a1)*5);
			if(x<10){
			x=10;
			}
			if(x>50){
			x=50;
			}
			return x;
		}
		/**
		 * 失败扣除的胜点
		 * @param a0 敌人仙位
		 * @param a1 自己仙位
		 */
		public static double getWinPointsLose (int a0, int a1) {
		
			int x=(int)(15-(a0-a1)*3);
			if(x<5){
			x=5;
			}
			if(x>25){
			x=25;
			}
			return x;
		}
		/**
		 * PVP 随机的3个人的段位等级范围
		 * @param a0 我的段位等级
		 * @param a1 当前段位胜利次数
		 * @param a2 连续失败次数
		 */
		public static List<Double> getLevelScope (int a0, int a1, int a2) {
		
			  int ag1 = a0 - 1 + a1 - a2 / 3;
			  if(ag1 <= a0 - 1) {
			                ag1 = a0 - 1;
			  }
			  ag1 = max(1, ag1);
			  ag1 = min(20, ag1);
			
			       int ag2 = a0 + a1 - a2 / 3;
			       if(ag2 >= a0 + 5) {
			                ag2 = a0 + 5;
			       }
			  ag2 = max(1, ag2);
			  ag2 = min(20, ag2);
			
			       int ag3 = a0 + 1 + a1 - a2 / 3;
			       if(ag3 >= a0 + 5) {
			                ag3 = a0 + 5;
			       }
			  ag3 = max(1, ag3);
			  ag3 = min(20, ag3);
			return list(ag1,ag2,ag3);
		}
		/**
		 * PVP 随机的3个人的战斗力范围
		 * @param a0 我的战斗力
		 * @param a1 当前段位胜利次数
		 * @param a2 连续失败次数
		 */
		public static List<Double> getShenJiaScope (int a0, int a1, int a2) {
		
			a1 = min(5, a1);
			int t = (a1-a2/3);
			t = min(0, t);
			
			double min1 = 0.25 + t * 0.1;
			double max1 = 0.45 + t * 0.1;
			double min2 = 0.46 + t * 0.1;
			double max2 = 0.65 + t * 0.1;
			double min3 = 0.66 + t * 0.1;      
			double max3 = 0.85 + t * 0.1;
			return list(min1, max1, min2, max2, min3, max3);
		}
	}

}
