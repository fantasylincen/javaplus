//[任务]成就章节奖励
		List<AchieveTaskMissionTemplet> all = new ArrayList<AchieveTaskMissionTemplet>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			if(equals(f.getChapter(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskMissionTemplet> all = new ArrayList<AchieveTaskMissionTemplet>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskMissionTemplet> all = new ArrayList<AchieveTaskMissionTemplet>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		short[] all = new short[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskMissionTemplet f = get(keys.get(i));
			all[i] = f.getChapter();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskMissionTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskMissionTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		List<Short> all = new ArrayList<Short>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			all.add(f.getChapter());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskMissionTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
