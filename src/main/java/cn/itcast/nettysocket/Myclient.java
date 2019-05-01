package cn.itcast.nettysocket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty�ͻ���
 * @author Administrator
 *
 */
public class Myclient {
	public static void main(String[] args) throws InterruptedException {
		//�����߳�ѭ����
		NioEventLoopGroup group =new NioEventLoopGroup();
		try {
			//�ͻ��˳���������
			Bootstrap strap = new Bootstrap();
			strap.group(group).channel(NioSocketChannel.class).handler(new MyClientInitlizer());
			ChannelFuture sync = strap.connect("localhost", 8899).sync();
			sync.channel().closeFuture().sync();
		} finally {
			// �ر��߳���
			group.shutdownGracefully();
		}
	}

}
