//[关卡]引导地图提取怪物表
		List<DemonGroupTemplet> all = new ArrayList<DemonGroupTemplet>();
		for (DemonGroupTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DemonGroupTemplet> all = new ArrayList<DemonGroupTemplet>();
		for (DemonGroupTemplet f : getAll()) {
			if(equals(f.getGroup(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DemonGroupTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DemonGroupTemplet f = get(keys.get(i));
			all[i] = f.getGroup();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DemonGroupTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DemonGroupTemplet f : getAll()) {
			all.add(f.getGroup());
		}
		return all;
	}
