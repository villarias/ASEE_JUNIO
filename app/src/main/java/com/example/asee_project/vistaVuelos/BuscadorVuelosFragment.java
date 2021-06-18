package com.example.asee_project.vistaVuelos;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.asee_project.AppExecutors;
import com.example.asee_project.R;
import com.example.asee_project.database.CiudadDatabase;
import com.example.asee_project.model.Ciudad;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuscadorVuelosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuscadorVuelosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String dateString;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText origen;
    private EditText destino;
    private static EditText fecha_inicio;
    private TextView notificacionError;
    private ImageButton button;
    private String fechaini;
    private Toast info;
    private String codOrigen ;
    private String codDestino ;
    private String nomOrigen;
    private String nomDestino;


    public BuscadorVuelosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuscadorVuelosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuscadorVuelosFragment newInstance(String param1, String param2) {
        BuscadorVuelosFragment fragment = new BuscadorVuelosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setDefaultDateTime() {

        // Default is current time + 7 days
       Date mDate = new Date();
        mDate = new Date(mDate.getTime());

        Calendar c = Calendar.getInstance();
        c.setTime(mDate);


        setDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        fecha_inicio.setText(dateString);
        fechaini=dateString;

    }



    private static void setDateString(int year, int monthOfYear, int dayOfMonth) {

        // Increment monthOfYear for Calendar/Date -> Time Format setting
        monthOfYear++;
        String mon = "" + monthOfYear;
        String day = "" + dayOfMonth;

        if (monthOfYear < 10)
            mon = "0" + monthOfYear;
        if (dayOfMonth < 10)
            day = "0" + dayOfMonth;

        dateString = year + "-" + mon + "-" + day;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.buscador_vuelos, container, false);
        origen = (EditText) v.findViewById(R.id.inputOrigen);
        destino = (EditText) v.findViewById(R.id.inputDestino);
        fecha_inicio = (EditText) v.findViewById(R.id.input_fecha_inicio);
        notificacionError = (TextView) v.findViewById(R.id.errorCiudades);
        notificacionError.setVisibility(View.INVISIBLE);
        setDefaultDateTime();
         fecha_inicio.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 showDatePickerDialog();
             }
         });
        CiudadDatabase database =CiudadDatabase.getInstance(getContext());
        button =(ImageButton) v.findViewById(R.id.boton_Buscar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (validate()) {
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Ciudad ciudadOrigen = database.getCiudadDao().findByName(origen.getText().toString());
                            Ciudad  ciudadDestino = database.getCiudadDao().findByName(destino.getText().toString());
                            if (ciudadOrigen == null || ciudadDestino ==null) {
                                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        notificacionError.setVisibility(View.VISIBLE);
                                    }
                                });
                            } else {
                                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                    @Override
                                    public void run() {
                     codOrigen = ciudadOrigen.getCod_ciudad();
                     codDestino = ciudadDestino.getCod_ciudad();
                     nomOrigen = ciudadOrigen.getNombre();
                     nomDestino = ciudadDestino.getNombre();
                    String[] parts = fecha_inicio.getText().toString().split("-");
                    String strDia = parts[2];
                    String strMes = parts[1];
                    String strAnio = parts[0];

                    ListaVuelosFragment fragment = new ListaVuelosFragment();
                    Bundle bundle = new Bundle();
                    //bundle.putString(ResultadoVuelosFragment.)

                    bundle.putString(ListaVuelosFragment.ARG_CODORIGEN, codOrigen);
                    bundle.putString(ListaVuelosFragment.ARG_CODDESTINO, codDestino);
                    bundle.putString(ListaVuelosFragment.ARG_ORIGEN,nomOrigen);
                    bundle.putString(ListaVuelosFragment.ARG_DESTINO,nomDestino);
                    bundle.putString(ListaVuelosFragment.ARG_DIA, strDia);
                    bundle.putString(ListaVuelosFragment.ARG_MES, strMes);
                    bundle.putString(ListaVuelosFragment.ARG_ANIO, strAnio);
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .addToBackStack(null)
                            .commit();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
        return v;
    }

    private boolean validate() {
        if (origen.getText().toString().isEmpty()){
            info = Toast.makeText(getActivity().getApplicationContext(),"Campo origen vacío", Toast.LENGTH_LONG);
            info.show();
            return false;
        }else if (destino.getText().toString().isEmpty()){
            info = Toast.makeText(getActivity().getApplicationContext(),"Campo destino vacío", Toast.LENGTH_LONG);
            info.show();
            return false;
        } else if (fecha_inicio.getText().toString().isEmpty()){
            info = Toast.makeText(getActivity().getApplicationContext(),"Campo fecha vacío", Toast.LENGTH_LONG);
            info.show();
            return false;
        }

        return true;
    }


    private void showDatePickerDialog() {
        //TODO - Create a Date Picker Dialog and show it
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getFragmentManager(), "datePicker");

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            setDateString(year,month,day);
            fecha_inicio.setText(dateString);
        }
    }
}
