//[奇遇]33[boss]属性基础数据表
		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getSection(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getHp(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getMAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getMDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getSpeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getDodge(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getRCrit(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getHit(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getRBlock(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getCritAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getSkill(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBasicDataTemplet> all = new ArrayList<BossBasicDataTemplet>();
		for (BossBasicDataTemplet f : getAll()) {
			if(equals(f.getMagic(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getSection();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getHp();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getAttack();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getMAttack();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getDefend();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getMDefend();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getSpeed();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getCrit();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getDodge();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getBlock();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getRCrit();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getHit();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getRBlock();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getCritAddition();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getSkill();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBasicDataTemplet f = get(keys.get(i));
			all[i] = f.getMagic();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getSection());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getHp());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getAttack());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getMAttack());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getDefend());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getMDefend());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getSpeed());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getCrit());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getDodge());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getBlock());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getRCrit());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getHit());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getRBlock());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getCritAddition());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getSkill());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBasicDataTemplet f : getAll()) {
			all.add(f.getMagic());
		}
		return all;
	}
