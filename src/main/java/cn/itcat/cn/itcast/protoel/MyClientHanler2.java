package cn.itcat.cn.itcast.protoel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Executors;

/**�ӷ�������Ϊ�ͻ��˵Ĵ�����
 * @author Administrator
 *
 */
public class MyClientHanler2 extends SimpleChannelInboundHandler<Proto> {

	private int number;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Proto msg) throws Exception {
		//����Server2����Ϣ
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
		//��channel���ڼ���״̬ʱ�ӷ�����1д���ݵ�������2
		//�������ݵ�server2
		//����proto����
		//�����µ��߳�ִ��д������
		Executors.newSingleThreadScheduledExecutor().submit(() ->{
			Proto proto=new Proto();
			for (int i=0;i<5;i++){
				byte[] bytes = "from server1 to server2".getBytes();
				//���������ݺ����ݳ������õ�proto
				proto.setArr(bytes);
				proto.setLength(bytes.length);
				ctx.writeAndFlush(proto);
			}
		} );
	}
}
