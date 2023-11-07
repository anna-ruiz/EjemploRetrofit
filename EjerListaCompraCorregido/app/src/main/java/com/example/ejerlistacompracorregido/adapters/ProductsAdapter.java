package com.example.ejerlistacompracorregido.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerlistacompracorregido.R;
import com.example.ejerlistacompracorregido.modelos.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductVH> {

    private List<Product> objects;
    private int resource;
    private Context context;


    public class ProductVH extends RecyclerView.ViewHolder{
        TextView lbName;
        TextView lbQuantity;
        ImageButton btnDelete;

        public ProductVH(@NonNull View itemView) {
            super(itemView);


            lbName = itemView.findViewById(R.id.lbNameProductViewHolder);
            lbQuantity = itemView.findViewById(R.id.lbQuantityProductoViewHolder);
            btnDelete = itemView.findViewById(R.id.btnDeleteProductViewHolder);



        }
    }

    public ProductsAdapter(List<Product> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(context).inflate(resource, null);

        productView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        return new ProductVH(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {

        Product product = objects.get(position);

        holder.lbName.setText(product.getName());
        holder.lbQuantity.setText(String.valueOf(product.getQuantiy()));

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete(product).show(); //Llamamos al metodo del aler y decimos q lo muestre
            }
        });

        //PARA PODER HACER ALGO AL HACER CLICK SOBRE LA FILA/CARD
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmUpdate(product).show();
            }
        }
        );

    }

    private AlertDialog confirmUpdate(Product product) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context); //Creamos constructor q recibe el contexto
        builder.setTitle(R.string.update); //Le pasamos el texto que hemos escrito en strings.xml
        builder.setCancelable(false);

        //Inflamos la vista del producto para editar
        View productViewModel = LayoutInflater.from(context).inflate(R.layout.product_view_model,null);

        //Crear la vista con la info
        EditText txtName = productViewModel.findViewById(R.id.txtNameProductViewModel);
        txtName.setEnabled(false); //PAra q nombre sea solo de lectura
        EditText txtQuantity = productViewModel.findViewById(R.id.txtQuantityProductViewModel);
        EditText txtPrice = productViewModel.findViewById(R.id.txtPriceProductViewModel);
        TextView lbTotal = productViewModel.findViewById(R.id.lbTotalProductViewModel);

        builder.setView(productViewModel); //Añadimos la vista al viewModel

        //Rellenamos la info de la vista
        txtQuantity.setText(product.getName());
        txtQuantity.setText(String.valueOf(product.getQuantiy()));
        txtPrice.setText(String.valueOf(product.getPrice()));
        lbTotal.setText(String.valueOf(product.getTotal()));

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    int quantity = Integer.parseInt(txtQuantity.getText().toString());
                    float price = Float.parseFloat(txtPrice.getText().toString());
                    float total = quantity * price;
                    lbTotal.setText(String.valueOf(total));
                }catch (Exception e){

                }

            }
        };

        txtQuantity.addTextChangedListener(textWatcher);
        txtPrice.addTextChangedListener(textWatcher);

        builder.setNegativeButton(R.string.buttonCancel,null);
        builder.setPositiveButton(R.string.buttonUpdate, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (txtQuantity.getText().toString().isEmpty() || txtPrice.getText().toString().isEmpty()){
                    Toast.makeText(context, R.string.missing, Toast.LENGTH_SHORT).show();
                }else {
                product.setQuantiy(Integer.parseInt(txtQuantity.getText().toString()));
                product.setPrice(Float.parseFloat(txtPrice.getText().toString()));
                //EL total se calcula solo en los set, q hace la llamada al metodo de calcular
                    notifyItemChanged(objects.indexOf(product));
                }
            }
        });

        return builder.create();
    }

    private AlertDialog confirmDelete(Product product){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.delete);
        builder.setCancelable(false);

        builder.setNegativeButton(R.string.buttonCancel, null);
        builder.setPositiveButton(R.string.buttonOk, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int position = objects.indexOf(product);
                objects.remove(product);
                notifyItemRemoved(position);
            }
        });

        return builder.create(); //devolvemos el builder q crea el alerttt
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


}
