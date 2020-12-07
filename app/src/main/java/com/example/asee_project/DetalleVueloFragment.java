package com.example.asee_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.asee_project.database.VueloDataBase;
import com.example.asee_project.model.Vuelo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleVueloFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class DetalleVueloFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_ORIGEN = "param1";
    public static final String ARG_DESTINO = "param2";
    public static final String ARG_SALIDA = "param3";
    public static final String ARG_LLEGADA = "param4";


    // TODO: Rename and change types of parameters
    private String origen;
    private String destino;
    private String horaLlegada;
    private String horaSalida;
    private String tiempoTrayecto;
    private long duracion;
    Timestamp tsSalida;
    Timestamp tsLlegada;
    Button button;
    TextView textAniadido;
    public DetalleVueloFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetallesVuelo.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleVueloFragment newInstance(String param1, String param2) {
        DetalleVueloFragment fragment = new DetalleVueloFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
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
            String horaAux=horaLlegada.substring(0,10);
            String horaAux2=horaLlegada.substring(11);
            String horaDefinitiva= horaAux + " "+horaAux2;
            tsLlegada = Timestamp.valueOf(horaDefinitiva);

            horaAux=horaSalida.substring(0,10);
            horaAux2=horaSalida.substring(11);
            horaDefinitiva= horaAux + " "+horaAux2;

            tsSalida = Timestamp.valueOf(horaDefinitiva);
            horaLlegada = tsLlegada.toString();
            horaSalida = tsSalida.toString();
            duracion = getDateDiff(tsSalida,tsLlegada, TimeUnit.HOURS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.detalles_vuelo_fragment, container, false);
        TextView dia = v.findViewById(R.id.dia);
        TextView viewDuracion = v.findViewById(R.id.duracion);
        TextView viewOrigen = v.findViewById(R.id.origen);
        TextView viewDestino = v.findViewById(R.id.destino);
        TextView viewHoraInicio = v.findViewById(R.id.hora_inicio);
        TextView viewHoraFin = v.findViewById(R.id.hora_fin);
        TextView viewHora = v.findViewById(R.id.hora);


        dia.setText("Dia: " +horaSalida.substring(0,10));
        viewHora.setText("Hora: " +horaSalida.substring(11,16));
        viewOrigen.setText(origen);
        viewDestino.setText(destino);
        viewHoraInicio.setText(horaSalida);
        viewHoraFin.setText(horaLlegada);

        viewDuracion.setText(duracion+" hora(s) de trayecto");
        textAniadido= v.findViewById(R.id.addVuelo);

        //Todo addVuelo
        button = v.findViewById(R.id.boton_favoritos);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Vuelo vuelo = new Vuelo(origen,destino,tsSalida+"",tsLlegada+"");
                        long id = VueloDataBase.getInstance(getActivity()).getDao().insert(vuelo);
                        vuelo.setIdVuelo(id);
                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                button.setVisibility(View.INVISIBLE);
                                textAniadido.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });
            }
        });
        return v;
    }
    /** * Get a diff between two dates * @param date1 the oldest date * @param date2 the newest date * @param timeUnit the unit in which you want the diff * @return the diff value, in the provided unit */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}