//[常量定义]
		List<DefineTemplet> all = new ArrayList<DefineTemplet>();
		for (DefineTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DefineTemplet> all = new ArrayList<DefineTemplet>();
		for (DefineTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DefineTemplet> all = new ArrayList<DefineTemplet>();
		for (DefineTemplet f : getAll()) {
			if(equals(f.getValue(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DefineTemplet> all = new ArrayList<DefineTemplet>();
		for (DefineTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DefineTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DefineTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DefineTemplet f = get(keys.get(i));
			all[i] = f.getValue();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DefineTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DefineTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DefineTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DefineTemplet f : getAll()) {
			all.add(f.getValue());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DefineTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
