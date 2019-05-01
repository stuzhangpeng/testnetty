package cn.itcat.cn.itcast.protoel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Executors;

/**
 * 客户端和服务器关系为多对一
 * 聊天服务器处理器
 *
 * @author AdministratorMyChatServerHandler
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<Proto> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Proto msg) throws Exception {
        //开启一个新的线程执行任务
        Executors.newSingleThreadScheduledExecutor().submit(() -> {
            System.out.println(++count);
            System.out.println(new String(msg.getArr()));
            //写数据到client
            Proto oroto = new Proto();
            for (int i = 0; i < 10; i++) {
                byte[] bytes = "from server1 to client".getBytes();
                //把数据类容和数据长度设置到proto
                oroto.setArr(bytes);
                oroto.setLength(bytes.length);
                ctx.writeAndFlush(oroto);
            }
        });
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
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //这个服务器充当客户端。读取完客户端数据后把数据写入到另一台服务器
        //这种情况的最佳实践就是共用一个eventlop
        EventLoop eventExecutors = ctx.channel().eventLoop();
        Bootstrap strap = new Bootstrap();
        //复用当前的eventloop
        strap.channel(NioSocketChannel.class).handler(new MyClientInitlizer1());
        ChannelFuture localhost = strap.group(eventExecutors).connect("localhost", 9999);
        localhost.channel().closeFuture();
    }
}

