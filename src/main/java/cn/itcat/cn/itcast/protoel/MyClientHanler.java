package cn.itcat.cn.itcast.protoel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**����ͻ��˴�����
 * @author Administrator
 *
 */
public class MyClientHanler extends SimpleChannelInboundHandler<Proto> {

	private int number;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Proto msg) throws Exception {
		//�������Է���������Ϣ
		System.out.println(new String(msg.getArr()));

		System.out.println(	++number);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// �����쳣
		System.out.println(cause);
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//��channel���ڼ���״̬ʱд���ݵ�������
		//����proto����
		Proto proto=new Proto();
		for (int i=0;i<10;i++){
			byte[] bytes = "from client toserver1".getBytes();
			//���������ݺ����ݳ������õ�proto
			proto.setArr(bytes);
			proto.setLength(bytes.length);
			ctx.writeAndFlush(proto);
		}

	}
}
