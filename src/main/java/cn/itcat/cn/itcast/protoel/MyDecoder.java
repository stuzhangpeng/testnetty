package cn.itcat.cn.itcast.protoel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 *
 * �Զ��������
 *
 */
public class MyDecoder extends ReplayingDecoder<Proto> {
    /**
     *
     * �Խ��յ���bytebuf���ݽ��н���
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //��ȡ���ݳ���
        int length = in.readInt();
        //����һ������������ݲ���
        byte[] bytes=new byte[length];
        //�����ݶ�ȡ��bytes����
        in.readBytes(bytes);
        Proto proto=new Proto();
        //��ӵ�proto������
        proto.setArr(bytes);
        proto.setLength(length);
        //��Ӷ���list��
        out.add(proto);
    }
}
