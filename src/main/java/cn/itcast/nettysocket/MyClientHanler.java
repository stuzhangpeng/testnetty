package cn.itcast.nettysocket;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**�ͻ��˴�����
 * @author Administrator
 *
 */
public class MyClientHanler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// ��ȡ������������
		System.out.println(msg);
		//�������д����
		ctx.writeAndFlush(LocalDateTime.now().toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// �����쳣
		System.out.println(cause);
		ctx.channel().close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("���Կͻ��˵��ʺ�");
	}
    
}
