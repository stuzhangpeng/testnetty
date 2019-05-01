package cn.itcat.cn.itcast.protoel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**聊天客户端处理器
 * @author Administrator
 *
 */
public class MyClientHanler extends SimpleChannelInboundHandler<Proto> {

	private int number;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Proto msg) throws Exception {
		//接收来自服务器的消息
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
		//当channel处于激活状态时写数据到服务器
		//构造proto对象
		Proto proto=new Proto();
		for (int i=0;i<10;i++){
			byte[] bytes = "from client toserver1".getBytes();
			//把数据类容和数据长度设置到proto
			proto.setArr(bytes);
			proto.setLength(bytes.length);
			ctx.writeAndFlush(proto);
		}

	}
}
