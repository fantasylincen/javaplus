package cn.mxz.dogz;

import java.util.Collection;

import cn.mxz.protocols.user.DogzP.DogzListPro;

class DogzListBuilder {


	public DogzListPro build(Collection<Dogz> dogzAll) {

		DogzListPro.Builder b = DogzListPro.newBuilder();

		for (Dogz dogz : dogzAll) {

			b.addDogzs(new DogzBuilder().build(dogz));
		}

		return b.build();
	}

}
