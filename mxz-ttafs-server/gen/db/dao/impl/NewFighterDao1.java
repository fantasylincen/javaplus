package db.dao.impl;
				ss.setNext(nfo.getTypeId());
				ss.setNext(nfo.getLevel());
				ss.setNext(nfo.getExp());
				for(int i = 0; i < 20; i++) {
					ss.setNext(nfo.getV(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenType(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenLevel(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenExp(i));
				}

				ss.setNext(nfo.getTypeId());
				ss.setNext(nfo.getLevel());
				ss.setNext(nfo.getExp());
				for(int i = 0; i < 20; i++) {
					ss.setNext(nfo.getV(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenType(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenLevel(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenExp(i));
				}

			ss.setNext(uname);

			ss.setNext(nfo.getExp());
			for(int i = 0; i < 20; i++) {
				ss.setNext(nfo.getV(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenType(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenLevel(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenExp(i));
			}

			ss.setNext(nfo.getTypeId());
			ss.setNext(nfo.getUname());

			ss.setNext(nfo.getExp());
			for(int i = 0; i < 20; i++) {
				ss.setNext(nfo.getV(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenType(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenLevel(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenExp(i));
			}

			ss.setNext(nfo.getTypeId());
			ss.setNext(nfo.getUname());

			ss.setNext(nfo.getUname());

			ss.setNext(nfo.getUname());

				ss.setNext(nfo.getTypeId());
				ss.setNext(nfo.getLevel());
				ss.setNext(nfo.getExp());
				for(int i = 0; i < 20; i++) {
					ss.setNext(nfo.getV(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenType(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenLevel(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenExp(i));
				}

				ss.setNext(nfo.getTypeId());
				ss.setNext(nfo.getLevel());
				ss.setNext(nfo.getExp());
				for(int i = 0; i < 20; i++) {
					ss.setNext(nfo.getV(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenType(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenLevel(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenExp(i));
				}

			ss.setNext(uname);

		nfo.setTypeId(rs.getInt("type_id"));		
		nfo.setLevel(rs.getInt("level"));		
		nfo.setExp(rs.getInt("exp"));		

		for(int i = 0; i < 20; i++) {
			nfo.setV( i, rs.getInt("v" + (i + 1)));
		}


		for(int i = 0; i < 10; i++) {
			nfo.setYuanshenType( i, rs.getInt("yuanshen_type" + (i + 1)));
		}


		for(int i = 0; i < 10; i++) {
			nfo.setYuanshenLevel( i, rs.getInt("yuanshen_level" + (i + 1)));
		}


		for(int i = 0; i < 10; i++) {
			nfo.setYuanshenExp( i, rs.getInt("yuanshen_exp" + (i + 1)));
		}


	
	
	
