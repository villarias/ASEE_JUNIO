package com.example.asee_project;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.modelo.Datum;
import com.example.asee_project.modelo.Offer;
import com.example.asee_project.modelo.Price;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Datum> data;
    private int id_view;
    public interface OnItemClickListener {
        void onItemClick(Datum datum);     //Type of the element to be returned
    }
    public OnItemClickListener listener;
    public MyAdapter(List<Datum>data, OnItemClickListener listener,int id_view) {
        this.listener=listener;
        this.data=data;
        this.id_view=id_view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        // Create new views (invoked by the layout manager)
        View v = LayoutInflater.from(parent.getContext())
                .inflate(id_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.d=data.get(position);
        holder.title.setText(data.get(position).getHotel().getName());
        holder.puntuacion.setText(data.get(position).getHotel().getRating());

        Price min;
        if (data.get(position).getOffers().size()>0) {
            min = data.get(position).getOffers().get(0).getPrice();
            for (Offer f : data.get(position).getOffers()) {

                if (Double.parseDouble(f.getPrice().getTotal()) < Double.parseDouble(min.getTotal())) {
                    min = f.getPrice();
                }


            }
            holder.precio.setText(min.getTotal());
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onItemClick(holder.d);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void swap(List<Datum> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView precio;
        public TextView puntuacion;
        public View mView;

        public Datum d;

        public ViewHolder(View v) {
            super(v);
            mView=v;
            //TODO - Get the references to every widget of the Item View
            title = (TextView) v.findViewById(R.id.titulo);
            precio = (TextView) v.findViewById(R.id.precio);
            puntuacion = (TextView) v.findViewById(R.id.putuacion);
        }
    }
}