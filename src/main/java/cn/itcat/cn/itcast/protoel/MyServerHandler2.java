package cn.itcat.cn.itcast.protoel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Executors;

/**
 * 主服务器
 *
 * @author AdministratorMyChatServerHandler
 *
 */
public class MyServerHandler2 extends SimpleChannelInboundHandler<Proto> {
	private int number;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Proto msg) throws Exception {
		System.out.println(++number);
		System.out.println(new String(msg.getArr()));
		//开启一个线程写数据到服务器2
		Proto oroto =new Proto();
		for (int i=0;i<5;i++){
			byte[] bytes = "from server2 to server1".getBytes();
			//把数据类容和数据长度设置到proto
			oroto.setArr(bytes);
			oroto.setLength(bytes.length);
			ctx.writeAndFlush( oroto);
		}
	}
	//捕获异常
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//出现异常
		cause.printStackTrace();
		//关闭通道
		ctx.close();
	}
}
