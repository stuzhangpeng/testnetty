package cn.itcat.cn.itcast.protoel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 自定义协议和编解码器实现java聊天服务器
 */
public class MyServer2 {
    public static void main(String[] args) throws InterruptedException {
        //创造两个事件循环组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        //启动服务器
        bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new MyServerInitnizer2());
        try {
            //绑定端口
            ChannelFuture channelFuture = bootstrap.bind(9999).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            //友好关闭事件循环组
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
