package com.example.ejercicioinmobiliariacorregido;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ejercicioinmobiliariacorregido.Modelos.Inmueble;
import com.example.ejercicioinmobiliariacorregido.databinding.ActivityCrearInmuebleBinding;

public class CrearInmuebleActivity extends AppCompatActivity {
    private ActivityCrearInmuebleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCrearInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAddInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnAnyadirAddInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inmueble inmueble = crearInmueble();
                if (inmueble != null){
                    //Creamos intent para mandar la info de vuelta al Main
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("INMUEBLE",inmueble);
                    intent.putExtras(bundle);

                    setResult(RESULT_OK, intent);
                    finish();

                }else {
                    Toast.makeText(CrearInmuebleActivity.this, "Falta información", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Inmueble crearInmueble() {

        if (binding.txtDireccionCrearInmueble.getText().toString().isEmpty() ||
         binding.txtCiudadCrearInmueble.getText().toString().isEmpty() ||
        binding.txtNumCrearInmueble.getText().toString().isEmpty() ||
        binding.txtCodigoPostalCrearInmueble.getText().toString().isEmpty() ||
        binding.rbValoracionCrearInmueble.getRating() < 0.5){
            return null;
        }
        return new Inmueble(binding.txtDireccionCrearInmueble.getText().toString(),
                Integer.parseInt(binding.txtNumCrearInmueble.getText().toString()),
                binding.txtCiudadCrearInmueble.getText().toString(),
                binding.txtProvinciaCrearInmueble.getText().toString(),
                binding.txtCodigoPostalCrearInmueble.getText().toString(),
                binding.rbValoracionCrearInmueble.getRating());
    }
}