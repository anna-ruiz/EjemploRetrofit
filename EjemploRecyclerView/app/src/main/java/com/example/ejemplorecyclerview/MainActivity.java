package com.example.ejemplorecyclerview;

import android.os.Bundle;

import com.example.ejemplorecyclerview.Modelos.ToDo;
import com.example.ejemplorecyclerview.adapters.ToDoAdapter;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;


import com.example.ejemplorecyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<ToDo> todoList;
    private ToDoAdapter adapter;
    //Creamos un layaout para q se muestre+
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        todoList = new ArrayList<>();
        crearTareas();

        //Inicializamos el adapter pasandole la lista, la vista y dnd se muestra
        adapter = new ToDoAdapter(todoList,R.layout.todo_view_model, MainActivity.this);
        //Añade en el contenedor del contentMain el adaptador q hemos creado
        binding.contentMain.contenedor.setAdapter(adapter);

        //Creamos nuevo layaout manager asociado al contexto y lo añadimos
        layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}


        });
    }

    private void crearTareas() {

        for (int i = 0; i < 100; i++) {
            todoList.add(new ToDo("Titulo "+i,"Contenido "+i));



        }

    }
}
