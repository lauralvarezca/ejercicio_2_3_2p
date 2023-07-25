package com.example.ejercicio_2_3_2p.Procesos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio_2_3_2p.R;

import java.util.ArrayList;

public class Adapt extends RecyclerView.Adapter<Adapt.ViewHolderClass> {

    ArrayList<Modelo> objectModeloList;
    public Adapt(ArrayList<Modelo> objectModeloList) {
        this.objectModeloList = objectModeloList;
    }

    @NonNull
    @Override
    public Adapt.ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderClass(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_usuario, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapt.ViewHolderClass holder, int position) {
        Modelo objectModelClass=objectModeloList.get(position);
        holder.imageNameTV.setText(objectModelClass.getNombre());
        holder.objectImageView.setImageBitmap(objectModelClass.getImage());
    }

    @Override
    public int getItemCount() {
        return objectModeloList.size();
    }

    public static class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView imageNameTV;
        ImageView objectImageView;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageNameTV=itemView.findViewById(R.id.detalle);
            objectImageView=itemView.findViewById(R.id.imagenvector);

        }
    }
}
