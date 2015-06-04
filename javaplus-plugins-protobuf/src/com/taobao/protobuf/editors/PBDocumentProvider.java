package com.taobao.protobuf.editors;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.FileDocumentProvider;

import com.taobao.protobuf.editors.D.Paths;
import com.taobao.protobuf.generator.ASGenerater;
import com.taobao.protobuf.generator.CPPGenerater;
import com.taobao.protobuf.generator.JavaGenerater;
import com.taobao.protobuf.util.Store;

public class PBDocumentProvider extends FileDocumentProvider {

	@Override
	protected void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite) throws CoreException {

		if (element instanceof IFileEditorInput) {

			IFileEditorInput f = (IFileEditorInput) element;
			IFile file = f.getFile();

			if (needGenerate(D.Paths.JAVA_CODE)) {
				new JavaGenerater().generate(file);
			}

			if (needGenerate(D.Paths.AS_CODE)) {
				new ASGenerater().generate(file);
			}

			if (needGenerate(D.Paths.CPP_CODE)) {
				new CPPGenerater().generate(file);
			}

			if (needGenerate(D.Paths.PHYTHON_CODE)) {
				new PhythonGenerater().generate(file);
			}
		}
		super.doSaveDocument(monitor, element, document, overwrite);
	}

	/**
	 * 是否需要生成这类代码
	 * 
	 * @param p
	 * @return
	 */
	private boolean needGenerate(Paths p) {
		String s = Store.getString(p);
		return new File(s).exists();
	}
}