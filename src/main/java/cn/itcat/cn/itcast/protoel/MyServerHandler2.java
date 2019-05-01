package cn.itcat.cn.itcast.protoel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Executors;

/**
 * ��������
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
		//����һ���߳�д���ݵ�������2
		Proto oroto =new Proto();
		for (int i=0;i<5;i++){
			byte[] bytes = "from server2 to server1".getBytes();
			//���������ݺ����ݳ������õ�proto
			oroto.setArr(bytes);
			oroto.setLength(bytes.length);
			ctx.writeAndFlush( oroto);
		}
	}
	//�����쳣
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//�����쳣
		cause.printStackTrace();
		//�ر�ͨ��
		ctx.close();
	}
}
