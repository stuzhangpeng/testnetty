package cn.itcast.nettyserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * 利用非阻塞式io书写server端
 * 
 * @author Administrator
 *
 */
public class NIOServer {
	public static void main(String[] args) throws IOException {
		// 创建服务器通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// 设置ServerSockethannel为非阻塞模式
		serverChannel.configureBlocking(false);
		SocketAddress endpoint = new InetSocketAddress(8899);
		// 绑定端口
		serverChannel.bind(endpoint);
		// 获得选择器
		Selector selector = Selector.open();
		// 把channel注入注册到selector
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (selector.select()>0) {
			try {
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				for (SelectionKey key : selectedKeys) {
					SocketChannel socketChannel = null;
					if (key.isAcceptable()) {
						// 如果已经连接，取出注册的ServerSocketChannel
						ServerSocketChannel channel = (ServerSocketChannel) key.channel();
						// 获得客户端socketChannel
						socketChannel = channel.accept();
						// 设置为非阻塞模式
						socketChannel.configureBlocking(false);
						// 把socketChannel注册到选择器中,监听相关事件
						socketChannel.register(selector, SelectionKey.OP_READ);
					} else if (key.isReadable()) {
						// 获得socket通道，读取数据
						socketChannel = (SocketChannel) key.channel();
						ByteBuffer dst = ByteBuffer.allocate(1024);
						// 获取数据
						int read = socketChannel.read(dst);
						// 如果读取到数据
						if (read >0) {
							dst.flip();
							byte[] dst1 = new byte[dst.limit()];
							Charset forName = Charset.forName("utf-8");
							System.out.println(String.valueOf(forName.decode(dst).array()));
							ByteBuffer allocate = ByteBuffer.allocate(20);
							allocate.put("hello".getBytes());
							allocate.flip();
							//向客户端写数据
							socketChannel.write(allocate);
						}

					}
					selectedKeys.clear();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
