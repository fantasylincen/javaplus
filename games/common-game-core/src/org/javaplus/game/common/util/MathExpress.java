package org.javaplus.game.common.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * +,-,*,/四则运算的表达式逆波兰解析计算类,精确计算,应用BigDecimal类处理
 * 支持负数,但规范除整个表达式第一个数为负数时可以不出现在'('后，其它表达式中间任何位置的
 * 负数必须出现在'('后,即：用括号括起来。比如：-3+(-2+1
 * )*10或-3+((-2)+1)*10或(-3)+(-2+1)*10或(-3)+((-2)+1)*10
 */
public class MathExpress {
	/**
	 * +
	 */
	private final static String OP1 = "+";

	/**
	 * -
	 */
	private final static String OP2 = "-";

	/**
	 * *
	 */
	private final static String OP3 = "*";

	/**
	 * /
	 */
	private final static String OP4 = "/";

	/**
	 * ^
	 */
	// private final static String OP5 = "^";

	/**
	 * %
	 */
	// private final static String OP6 = "%";
	/**
	 * (
	 */
	private final static String OPSTART = "(";

	/**
	 * )
	 */
	private final static String OPEND = ")";

	/**
	 * !用来替代负数前面的'-'
	 */
	// private final static String NEGATIVESING = "!";

	/**
	 * !用来替代负数前面的'+'
	 */
	// private final static String PLUSSING = "@";

	/**
	 * '#'用来代表运算级别最低的特殊字符
	 */
	// private final static String LOWESTSING = "#";

	// 最原始的四则运算式
	private String expBase;

	// 经过初始化处理后的四则运算式
	private String expInited;

	// 精度
	private int precision = 20;

	// 取舍模式
	private RoundingMode roundingMode = RoundingMode.HALF_UP;

	// 精度上下文
	private MathContext mc;

	// 四则运算解析
	private List<String> expList = new ArrayList<String>();

	// 存放逆波兰表达式
	private List<String> rpnList = new ArrayList<String>();

	public MathExpress() {
	}

	public MathExpress(String expBase) {
		this(expBase, 0);
	}

	public MathExpress(String expBase, int precision) {
		this(expBase, precision, RoundingMode.HALF_UP);
	}

	public MathExpress(String expBase, int precision, RoundingMode roundingMode) {
		this.expBase = expBase;
		this.precision = precision;
		this.roundingMode = roundingMode;
		this.mc = new MathContext(precision, roundingMode);
		this.expInited = initExpress(expBase);

		StringTokenizer st = new StringTokenizer(this.expInited, "+-*/^%()",
				true);
		while (st.hasMoreElements()) {
			this.expList.add(st.nextElement().toString().trim());
		}

		this.rpnList = initRPN(this.expList);
	}

	/**
	 * @return the expBase
	 */
	public String getExpBase() {
		return expBase;
	}

	/**
	 * @param expBase
	 *            the expBase to set
	 */
	public void setExpBase(String expBase) {
		this.expBase = expBase;
	}

	/**
	 * @return the expInited
	 */
	public String getExpInited() {
		return expInited;
	}

	/**
	 * @param expInited
	 *            the expInited to set
	 */
	public void setExpInited(String expInited) {
		this.expInited = expInited;
	}

	/**
	 * @return the precision
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * @return the roundingMode
	 */
	public RoundingMode getRoundingMode() {
		return roundingMode;
	}

	/**
	 * @param roundingMode
	 *            the roundingMode to set
	 */
	public void setRoundingMode(RoundingMode roundingMode) {
		this.roundingMode = roundingMode;
	}

	/**
	 * @return the expList
	 */
	public List<String> getExpList() {
		return expList;
	}

	/**
	 * @param expList
	 *            the expList to set
	 */
	public void setExpList(List<String> expList) {
		this.expList = expList;
	}

	/**
	 * @return the rpnList
	 */
	public List<String> getRpnList() {
		return rpnList;
	}

	/**
	 * @param rpnList
	 *            the rpnList to set
	 */
	public void setRpnList(List<String> rpnList) {
		this.rpnList = rpnList;
	}

	/**
	 * @return the mc
	 */
	public MathContext getMc() {
		return mc;
	}

	/**
	 * @param mc
	 *            the mc to set
	 */
	public void setMc(MathContext mc) {
		this.mc = mc;
	}

	/**
	 * 去除空白字符和在负号'-'前加'0',便于后面的StringTokenizer
	 * 
	 * @param exp
	 * @return
	 */
	private static String initExpress(String exp) {
		String reStr = null;
		reStr = exp.replaceAll("\\s", "");
		if (reStr.startsWith("-")) {
			reStr = "0" + reStr;
		}
		reStr = reStr.replaceAll("\\(\\-", "(0-");
		return reStr;
	}

	/**
	 * 是否是整数或是浮点数,但默认-05.15这种也认为是正确的格式
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNumber(String str) {
		Pattern p = Pattern.compile("^(-?\\d+)(\\.\\d+)?{1}");
		Matcher m = p.matcher(str);
		boolean isNumber = m.matches();
		return isNumber;
	}

	/**
	 * 设置优先级顺序()设置与否无所谓
	 * 
	 * @param sign
	 * @return
	 */
	private int precedence(String str) {
		char sign = str.charAt(0);
		switch (sign) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
		case '%':
			return 3;
		case '(':
		case ')':
			// case '#':
		default:
			return 0;

		}
	}

	/**
	 * 转变为逆波兰表达式
	 * 
	 * @param strList
	 * @return
	 */
	public List<String> initRPN(List<String> strList) {
		List<String> returnList = new ArrayList<String>();
		// 用来存放操作符的栈
		Stack stack = new Stack();
		// stack.push(LOWESTSING);
		int length = strList.size();
		for (int i = 0; i < length; i++) {
			String str = strList.get(i);
			if (isNumber(str)) {
				returnList.add(str);
			} else {
				if (str.equals(OPSTART)) {
					// '('直接入栈
					stack.push(str);
				} else if (str.equals(OPEND)) {
					// ')'
					// 进行出栈操作，直到栈为空或者遇到第一个左括号
					while (!stack.isEmpty()) {
						// 将栈顶字符串做出栈操作
						String tempC = stack.pop();
						if (!tempC.equals(OPSTART)) {
							// 如果不是左括号，则将字符串直接放到逆波兰链表的最后
							returnList.add(tempC);
						} else {
							// 如果是左括号，退出循环操作
							break;
						}
					}
				} else {
					if (stack.isEmpty()) {
						// 如果栈内为空
						// 将当前字符串直接压栈
						stack.push(str);
					} else {
						// 栈不空,比较运算符优先级顺序
						if (precedence(stack.top()) >= precedence(str)) {
							// 如果栈顶元素优先级大于当前元素优先级则
							while (!stack.isEmpty()
									&& precedence(stack.top()) >= precedence(str)) {
								returnList.add(stack.pop());
							}
						}
						stack.push(str);
					}
				}
			}
		}
		// 如果栈不为空，则将栈中所有元素出栈放到逆波兰链表的最后
		while (!stack.isEmpty()) {
			returnList.add(stack.pop());
		}
		return returnList;
	}

	/**
	 * 计算逆波兰表达式
	 * 
	 * @param rpnList
	 * @return
	 */
	public String caculate(List<String> rpnList) {
		Stack numberStack = new Stack();

		int length = rpnList.size();
		for (int i = 0; i < length; i++) {
			String temp = rpnList.get(i);
			if (isNumber(temp)) {
				numberStack.push(temp);
			} else {
				BigDecimal tempNumber1 = new BigDecimal(numberStack.pop(),
						this.mc);

				BigDecimal tempNumber2 = new BigDecimal(numberStack.pop(),
						this.mc);

				BigDecimal tempNumber = new BigDecimal("0", this.mc);

				if (temp.equals(OP1)) {
					tempNumber = tempNumber2.add(tempNumber1);
				} else if (temp.equals(OP2)) {
					tempNumber = tempNumber2.subtract(tempNumber1);
				} else if (temp.equals(OP3)) {
					tempNumber = tempNumber2.multiply(tempNumber1);
				} else if (temp.equals(OP4)) {
					tempNumber = tempNumber2.divide(tempNumber1, precision,
							roundingMode);
				}
				numberStack.push(tempNumber.toString());

			}
		}

		return numberStack.pop();

	}

	/**
	 * 按照类的缺省参数进行计算
	 * 
	 * @return
	 */
	public String caculate() {
		return caculate(this.rpnList);
	}

	/**
	 * 数字条件表达式精确比较 eg: "3.0>2" "1<5" "1==5" "1!=5" "(1.0+2)>3"
	 * "((-0.9+3)>=2. 1)" 不支持&&,||等连接符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean compareTo(String strParm, int precision) {
		boolean reBoolean = false;
		boolean isParentheses = false;// 标记是否有()括上整个字符串
		String str = initExpress(strParm);
		Pattern p = Pattern.compile("^\\([\\s\\S]*\\){1}");
		Matcher m = p.matcher(str);
		isParentheses = m.matches();
		if (-1 == str.indexOf(">=") && -1 == str.indexOf("<=")
				&& -1 == str.indexOf("==") && -1 == str.indexOf("!=")) {
			if (-1 == str.indexOf(">") && -1 == str.indexOf("<"))
				throw new IllegalArgumentException("异常:条件表达式不正确!");
		}
		if (-1 != str.indexOf(">=")) {
			String[] strTemps = str.split(">=");
			if (isParentheses) {
				strTemps[0] = strTemps[0] + ")";
				strTemps[1] = "(" + strTemps[1];
			}
			int r = new BigDecimal(
					(new MathExpress(strTemps[0], precision).caculate()))
					.compareTo(new BigDecimal((new MathExpress(strTemps[1],
							precision).caculate())));
			if (-1 == r) {
				reBoolean = false;
			} else {
				reBoolean = true;
			}
		} else if (-1 != str.indexOf("<=")) {
			String[] strTemps = str.split("<=");
			if (isParentheses) {
				strTemps[0] = strTemps[0] + ")";
				strTemps[1] = "(" + strTemps[1];
			}
			int r = new BigDecimal(
					(new MathExpress(strTemps[0], precision).caculate()))
					.compareTo(new BigDecimal((new MathExpress(strTemps[1],
							precision).caculate())));
			if (1 == r) {
				reBoolean = false;
			} else {
				reBoolean = true;
			}
		} else if (-1 != str.indexOf("==")) {
			String[] strTemps = str.split("==");
			if (isParentheses) {
				strTemps[0] = strTemps[0] + ")";
				strTemps[1] = "(" + strTemps[1];
			}
			int r = new BigDecimal(
					(new MathExpress(strTemps[0], precision).caculate()))
					.compareTo(new BigDecimal((new MathExpress(strTemps[1],
							precision).caculate())));
			if (0 == r) {
				reBoolean = true;
			} else {
				reBoolean = false;
			}
		} else if (-1 != str.indexOf("!=")) {
			String[] strTemps = str.split("!=");
			if (isParentheses) {
				strTemps[0] = strTemps[0] + ")";
				strTemps[1] = "(" + strTemps[1];
			}
			int r = new BigDecimal(
					(new MathExpress(strTemps[0], precision).caculate()))
					.compareTo(new BigDecimal((new MathExpress(strTemps[1],
							precision).caculate())));
			if (0 != r) {
				reBoolean = true;
			} else {
				reBoolean = false;
			}
		} else if ((-1 != str.indexOf(">")) && (-1 == str.indexOf("="))) {
			String[] strTemps = str.split(">");
			if (isParentheses) {
				strTemps[0] = strTemps[0] + ")";
				strTemps[1] = "(" + strTemps[1];
			}
			int r = new BigDecimal(
					(new MathExpress(strTemps[0], precision).caculate()))
					.compareTo(new BigDecimal((new MathExpress(strTemps[1],
							precision).caculate())));
			if (1 == r) {
				reBoolean = true;
			} else {
				reBoolean = false;
			}
		} else if ((-1 != str.indexOf("<")) && (-1 == str.indexOf("="))) {
			String[] strTemps = str.split("<");
			if (isParentheses) {
				strTemps[0] = strTemps[0] + ")";
				strTemps[1] = "(" + strTemps[1];
			}
			int r = new BigDecimal(
					(new MathExpress(strTemps[0], precision).caculate()))
					.compareTo(new BigDecimal((new MathExpress(strTemps[1],
							precision).caculate())));
			if (-1 == r) {
				reBoolean = true;
			} else {
				reBoolean = false;
			}
		}
		return reBoolean;
	}

//	public static void main(String... args) {
//		// MathExpress me = new
//		// MathExpress("-(-0.5+0.1)*10+2",10,RoundingMode.HALF_UP);
//		// System.out.println(me.getExpList());
//		// List<String> tempList = me.initRPN(me.getExpList());
//		// System.out.println(tempList);
//		// String resultStr = me.caculate(tempList);
//		// System.out.println(resultStr);
//
//		System.out.println(Util.Math.calc(
//				"1.25464879131654978931312309999999914312123123464987 * 647.123469", 100));
//
//		// MathExpress me = new
//		// MathExpress("-(-1.500123123123656456600000123103+0.1)*10+2");
//		// String resultStr = me.caculate();
//		// BigDecimal bd = new BigDecimal(resultStr);
//		// BigDecimal bd2 = bd.setScale(2, RoundingMode.HALF_UP);
//		// System.out.println(me.caculate());
//		// System.out.println(bd.toString());
//		// System.out.println(bd.scale());
//		// System.out.println(bd2.toString());
//		// System.out.println(bd2.scale());
//
//		// System.out.println("------------------------------------");
//		// Pattern p =
//		// Pattern.compile("^\\([\\s\\S]*\\){1}quot;);//匹配类似以'('开头')'结尾的字符串
//		// Matcher m = p.matcher("(2.  0>2.22)");
//		// System.out.println(m.matches());
//
//		boolean reBoolean = MathExpress.compareTo("((-8.0+3)>=2. 1)", 20);
//		System.out.println(reBoolean);
//
//	}

	/**
	 * 栈
	 */
	private class Stack {

		LinkedList<String> stackList = new LinkedList<String>();

		public Stack() {

		}

		/**
		 * 入栈
		 * 
		 * @param expression
		 */
		public void push(String expression) {
			stackList.addLast(expression);
		}

		/**
		 * 出栈
		 * 
		 * @return
		 */
		public String pop() {
			return stackList.removeLast();
		}

		/**
		 * 栈顶元素
		 * 
		 * @return
		 */
		public String top() {
			return stackList.getLast();
		}

		/**
		 * 栈是否为空
		 * 
		 * @return
		 */
		public boolean isEmpty() {
			return stackList.isEmpty();
		}
	}
}