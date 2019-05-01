package cn.itcat.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * ����������
 * @author Administrator
 *
 */
public class MyServer {
	public static void main(String[] args) throws InterruptedException {
		//���������¼�ѭ����
		NioEventLoopGroup bossGroup =new NioEventLoopGroup();
		NioEventLoopGroup workGroup =new NioEventLoopGroup();
		try {
			//������������
			ServerBootstrap bootStrap = new ServerBootstrap();
			//hanler��bossgroup����,childHanler��workgroup����
			bootStrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new MyServerInitlizer());
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
