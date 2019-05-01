package cn.itcat.heartbeat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
/**
 * 
 * 处理空闲检查hanler
 * @author Administrator
 *
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		
		if(evt instanceof IdleStateEvent){
			//判断事件类型
			IdleStateEvent event =(IdleStateEvent) evt;
			String eventType =null;
			switch(event.state()){
			   //判断空闲类型
			    case READER_IDLE:
			    	eventType="读空闲";
			    case WRITER_IDLE:
			    	eventType="写空闲";
			    case ALL_IDLE:
			    	eventType="读写空闲";
			        break;
			
			}
			System.out.println(ctx.channel().remoteAddress()+"空闲事件"+eventType);
			ctx.channel().close();
		}
	}

	
    
}
