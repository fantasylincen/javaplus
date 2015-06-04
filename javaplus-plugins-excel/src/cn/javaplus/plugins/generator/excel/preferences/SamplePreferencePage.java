package cn.javaplus.plugins.generator.excel.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import cn.javaplus.plugins.generator.excel.Activator;
import cn.javaplus.plugins.generator.excel.preferences.D.Paths;

public class SamplePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public SamplePreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	public void createFieldEditors() {
		addDirEditor("Excel from:", D.Paths.EXCEL);
		addDirEditor("Java code to:", D.Paths.JAVA_CODE);
		addDirEditor("Java xml to:", D.Paths.JAVA_XML);
		addDirEditor("AS code to:", D.Paths.AS_CODE);
		addDirEditor("AS xml to:", D.Paths.AS_XML);

		addFileEditor("External config path:", D.Paths.INTERFACE_CONFIG);
		addEditor("Package name:", D.Paths.PACKAGE_NAME);
	}

	private void addEditor(String content, Paths id) {
		StringFieldEditor editor = new StringFieldEditor(id + "", content, getFieldEditorParent());
		addField(editor);
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		editor.setStringValue(getValue(id, store));
	}

	private void addDirEditor(String content, Paths id) {
		DirectoryFieldEditor editor = new DirectoryFieldEditor(id + "", content, getFieldEditorParent());
		addField(editor);
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		editor.setStringValue(getValue(id, store));
	}

	private String getValue(Paths id, IPreferenceStore store) {
		String string = store.getString(id + "");
		return string;
	}

	private void addFileEditor(String content, Paths id) {
		FileFieldEditor editor = new FileFieldEditor(id + "", content, getFieldEditorParent());
		addField(editor);
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		editor.setStringValue(getValue(id, store));
	}

	@Override
	public void init(IWorkbench workbench) {
	}
}