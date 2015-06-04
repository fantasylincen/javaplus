package com.taobao.protobuf.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class ProtoEditor extends TextEditor {

	private ColorManager	colorManager;

	public ProtoEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new PBSourceViewerConfiguration(colorManager));
		setDocumentProvider(new PBDocumentProvider());
	}

	@Override
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
