package com.taobao.protobuf.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

import _util.Util;
import cn.javaplus.plugins.console.Debuger;

import com.taobao.protobuf.editors.D;
import com.taobao.protobuf.util.PathFetcher;
import com.taobao.protobuf.util.Store;

public abstract class PBGeneratorDelegate /* extends Action * */implements IObjectActionDelegate {

	private String			type		= null;

	IStructuredSelection	selection	= null;

	private IWorkbenchPart	part		= null;

	private String			plugin;

	protected PBGeneratorDelegate(String type, String plugin) {
		this.type = type;
		this.plugin = plugin;
	}

	@Override
	public void run(IAction action) {

		Debuger.debug("PBGeneratorDelegate.run()");

		if (selection != null && !selection.isEmpty()) {

			save();
			String protocPath = PathFetcher.getProtocExePath();
			if (protocPath.length() == 0) {
				MessageDialog.openError(part.getSite().getShell(), "Generate sources error", "Can't get protoc path,Set the protoc path  on the perference Page first!");
				return;
			}

			@SuppressWarnings("rawtypes")
			Iterator ite = selection.iterator();
			List<IFile> files = new ArrayList<IFile>();
			while (ite.hasNext()) {
				Object obj = ite.next();
				if (obj instanceof IFile) {
					IFile f = (IFile) obj;
					files.add(f);
					if (!f.getName().endsWith(D.Extensions.PROTO_FILE)) {
						continue;
					}
					generate(protocPath, f);

					try {
						f.getParent().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
					} catch (CoreException e) {
						throw Util.toRuntimeException(e);
					}
				}
			}
		}
	}

	protected void generate(String protocPath, IFile f) {
		new Generator().generateFile(protocPath, f, type, plugin, f.getLocation().toOSString());
	}

	private void save() {
		IWorkbenchPage[] pages = part.getSite().getWorkbenchWindow().getPages();
		for (IWorkbenchPage page : pages) {
			page.saveAllEditors(true);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) selection;
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.part = targetPart;
	}

	public void generate(IFile file) {
		String protocDir = Store.getString(D.Paths.PROTOC);
		new Generator().generateFile(protocDir, file, type, plugin, getDestFile());
	}

	/**
	 * 代码生成的目标文件夹
	 * 
	 * @return
	 */
	protected abstract String getDestFile();
}
