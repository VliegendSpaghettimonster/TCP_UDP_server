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

        ip = (EditText)findViewById(R.id.edtIP);
        ip.setText("127.0.0.1"); // veranderen in actuele ip-adres
        port = (EditText)findViewById(R.id.edtPort);
        port.setText("9876");

        final Switch s = (Switch)findViewById(R.id.swtProtocol);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s.setText(isChecked ? "UDP" : "TCP");
            }
        });

        Button b1 = (Button)findViewById(R.id.btnClient);
        b1.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ClientActivity.class);
                i.putExtra("IPADDRESS", ip.getText().toString());
                i.putExtra("PORT", port.getText().toString());
                i.putExtra("PROTOCOL", s.getText().toString());
                startActivity(i);
            }
        });

        Button b2 = (Button)findViewById(R.id.btnServer);
        b2.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ServerActivity.class);
                i.putExtra("IPADDRESS", ip.getText().toString());
                i.putExtra("PORT", port.getText().toString());
                i.putExtra("PROTOCOL", s.getText().toString());
                startActivity(i);
            }
        });

    }

}
