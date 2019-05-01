package cn.itcast.nettyserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * nio客户端
 * 
 * @author Administrator
 *
 */
public class NIOClientt4 {
	public static void main(String[] args) throws IOException {
		// 获取通道
		SocketChannel clientChannel = SocketChannel.open();
		// 设置为非阻塞
		clientChannel.configureBlocking(false);
		// 注册监听
		Selector selector = Selector.open();
		clientChannel.register(selector, SelectionKey.OP_CONNECT);
		// 监听端口
		SocketAddress remote = new InetSocketAddress(8899);
		// 打开链接
		clientChannel.connect(remote);
		// 轮询选择器上准备就绪的事件
		while (selector.select() > 0) {
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey next = iterator.next();
				// 判断具体发生的事件
				if (next.isConnectable()) {
					// 连接就绪。获取channel
					SocketChannel client = (SocketChannel) next.channel();
					// 判断通道是否正在操作
					if (client.isConnectionPending()) {
						// 完成连接
						client.finishConnect();
						System.out.println(3333);
						// 连接成功发送数据
						ByteBuffer writerBuffer = ByteBuffer.allocate(512);
						writerBuffer.put((LocalDateTime.now().toString() + "客户端请求连接").getBytes());
						// 向服务端发送数据
						writerBuffer.flip();
						client.write(writerBuffer);
						writerBuffer.clear();
						// 开启线程池一个新的线程来处理键盘录入(使用singelThreadpool)
						ExecutorService pool = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
						pool.submit(new Runnable() {
							@Override
							public void run() {
								// 执行键盘输入的监听和发送
								BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
								try {
									ByteBuffer buffer = ByteBuffer.allocate(512);
									String line = null;
									while ((line = bufferedReader.readLine()) != null) {
										System.out.println("shoudaode "+line);
										buffer.put(line.getBytes());
										buffer.flip();
										client.write(buffer);
										buffer.clear();
									}
									// 把键盘输入的数据发送到服务器
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} finally {
									// 关流
									try {
										bufferedReader.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

							}
						});// 匿名内部类.

					}
					// 注册到selector
					client.register(selector, SelectionKey.OP_READ);
				} else if (next.isReadable()) {
					// 读取服务器数据
					SocketChannel channel = (SocketChannel) next.channel();
					// 创建一个1kb的缓冲区读取数据
					ByteBuffer dsts = ByteBuffer.allocate(1024);
					// 读取数据
					int read = channel.read(dsts);
					if (read > 0) {
						System.out.println(new String(dsts.array(), 0, read));
					}

				}
				// 清空selectionkey
				iterator.remove();
			}

		}
	}
}
