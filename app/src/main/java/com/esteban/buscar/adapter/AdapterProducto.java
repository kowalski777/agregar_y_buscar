package com.esteban.buscar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esteban.buscar.R;
import com.esteban.buscar.pojo.Producto;

import java.util.List;
import java.util.UUID;

public class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.viewholderproductos> {

    List<Producto> productoList;  // Lista

    public AdapterProducto(List<Producto> productoList) {  //Constructor
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public viewholderproductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_productos,parent,false);  //layout
        viewholderproductos holder = new viewholderproductos(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderproductos holder, int position) {
        Producto pr = productoList.get(position);

        holder.tv_uid.setText(pr.getUid());
        holder.tv_nombre.setText(pr.getNombre());
        holder.tv_precio.setText(String.valueOf(pr.getPrecio()));
        holder.tvcantidad.setText(String.valueOf(pr.getCantidad()));
        holder.tvcategoria.setText(pr.getCategoria());
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class viewholderproductos extends RecyclerView.ViewHolder {
        TextView tv_uid, tv_nombre, tv_precio, tvcantidad, tvcategoria;
        //TextView tv_nombre, tv_precio, tvcantidad, tvcategoria;

        public viewholderproductos(@NonNull View itemView) {
            super(itemView);
            tv_uid = itemView.findViewById(R.id.tv_uid);
            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_precio = itemView.findViewById(R.id.tv_precio);
            tvcantidad = itemView.findViewById(R.id.tv_cantidad);
            tvcategoria =itemView.findViewById(R.id.tv_categoria);
        }
    }
}
