//[开启]功能开启
		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getIdentifying(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FunctionOpenTemplet> all = new ArrayList<FunctionOpenTemplet>();
		for (FunctionOpenTemplet f : getAll()) {
			if(equals(f.getRemark(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getIdentifying();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FunctionOpenTemplet f = get(keys.get(i));
			all[i] = f.getRemark();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getIdentifying());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FunctionOpenTemplet f : getAll()) {
			all.add(f.getRemark());
		}
		return all;
	}
