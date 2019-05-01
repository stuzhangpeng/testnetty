package cn.itcat.heartbeat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
/**
 * 
 * ������м��hanler
 * @author Administrator
 *
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		
		if(evt instanceof IdleStateEvent){
			//�ж��¼�����
			IdleStateEvent event =(IdleStateEvent) evt;
			String eventType =null;
			switch(event.state()){
			   //�жϿ�������
			    case READER_IDLE:
			    	eventType="������";
			    case WRITER_IDLE:
			    	eventType="д����";
			    case ALL_IDLE:
			    	eventType="��д����";
			        break;
			
			}
			System.out.println(ctx.channel().remoteAddress()+"�����¼�"+eventType);
			ctx.channel().close();
		}
	}

	
    
}
