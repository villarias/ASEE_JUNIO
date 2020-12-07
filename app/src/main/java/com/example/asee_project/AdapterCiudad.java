package com.example.asee_project;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.model.Ciudad;

import java.util.List;

public class AdapterCiudad extends RecyclerView.Adapter<AdapterCiudad.ViewHolderCiudad> {
    private List<Ciudad> mItems;
    public AdapterCiudad.OnCiudadClickListener listener;

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
        // create a new view
        // Create new views (invoked by the layout manager)
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_detalle_ciudad, parent, false);

        return new ViewHolderCiudad(v);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderCiudad holder, int position) {
        holder.v = mItems.get(position);
        holder.nombre.setText(mItems.get(position).getNombre());
        holder.codigo.setText(mItems.get(position).getCod_ciudad());

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

        private TextView nombre;
        private TextView codigo;

        public View mView;

        public Ciudad v;


        public ViewHolderCiudad(View v) {
            super(v);
            mView = v;
            //TODO - Get the references to every widget of the Item View
            nombre = (TextView) v.findViewById(R.id.c_codigo_ciudad);
            codigo = (TextView) v.findViewById(R.id.c_nombre_ciudad);
        }
    }

}
