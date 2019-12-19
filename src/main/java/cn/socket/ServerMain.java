package cn.socket;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cn.pojo.SocketChannel;

public class ServerMain {
	private static ServerSocket server;
	private static Map<String, SocketChannel> map = new HashMap<String, SocketChannel>();
	private static int port;

	static {
		try {
			// 从配置文件读取接收socket的端口号
			InputStream is = ServerMain.class.getResourceAsStream("/JGServer.properties");
			Properties properties = new Properties();
			properties.load(is);
			port = Integer.parseInt(properties.getProperty("port"));
			System.out.println("port=" + port);
			is.close();
			server = new ServerSocket(port);
			System.out.println("服务器启动！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Map<String, SocketChannel> getChannels() {
		return map;
	}

	/**
	 * 接收客户端的socket
	 * 
	 * @author Administrator
	 *
	 */
	static class AcceptClient implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Socket socket = null;
				// 实时监听接收客户端连过来的所有socket
				while ((socket = server.accept()) != null) {
					System.out.println(socket);
					AcceptMsg acceptMsg = new AcceptMsg(socket);
					Thread thread = new Thread(acceptMsg);
					thread.start();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 让每个socket实时监听数据
	 * 
	 * @author Administrator
	 *
	 */
	static class AcceptMsg implements Runnable {
		private DataInputStream in;
		private Socket socket;

		public AcceptMsg(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				in = new DataInputStream(socket.getInputStream());
				byte[] bt = new byte[1024];
				while (in != null) {
					bt = new byte[1024];
					try {
						in.read(bt);
						long time = System.currentTimeMillis() / 1000;
						String ip = socket.getRemoteSocketAddress().toString().split(":")[0].substring(1);
					} catch (SocketTimeoutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						socket.close();
					}
					if (bt[0] == 0x7f) {
						byte bt1 = 0;
						for (int i = 0; i < bt.length - 1; i++) {
							bt1 += bt[i];
						}

						if (bt1 == bt[bt.length - 1]) {
							System.out.println("校验码正确");
							int type = JudeInfoType(bt[2]);
							switch (type) {
							case 0: {
								// 注册
								break;
							}
							case 1: {
								// 心跳
								break;
							}
							case 2: {
								// 报警
								break;
							}
							}
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 判断IG-->IC数据
	 * 
	 * @param bt
	 * @return 0:注册;1:心跳;2:报警
	 */
	public static int JudeInfoType(byte bt) {
		int result = 0;
		switch (bt) {
		case 0x30: {
			System.out.println("注册");
			result = 0;
			break;
		}
		case 0x33: {
			break;
		}
		case 0x43: {
			System.out.println("心跳");
			result = 1;
			break;
		}
		case 0x46: {
			break;
		}
		case 0x48: {
			break;
		}
		case 0x4A: {
			break;
		}
		case 0x4B: {
			System.out.println("报警");
			result = 2;
			break;
		}
		case 0x4F: {
			break;
		}
		}
		return result;
	}
}
