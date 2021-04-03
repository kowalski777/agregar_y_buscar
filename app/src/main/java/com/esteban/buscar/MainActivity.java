package com.esteban.buscar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.esteban.buscar.adapter.AdapterProducto;
import com.esteban.buscar.crud.GuardarActivity;
import com.esteban.buscar.pojo.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<Producto> list;
    RecyclerView rv;
    SearchView searchView;
    AdapterProducto adapter;

    LinearLayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ref= FirebaseDatabase.getInstance().getReference().child("Productos/items");
        rv = findViewById(R.id.rv);
        searchView = findViewById(R.id.search);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm); //conectar el rv con el lm y le pasamos la orientacion
        list = new ArrayList<>();
        adapter = new AdapterProducto(list);
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Producto pr = snapshot.getValue(Producto.class);
                        list.add(pr);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                buscar(s);
                return true;
            }
        });

        Button irAGuardar = (Button) findViewById(R.id.iraguardar);

        irAGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GuardarActivity.class);
                startActivity(intent);
            }
        });


    }//fin de Oncreate

    private void buscar(String s){
        ArrayList<Producto> mylista = new ArrayList<>();
        for(Producto obj: list){
            if (obj.getNombre().toLowerCase().contains(s.toLowerCase())){
                mylista.add(obj);
            }

        }
        AdapterProducto adapter = new AdapterProducto(mylista);
        rv.setAdapter(adapter);
    }
}