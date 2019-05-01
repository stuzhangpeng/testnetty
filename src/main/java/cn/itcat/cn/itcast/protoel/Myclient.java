package cn.itcat.cn.itcast.protoel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.IOException;
/**
 * 聊天netty客户端
 * 
 * @author Administrator
 *
 */
public class Myclient {
	public static void main(String[] args) throws InterruptedException, IOException {
		// 开启线程循环组
		NioEventLoopGroup group = new NioEventLoopGroup();
		try {
			// 客户端程序启动器
			Bootstrap strap = new Bootstrap();
			strap.group(group).channel(NioSocketChannel.class).handler(new MyClientInitlizer());
			// 获取channnel
			ChannelFuture localhost = strap.connect("localhost", 8899).sync();
			localhost.channel().closeFuture().sync();
		} finally {
			// 关闭线程组
			group.shutdownGracefully();
		}
	}

}
