//[任务]日常任务
		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getIsOpenDefault(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getCode(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getArg1(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getPrestrain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getIsDaily(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getBeizhu(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getLink(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getUserLevelNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getIsCheck(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getIntegral(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyTaskTemplet> all = new ArrayList<DailyTaskTemplet>();
		for (DailyTaskTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getIsOpenDefault();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getCode();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getArg1();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getPrestrain();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getIsDaily();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getBeizhu();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getLink();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getUserLevelNeed();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getIsCheck();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getIntegral();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyTaskTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getIsOpenDefault());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getCode());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getArg1());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getPrestrain());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getIsDaily());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getBeizhu());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getLink());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getUserLevelNeed());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getIsCheck());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getIntegral());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyTaskTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
