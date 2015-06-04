package cn.mxz.protocols.user;

message InvitesPro {
	required int32 count = 1231;

	repeated InvitePro gifts = 10;
}
message InvitePro {
	required int32 count = 10;
	required bool hasReceived = 20;
}