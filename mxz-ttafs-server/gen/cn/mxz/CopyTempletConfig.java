//[关卡][2]副本章节(一个副本里有多个剧情)
		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getMissionId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getIsNew(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getPictype(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getAward1(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getAward2(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopyTemplet> all = new ArrayList<CopyTemplet>();
		for (CopyTemplet f : getAll()) {
			if(equals(f.getAward3(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getMissionId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getIsNew();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getPictype();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getAward1();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getAward2();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopyTemplet f = get(keys.get(i));
			all[i] = f.getAward3();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getMissionId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getIsNew());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getPictype());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getAward1());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getAward2());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CopyTemplet f : getAll()) {
			all.add(f.getAward3());
		}
		return all;
	}
