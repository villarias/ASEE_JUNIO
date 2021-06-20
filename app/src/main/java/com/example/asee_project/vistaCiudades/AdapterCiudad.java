package com.example.asee_project.vistaCiudades;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.R;
import com.example.asee_project.model.Ciudad;

import java.util.List;

public class AdapterCiudad extends RecyclerView.Adapter<AdapterCiudad.ViewHolderCiudad> {
    private List<Ciudad> mItems;
    public AdapterCiudad.OnCiudadClickListener listener;
    public Resources res;

    public interface OnCiudadClickListener {
        void onItemClick(Ciudad v);
    }

    public AdapterCiudad(List<Ciudad> mItems, AdapterCiudad.OnCiudadClickListener ls) {
        this.mItems = mItems;
        this.listener = ls;
    }

    @NonNull
    @Override
    public ViewHolderCiudad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_ciudades, parent, false);
        res =parent.getContext().getResources();

        return new ViewHolderCiudad(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderCiudad holder, int position) {
        holder.v = mItems.get(position);
        holder.nombre.setText(mItems.get(position).getNombre());
        Bitmap bmp = BitmapFactory.decodeResource(res, mItems.get(position).getImage());
        holder.image.setImageBitmap(bmp);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public void swap(List<Ciudad> data) {
        this.mItems = data;
        notifyDataSetChanged();
    }

    public static class ViewHolderCiudad extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView nombre;

        public View mView;

        public Ciudad v;


        public ViewHolderCiudad(View v) {
            super(v);
            mView = v;
            //TODO - Get the references to every widget of the Item View
            nombre =  v.findViewById(R.id.c_nombre_ciudad);
            image =  v.findViewById(R.id.imagenItemList);

        }
    }

}
