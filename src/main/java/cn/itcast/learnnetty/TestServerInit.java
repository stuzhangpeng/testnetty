package cn.itcast.learnnetty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * ³õÊ¼»¯Æ÷
 * @author Administrator
 *
 */
public class TestServerInit extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		 pipeline.addLast("httpserverCodc", new HttpServerCodec());
		 pipeline.addLast("httpserverhandler", new MyHttpServerHandler());
	}

}
