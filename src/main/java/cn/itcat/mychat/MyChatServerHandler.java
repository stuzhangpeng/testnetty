package cn.itcat.mychat;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * �ͻ��˺ͷ�������ϵΪ���һ
 * ���������������
 * @author Administrator
 *
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
	//��һ��set����,�����Ѿ�add��channel
	private  static ChannelGroup channelGroup =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("msg"+msg);
		//����Ѿ����ӵĿͻ���ͨ��
		Channel channel = ctx.channel();
		//����group���ҵ���channel
		for(Channel ch:channelGroup){
			//�жϿͻ����Ƿ����Լ�
			if(ch!= channel){
				//���͸������ͻ��˸���Ϣ
				ch.writeAndFlush(channel.remoteAddress()+"������Ϣ"+msg+"\n");
			}
			else{
			//���͸��Լ�	
				ch.writeAndFlush("�Լ�����"+msg+"\n");
			}
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
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.add(channel);
		channelGroup.writeAndFlush("������"+channel.remoteAddress()+"����\n");
		//�����ӵ�ͨ�������뵽channelgroup
	}
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// ���ͨ���Ƴ�
		Channel channel = ctx.channel();
		//������Ϣ������ͨ��
		channelGroup.writeAndFlush("������"+channel.remoteAddress()+"�뿪\n");
		//���Զ���group�Ƴ���channel
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// ͨ���ǻ�Ծ״̬
		Channel channel = ctx.channel();
		//������Ϣ������ͨ��
		System.out.println("������"+channel.remoteAddress()+"������\n");
	}
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	// ͨ��������״̬
    			Channel channel = ctx.channel();
    			//������Ϣ������ͨ��
    			System.out.println("������"+channel.remoteAddress()+"������\n");
    }
}
