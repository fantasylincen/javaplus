
package cn.mxz.protocols.user.friend;

message ApplyPro {
	
	required string nick = 10;
	required bool isMan = 20;
	required int32 level = 30;	
	required int32 type = 40; 
	required int32 lastLogin =50;	
	required bool isSend = 60; 
	required string userId = 70;
}