//[战斗][技能]技能材料基础概率
		List<SkillBasisTemplet> all = new ArrayList<SkillBasisTemplet>();
		for (SkillBasisTemplet f : getAll()) {
			if(equals(f.getSpetLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SkillBasisTemplet> all = new ArrayList<SkillBasisTemplet>();
		for (SkillBasisTemplet f : getAll()) {
			if(equals(f.getFormationLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SkillBasisTemplet> all = new ArrayList<SkillBasisTemplet>();
		for (SkillBasisTemplet f : getAll()) {
			if(equals(f.getFormationSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SkillBasisTemplet> all = new ArrayList<SkillBasisTemplet>();
		for (SkillBasisTemplet f : getAll()) {
			if(equals(f.getBasisPro(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillBasisTemplet f = get(keys.get(i));
			all[i] = f.getSpetLv();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillBasisTemplet f = get(keys.get(i));
			all[i] = f.getFormationLv();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillBasisTemplet f = get(keys.get(i));
			all[i] = f.getFormationSpet();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SkillBasisTemplet f = get(keys.get(i));
			all[i] = f.getBasisPro();
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SkillBasisTemplet f : getAll()) {
			all.add(f.getSpetLv());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SkillBasisTemplet f : getAll()) {
			all.add(f.getFormationLv());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SkillBasisTemplet f : getAll()) {
			all.add(f.getFormationSpet());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (SkillBasisTemplet f : getAll()) {
			all.add(f.getBasisPro());
		}
		return all;
	}
