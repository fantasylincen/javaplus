//[任务]成就任务
		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getOpenOnGiveBack(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getChapterNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getCode(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getReturnType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getArg1(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getChapter(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getGoalid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getIsOpenDefault(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getLink(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getUserLevelNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getIsCheck(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AchieveTaskTemplet> all = new ArrayList<AchieveTaskTemplet>();
		for (AchieveTaskTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getOpenOnGiveBack();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getChapterNeed();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getCode();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getReturnType();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getArg1();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}

		short[] all = new short[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getChapter();
		}
		return all;
	}

		short[] all = new short[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getGoalid();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getIsOpenDefault();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getLink();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getUserLevelNeed();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getIsCheck();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AchieveTaskTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getOpenOnGiveBack());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getChapterNeed());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getCode());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getReturnType());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getArg1());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}

		List<Short> all = new ArrayList<Short>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getChapter());
		}
		return all;
	}

		List<Short> all = new ArrayList<Short>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getGoalid());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getIsOpenDefault());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getLink());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getUserLevelNeed());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getIsCheck());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AchieveTaskTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
