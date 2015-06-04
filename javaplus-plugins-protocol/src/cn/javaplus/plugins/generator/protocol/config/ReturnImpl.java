package cn.javaplus.plugins.generator.protocol.config;

import _util.BaseTypeConfig;


public class ReturnImpl implements IReturn {

	private String	keyWord;

	private String	returnDoc;

	public ReturnImpl() {
	}

	@Override
	public String getReturnDoc() {
		return returnDoc;
	}

	@Override
	public void setReturnDoc(String returnDoc) {
		this.returnDoc = returnDoc;
	}

	@Override
	public void setKeyWord(String string) {
		if (string.equals("boolean")) {
			string = "Boolean";
		}
		this.keyWord = string;
	}

	@Override
	public String getType() {
		return keyWord;
	}

	@Override
	public String getTypeSimple() {
		int last = keyWord.lastIndexOf(".");
		if (last == -1) {
			return keyWord;
		}
		String ts = keyWord.substring(last + 1, keyWord.length());
		return ts;
	}

	@Override
	public boolean isVoid() {
		return keyWord.equals("void");
	}

	@Override
	public boolean isBaseType() {
		return BaseTypeConfig.contains(keyWord);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyWord == null) ? 0 : keyWord.hashCode());
		result = prime * result + ((returnDoc == null) ? 0 : returnDoc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReturnImpl other = (ReturnImpl) obj;
		if (keyWord == null) {
			if (other.keyWord != null)
				return false;
		} else if (!keyWord.equals(other.keyWord))
			return false;
		if (returnDoc == null) {
			if (other.returnDoc != null)
				return false;
		} else if (!returnDoc.equals(other.returnDoc))
			return false;
		return true;
	}
}
