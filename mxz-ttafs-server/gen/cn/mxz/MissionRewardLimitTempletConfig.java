//[关卡]4[地图]副本掉落限制
		List<MissionRewardLimitTemplet> all = new ArrayList<MissionRewardLimitTemplet>();
		for (MissionRewardLimitTemplet f : getAll()) {
			if(equals(f.getHead(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MissionRewardLimitTemplet> all = new ArrayList<MissionRewardLimitTemplet>();
		for (MissionRewardLimitTemplet f : getAll()) {
			if(equals(f.getModuleId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MissionRewardLimitTemplet> all = new ArrayList<MissionRewardLimitTemplet>();
		for (MissionRewardLimitTemplet f : getAll()) {
			if(equals(f.getComment(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionRewardLimitTemplet f = get(keys.get(i));
			all[i] = f.getHead();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionRewardLimitTemplet f = get(keys.get(i));
			all[i] = f.getModuleId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MissionRewardLimitTemplet f = get(keys.get(i));
			all[i] = f.getComment();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MissionRewardLimitTemplet f : getAll()) {
			all.add(f.getHead());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MissionRewardLimitTemplet f : getAll()) {
			all.add(f.getModuleId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (MissionRewardLimitTemplet f : getAll()) {
			all.add(f.getComment());
		}
		return all;
	}
