package cn.javaplus.file;

import java.io.File;

/**
 * 日志扫描器, 排除掉所有文件夹
 * @author 林岑
 *
 */
public interface FileScanner {

	Iterable<File> iterator();

}
