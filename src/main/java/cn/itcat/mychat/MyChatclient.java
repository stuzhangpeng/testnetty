package cn.itcat.mychat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
/**
 * 聊天netty客户端
 * 
 * @author Administrator
 *
 */
public class MyChatclient {
	public static void main(String[] args) throws InterruptedException, IOException {
		// 开启线程循环组
		NioEventLoopGroup group = new NioEventLoopGroup();
		try {
			// 客户端程序启动器
			Bootstrap strap = new Bootstrap();
			strap.group(group).channel(NioSocketChannel.class).handler(new MyChatClientInitlizer());
			// 获取channnel
			Channel channel = strap.connect("localhost", 8899).sync().channel();
			// 处理客户端的键盘录入数据
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// 获取客户端输入的数据
			while (true) {
				channel.writeAndFlush(br.readLine() + "\r\n");
			}
		} finally {
			// 关闭线程组
			group.shutdownGracefully();
		}
	}

}
