//[任务]日常任务积分奖励表
		List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			if(equals(f.getIntegral(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			if(equals(f.getModuleId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DailyIntegralTaskTemplet> all = new ArrayList<DailyIntegralTaskTemplet>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			if(equals(f.getActivityAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyIntegralTaskTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyIntegralTaskTemplet f = get(keys.get(i));
			all[i] = f.getIntegral();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyIntegralTaskTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyIntegralTaskTemplet f = get(keys.get(i));
			all[i] = f.getModuleId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DailyIntegralTaskTemplet f = get(keys.get(i));
			all[i] = f.getActivityAwards();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			all.add(f.getIntegral());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			all.add(f.getModuleId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DailyIntegralTaskTemplet f : getAll()) {
			all.add(f.getActivityAwards());
		}
		return all;
	}
