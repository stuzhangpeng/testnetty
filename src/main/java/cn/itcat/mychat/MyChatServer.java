package cn.itcat.mychat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * �������������
 * @author Administrator
 *
 */
public class MyChatServer {
	public static void main(String[] args) throws InterruptedException {
		//���������¼�ѭ����
		NioEventLoopGroup bossGroup =new NioEventLoopGroup();
		NioEventLoopGroup workGroup =new NioEventLoopGroup();
		try {
			//������������
			ServerBootstrap bootStrap = new ServerBootstrap();
			bootStrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new MyChatServerInitlizer());
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
