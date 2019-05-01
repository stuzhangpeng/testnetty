package cn.itcat.cn.itcast.protoel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * �Զ��������
 */
public class MyEncorder extends MessageToByteEncoder<Proto> {
    /**
     * ʵ�ֱ����߼�
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Proto msg, ByteBuf out) throws Exception {
        //����Զ���Э�����msg���������ݺ����ݳ���
        int length = msg.getLength();
        byte[] arr = msg.getArr();
        //������д�뵽bytebuf
        out.writeInt(length);
        out.writeBytes(arr);
    }
}
