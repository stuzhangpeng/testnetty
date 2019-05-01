package cn.itcast.nettysocket;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**客户端处理器
 * @author Administrator
 *
 */
public class MyClientHanler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// 获取服务器的数据
		System.out.println(msg);
		//向服务器写数据
		ctx.writeAndFlush(LocalDateTime.now().toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 处理异常
		System.out.println(cause);
		ctx.channel().close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("来自客户端的问候");
	}
    
}
