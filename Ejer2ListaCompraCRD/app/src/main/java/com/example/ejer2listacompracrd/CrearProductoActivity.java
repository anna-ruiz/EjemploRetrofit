package com.example.ejer2listacompracrd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.ejer2listacompracrd.Modelos.Producto;
import com.example.ejer2listacompracrd.databinding.ActivityCrearProductoBinding;

public class CrearProductoActivity extends AppCompatActivity {
    private ActivityCrearProductoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCrearProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAgregarCrearProductoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto producto = crearProducto();
            }
        });

    }

    private Producto crearProducto() {


        if (binding.txtNombreCrearProductoActivity.getText().toString().isEmpty() ||
        binding.txtCantidadCrearProductoActivity.getText().toString().isEmpty() ||
        binding.txtPrecioCrearProductoActivity.getText().toString().isEmpty()){
            return null;
        }else{
            Producto producto = new Producto(binding.txtNombreCrearProductoActivity.getText().toString(),
                    Float.parseFloat(binding.txtPrecioCrearProductoActivity.getText().toString()),
                    Integer.parseInt(binding.txtCantidadCrearProductoActivity.getText().toString()));
            return producto;
        }

    }

}