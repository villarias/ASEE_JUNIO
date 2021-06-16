package com.example.asee_project.vistaHoteles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.R;
import com.example.asee_project.model.HotelDB;

import java.util.List;

public class AdaptadorMisHoteles extends RecyclerView.Adapter<AdaptadorMisHoteles.ViewHolder> {
    private List<HotelDB> data;

    public interface OnHotelClickListener {
        void onItemHotelClick(HotelDB datum);     //Type of the element to be returned
    }
    public interface OnEditarClickListener {
        void onEditaItemClick(HotelDB datum);     //Type of the element to be returned
    }
    public interface OnBorrarlClickListener {
        void onBorrarItemClick(HotelDB datum);     //Type of the element to be returned
    }
    public OnHotelClickListener listener;
    public OnEditarClickListener listener_edit;
    public OnBorrarlClickListener listener_borrar;
    public AdaptadorMisHoteles(List<HotelDB>data, OnHotelClickListener listener, OnEditarClickListener listener_edit, OnBorrarlClickListener listener_borrar) {
        this.listener=listener;
        this.data=data;
        this.listener_edit=listener_edit;
        this.listener_borrar=listener_borrar;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mis_hoteles, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.h=data.get(position);
        holder.title.setText(data.get(position).getNombre());
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener_edit.onEditaItemClick(holder.h);
            }
        });
        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener_borrar.onBorrarItemClick(holder.h);
            }
        });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemHotelClick(holder.h);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void swap(List<HotelDB> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageButton editar;
        public ImageButton borrar;
        public View mView;

        public HotelDB h;

        public ViewHolder(View v) {
            super(v);
            mView=v;
            //TODO - Get the references to every widget of the Item View
            title = (TextView) v.findViewById(R.id.titulo);
            editar = (ImageButton) v.findViewById(R.id.editar);
            borrar = (ImageButton) v.findViewById(R.id.borrar);
        }


    }

}
