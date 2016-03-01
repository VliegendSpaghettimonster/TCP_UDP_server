package nl.joostvanstuijvenberg.tcp_udp_server;

/**
 * Private interface voor callback bij ontvangst data.
 */
public interface ClientCallBack {
    public void onDataReceived(String data);
}
