package com.taobao.protobuf.perference;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.taobao.protobuf.editors.D;
import com.taobao.protobuf.editors.D.Paths;
import com.taobao.protobuf.util.Store;

public class PBPerferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public PBPerferencePage() {
		super(GRID);
		setPreferenceStore(Store.getStore());
	}

	@Override
	protected void createFieldEditors() {
		addDirEditor("protoc dir:", D.Paths.PROTOC);
		addDirEditor("Java code to:", D.Paths.JAVA_CODE);
		addDirEditor("AS code to:", D.Paths.AS_CODE);
		addDirEditor("C++ code to:", D.Paths.CPP_CODE);
		addDirEditor("Phython code to:", D.Paths.PHYTHON_CODE);
	}

	private DirectoryFieldEditor addDirEditor(String content, Paths id) {
		DirectoryFieldEditor editor = new DirectoryFieldEditor(id + "", content, getFieldEditorParent());
		addField(editor);
		String value = Store.getString(id);
		editor.setStringValue(value);
		return editor;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String key = event.getProperty();
		Object n = event.getNewValue();
		Store.put(key, n + "");
	}

	@Override
	public void init(IWorkbench workbench) {
	}
}
