package com.linekong.platform.protocol.erating.server.util;


/**
 * Created by Administrator on 14-3-13.
 */
public class DataTransform{


    public static final long getUnsignedInt(int num){
        return num & 0xffffffffL;
    }

    public static final int getUnsignedShort(short num){
        return num & 0xffff;
    }

    public static final short getUnsigned(byte num){
        return (short) (num & 0xff);
    }

    public static final void main(String[] args){
        int num = -20;
        System.out.println(getUnsignedInt(num));

        byte b = -1;
        System.out.println(getUnsigned(b));

        b = 10;
        System.out.println(getUnsigned(b));
    }

}
