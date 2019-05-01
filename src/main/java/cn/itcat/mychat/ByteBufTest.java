package cn.itcat.mychat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 学习byteBuf
 *
 *
 */

public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        ByteBuf byteBuf = buffer.writeByte(12);
        byte aByte = byteBuf.getByte(0);
        System.out.println("hello");
        //引用计数，进行内存管理



    }
}
