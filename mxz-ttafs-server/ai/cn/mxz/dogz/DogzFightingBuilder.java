package cn.mxz.dogz;

import cn.mxz.protocols.user.DogzP.DogzFighting;

class DogzFightingBuilder {

	public DogzFighting build(Dogz fighting) {

		DogzFighting.Builder b = DogzFighting.newBuilder();

		if(fighting != null) {

			b.setDogz(new DogzBuilder().build(fighting));
		}

		return b.build();
	}

}
