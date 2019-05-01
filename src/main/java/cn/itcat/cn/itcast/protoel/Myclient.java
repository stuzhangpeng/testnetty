package cn.itcat.cn.itcast.protoel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.IOException;
/**
 * ����netty�ͻ���
 * 
 * @author Administrator
 *
 */
public class Myclient {
	public static void main(String[] args) throws InterruptedException, IOException {
		// �����߳�ѭ����
		NioEventLoopGroup group = new NioEventLoopGroup();
		try {
			// �ͻ��˳���������
			Bootstrap strap = new Bootstrap();
			strap.group(group).channel(NioSocketChannel.class).handler(new MyClientInitlizer());
			// ��ȡchannnel
			ChannelFuture localhost = strap.connect("localhost", 8899).sync();
			localhost.channel().closeFuture().sync();
		} finally {
			// �ر��߳���
			group.shutdownGracefully();
		}
	}

}
