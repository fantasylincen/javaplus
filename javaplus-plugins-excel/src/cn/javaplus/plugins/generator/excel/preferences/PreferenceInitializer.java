package cn.javaplus.plugins.generator.excel.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import cn.javaplus.plugins.generator.excel.Activator;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {

		final IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		store.addPropertyChangeListener(new IPropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				String key = event.getProperty();
				Object n = event.getNewValue();
				store.putValue(key, n + "");

				if (key.equals(D.Paths.EXCEL)) {
					Activator.getDefault().getFileMonitor().relink(n + "");
				}
			}
		});
	}

}
