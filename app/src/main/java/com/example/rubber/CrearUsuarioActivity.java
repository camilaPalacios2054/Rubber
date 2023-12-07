package com.example.rubber;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearUsuarioActivity extends AppCompatActivity {

    private EditText NombreyApellido,Email,Password;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        NombreyApellido=findViewById(R.id.NombreyApellido);
        Email=findViewById(R.id.Email);
        Password=findViewById(R.id.PasswordRegistro);

        db=FirebaseFirestore.getInstance();
    }
    public void enviarDatosFirestore(View view)
    {
        //Obtenemos datos del formulario
        String nombre=NombreyApellido.getText().toString();
        String email=Email.getText().toString();
        String password=Password.getText().toString();

        //Crea mapa para enviar los datos
        Map<String,Object> usuario=new HashMap<>();
        usuario.put("nombre",nombre);
        usuario.put("email",email);
        usuario.put("password",password);

        //Envia datos a firestore
        db.collection("usuarios")
                .document(nombre)
                .set(usuario)
                .addOnSuccessListener(aVoid ->
                {
                    Toast.makeText(CrearUsuarioActivity.this,"Datos enviados correctamente",Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e ->
                {
                    Toast.makeText(CrearUsuarioActivity.this,"Error al enviar los datos"+e.getMessage(),Toast.LENGTH_SHORT).show();
                });

    }
}