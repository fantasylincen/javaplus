package cn.javaplus.plugins.generator.protocol.config;

import java.util.ArrayList;
import java.util.List;

public class MethodImpl implements IMethod {

	private IReturn				returnData;

	private String				name;

	private List<IField>		args;

	private long				id;

	private String				methodDoc;

	private List<MethodBack>	backs;

	private GeneratorConfig		config;

	private String				compressType;

	public MethodImpl(String methodText, String methodDoc, String returnDoc, List<String> argsDoc, List<String> backs, long id, GeneratorConfig config) {

		this.config = config;
		returnData = buildReturn(methodText, returnDoc);
		name = buildName(methodText);
		args = buildArgs(methodText, argsDoc);

		this.backs = buildBacks(backs);

		this.id = id;
		this.setMethodDoc(methodDoc);
	}

	public List<MethodBack> buildBacks(List<String> b) {
		List<MethodBack> mbs = new ArrayList<MethodBack>();
		for (String s : b) {
			mbs.add(new MethodBackImpl(s, config));
		}
		return mbs;
	}

	@Override
	public String toString() {
		return name;
	}

	private List<IField> buildArgs(String methodText, List<String> argsDoc) {

		List<IField> all = new ArrayList<IField>();
		String fileds = methodText.replaceAll(".*\\(", "").replaceAll("\\)", "");

		String[] split;
		if (fileds.isEmpty()) {
			split = new String[0];
		} else {
			split = fileds.split(",");
		}

		checkArgsCount(split, argsDoc, methodText);

		for (int i = 0; i < split.length; i++) {
			String filed = split[i].trim();
			IField f = new FieldImpl(config);

			String[] s = filed.split(" ");
			f.setType(s[0]);
			f.setDoc(argsDoc.get(i));
			f.setName(s[1]);
			all.add(f);
		}

		return all;
	}

	private void checkArgsCount(String[] split, List<String> argsDoc, String methodText) {
		if (split.length != argsDoc.size()) {
			throw new RuntimeException("参数数量不一致:" + split.length + ", " + argsDoc.size() + ", " + methodText);
		}
	}

	private String buildName(String methodText) {
		return methodText.replaceAll("\\(.*\\)", "").replaceAll(".* ", "");
	}

	private IReturn buildReturn(String methodText, String returnDoc) {
		IReturn returnData = new ReturnImpl();
		String[] split = methodText.split(" ");
		returnData.setKeyWord(split[0]);
		returnData.setReturnDoc(returnDoc);
		return returnData;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public List<IField> getArgs() {
		return args;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IReturn getReturn() {
		return returnData;
	}

	@Override
	public String getMethodDoc() {
		return methodDoc;
	}

	private void setMethodDoc(String methodDoc) {
		this.methodDoc = methodDoc;
	}

	@Override
	public List<MethodBack> getBacks() {
		return backs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		MethodImpl other = (MethodImpl) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @param compressType
	 *            鍘嬬缉鏂瑰紡
	 */
	public void setCompressType(String compressType) {
		this.compressType = compressType;
	}

	@Override
	public String getCompressType() {
		return compressType;
	}
}
