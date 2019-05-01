package cn.itcat.cn.itcast.protoel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.net.Socket;

/**
 *
 * add ChannelHandler for Channel
 *
 *
 */
public class MyServerInitnizer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new MyEncorder());
        pipeline.addLast(new MyChatServerHandler());
    }
}
