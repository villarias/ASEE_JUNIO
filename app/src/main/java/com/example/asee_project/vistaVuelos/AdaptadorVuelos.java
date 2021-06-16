package com.example.asee_project.vistaVuelos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.R;
import com.example.asee_project.model.Vuelo;

import java.util.List;

public class AdaptadorVuelos extends RecyclerView.Adapter<AdaptadorVuelos.ViewHolderVuelos> {
    private List<Vuelo> mItems;
    public OnVueloClickListener listener;
    public String diaSalida;
    public String diaLlegada;
    public String horaSalida;
    public String horaLlegada;
    public interface OnVueloClickListener {
        void onItemClick(Vuelo v);
    }

    public AdaptadorVuelos (List<Vuelo>mItems,OnVueloClickListener ls){

        this.mItems=mItems;
        this.listener=ls;
    }

    @NonNull
    @Override
    public ViewHolderVuelos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        // Create new views (invoked by the layout manager)
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vuelo_item, parent, false);

        return new ViewHolderVuelos(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVuelos holder, int position) {
        holder.v=mItems.get(position);
        holder.origen.setText(mItems.get(position).getOrigen());
        holder.destino.setText(mItems.get(position).getDestino());
        diaSalida = mItems.get(position).getSalida().substring(0,10);
        horaSalida = mItems.get(position).getSalida().substring(11,16);
        horaLlegada= mItems.get(position).getLlegada().substring(11,16);
        holder.salida.setText(horaSalida);
        holder.llegada.setText(horaLlegada);
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
    public void swap(List<Vuelo> data){
        this.mItems = data;
        notifyDataSetChanged();
    }
    public static class ViewHolderVuelos extends RecyclerView.ViewHolder {

        private TextView destino;
        private TextView origen;
        private TextView llegada;
        private TextView salida;
        public View mView;

        public Vuelo v;


        public ViewHolderVuelos(View v) {
            super(v);
            mView=v;
            //TODO - Get the references to every widget of the Item View

            destino = (TextView) v.findViewById(R.id.tv_destino);
            origen = (TextView) v.findViewById(R.id.tv_origen);
            llegada = (TextView) v.findViewById(R.id._llegada);
            salida = (TextView) v.findViewById(R.id._salida);
        }
    }

}
