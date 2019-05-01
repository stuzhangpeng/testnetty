package cn.itcast.nettysocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * 服务器初始化
 * @author Administrator
 *
 */
public class MyServerInitlizer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// 添加初始化组件，编解码组件
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4,0,4));
		pipeline.addLast(new LengthFieldPrepender(4));
		pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
		//添加自定义组件
		pipeline.addLast(new MyServerHandler());
		
	}

}
