package nl.joostvanstuijvenberg.tcp_udp_server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Private interface voor client, volgens Strategy pattern.
 */
public interface Client {
    public void connect() throws IOException;
    public void setClientCallBack(ClientCallBack callBack);
    public void send(String data) throws IOException;
}
