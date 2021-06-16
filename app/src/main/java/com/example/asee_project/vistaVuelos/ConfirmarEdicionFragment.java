package com.example.asee_project.vistaVuelos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.asee_project.AppExecutors;
import com.example.asee_project.R;
import com.example.asee_project.database.VueloDataBase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmarEdicionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmarEdicionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_ORIGEN = "param1";
    public static final String ARG_DESTINO = "param2";
    public static final String ARG_SALIDA = "param3";
    public static final String ARG_LLEGADA = "param4";
    private static String dateString;

    private String origen;
    private String destino;
    private String horaLlegada;
    private String horaSalida;
    private  static EditText editFechaSalida ;
    private  EditText editFechaLlegada;
    private boolean errorParametros = true;

    private String origenNuevo;
    private String destinoNuevo;
    private String horaSalidaNueva;
    private String horaLlegadaNueva;
    private String fechaLlegadaNueva;
    private String fechaSalidaNueva;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConfirmarEdicionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfirmarEdicion.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmarEdicionFragment newInstance(String param1, String param2) {
        ConfirmarEdicionFragment fragment = new ConfirmarEdicionFragment();
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
            origen = getArguments().getString(ARG_ORIGEN);
            destino = getArguments().getString(ARG_DESTINO);
            horaLlegada = getArguments().getString(ARG_LLEGADA);
            horaSalida = getArguments().getString(ARG_SALIDA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_confirmar_edicion, container, false);
        EditText editOrigen = v.findViewById(R.id.editOrigen);
        EditText editDestino = v.findViewById(R.id.editDestino);
        editFechaSalida = (EditText) v.findViewById(R.id.edit_fecha_inicio);
        editFechaLlegada = (EditText) v.findViewById(R.id.edit_fecha_fin);
        EditText editHoraSalida = v.findViewById(R.id.edit_hora_salida);
        EditText editHoraLlegada = v.findViewById(R.id.edit_hora_llegada);

        editOrigen.setText(origen);
        editDestino.setText(destino);
        editFechaLlegada.setText(horaLlegada.substring(0, 10));
        editFechaSalida.setText(horaSalida.substring(0, 10));
        editHoraLlegada.setText(horaLlegada.substring(11, 16));
        editHoraSalida.setText(horaSalida.substring(11, 16));

        TextView notificacion = v.findViewById(R.id.notificacion_edit);
        TextView notificacionParametros = v.findViewById(R.id.notificacion_parametros);
        //Todo EditarVuelo
        Button button = v.findViewById(R.id.boton_confirmar_edicion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        origenNuevo = editOrigen.getText().toString();
                        destinoNuevo = editDestino.getText().toString();
                        horaSalidaNueva = editHoraSalida.getText().toString();
                        horaLlegadaNueva = editHoraLlegada.getText().toString();
                        fechaLlegadaNueva = editFechaLlegada.getText().toString();
                        fechaSalidaNueva = editFechaSalida.getText().toString();
                        if(validateHora(horaSalidaNueva) && validateHora(horaSalidaNueva) && validateFecha(fechaSalidaNueva) && validateFecha(fechaLlegadaNueva)) {
                            VueloDataBase.getInstance(getActivity()).getDao().update(origenNuevo, destinoNuevo, fechaSalidaNueva + " " + horaSalidaNueva + ":00.0", fechaLlegadaNueva + " " + horaLlegadaNueva + ":00.0", horaSalida);
                            errorParametros=false;
                        }
                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                if(!errorParametros) {
                                    notificacion.setVisibility(View.VISIBLE);
                                    button.setVisibility(View.INVISIBLE);
                                    notificacionParametros.setVisibility(View.INVISIBLE);
                                }else{
                                    notificacionParametros.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }
                });
            }
        });

        return v;
    }

    public boolean validateHora(String hora){
        boolean validado = false;
        String horas;
        String minutos;
        if(hora.length()==5) {
            horas = hora.substring(0, 2);
            minutos = hora.substring(3, 5);
            if(0<=Integer.parseInt(horas)&& Integer.parseInt(horas)<=24) {
                if (0 < Integer.parseInt(minutos) && Integer.parseInt(minutos) <= 59) {
                        if (hora.substring(2,3).equals(":"))
                            validado=true;
                    }
                }
            }
                return validado;
        }

    public boolean validateFecha(String fecha){
        boolean validado = false;
        String anio;
        String mes;
        String dia;
        if(fecha.length()==10) {
            anio = fecha.substring(0, 4);
            mes = fecha.substring(5, 7);
            dia = fecha.substring(8,10);
            if(1<=Integer.parseInt(mes)&& Integer.parseInt(mes)<=12) {
                if (1 < Integer.parseInt(dia) && Integer.parseInt(dia) <= 31) {
                    if (fecha.substring(4,5).equals("-")&&fecha.substring(7,8).equals("-"))
                        validado=true;
                }
            }
        }
        return validado;
    }



}

