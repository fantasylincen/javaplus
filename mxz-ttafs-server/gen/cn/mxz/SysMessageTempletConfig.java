//[消息提示]系统信息
		List<SysMessageTemplet> all = new ArrayList<SysMessageTemplet>();
		for (SysMessageTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SysMessageTemplet> all = new ArrayList<SysMessageTemplet>();
		for (SysMessageTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SysMessageTemplet> all = new ArrayList<SysMessageTemplet>();
		for (SysMessageTemplet f : getAll()) {
			if(equals(f.getPriority(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SysMessageTemplet> all = new ArrayList<SysMessageTemplet>();
		for (SysMessageTemplet f : getAll()) {
			if(equals(f.getValue(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SysMessageTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SysMessageTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SysMessageTemplet f = get(keys.get(i));
			all[i] = f.getPriority();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SysMessageTemplet f = get(keys.get(i));
			all[i] = f.getValue();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SysMessageTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SysMessageTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SysMessageTemplet f : getAll()) {
			all.add(f.getPriority());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SysMessageTemplet f : getAll()) {
			all.add(f.getValue());
		}
		return all;
	}
