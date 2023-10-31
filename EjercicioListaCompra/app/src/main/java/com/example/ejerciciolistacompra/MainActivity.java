package com.example.ejerciciolistacompra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.ejerciciolistacompra.adapters.ProductoAdapter;
import com.example.ejerciciolistacompra.modelos.Producto;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciolistacompra.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<Producto> listaCompra; //lista de objetos
    private ProductoAdapter adapter;
    CardView cardProducto;
    private RecyclerView.LayoutManager layoutManager;//Creamos manejador obligatorio para el recycler view
    //private ActivityResultLauncher<Intent> addProductoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        
        listaCompra = new ArrayList<>(); //Creamos la lista
         crearProducto();

        //CREAMOS ADAPTER            //listaObjetos, FIla/estructura, Contexto
        adapter = new ProductoAdapter(listaCompra,R.layout.producto_view_model, MainActivity.this);
        //enlazamos el recycler y el adapter
        binding.contentMain.contenedor.setAdapter(adapter);

        //Creamos nuevo layaout pasandole el contexto/ventana
        layoutManager = new LinearLayoutManager(MainActivity.this);
        //lo añadimos a la vista (contenedor)
        binding.contentMain.contenedor.setLayoutManager(layoutManager);

       /* cardProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProducto();
            }
        });*/


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProducto().show();
            }
        });
    }

    private void crearProducto() {
        for (int i = 0; i < 100; i++) {
            listaCompra.add(new Producto("Producto"+i, i,1 ));
        }
    }

    private AlertDialog createProducto(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Producto");
        builder.setCancelable(false);


        View productoAlert = LayoutInflater.from(this).inflate(R.layout.producto_editar_alert, null);
        EditText txtCantidad = productoAlert.findViewById(R.id.txtCantidadEditarAlert);
        EditText txtPrecio = productoAlert.findViewById(R.id.txtPrecioEditarAlert);
        EditText txtImporte = productoAlert.findViewById(R.id.txtImporteTotalEditarAlert);
        String txtNombre = (String)productoAlert.findViewById(R.id.txtNombreEditarAlert).toString();
        builder.setView(productoAlert);

        builder.setNegativeButton("Cancelar",null);
        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!txtCantidad.getText().toString().isEmpty() && !txtPrecio.getText().toString().isEmpty()){
                    listaCompra.add(new Producto(txtNombre, Float.parseFloat(txtPrecio.getText().toString()), Integer.parseInt(txtCantidad.getText().toString())));
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MainActivity.this, "Faltan datos",Toast.LENGTH_SHORT);
                }
            }
        });

        return builder.create();
    }

}