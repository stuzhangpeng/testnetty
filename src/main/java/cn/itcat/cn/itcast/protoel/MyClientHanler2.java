package cn.itcat.cn.itcast.protoel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Executors;

/**从服务器作为客户端的处理器
 * @author Administrator
 *
 */
public class MyClientHanler2 extends SimpleChannelInboundHandler<Proto> {

	private int number;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Proto msg) throws Exception {
		//接收Server2的消息
		System.out.println(new String(msg.getArr()));

		System.out.println(	++number);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 处理异常
		System.out.println(cause);
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//当channel处于激活状态时从服务器1写数据到服务器2
		//发送数据到server2
		//构造proto对象
		//开启新的线程执行写出操作
		Executors.newSingleThreadScheduledExecutor().submit(() ->{
			Proto proto=new Proto();
			for (int i=0;i<5;i++){
				byte[] bytes = "from server1 to server2".getBytes();
				//把数据类容和数据长度设置到proto
				proto.setArr(bytes);
				proto.setLength(bytes.length);
				ctx.writeAndFlush(proto);
			}
		} );
	}
}
