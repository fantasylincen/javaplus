//[神将]神将属性颜色表
		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getHp(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getMAttack(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getMDefend(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getSpeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GodColourTemplet> all = new ArrayList<GodColourTemplet>();
		for (GodColourTemplet f : getAll()) {
			if(equals(f.getColor(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getHp();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getAttack();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getMAttack();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getDefend();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getMDefend();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getSpeed();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GodColourTemplet f = get(keys.get(i));
			all[i] = f.getColor();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getHp());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getAttack());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getMAttack());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getDefend());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getMDefend());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getSpeed());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GodColourTemplet f : getAll()) {
			all.add(f.getColor());
		}
		return all;
	}
