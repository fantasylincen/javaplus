package cn.mxz.mission.old;

import cn.mxz.RandomEventTemplet;
import cn.mxz.RandomEventTempletConfig;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.city.City;
import cn.mxz.util.needs.Deductor;

public class MapRandomEventImpl implements MapRandomEvent {

	private int id;


	private City city;


	public MapRandomEventImpl(int id, City city) {
		this.id = id;


		this.city = city;

	}

	@Override
	public int getId() {

		return id;
	}


	@Override
	public String responseEvent() {

		RandomEventTemplet temp = RandomEventTempletConfig.get(getId());

		Deductor n = DeductorFactory.getDeductorAsMuch(temp);

		n.deduct(city.getPlayer());

		PrizeSender ps = PrizeSenderFactory.getPrizeSender();

		ps.send(city.getPlayer(), temp.getAwards());

		return "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapRandomEventImpl other = (MapRandomEventImpl) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	public int getPath() {
		return 0;
	}




}
