package cn.itcast.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * nettywebsocket聊天服务器程序
 * @author Administrator
 *
 */
public class MyWebSocketChatServer {
	public static void main(String[] args) throws InterruptedException {
		//启动两个事件循环组
		NioEventLoopGroup bossGroup =new NioEventLoopGroup();
		NioEventLoopGroup workGroup =new NioEventLoopGroup();
		try {
			//服务器启动器
			ServerBootstrap bootStrap = new ServerBootstrap();
			bootStrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new MyWebSocketChatServerInitlizer());
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
