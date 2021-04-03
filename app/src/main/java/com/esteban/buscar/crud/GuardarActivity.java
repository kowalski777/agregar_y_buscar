package com.esteban.buscar.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.esteban.buscar.R;
import com.esteban.buscar.pojo.Producto;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GuardarActivity extends AppCompatActivity {

    public List<Producto> listProducto = new ArrayList<Producto>();
    public ArrayAdapter<Producto> arrayAdapterProducto;

    ListView lvId;


    private EditText mNombre;
    private EditText mPrecio;
    private EditText mCantidad;
    private EditText mCategoria;
    private Button mGuardar;


    //VARIABLE DE LOS DATOS QUE VAMOS A REGISTRAR
    private String nombre ="";
    private int precio = ' ';
    private int cantidad = ' ';
    private String categoria ="";

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        lvId = findViewById(R.id.lv_datosProductos);

        mNombre = (EditText) findViewById(R.id.editTextNombre);
        mPrecio = (EditText) findViewById(R.id.editTextPrecio);
        mCantidad = (EditText) findViewById(R.id.editTextCantidad);
        mCategoria = (EditText) findViewById(R.id.editTextCategoria);

        mGuardar = (Button) findViewById(R.id.btnGuardar);

        mGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                nombre = mNombre.getText().toString();
                precio = Integer.parseInt(mPrecio.getText().toString());
                cantidad = Integer.parseInt(mCantidad.getText().toString());
                categoria = mCategoria.getText().toString();

                if (!nombre.isEmpty() && precio != ' ' && cantidad != ' ' && !categoria.isEmpty()){
                    guardar();
                    nombre=""; precio =' '; cantidad =' '; categoria ="";
                    Toast.makeText(GuardarActivity.this, "Se alamcenó Item correctamente", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                }
                else{
                    Toast.makeText(GuardarActivity.this, "Debe completar los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }//fin oncreate

    private void guardar() {
        Producto p = new Producto();
        p.setUid(UUID.randomUUID().toString());
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        p.setCategoria(categoria);
        //mDatabase.child("Productos").setValue(p);
        mDatabase.child("Productos").child("items").child(p.getUid()).setValue(p);
    }
}