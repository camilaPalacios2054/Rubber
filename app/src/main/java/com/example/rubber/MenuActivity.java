package com.example.rubber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rubber.MenuCardView.MenuAdapter;
import com.example.rubber.MenuCardView.MenuElement;

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
        elements.add(new MenuElement("#775447","Perfil"));
        elements.add(new MenuElement("#607d8b","Gato"));
        elements.add(new MenuElement("#775447","Cuenta"));
        elements.add(new MenuElement("#775447","Perfil"));

        MenuAdapter menuAdapter=new MenuAdapter(elements,this);
        RecyclerView recyclerView=findViewById(R.id.menuRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(menuAdapter);
    }
}