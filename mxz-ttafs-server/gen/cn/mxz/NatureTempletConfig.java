//[属性]属性id表
		List<NatureTemplet> all = new ArrayList<NatureTemplet>();
		for (NatureTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<NatureTemplet> all = new ArrayList<NatureTemplet>();
		for (NatureTemplet f : getAll()) {
			if(equals(f.getNatureName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<NatureTemplet> all = new ArrayList<NatureTemplet>();
		for (NatureTemplet f : getAll()) {
			if(equals(f.getEquipmentPart(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NatureTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NatureTemplet f = get(keys.get(i));
			all[i] = f.getNatureName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NatureTemplet f = get(keys.get(i));
			all[i] = f.getEquipmentPart();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (NatureTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (NatureTemplet f : getAll()) {
			all.add(f.getNatureName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (NatureTemplet f : getAll()) {
			all.add(f.getEquipmentPart());
		}
		return all;
	}
