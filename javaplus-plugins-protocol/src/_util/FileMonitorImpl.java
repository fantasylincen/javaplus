package _util;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileMonitorImpl extends Thread implements FileMonitor {

	private boolean isRunning;
	private List<FileListener> listeners;
	private Map<String, Long> record;
	private String listenDir;

	/**
	 * @param listenDir	被监听的文件夹
	 */
	public FileMonitorImpl(String listenDir) {
		this.listenDir = listenDir;
		listeners = new ArrayList<FileListener>();
		record = new HashMap<String, Long>();
	}

	@Override
	public void addListener(FileListener ls) {
		this.listeners.add(ls);
	}

	@Override
	public void stopNormal() {
		isRunning = false;
	}

	@Override
	public synchronized void start() {
		isRunning = true;
		super.start();
	}

	@Override
	public void run() {

		while(isRunning) {
			List<File> files = getChangedFiles();
			for (File file : files) {
				generate(file);
			}
			sleep();
		}
	}

	private void generate(File file) {
		for (FileListener f : this.listeners) {
			f.onFileChanged(file);
		}
		markTimeVersion(file);
	}

	private void markTimeVersion(File file) {
		String key = file.getPath();
		record.put(key, file.lastModified());
	}

	private List<File> getChangedFiles() {
		List<File> ls = new ArrayList<File>();
		File dir = new File(listenDir);
		if(!dir.exists()) {
			return ls;
		}
		String[] list = dir.list();
		for (String f : list) {
			File file = FileUtil.getFile(dir, f);
			Long lastGenerateTime = getLastGenerateTimeVersion(file);
			if(file.lastModified() != lastGenerateTime) {
				ls.add(file);
			}
		}
		return ls;
	}

	private Long getLastGenerateTimeVersion(File file) {
		String key = file.getPath();
		Long lastTime = record.get(key);//最后修改时间
		if(lastTime == null) {
			lastTime = -1L;
		}
		return lastTime;
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void relink(String dir) {
		listenDir = dir;
		record.clear();
	}
}
