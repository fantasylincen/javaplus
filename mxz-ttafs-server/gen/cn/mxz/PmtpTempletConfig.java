//[VIP]成长计划
		List<PmtpTemplet> all = new ArrayList<PmtpTemplet>();
		for (PmtpTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<PmtpTemplet> all = new ArrayList<PmtpTemplet>();
		for (PmtpTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<PmtpTemplet> all = new ArrayList<PmtpTemplet>();
		for (PmtpTemplet f : getAll()) {
			if(equals(f.getRebate(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<PmtpTemplet> all = new ArrayList<PmtpTemplet>();
		for (PmtpTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PmtpTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PmtpTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PmtpTemplet f = get(keys.get(i));
			all[i] = f.getRebate();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PmtpTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (PmtpTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (PmtpTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (PmtpTemplet f : getAll()) {
			all.add(f.getRebate());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (PmtpTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
