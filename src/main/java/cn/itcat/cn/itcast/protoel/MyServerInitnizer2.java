package cn.itcat.cn.itcast.protoel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 *
 * add ChannelHandler for Channel
 *
 *
 */
public class MyServerInitnizer2 extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new MyEncorder());
        pipeline.addLast(new MyServerHandler2());
    }
}
