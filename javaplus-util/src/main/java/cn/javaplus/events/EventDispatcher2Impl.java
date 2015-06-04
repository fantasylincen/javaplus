package cn.javaplus.events;

import java.util.List;

import cn.javaplus.collections.list.Lists;

public class EventDispatcher2Impl implements EventDispatcher2 {

	private List<Listener2>	listeners	= Lists.newArrayList();

	@Override
	public void addListener(Listener2 listener) {

		synchronized (this.listeners) {

			this.listeners.add(listener);
		}
	}

	@Override
	public void clear() {

		synchronized (listeners) {

			listeners.clear();
		}
	}

	@Override
	public void dispatchEvent(Object e) {

		synchronized (listeners) {

			for (Listener2 l : listeners) {

				dispatchEvent(e, l);
			}
		}
	}

	private void dispatchEvent(Object e, Listener2 l) {

		if (isTypeSame(e.getClass(), l.getEventListendClass())) {

			l.onEvent(e);
		}
	}

	private boolean isTypeSame(Class<?> class1, Class<?> eventClass) {

		while (true) {

			if (class1.equals(eventClass)) {

				return true;
			}

			class1 = class1.getSuperclass();

			if (class1 == null) {

				break;
			}
		}

		return false;
	}

	@Override
	public void removeListener(Listener2 listener) {
		listeners.remove(listener);
	}

	
}
