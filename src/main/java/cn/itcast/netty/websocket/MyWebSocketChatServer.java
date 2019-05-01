package cn.itcast.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * nettywebsocket�������������
 * @author Administrator
 *
 */
public class MyWebSocketChatServer {
	public static void main(String[] args) throws InterruptedException {
		//���������¼�ѭ����
		NioEventLoopGroup bossGroup =new NioEventLoopGroup();
		NioEventLoopGroup workGroup =new NioEventLoopGroup();
		try {
			//������������
			ServerBootstrap bootStrap = new ServerBootstrap();
			bootStrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new MyWebSocketChatServerInitlizer());
			//�󶨶˿�,����ʽ
			ChannelFuture sync = bootStrap.bind(8899).sync();
			sync.channel().closeFuture().sync();
		} finally {
			//���Źر�ѭ����
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
		 
	}

}
