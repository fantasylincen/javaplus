package cn.javaplus.plugins.generator.protocol.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import cn.javaplus.plugins.generator.protocol.Activator;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;

public class SamplePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public SamplePreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	public void createFieldEditors() {
		addDirEditor("Protocol xml from:", Paths.XML);
		addDirEditor("Java code to:", Paths.JAVA);
		addDirEditor("AS code to:", Paths.AS);
		addEditor("Package Name:", Paths.PACKAGE_NAME);
	}

	private void addDirEditor(String content, Paths id) {
		DirectoryFieldEditor editor = new DirectoryFieldEditor(id + "", content, getFieldEditorParent());
		addField(editor);
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		editor.setStringValue(getValue(id, store));
	}

	private void addEditor(String content, Paths id) {
		StringFieldEditor editor = new StringFieldEditor(id + "", content, getFieldEditorParent());
		addField(editor);
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		editor.setStringValue(getValue(id, store));
	}

	private String getValue(Paths id, IPreferenceStore store) {
		String string = store.getString(id + "");
		return string;
	}

	@Override
	public void init(IWorkbench workbench) {
	}
}