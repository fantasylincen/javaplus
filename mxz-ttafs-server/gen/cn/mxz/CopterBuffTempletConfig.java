//[奇遇]35[渡天劫]buff配置
		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getPrevious(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getFront(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getFrontPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getGrade(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterBuffTemplet> all = new ArrayList<CopterBuffTemplet>();
		for (CopterBuffTemplet f : getAll()) {
			if(equals(f.getStar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getPrevious();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getFront();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getFrontPar();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getGrade();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterBuffTemplet f = get(keys.get(i));
			all[i] = f.getStar();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getPrevious());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getFront());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getFrontPar());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getGrade());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterBuffTemplet f : getAll()) {
			all.add(f.getStar());
		}
		return all;
	}
