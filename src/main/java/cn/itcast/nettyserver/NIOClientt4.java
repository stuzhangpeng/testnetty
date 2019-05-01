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
 * nio�ͻ���
 * 
 * @author Administrator
 *
 */
public class NIOClientt4 {
	public static void main(String[] args) throws IOException {
		// ��ȡͨ��
		SocketChannel clientChannel = SocketChannel.open();
		// ����Ϊ������
		clientChannel.configureBlocking(false);
		// ע�����
		Selector selector = Selector.open();
		clientChannel.register(selector, SelectionKey.OP_CONNECT);
		// �����˿�
		SocketAddress remote = new InetSocketAddress(8899);
		// ������
		clientChannel.connect(remote);
		// ��ѯѡ������׼���������¼�
		while (selector.select() > 0) {
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey next = iterator.next();
				// �жϾ��巢�����¼�
				if (next.isConnectable()) {
					// ���Ӿ�������ȡchannel
					SocketChannel client = (SocketChannel) next.channel();
					// �ж�ͨ���Ƿ����ڲ���
					if (client.isConnectionPending()) {
						// �������
						client.finishConnect();
						System.out.println(3333);
						// ���ӳɹ���������
						ByteBuffer writerBuffer = ByteBuffer.allocate(512);
						writerBuffer.put((LocalDateTime.now().toString() + "�ͻ�����������").getBytes());
						// �����˷�������
						writerBuffer.flip();
						client.write(writerBuffer);
						writerBuffer.clear();
						// �����̳߳�һ���µ��߳����������¼��(ʹ��singelThreadpool)
						ExecutorService pool = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
						pool.submit(new Runnable() {
							@Override
							public void run() {
								// ִ�м�������ļ����ͷ���
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
									// �Ѽ�����������ݷ��͵�������
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} finally {
									// ����
									try {
										bufferedReader.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

							}
						});// �����ڲ���.

					}
					// ע�ᵽselector
					client.register(selector, SelectionKey.OP_READ);
				} else if (next.isReadable()) {
					// ��ȡ����������
					SocketChannel channel = (SocketChannel) next.channel();
					// ����һ��1kb�Ļ�������ȡ����
					ByteBuffer dsts = ByteBuffer.allocate(1024);
					// ��ȡ����
					int read = channel.read(dsts);
					if (read > 0) {
						System.out.println(new String(dsts.array(), 0, read));
					}

				}
				// ���selectionkey
				iterator.remove();
			}

		}
	}
}
