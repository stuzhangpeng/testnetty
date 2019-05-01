package cn.itcat.cn.itcast.protoel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 *
 * 自定义解码器
 *
 */
public class MyDecoder extends ReplayingDecoder<Proto> {
    /**
     *
     * 对接收到的bytebuf数据进行解码
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //获取数据长度
        int length = in.readInt();
        //定义一个数组接收内容部分
        byte[] bytes=new byte[length];
        //把数据读取到bytes数组
        in.readBytes(bytes);
        Proto proto=new Proto();
        //添加到proto对象中
        proto.setArr(bytes);
        proto.setLength(length);
        //添加对象到list中
        out.add(proto);
    }
}
