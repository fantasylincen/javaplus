//[消息提示]
		List<LoadMessageTemplet> all = new ArrayList<LoadMessageTemplet>();
		for (LoadMessageTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<LoadMessageTemplet> all = new ArrayList<LoadMessageTemplet>();
		for (LoadMessageTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<LoadMessageTemplet> all = new ArrayList<LoadMessageTemplet>();
		for (LoadMessageTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<LoadMessageTemplet> all = new ArrayList<LoadMessageTemplet>();
		for (LoadMessageTemplet f : getAll()) {
			if(equals(f.getChtype(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<LoadMessageTemplet> all = new ArrayList<LoadMessageTemplet>();
		for (LoadMessageTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LoadMessageTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LoadMessageTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LoadMessageTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LoadMessageTemplet f = get(keys.get(i));
			all[i] = f.getChtype();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LoadMessageTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (LoadMessageTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (LoadMessageTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (LoadMessageTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (LoadMessageTemplet f : getAll()) {
			all.add(f.getChtype());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (LoadMessageTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
