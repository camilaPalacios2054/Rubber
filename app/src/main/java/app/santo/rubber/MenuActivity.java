package app.santo.rubber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import app.santo.rubber.MenuCardView.MenuAdapter;
import app.santo.rubber.MenuCardView.MenuElement;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    List<MenuElement>elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();

    }
    public void init()
    {
        elements=new ArrayList<>();
        elements.add(new MenuElement("#FFFFFF","Datos de usuario"));
        elements.add(new MenuElement("#FFFFFF","Rutas creadas"));
        elements.add(new MenuElement("#FFFFFF","Crear ruta"));
        elements.add(new MenuElement("#FFFFFF","Iniciar viaje"));
        elements.add(new MenuElement("#FFFFFF","Buscar dirección"));
        elements.add(new MenuElement("#FFFFFF","Cerrar sesión"));
        elements.add(new MenuElement("#FFFFFF","Cerrar sesión"));
        elements.add(new MenuElement("#FFFFFF","Cerrar sesión"));

        MenuAdapter menuAdapter=new MenuAdapter(elements,this);
        RecyclerView recyclerView=findViewById(R.id.menuRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(menuAdapter);
    }
}