//[VIP]特权礼包
		List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();
		for (VipGiftTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();
		for (VipGiftTemplet f : getAll()) {
			if(equals(f.getGiftId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();
		for (VipGiftTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();
		for (VipGiftTemplet f : getAll()) {
			if(equals(f.getGoldOld(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<VipGiftTemplet> all = new ArrayList<VipGiftTemplet>();
		for (VipGiftTemplet f : getAll()) {
			if(equals(f.getGoldNew(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipGiftTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipGiftTemplet f = get(keys.get(i));
			all[i] = f.getGiftId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipGiftTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipGiftTemplet f = get(keys.get(i));
			all[i] = f.getGoldOld();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			VipGiftTemplet f = get(keys.get(i));
			all[i] = f.getGoldNew();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (VipGiftTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (VipGiftTemplet f : getAll()) {
			all.add(f.getGiftId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (VipGiftTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (VipGiftTemplet f : getAll()) {
			all.add(f.getGoldOld());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (VipGiftTemplet f : getAll()) {
			all.add(f.getGoldNew());
		}
		return all;
	}
