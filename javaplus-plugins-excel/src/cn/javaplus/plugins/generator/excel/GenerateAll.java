package cn.javaplus.plugins.generator.excel;

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import _util.FileUtil;
import cn.javaplus.plugins.generator.excel.generator.Caller;
import cn.javaplus.plugins.generator.excel.preferences.D;

public class GenerateAll implements IWorkbenchWindowActionDelegate {

	boolean	isRunning;

	@Override
	public void run(IAction action) {

		new Thread() {
			@Override
			public void run() {

				if (isRunning) {
					throw new RuntimeException("正在生成中....请稍后再试....");
				}
				isRunning = true;
				String path = Activator.getDefault().getPreferenceStore().getString(D.Paths.EXCEL + "");

				File dir = new File(path);
				if (!dir.exists()) {
					throw new RuntimeException("文件夹不存在:" + path);
				}
				String[] list = dir.list();
				for (String f : list) {
					File file = FileUtil.getFile(dir, f);
					if (file.getName().endsWith(".xls")) {
						try {
							new Caller().generate(file);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}

				isRunning = false;

			};
		}.start();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
	}

}
