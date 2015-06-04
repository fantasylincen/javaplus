import cn.mxz.FormulaTemplet;
import cn.mxz.IFormula;


public class FormulaAdaptor implements IFormula {

	private FormulaTemplet	t;

	public FormulaAdaptor(FormulaTemplet t) {
		this.t = t;
	}

	public int getId() {
		return t.getId();
	}

	public String getType() {
		return t.getType();
	}

	public String getMethodName() {
		return t.getMethodName();
	}

	public String getArgs() {
		return t.getArgs();
	}

	public String getFormula() {
		return t.getFormula();
	}

	public String getComment() {
		return t.getComment();
	}

	public int hashCode() {
		return t.hashCode();
	}

	public boolean equals(Object obj) {
		return t.equals(obj);
	}

	public String toString() {
		return t.toString();
	}

	@Override
	public String getReturnType() {
		return t.getReturnType();
	}


}
