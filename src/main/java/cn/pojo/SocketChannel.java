package cn.pojo;

import java.net.Socket;

public class SocketChannel {
	private String deviceid;
	private Socket socket;
	private long time;

	public SocketChannel() {
	}

	public SocketChannel(String deviceid, Socket socket, long time) {
		this.deviceid = deviceid;
		this.socket = socket;
		this.time = time;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Heart [deviceid=" + deviceid + ", socket=" + socket + ", time=" + time + "]";
	}
}
