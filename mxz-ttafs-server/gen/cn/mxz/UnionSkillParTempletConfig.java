//[战斗]连携技能伤害加成系数
		List<UnionSkillParTemplet> all = new ArrayList<UnionSkillParTemplet>();
		for (UnionSkillParTemplet f : getAll()) {
			if(equals(f.getMeanLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<UnionSkillParTemplet> all = new ArrayList<UnionSkillParTemplet>();
		for (UnionSkillParTemplet f : getAll()) {
			if(equals(f.getHarmPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			UnionSkillParTemplet f = get(keys.get(i));
			all[i] = f.getMeanLv();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			UnionSkillParTemplet f = get(keys.get(i));
			all[i] = f.getHarmPar();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (UnionSkillParTemplet f : getAll()) {
			all.add(f.getMeanLv());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (UnionSkillParTemplet f : getAll()) {
			all.add(f.getHarmPar());
		}
		return all;
	}
