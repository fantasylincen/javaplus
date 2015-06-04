package cn.javaplus.plugins.generator.protocol;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.ui.packageview.PackageFragmentRootContainer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import _util.FileMonitor;
import _util.FileMonitorImpl;
import cn.javaplus.plugins.generator.protocol.preferences.D;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings("restriction")
public class Activator extends AbstractUIPlugin {

	public static final String	PLUGIN_ID	= "cn.javaplus.plugins.generator.protocol";

	private static Activator	plugin;

	private FileMonitor			fileMonitor;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String path = store.getString(D.Paths.XML + "");
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

	public static IProject getProject() {
		IProject project = null;

		// 1.根据当前编辑器获取工程
		IWorkbench workbench = getDefault().getWorkbench();
		IWorkbenchWindow aw = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage ap = aw.getActivePage();
		IEditorPart part = ap.getActiveEditor();

		if (part != null) {
			Object object = part.getEditorInput().getAdapter(IFile.class);
			if (object != null) {
				project = ((IFile) object).getProject();
			}
		}

		if (project == null) {
			ISelectionService selectionService = Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();
			ISelection selection = selectionService.getSelection();
			if (selection instanceof IStructuredSelection) {
				Object element = ((IStructuredSelection) selection).getFirstElement();

				if (element instanceof IResource) {
					project = ((IResource) element).getProject();
				} else if (element instanceof PackageFragmentRootContainer) {
					IJavaProject jProject = ((PackageFragmentRootContainer) element).getJavaProject();
					project = jProject.getProject();
				} else if (element instanceof IJavaElement) {
					IJavaProject jProject = ((IJavaElement) element).getJavaProject();
					project = jProject.getProject();
				}
			}
		}

		return project;
	}
}
