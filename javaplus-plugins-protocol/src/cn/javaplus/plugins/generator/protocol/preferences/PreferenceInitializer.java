package cn.javaplus.plugins.generator.protocol.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import cn.javaplus.plugins.generator.protocol.Activator;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;
import cn.javaplus.plugins.generator.protocol.util.Store;

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

				Activator.getDefault().getFileMonitor().relink(Store.getString(Paths.XML));
			}
		});
	}

}
