package cn.itcat.heartbeat;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
/**
 * ��������ʼ��
 * @author Administrator
 *
 */
public class MyServerInitlizer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// ��ӳ�ʼ���������������
		ChannelPipeline pipeline = ch.pipeline();
		//�������hanler�������Ӽ��
		pipeline.addLast(new IdleStateHandler(5,7 ,10));
		pipeline.addLast(new MyServerHandler());
		
	}

}
