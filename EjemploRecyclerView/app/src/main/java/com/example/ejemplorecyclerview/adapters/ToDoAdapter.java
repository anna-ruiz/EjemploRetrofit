package com.example.ejemplorecyclerview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejemplorecyclerview.Modelos.ToDo;
import com.example.ejemplorecyclerview.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoVH> {
    //Lista de cosas q quiero mostrar
    private List<ToDo> objects;
    //La fila del elemento que queremos mostrar, lo trata como un entero
    private int resource;
    //El contexto/actividad en la que la voy a mostrar
    private Context context;

    //Creamos un constructor q recibe los 3 elementos
    public ToDoAdapter(List<ToDo> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public ToDoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Instancia tantos elementos como quepan en pantalla

        //1.Creamos la vista, recibe el context/vista y las filas
        View todoView = LayoutInflater.from(context).inflate(resource, null);

        //2. devolvemos la vista
        return new ToDoVH(todoView);

    }

    //para rellenar el objeto con sus propiedades
    @Override
    public void onBindViewHolder(@NonNull ToDoVH holder, int position) {
        ToDo toDo = objects.get(position);

        holder.lbTitulo.setText(toDo.getTitulo());
        holder.lbContenido.setText(toDo.getContenido());
        holder.lbFecha.setText(toDo.getFecha().toString());

        if (toDo.isCompletado()){
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_on_background);
        }else {
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_off_background);
        }

    }

    @Override
    public int getItemCount() {
        //Devolvemos cuantos elementos/objetos tenemos para mostrar(tamaño de la lista)
        return objects.size();
    }

    public class ToDoVH extends RecyclerView.ViewHolder{
        TextView lbTitulo, lbContenido, lbFecha;
        ImageButton btnCompletado;


        public ToDoVH(@NonNull View itemView) {
            super(itemView);

            lbTitulo = itemView.findViewById(R.id.lbTituloTodoViewModel);
            lbContenido = itemView.findViewById(R.id.lbContenidoTodoViewModel);
            lbFecha = itemView.findViewById(R.id.lbFechaTodoViewModel);
            btnCompletado = itemView.findViewById(R.id.btnCompletadoTodoViewModel);
        }
    }
}
