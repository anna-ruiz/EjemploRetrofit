package com.example.ejerciciolistacompra.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciolistacompra.R;
import com.example.ejerciciolistacompra.modelos.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoVH> {


    public class ProductoVH extends RecyclerView.ViewHolder{
        TextView lbNombre, lbCantidad;
        ImageButton btnEliminar;
        CardView cardProducto;
        public ProductoVH(@NonNull View itemView) {
            super(itemView);

            lbNombre = itemView.findViewById(R.id.lbNombreProductoViewModel);
            lbCantidad = itemView.findViewById(R.id.lbCantidadProductoViewModel);
            btnEliminar = itemView.findViewById(R.id.btnEliminarProductoViewModel);
            cardProducto = itemView.findViewById(R.id.cardProductoViewModel);

        }
    }


    //Lista de elementos q quiero mostrar
    private List<Producto> objects;
    //fila del elemento q quiero mostrar
    private int resource;
    //Actividad dnd lo vamos a mostrar
    private Context context;



    ///GENERAMOS CONSTRUCTOR
                            //lista objetos           fila             vista
    public ProductoAdapter(List<Producto> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }




    //IMPLEMENTAMOS METODOS DEL EXTENDS


    //Instancia los elementos que quepan en pantalla, devuelve un objeto tipo ProductView
    @NonNull
    @Override
    public ProductoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productoView = LayoutInflater.from(context).inflate(resource,null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams( //Le pasamos parametros para q sea el ancho d la pantalla
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        productoView.setLayoutParams(lp);
        return new ProductoVH(productoView);
    }


    //Se llama automaticamente para rellenar el elemento/objeto
    @Override
    public void onBindViewHolder(@NonNull ProductoVH holder, int position) {
        Producto producto = objects.get(position);

        holder.lbNombre.setText(producto.getNombre());
        holder.lbCantidad.setText("Cantidad: "+producto.getCantidad());

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete("¿Estás seguro de que quieres eliminarlo?",holder.getAdapterPosition()).show();
            }
        });



    }

    private AlertDialog confirmDelete(String titulo, int posicion){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(titulo);
        builder.setCancelable(false);

        builder.setNegativeButton("No",null);
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                objects.remove(posicion);
                notifyItemRemoved(posicion);
            }
        });

        return builder.create();

    }


    //Define cuantos elementos tenemos para mostrar
    @Override
    public int getItemCount() {
        return objects.size();
    }

    private AlertDialog confirmUpdate(String titulo, Producto producto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);

return builder.create();
    }

    //Metodo alert Eliminar
    private AlertDialog alertEliminarProducto(String titulo, Producto producto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setCancelable(false);
        builder.setNegativeButton("Cancelar",null);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }




    //Metodo alert Modificar
    private AlertDialog alertModificarProducto(String titulo, Producto producto){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(titulo); //Pasamos titulo del alert
        builder.setCancelable(false); //Para q solo pueda salir punsaldo cancelar

        //Creamos btn cancelar
        builder.setNegativeButton("Cancelar",null);
        //Creamos btn confirmar
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }


}
