package db.dao.impl;
				ss.setNext(peo.getStatus());
				ss.setNext(new java.sql.Date(peo.getPvpUpTime().getTime()));
				for(int i = 0; i < 15; i++) {
					ss.setNext(peo.getV(i));
				}

				ss.setNext(peo.getStatus());
				ss.setNext(new java.sql.Date(peo.getPvpUpTime().getTime()));
				for(int i = 0; i < 15; i++) {
					ss.setNext(peo.getV(i));
				}


			ss.setNext(new java.sql.Date(peo.getPvpUpTime().getTime()));
			for(int i = 0; i < 15; i++) {
				ss.setNext(peo.getV(i));
			}

			ss.setNext(peo.getUname());

			ss.setNext(new java.sql.Date(peo.getPvpUpTime().getTime()));
			for(int i = 0; i < 15; i++) {
				ss.setNext(peo.getV(i));
			}

			ss.setNext(peo.getUname());



				ss.setNext(peo.getStatus());
				ss.setNext(new java.sql.Date(peo.getPvpUpTime().getTime()));
				for(int i = 0; i < 15; i++) {
					ss.setNext(peo.getV(i));
				}

				ss.setNext(peo.getStatus());
				ss.setNext(new java.sql.Date(peo.getPvpUpTime().getTime()));
				for(int i = 0; i < 15; i++) {
					ss.setNext(peo.getV(i));
				}


		peo.setStatus(rs.getString("status"));		
		peo.setPvpUpTime(rs.getDate("pvp_up_time"));		

		for(int i = 0; i < 15; i++) {
			peo.setV( i, rs.getInt("v" + (i + 1)));
		}


	
	
