package cn.itcat.heartbeat;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
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
		//添加内置hanler进行连接检测
		pipeline.addLast(new IdleStateHandler(5,7 ,10));
		pipeline.addLast(new MyServerHandler());
		
	}

}
