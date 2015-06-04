package cn.mxz.util.packet;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.comunication.PacketS2C;

@Component("packet_server_2_client")
@Scope("prototype")

public class MyPacket extends PacketS2C {}
