package cn.itcat.mychat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
/**
 * ����netty�ͻ���
 * 
 * @author Administrator
 *
 */
public class MyChatclient {
	public static void main(String[] args) throws InterruptedException, IOException {
		// �����߳�ѭ����
		NioEventLoopGroup group = new NioEventLoopGroup();
		try {
			// �ͻ��˳���������
			Bootstrap strap = new Bootstrap();
			strap.group(group).channel(NioSocketChannel.class).handler(new MyChatClientInitlizer());
			// ��ȡchannnel
			Channel channel = strap.connect("localhost", 8899).sync().channel();
			// ����ͻ��˵ļ���¼������
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// ��ȡ�ͻ������������
			while (true) {
				channel.writeAndFlush(br.readLine() + "\r\n");
			}
		} finally {
			// �ر��߳���
			group.shutdownGracefully();
		}
	}

}
