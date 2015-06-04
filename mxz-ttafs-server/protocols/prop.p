




package cn.mxz.protocols.user;


message PropPro {
	required string type = 1;
	required int32 typeId = 10;
	required int32 count = 20;
}


message PropsPro {

	repeated PropPro props = 10;
}