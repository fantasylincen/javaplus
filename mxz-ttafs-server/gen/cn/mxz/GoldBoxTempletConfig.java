//[道具]金宝箱特殊开启
		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getWeight1(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getWeight2(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getStop(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getAwards10(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GoldBoxTemplet> all = new ArrayList<GoldBoxTemplet>();
		for (GoldBoxTemplet f : getAll()) {
			if(equals(f.getAwards11(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getWeight1();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getWeight2();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getStop();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getAwards10();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GoldBoxTemplet f = get(keys.get(i));
			all[i] = f.getAwards11();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getWeight1());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getWeight2());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getStop());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getAwards10());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GoldBoxTemplet f : getAll()) {
			all.add(f.getAwards11());
		}
		return all;
	}
