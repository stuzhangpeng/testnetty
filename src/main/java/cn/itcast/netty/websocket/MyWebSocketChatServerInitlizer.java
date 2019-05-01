package cn.itcast.netty.websocket;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 *  nettywebsocket�����������ʼ��
 * @author Administrator
 *
 */
public class MyWebSocketChatServerInitlizer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// ��ӳ�ʼ���������������
		ChannelPipeline pipeline = ch.pipeline();
		
		//����Զ������
		pipeline.addLast(new MyWebSocketChatServerHandler());
		
	}

}
