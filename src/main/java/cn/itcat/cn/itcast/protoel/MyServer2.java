package cn.itcat.cn.itcast.protoel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * �Զ���Э��ͱ������ʵ��java���������
 */
public class MyServer2 {
    public static void main(String[] args) throws InterruptedException {
        //���������¼�ѭ����
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        //����������
        bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new MyServerInitnizer2());
        try {
            //�󶨶˿�
            ChannelFuture channelFuture = bootstrap.bind(9999).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            //�Ѻùر��¼�ѭ����
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
