//[关卡][1]关卡(章节,第1关卡即第1章)(一个关卡里有多个副本)
		List<MissionTemplet> all = new ArrayList<MissionTemplet>();
		for (MissionTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MissionTemplet> all = new ArrayList<MissionTemplet>();
		for (MissionTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MissionTemplet> all = new ArrayList<MissionTemplet>();
		for (MissionTemplet f : getAll()) {
			if(equals(f.getPictype(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionTemplet f = get(keys.get(i));
			all[i] = f.getPictype();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MissionTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (MissionTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MissionTemplet f : getAll()) {
			all.add(f.getPictype());
		}
		return all;
	}
