package com.example.ejercicioinmobiliariacorregido;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;

import com.example.ejercicioinmobiliariacorregido.Modelos.Inmueble;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.example.ejercicioinmobiliariacorregido.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Inmueble> listaInmuebles;
    private ActivityResultLauncher<Intent> addInmuebleLauncher;
    private ActivityResultLauncher<Intent> editInmuebleLauncher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Inicializamos las variables!!!
        listaInmuebles = new ArrayList<>();
        inicializarLaunchers();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Que cuando pulse el btn + lance la actividad CrearInmuebleActivity
                addInmuebleLauncher.launch(new Intent(MainActivity.this, CrearInmuebleActivity.class));
            }
        });
    }

    private void inicializarLaunchers() {

        addInmuebleLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        //Que hago a la vuelta de crear Inmueble
                        if (o.getResultCode() == RESULT_OK) {
                            if (o.getData() != null && o.getData().getExtras() != null) {
                                Inmueble inmueble = (Inmueble) o.getData().getExtras().getSerializable("INMUEBLE");
                                Toast.makeText(MainActivity.this, inmueble.toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Accion cancelada", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );


        editInmuebleLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        //Que hago a la vuelta de editar inmueble
                    }
                }
        );
    }
}