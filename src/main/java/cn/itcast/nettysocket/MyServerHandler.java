package cn.itcast.nettysocket;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 
 * ������������
 * @author Administrator
 *
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		//��ӡ��������õ�����,��IP��ַ
		  System.out.println(msg);
		  System.out.println(ctx.channel().remoteAddress());
		//ͨ�������Ķ��󣬷������ݸ��ͻ�������
		ctx.writeAndFlush(UUID.randomUUID().toString());
	}
     //�����쳣
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(cause);
		ctx.channel().close();
	}
    
}
