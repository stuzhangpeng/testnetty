package cn.itcat.cn.itcast.protoel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义编码器
 */
public class MyEncorder extends MessageToByteEncoder<Proto> {
    /**
     * 实现编码逻辑
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Proto msg, ByteBuf out) throws Exception {
        //获得自定义协议对象msg的数据内容和数据长度
        int length = msg.getLength();
        byte[] arr = msg.getArr();
        //把数据写入到bytebuf
        out.writeInt(length);
        out.writeBytes(arr);
    }
}
