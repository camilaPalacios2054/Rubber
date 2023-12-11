package com.example.rubber;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class MqttActivity extends AppCompatActivity {

    String clienteID="";

    //CONEXION AL SERVIDOR

    //mqtt://santotomasproyect-mqtt:L8d9iJ3GBRveTeU9@santotomasproyect-mqtt.cloud.shiftr.io
    //mqtt://santotomasproyect-mqtt:L8d9iJ3GBRveTeU9@santotomasproyect-mqtt.cloud.shiftr.io
    //mqtt://sharddrifter615:s6krS92TYNl3w4dh@sharddrifter615.cloud.shiftr.io
    static String MQTTHOST="tcp://santotomasproyect-mqtt.cloud.shiftr.io:1883";
    static String MQTTUSER="santotomasproyect-mqtt";
    static String MQTTPASS="L8d9iJ3GBRveTeU9";
    static String TOPIC="LED";
    static String TOPIC_MSG_ON="ENCENDER";
    static String TOPIC_MSG_OFF="APAGAR";

    MqttAndroidClient cliente;
    MqttConnectOptions opciones;

    TextView txtIdCliente;
    Boolean permisoPublicar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt);

        txtIdCliente=findViewById(R.id.txtIdCliente);
        Button btnON=findViewById(R.id.btnON );
        Button btnOFF=findViewById(R.id.btnOFF);

        getNombreCliente();
        connectBroker();
        btnON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje(TOPIC,TOPIC_MSG_ON);
            }
        });
        btnOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje(TOPIC,TOPIC_MSG_OFF);
            }
        });
    }
    private void checkConnection()
    {
        if(this.cliente.isConnected())
        {
            this.permisoPublicar=true;
        }else {
            this.permisoPublicar=false;
            connectBroker();

        }
    }
    private void enviarMensaje(String topic,String msg)
    {
        checkConnection();
        if(this.permisoPublicar)
        {
            try
            {
                int qos=0;
                this.cliente.publish(topic,msg.getBytes(),qos,false);
                Toast.makeText(getBaseContext(),topic+" : "+msg,Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
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
                    suscribirseTopic();
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
    private void suscribirseTopic()
    {
        try
        {
            this.cliente.subscribe(TOPIC,0);
        }
        catch (MqttSecurityException e)
        {
            e.printStackTrace();
        }
        catch (MqttException e)
        {
            e.printStackTrace();
        }
        this.cliente.setCallback(new MqttCallback()
        {
            @Override
            public void connectionLost(Throwable cause)
            {
                Toast.makeText(getBaseContext(),"CONEXIÓN PERDIDA",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception
            {
                TextView txtInfo=findViewById(R.id.txtInfo);

               if(topic.matches(TOPIC))
               {
                   String msg=new String(message.getPayload());
                   if(msg.matches(TOPIC_MSG_ON)) {
                       txtInfo.setText(msg);
                       txtInfo.setBackgroundColor(GREEN);
                   }
                   if(msg.matches(TOPIC_MSG_OFF)) {
                       txtInfo.setText(msg);
                       txtInfo.setBackgroundColor(RED);
                   }
               }

            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token)
            {
                Toast.makeText(getBaseContext(),"ENTREGA COMPLETA",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getNombreCliente()
    {
        String manufacturer= Build.MANUFACTURER;
        String modelName=Build.MODEL;
        this.clienteID=manufacturer+" "+modelName;
        txtIdCliente.setText(this.clienteID);
    }
}