//[战斗]Buffer
		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getHpReduce(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getHpReduceScale(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getLowerDefense(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getLowerDefensePar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getWaterEffect(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getReleaseSkill(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getCanHit(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getRounds(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getResId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getCategory(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getFriendlyStatus(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BuffTemplet> all = new ArrayList<BuffTemplet>();
		for (BuffTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getHpReduce();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getHpReduceScale();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getLowerDefense();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getLowerDefensePar();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getWaterEffect();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getReleaseSkill();
		}
		return all;
	}

		byte[] all = new byte[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getCanHit();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getRounds();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getResId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getCategory();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getFriendlyStatus();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BuffTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getHpReduce());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getHpReduceScale());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getLowerDefense());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getLowerDefensePar());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getWaterEffect());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getReleaseSkill());
		}
		return all;
	}

		List<Byte> all = new ArrayList<Byte>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getCanHit());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getRounds());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getResId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getCategory());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getFriendlyStatus());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BuffTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
