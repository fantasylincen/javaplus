package cn.mxz.protocols.user.mission;

import "prop.p";


message BoxPro {

	repeated PrizePro prizes = 20;

	required int32 propType = 12312;


	message PrizePro {

		required int32 id = 10;

		required int32 count = 20;
	}
}