//[神将]属性成长系数
		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getTypeState(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getHpPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getAttackPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getMAttackPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getDefendPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getMDefendPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getSpeedPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getCritPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getDodgePar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getBlockPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getRCritPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getHitPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getRBlockPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getCritAdditionPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<QualityRatioTemplet> all = new ArrayList<QualityRatioTemplet>();
		for (QualityRatioTemplet f : getAll()) {
			if(equals(f.getMagicPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getTypeState();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getHpPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getAttackPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getMAttackPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getDefendPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getMDefendPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getSpeedPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getCritPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getDodgePar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getBlockPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getRCritPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getHitPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getRBlockPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getCritAdditionPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			QualityRatioTemplet f = get(keys.get(i));
			all[i] = f.getMagicPar();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getTypeState());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getHpPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getAttackPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getMAttackPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getDefendPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getMDefendPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getSpeedPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getCritPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getDodgePar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getBlockPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getRCritPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getHitPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getRBlockPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getCritAdditionPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (QualityRatioTemplet f : getAll()) {
			all.add(f.getMagicPar());
		}
		return all;
	}
