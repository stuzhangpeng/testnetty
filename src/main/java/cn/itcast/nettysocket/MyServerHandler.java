package cn.itcast.nettysocket;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 
 * 服务器处理器
 * @author Administrator
 *
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		//打印服务器获得的数据,及IP地址
		  System.out.println(msg);
		  System.out.println(ctx.channel().remoteAddress());
		//通过上下文对象，返回数据给客户端数据
		ctx.writeAndFlush(UUID.randomUUID().toString());
	}
     //捕获异常
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(cause);
		ctx.channel().close();
	}
    
}
