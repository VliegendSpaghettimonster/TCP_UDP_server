package nl.joostvanstuijvenberg.tcp_udp_server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Private class voor implementatie UDP-client.
 */
class UDPClient implements Client, Runnable {

    private DatagramSocket socket;

    private InetAddress address;

    private short port;

    private ClientCallBack callback;

    public UDPClient(InetAddress address, short port) {
        this.address = address;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new DatagramSocket();
    }

    public boolean isConnected() {
        if (socket != null)
            return socket.isConnected();
        return false;
    }

    public void setClientCallBack(ClientCallBack callback) {
        this.callback = callback;
    }

    public void send(String data) throws IOException {
        byte[] d = new byte[1024];
        d = data.getBytes();
        DatagramPacket p = new DatagramPacket(d, d.length, address, port);
        socket.send(p);
    }

    public void run() {

    }

}
