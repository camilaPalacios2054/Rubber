package com.example.rubber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttActivity extends AppCompatActivity {

    String clienteID="";

    //CONEXION AL SERVIDOR

    //mqtt://santotomasproyect-mqtt:L8d9iJ3GBRveTeU9@santotomasproyect-mqtt.cloud.shiftr.io
    //mqtt://sharddrifter615:s6krS92TYNl3w4dh@sharddrifter615.cloud.shiftr.io
    static String MQTTHOST="tcp://broker.shiftr.io:1883";
    static String MQTTUSER="sharddrifter615";
    static String MQTTPASS="s6krS92TYNl3w4dh";

    MqttAndroidClient cliente;
    MqttConnectOptions opciones;

    TextView txtIdCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt);

        txtIdCliente=findViewById(R.id.txtIdCliente);

        getNombreCliente();
        connectBroker();
    }
    private void connectBroker()
    {
        this.cliente=new MqttAndroidClient(this.getApplicationContext(),MQTTHOST,this.clienteID);
        this.opciones=new MqttConnectOptions();
        this.opciones.setUserName(MQTTUSER);
        this.opciones.setPassword(MQTTPASS.toCharArray());

        try
        {
            IMqttToken token=this.cliente.connect(opciones);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(getBaseContext(),"CONECTADO",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(getBaseContext(),"CONEXIÓN FALLIDA",Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (MqttException e){
            e.printStackTrace();
        }
    }
    private void getNombreCliente()
    {
        String manufacturer= Build.MANUFACTURER;
        String modelName=Build.MODEL;
        this.clienteID=manufacturer+" "+modelName;
        txtIdCliente.setText(this.clienteID);
    }
}