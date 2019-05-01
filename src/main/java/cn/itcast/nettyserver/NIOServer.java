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
 * ���÷�����ʽio��дserver��
 * 
 * @author Administrator
 *
 */
public class NIOServer {
	public static void main(String[] args) throws IOException {
		// ����������ͨ��
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// ����ServerSockethannelΪ������ģʽ
		serverChannel.configureBlocking(false);
		SocketAddress endpoint = new InetSocketAddress(8899);
		// �󶨶˿�
		serverChannel.bind(endpoint);
		// ���ѡ����
		Selector selector = Selector.open();
		// ��channelע��ע�ᵽselector
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (selector.select()>0) {
			try {
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				for (SelectionKey key : selectedKeys) {
					SocketChannel socketChannel = null;
					if (key.isAcceptable()) {
						// ����Ѿ����ӣ�ȡ��ע���ServerSocketChannel
						ServerSocketChannel channel = (ServerSocketChannel) key.channel();
						// ��ÿͻ���socketChannel
						socketChannel = channel.accept();
						// ����Ϊ������ģʽ
						socketChannel.configureBlocking(false);
						// ��socketChannelע�ᵽѡ������,��������¼�
						socketChannel.register(selector, SelectionKey.OP_READ);
					} else if (key.isReadable()) {
						// ���socketͨ������ȡ����
						socketChannel = (SocketChannel) key.channel();
						ByteBuffer dst = ByteBuffer.allocate(1024);
						// ��ȡ����
						int read = socketChannel.read(dst);
						// �����ȡ������
						if (read >0) {
							dst.flip();
							byte[] dst1 = new byte[dst.limit()];
							Charset forName = Charset.forName("utf-8");
							System.out.println(String.valueOf(forName.decode(dst).array()));
							ByteBuffer allocate = ByteBuffer.allocate(20);
							allocate.put("hello".getBytes());
							allocate.flip();
							//��ͻ���д����
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
