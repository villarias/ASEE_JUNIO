package com.example.asee_project.vistaVuelos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.asee_project.AppExecutors;
import com.example.asee_project.R;
import com.example.asee_project.database.VueloDataBase;
import com.example.asee_project.model.Vuelo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EdicionVueloFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EdicionVueloFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_ORIGEN = "param1";
    public static final String ARG_DESTINO = "param2";
    public static final String ARG_SALIDA = "param3";
    public static final String ARG_LLEGADA = "param4";
    public static final String ARG_BORRADO = "param5";
    public static final String ARG_CODIGO = "param 6";

    private String origen;
    private String destino;
    private String horaLlegada;
    private String horaSalida;
    private long duracion;
    private String codigo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EdicionVueloFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EdicionVueloFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EdicionVueloFragment newInstance(String param1, String param2) {
        EdicionVueloFragment fragment = new EdicionVueloFragment();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            origen  = getArguments().getString(ARG_ORIGEN);
            destino = getArguments().getString(ARG_DESTINO);
            horaLlegada = getArguments().getString(ARG_LLEGADA);
            horaSalida = getArguments().getString(ARG_SALIDA);
            duracion = getDateDiff(Timestamp.valueOf(horaSalida),Timestamp.valueOf(horaLlegada),TimeUnit.HOURS);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_edicion_vuelo, container, false);
        TextView dia = v.findViewById(R.id.dia_edit);
        TextView viewDuracion = v.findViewById(R.id.duracion_edit);
        TextView viewOrigen = v.findViewById(R.id.origen_edit);
        TextView viewDestino = v.findViewById(R.id.destino_edit);
        TextView viewHoraInicio = v.findViewById(R.id.hora_inicio_edit);
        TextView viewHoraFin = v.findViewById(R.id.hora_fin_edit);
        TextView viewHora = v.findViewById(R.id.hora_edit);


        dia.setText("Dia: " +horaSalida.substring(0,10));
        viewHora.setText("Hora: " +horaSalida.substring(11,16));
        viewOrigen.setText(origen);
        viewDestino.setText(destino);
        viewHoraInicio.setText(horaSalida);
        viewHoraFin.setText(horaLlegada);
        viewDuracion.setText(duracion+" hora(s) de trayecto");
        final Vuelo vuelo = new Vuelo(origen,destino,horaSalida,horaLlegada);

        TextView notificacion= v.findViewById(R.id.notificacion_Borrado);
        Button buttonEdit = v.findViewById(R.id.boton_Editar);

        //Todo borrarVuelo

        Button button = v.findViewById(R.id.boton_Borrado);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        VueloDataBase.getInstance(getActivity()).getDao().delete(vuelo.getSalida());
                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                button.setVisibility(View.INVISIBLE);
                                buttonEdit.setVisibility(View.INVISIBLE);
                                notificacion.setText("Vuelo borrado");
                                notificacion.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });
            }
        });
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmarEdicionFragment fragment = new ConfirmarEdicionFragment();
                Bundle bundle =new Bundle();
                bundle.putString(ConfirmarEdicionFragment.ARG_ORIGEN,origen);
                bundle.putString(ConfirmarEdicionFragment.ARG_DESTINO,destino);
                bundle.putString(ConfirmarEdicionFragment.ARG_LLEGADA,horaLlegada);
                bundle.putString(ConfirmarEdicionFragment.ARG_SALIDA,horaSalida);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmarEdicionFragment fragment = new ConfirmarEdicionFragment();
                Bundle bundle =new Bundle();
                bundle.putString(ConfirmarEdicionFragment.ARG_ORIGEN,origen);
                bundle.putString(ConfirmarEdicionFragment.ARG_DESTINO,destino);
                bundle.putString(ConfirmarEdicionFragment.ARG_LLEGADA,horaLlegada);
                bundle.putString(ConfirmarEdicionFragment.ARG_SALIDA,horaSalida);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}