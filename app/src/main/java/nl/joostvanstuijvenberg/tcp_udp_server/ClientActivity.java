package nl.joostvanstuijvenberg.tcp_udp_server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientActivity extends AppCompatActivity {

    private String ip;
    private short port;
    private String protocol;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Bundle extras = getIntent().getExtras();
        ip = extras.getString("IPADDRESS");
        port = Short.decode(extras.getString("PORT"));
        protocol = extras.getString("PROTOCOL");
        if (protocol.equals("TCP"))
            client = new TCPClient();
        else
            client = new UDPClient();

        // Soort van titelbalk bovenaan.
        ((TextView)findViewById(R.id.title)).setText(protocol + "-client to " + ip + " at " + port);

        // Send-button, gedisabled als er geen verbinding is.
        Button b = (Button)findViewById(R.id.btnSend);
        b.setEnabled(client.isConnected());
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    // hier een andere life cycle event method om de Send-button te enablen.

    private interface Client {
        public void connect();
        public boolean isConnected();
        public void send();
    }

    private class TCPClient implements Client {
        private boolean isConnected =false;
        public void connect() {
            try {
                socket = new Socket(InetAddress.getByName(ip), port);
            }
            catch (UnknownHostException e) {

            }
            catch (IOException e) {

            }
        }
    }

}
