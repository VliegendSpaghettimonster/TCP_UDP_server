package nl.joostvanstuijvenberg.tcp_udp_server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ServerActivity extends AppCompatActivity {

    private String ip;
    private String port;
    private String protocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        Bundle extras = getIntent().getExtras();
        ip = extras.getString("IPADDRESS");
        port = extras.getString("PORT");
        protocol = extras.getString("PROTOCOL");

        ((TextView)findViewById(R.id.title)).setText(protocol + "-server on " + ip + " at " + port);

    }
}
