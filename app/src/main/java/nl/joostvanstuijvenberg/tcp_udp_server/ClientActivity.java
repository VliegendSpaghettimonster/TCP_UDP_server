package nl.joostvanstuijvenberg.tcp_udp_server;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;

public class ClientActivity extends AppCompatActivity implements ClientCallBack {

    private Client client;
    private EditText edtSendData;
    private TextView txvReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        edtSendData = (EditText) findViewById(R.id.edtSendData);
        txvReceived = (TextView) findViewById(R.id.txvReceived);

        // Bepaal de verbindingsparameters en het protocol voor de client.
        Bundle extras = getIntent().getExtras();
        try {
            InetAddress i = InetAddress.getByName(extras.getString("IPADDRESS"));
            short p = Short.decode(extras.getString("PORT"));
            String proto = extras.getString("PROTOCOL");
            if (proto.equals("TCP"))
                client = new TCPClient(i, p);
            else
                client = new UDPClient(i, p);
            client.setClientCallBack(this);

            // Maak de connectie.
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        client.connect();
                    }
                    catch (IOException e) {
                        //TODO foutafhandeling
                    }
                    return null;
                }
            }.execute();

            // Soort van titelbalk bovenaan.
            ((TextView) findViewById(R.id.title)).setText(proto + "-client to " + i + " at " + p);

            // Send-button, gedisabled als er geen verbinding is.
            Button b = (Button) findViewById(R.id.btnSend);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String d = edtSendData.getText().toString();
                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                client.send(d);
                            }
                            catch (IOException e) {
                                //TODO foutafhandeling
                            }
                            return null;
                        }
                    }.execute();
                }
            });

        } catch (IOException e) {
            Log.e("", e.getMessage(), e);
        }
    }


    @Override
    public void onDataReceived(String data) {
        txvReceived.append(data + '\n');
    }
}
