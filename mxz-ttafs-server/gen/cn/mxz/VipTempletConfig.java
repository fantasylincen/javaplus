//[VIP]月卡
		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getAccumulate(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getPrice(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getPrivilege1(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getPrivilege2(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getPrivilege3(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getWelfare1(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getWelfare2(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getWelfare3(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipTemplet> all = new ArrayList<VipTemplet>();
		for (VipTemplet f : getAll()) {
			if(equals(f.getWelfare4(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getAccumulate();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getPrice();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getPrivilege1();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getPrivilege2();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getPrivilege3();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getWelfare1();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getWelfare2();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getWelfare3();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipTemplet f = get(keys.get(i));
			all[i] = f.getWelfare4();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (VipTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (VipTemplet f : getAll()) {
			all.add(f.getAccumulate());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (VipTemplet f : getAll()) {
			all.add(f.getPrice());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getPrivilege1());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getPrivilege2());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getPrivilege3());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getWelfare1());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getWelfare2());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getWelfare3());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (VipTemplet f : getAll()) {
			all.add(f.getWelfare4());
		}
		return all;
	}
