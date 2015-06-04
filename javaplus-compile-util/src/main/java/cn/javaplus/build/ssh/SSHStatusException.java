package cn.javaplus.build.ssh;

public class SSHStatusException extends RuntimeException {

	private Integer status;

	public SSHStatusException(Integer status) {
		this.status = status;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6894015549719606658L;

	public Integer getStatus() {
		return status;
	}
}
