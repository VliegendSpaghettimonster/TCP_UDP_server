package nl.joostvanstuijvenberg.tcp_udp_server;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    EditText ip;
    EditText port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // IP-adres (hostnaam) en poortnummer.
        ip = (EditText)findViewById(R.id.edtIP);
        ip.setText("10.0.2.2"); // veranderen in actuele ip-adres
        port = (EditText)findViewById(R.id.edtPort);
        port.setText("9876");

        // Keuze client of server.
        final Switch s1 = (Switch)findViewById(R.id.swtClientServer);
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s1.setText(isChecked ? "Server" : "Client");
            }
        });

        // Keuze TCP of UDP.
        final Switch s2 = (Switch)findViewById(R.id.swtProtocol);
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s2.setText(isChecked ? "UDP" : "TCP");
            }
        });

        // Startknop.
        Button b1 = (Button)findViewById(R.id.btnStart);
        b1.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Bepaal welke Strategy de client-functionaliteit gaat implementeren.
                Class client;
                if (s1.getText().toString().equals("Client"))
                    client = ClientActivity.class;
                else
                    client = ServerActivity.class;

                // Start de juiste Activity met de gevraagde verbindingseigenschappen.
                Intent i = new Intent(getApplicationContext(), client);
                i.putExtra("IPADDRESS", ip.getText().toString());
                i.putExtra("PORT", port.getText().toString());
                i.putExtra("PROTOCOL", s2.getText().toString());
                startActivity(i);
            }
        });

    }

}
