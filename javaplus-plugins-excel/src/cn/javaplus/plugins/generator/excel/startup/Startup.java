package cn.javaplus.plugins.generator.excel.startup;

import java.io.File;

import org.eclipse.ui.IStartup;

import _util.FileListener;
import _util.FileMonitor;
import cn.javaplus.plugins.console.ConsoleFactory;
import cn.javaplus.plugins.console.Debuger;
import cn.javaplus.plugins.generator.excel.Activator;
import cn.javaplus.plugins.generator.excel.generator.Caller;

public class Startup implements IStartup {


	@Override
	public void earlyStartup() {

		FileMonitor fm = Activator.getDefault().getFileMonitor();

		fm.addListener(new FileListener() {

			@Override
			public void onFileChanged(File file) {
				try {
					new Caller().generate(file);
				} catch (Throwable e) {
					e.printStackTrace(ConsoleFactory.getErr());
				}
				Debuger.debug("Excel generate changed file:" + file);
			}

		});

	}
}
