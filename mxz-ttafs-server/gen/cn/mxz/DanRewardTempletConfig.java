//[竞技场]段位奖励
		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getDan(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getDanLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getTitle(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getGrading(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getCanDanDown(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getRank(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getNeedCompareToTianZun(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getWinPoints(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getSocial(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getDanId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DanRewardTemplet> all = new ArrayList<DanRewardTemplet>();
		for (DanRewardTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getDan();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getDanLv();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getTitle();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getGrading();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getCanDanDown();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getRank();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getNeedCompareToTianZun();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getWinPoints();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getSocial();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getDanId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DanRewardTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getDan());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getDanLv());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getTitle());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getGrading());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getCanDanDown());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getRank());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getNeedCompareToTianZun());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getWinPoints());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getSocial());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getDanId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DanRewardTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
