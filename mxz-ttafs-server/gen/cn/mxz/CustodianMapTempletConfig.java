//[奇遇]24[保护妲己]怪物波数
		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getMonsterId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getMonsterGrade(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getDriveAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getCoolTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getCoolTimevip(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getWilsonParam(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianMapTemplet> all = new ArrayList<CustodianMapTemplet>();
		for (CustodianMapTemplet f : getAll()) {
			if(equals(f.getHurtMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getMonsterId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getMonsterGrade();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getDriveAward();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getCoolTime();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getCoolTimevip();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getWilsonParam();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianMapTemplet f = get(keys.get(i));
			all[i] = f.getHurtMin();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getMonsterId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getMonsterGrade());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getDriveAward());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getCoolTime());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getCoolTimevip());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getWilsonParam());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianMapTemplet f : getAll()) {
			all.add(f.getHurtMin());
		}
		return all;
	}
