package com.unlimitedappworks.serviciosweb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.unlimitedappworks.serviciosweb.SW.Enlace;
import com.unlimitedappworks.serviciosweb.SW.JSON;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tvDatos);
        new Enlace("http://couplecare.us/backend/women/getDates/4628") {
            String dt;

            @Override
            public void onRecibir(String datos) {
                super.onRecibir(datos);
                dt = datos;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(dt);
                        List<HashMap<Object, Object>> list = JSON.decodificar(dt);
                        try {
                            msg(list.get(0).get("PeriodStart").toString());
                        } catch (Exception e) {

                        }
                    }
                });
            }

            @Override
            public void onError(final Exception e) {
                super.onError(e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        msg(e.getMessage());
                    }
                });
            }
        }.navegar();
    }

    public void msg(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }
}
