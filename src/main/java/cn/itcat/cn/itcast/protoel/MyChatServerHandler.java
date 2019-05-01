package cn.itcat.cn.itcast.protoel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Executors;

/**
 * �ͻ��˺ͷ�������ϵΪ���һ
 * ���������������
 *
 * @author AdministratorMyChatServerHandler
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<Proto> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Proto msg) throws Exception {
        //����һ���µ��߳�ִ������
        Executors.newSingleThreadScheduledExecutor().submit(() -> {
            System.out.println(++count);
            System.out.println(new String(msg.getArr()));
            //д���ݵ�client
            Proto oroto = new Proto();
            for (int i = 0; i < 10; i++) {
                byte[] bytes = "from server1 to client".getBytes();
                //���������ݺ����ݳ������õ�proto
                oroto.setArr(bytes);
                oroto.setLength(bytes.length);
                ctx.writeAndFlush(oroto);
            }
        });
    }

    //�����쳣
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //�����쳣
        cause.printStackTrace();
        //�ر�ͨ��
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //����������䵱�ͻ��ˡ���ȡ��ͻ������ݺ������д�뵽��һ̨������
        //������������ʵ�����ǹ���һ��eventlop
        EventLoop eventExecutors = ctx.channel().eventLoop();
        Bootstrap strap = new Bootstrap();
        //���õ�ǰ��eventloop
        strap.channel(NioSocketChannel.class).handler(new MyClientInitlizer1());
        ChannelFuture localhost = strap.group(eventExecutors).connect("localhost", 9999);
        localhost.channel().closeFuture();
    }
}

