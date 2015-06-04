package cn.javaplus.plugins.generator.excel;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import _util.FileMonitor;
import _util.FileMonitorImpl;
import cn.javaplus.plugins.generator.excel.preferences.D;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	public static final String	PLUGIN_ID	= "cn.javaplus.plugins.generator.excel";

	private static Activator	plugin;

	private FileMonitor			fileMonitor;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String path = store.getString(D.Paths.EXCEL + "");
		fileMonitor = new FileMonitorImpl(path);
		fileMonitor.start();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		fileMonitor.stopNormal();
	}

	public static Activator getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public FileMonitor getFileMonitor() {

		return fileMonitor;
	}
}
