package cn.javaplus.compressor;

/**
 * 压缩器
 * @author 林岑
 *
 */
public interface Compressor {

	/**
	 * 压缩
	 * @param data
	 * @return
	 */
	byte [] compress(byte [] data);

	/**
	 * 解压
	 * @param data
	 * @return
	 */
	byte [] unCompress(byte [] data);

}
