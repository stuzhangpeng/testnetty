package cn.itcat.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 服务器程序
 * @author Administrator
 *
 */
public class MyServer {
	public static void main(String[] args) throws InterruptedException {
		//启动两个事件循环组
		NioEventLoopGroup bossGroup =new NioEventLoopGroup();
		NioEventLoopGroup workGroup =new NioEventLoopGroup();
		try {
			//服务器启动器
			ServerBootstrap bootStrap = new ServerBootstrap();
			//hanler在bossgroup调用,childHanler在workgroup调用
			bootStrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new MyServerInitlizer());
			//绑定端口,阻塞式
			ChannelFuture sync = bootStrap.bind(8899).sync();
			sync.channel().closeFuture().sync();
		} finally {
			//优雅关闭循环组
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
		 
	}

}
