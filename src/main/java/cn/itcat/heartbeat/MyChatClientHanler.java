package cn.itcat.heartbeat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**����ͻ��˴�����
 * @author Administrator
 *
 */
public class MyChatClientHanler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
	//�������Է�����������
		System.out.println(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// �����쳣
		System.out.println(cause);
		ctx.close();
	}
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	ctx.channel().writeAndFlush("aaa");
    }
}
