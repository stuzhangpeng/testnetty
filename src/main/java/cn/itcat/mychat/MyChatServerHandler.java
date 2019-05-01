package cn.itcat.mychat;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 客户端和服务器关系为多对一
 * 聊天服务器处理器
 * @author Administrator
 *
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
	//是一个set集合,储存已经add的channel
	private  static ChannelGroup channelGroup =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("msg"+msg);
		//获得已经连接的客户端通道
		Channel channel = ctx.channel();
		//遍历group查找到该channel
		for(Channel ch:channelGroup){
			//判断客户端是否是自己
			if(ch!= channel){
				//发送给其他客户端该信息
				ch.writeAndFlush(channel.remoteAddress()+"发送消息"+msg+"\n");
			}
			else{
			//发送给自己	
				ch.writeAndFlush("自己发送"+msg+"\n");
			}
		}
	}
     //捕获异常
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//出现异常
		cause.printStackTrace();
		//关闭通道
		ctx.close();
	}
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.add(channel);
		channelGroup.writeAndFlush("服务器"+channel.remoteAddress()+"加入\n");
		//把连接的通道，加入到channelgroup
	}
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// 如果通道移除
		Channel channel = ctx.channel();
		//发送消息给其他通道
		channelGroup.writeAndFlush("服务器"+channel.remoteAddress()+"离开\n");
		//会自动从group移除该channel
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 通道是活跃状态
		Channel channel = ctx.channel();
		//发送消息给其他通道
		System.out.println("服务器"+channel.remoteAddress()+"上线了\n");
	}
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	// 通道是离线状态
    			Channel channel = ctx.channel();
    			//发送消息给其他通道
    			System.out.println("服务器"+channel.remoteAddress()+"下线了\n");
    }
}
