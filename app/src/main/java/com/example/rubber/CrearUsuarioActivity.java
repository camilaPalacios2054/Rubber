package com.example.rubber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrearUsuarioActivity extends AppCompatActivity {

    private EditText NombreyApellido,Email,Password;
    private ListView datos;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_crear_usuario);

        db=FirebaseFirestore.getInstance();
        NombreyApellido=findViewById(R.id.NombreyApellido);
        Email=findViewById(R.id.Email);
        Password=findViewById(R.id.PasswordRegistro);
        datos=findViewById(R.id.datos);


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
    public void CargarLista(View view)
    {CargarListaFirestore();}
    public void CargarListaFirestore()
    {
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            List<String>datoUsuarios=new ArrayList<>();

                            for(QueryDocumentSnapshot document: task.getResult()){
                                String linea="||"+document.getString("nombre")+"||"+
                                        document.getString("email")+"||"+
                                        document.getString("password");
                                datoUsuarios.add(linea);
                            }

                            ArrayAdapter<String> adaptador=new ArrayAdapter<>(CrearUsuarioActivity.this, android.R.layout.simple_list_item_1,datoUsuarios);
                            datos.setAdapter(adaptador);

                        } else {
                            Log.e("TAG", "Error al obtener los datos",task.getException());
                        }
                    }
                });
    }
}