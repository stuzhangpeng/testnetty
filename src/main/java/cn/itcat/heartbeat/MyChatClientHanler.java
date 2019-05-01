package cn.itcat.heartbeat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**聊天客户端处理器
 * @author Administrator
 *
 */
public class MyChatClientHanler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
	//处理来自服务器的数据
		System.out.println(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 处理异常
		System.out.println(cause);
		ctx.close();
	}
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	ctx.channel().writeAndFlush("aaa");
    }
}
