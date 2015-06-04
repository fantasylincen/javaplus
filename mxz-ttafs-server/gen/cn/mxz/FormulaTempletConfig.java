//[公式]
		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getMethodName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getReturnType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getArgs(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getFormula(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormulaTemplet> all = new ArrayList<FormulaTemplet>();
		for (FormulaTemplet f : getAll()) {
			if(equals(f.getComment(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getMethodName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getReturnType();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getArgs();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getFormula();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormulaTemplet f = get(keys.get(i));
			all[i] = f.getComment();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getMethodName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getReturnType());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getArgs());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getFormula());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FormulaTemplet f : getAll()) {
			all.add(f.getComment());
		}
		return all;
	}
