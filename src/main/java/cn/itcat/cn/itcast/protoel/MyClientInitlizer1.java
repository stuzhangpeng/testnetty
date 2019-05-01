package cn.itcat.cn.itcast.protoel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 聊天客户端初始化
 * @author Administrator
 *msg
 */
public class MyClientInitlizer1 extends ChannelInitializer<SocketChannel>{
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		/*
		添加自定义的encodeer
		添加自定义decodeer
		 */
		pipeline.addLast(new MyDecoder());
		pipeline.addLast(new MyEncorder());
		pipeline.addLast(new MyClientHanler2());
	}

  
}
