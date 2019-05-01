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
 *  nettywebsocket聊天服务器初始化
 * @author Administrator
 *
 */
public class MyWebSocketChatServerInitlizer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// 添加初始化组件，编解码组件
		ChannelPipeline pipeline = ch.pipeline();
		
		//添加自定义组件
		pipeline.addLast(new MyWebSocketChatServerHandler());
		
	}

}
