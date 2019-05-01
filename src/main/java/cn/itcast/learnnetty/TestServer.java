package cn.itcast.learnnetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * http服务器监听8899端口
 * @author Administrator
 *
 */
public class TestServer {
	public static void main(String[] args)  {
		//启动2个线程组
		EventLoopGroup BossGroup = new NioEventLoopGroup(); 
		EventLoopGroup WorkerGroup = new NioEventLoopGroup(); 
		ServerBootstrap bootStrap =new ServerBootstrap();
		bootStrap.group(BossGroup, WorkerGroup).channel(NioServerSocketChannel.class)
		.childHandler(new TestServerInit());
		ChannelFuture future;
		try {
			future = bootStrap.bind(8899).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			BossGroup .shutdownGracefully();
			WorkerGroup .shutdownGracefully();
			
		}
	
		
	}

}
