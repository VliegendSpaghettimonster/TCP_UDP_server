package nl.joostvanstuijvenberg.tcp_udp_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Private class voor implementatie TCP-client.
 */
class TCPClient implements Client, Runnable {

    private InetAddress address;

    private short port;

    private Socket socket;

    private BufferedReader reader;

    private DataOutputStream writer;

    private ClientCallBack callback;

    public TCPClient(InetAddress address, short port) {
        this.address = address;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket(address, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new DataOutputStream(socket.getOutputStream());
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public void setClientCallBack(ClientCallBack callback) {
        this.callback = callback;
    }

    public void send(String data) throws IOException {
        writer.writeBytes(data + '\n');
    }

    public void run() {
        try {
            while (true) {

            }
        }
        catch (Exception e) {}
    }

}
