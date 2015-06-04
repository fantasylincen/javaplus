package cn.javaplus.plugins.generator.protocol.startup;

import java.io.File;

import org.eclipse.ui.IStartup;

import _util.FileListener;
import _util.FileMonitor;
import cn.javaplus.plugins.console.ConsoleFactory;
import cn.javaplus.plugins.console.Debuger;
import cn.javaplus.plugins.generator.protocol.Activator;
import cn.javaplus.plugins.generator.protocol.generator.Caller;

public class Startup implements IStartup {

	@Override
	public void earlyStartup() {

		FileMonitor fm = Activator.getDefault().getFileMonitor();

		fm.addListener(new FileListener() {

			@Override
			public void onFileChanged(File file) {
				try {
					Debuger.debug("protocol generator changed file:" + file);
					if (file.getName().endsWith(".xml")) {
						new Caller().generate(file);
					}
				} catch (Throwable e) {
					e.printStackTrace(ConsoleFactory.getErr());
				}
			}

		});

	}
	
//	public static void main(String[] args) {
//		File f = new File("D:/workspace/Protocol2Method/protocols/activity.xml");
//		new Caller().generate(f);
//	}
}
