package com.example.asee_project.vistaHoteles;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.asee_project.AppExecutors;
import com.example.asee_project.R;
import com.example.asee_project.database.AppDatabase;
import com.example.asee_project.model.Ciudad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuscadorHotelesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuscadorHotelesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean encontrado= true;
    private static String dateString;
    private String fechaini;
    private String fechafin;
    private static final int SEVEN_DAYS = 604800000;
    private static final String PERSONAS_DEFAULT ="1";
    private Date mDate;
    private EditText ciudad;
    private EditText fecha_inicio;
    private EditText fecha_fin;
    private EditText numPersonas;
    private ImageView error_inicio;
    private ImageView error_fin;
    private ImageButton boton_buscar;
    private Toast info;
    private TextView errorCiudad;

    public BuscadorHotelesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuscadorHotelesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuscadorHotelesFragment newInstance(String param1, String param2) {
        BuscadorHotelesFragment fragment = new BuscadorHotelesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.buscar_hoteles, container, false);
        errorCiudad = (TextView) v.findViewById(R.id.error_ciudadHotel) ;
        errorCiudad.setVisibility(View.INVISIBLE);
        ciudad = (EditText) v.findViewById(R.id.input_ciudad);
        fecha_inicio = (EditText) v.findViewById(R.id.input_fecha_inicio);
        fecha_fin = (EditText) v.findViewById(R.id.input_fecha_fin);
        numPersonas = (EditText) v.findViewById(R.id.input_num_personas);
        boton_buscar = (ImageButton) v.findViewById(R.id.boton_Buscar);
        error_inicio = (ImageView) v.findViewById(R.id.error_image);
        error_fin = (ImageView) v.findViewById(R.id.error_image2);
        numPersonas.setText(PERSONAS_DEFAULT);
        setDefaultDateTime();

        fecha_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog_fechainicio();
            }
        });

        fecha_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog_fechafin();
            }
        });

        boton_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase database = AppDatabase.getInstance(getActivity().getApplicationContext());

                    if (validate()) {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                Ciudad c = AppDatabase.getInstance(getContext()).getCiudadDao().findByName(ciudad.getText().toString());
                                if (c == null) {
                                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            errorCiudad.setVisibility(View.VISIBLE);
                                        }
                                    });
                                } else {
                                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            ListaHotelFragment fragment = new ListaHotelFragment();
                                            Bundle bundle = new Bundle();
                                            bundle.putString(ListaHotelFragment.ARG_CIUDAD, ciudad.getText().toString());
                                            bundle.putString(ListaHotelFragment.ARG_FECHA_IN, fecha_inicio.getText().toString());
                                            bundle.putString(ListaHotelFragment.ARG_FECHA_FIN, fecha_fin.getText().toString());
                                            bundle.putString(ListaHotelFragment.ARG_NUM_PERSONAS, numPersonas.getText().toString());
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



                        //Intent intent = new Intent(BuscadorHotelesActivity.this, ListaHotelActivity.class);
                        //createDataPackage(intent,ciudad.getText().toString(),fecha_inicio.getText().toString(),fecha_fin.getText().toString(),numPersonas.getText().toString());
                        //startActivity(intent);
                    }
            }
        });
        return v;
    }
    private void createDataPackage(Intent data, String ciudad, String fecha_inicio, String fecha_fin, String personas) {
        data.putExtra("Ciudad", ciudad);
        data.putExtra("FechaInicio",fecha_inicio);
        data.putExtra("FechaFin",fecha_fin);
        data.putExtra("Personas",personas);
    }

    private boolean validate() {
        if (ciudad.getText().toString().isEmpty()){
            info = Toast.makeText(getActivity().getApplicationContext(),"Campo ciudad vacío", Toast.LENGTH_LONG);
            info.show();
            return false;
        }else if (fecha_inicio.getText().toString().isEmpty() || fecha_fin.getText().toString().isEmpty()){
            info = Toast.makeText(getActivity().getApplicationContext(),"Campo fecha vacío", Toast.LENGTH_LONG);
            info.show();
            return false;
        }else if(numPersonas.getText().toString().isEmpty()){
            info = Toast.makeText(getActivity().getApplicationContext(),"Campo numero de personas vacío", Toast.LENGTH_LONG);
            info.show();
            return false;
        }
        return true;
    }

    private void setDefaultDateTime() {

        // Default is current time + 7 days
        mDate = new Date();
        mDate = new Date(mDate.getTime() + SEVEN_DAYS);

        Calendar c = Calendar.getInstance();
        c.setTime(mDate);

        setDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));

        Date fDate = new Date ();
        fDate = new Date(fDate.getTime());
        c.setTime(fDate);
        fecha_fin.setText(dateString);
        fechafin=dateString;

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

    public static class DatePickerFragment extends DialogFragment {
        private DatePickerDialog.OnDateSetListener listener;

        public static BuscadorHotelesFragment.DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            BuscadorHotelesFragment.DatePickerFragment fragment = new BuscadorHotelesFragment.DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current date as the default date in the picker

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog picker = new DatePickerDialog(getActivity(),
                    listener, year, month, day);
            picker.getDatePicker().setMinDate(c.getTime().getTime());
            return picker;
        }



    }
    private void showDatePickerDialog_fechainicio() {
        BuscadorHotelesFragment.DatePickerFragment newFragment = BuscadorHotelesFragment.DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                setDateString(year, month, day);
                fechaini=dateString;
                fecha_inicio.setText(dateString);
                try {

                    if ( !dateValidation() ){
                        error_inicio.setVisibility(View.VISIBLE);
                        error_fin.setVisibility(View.VISIBLE);
                        boton_buscar.setEnabled(false);
                        info = Toast.makeText(getActivity().getApplicationContext(),"La fecha inicial no puede ser mayor a la final",Toast.LENGTH_LONG);
                        info.show();

                    }else{
                        error_inicio.setVisibility(View.INVISIBLE);
                        error_fin.setVisibility(View.INVISIBLE);
                        boton_buscar.setEnabled(true);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        newFragment.show(getActivity().getFragmentManager(), "DatePicker");
    }
    private void showDatePickerDialog_fechafin() {
        BuscadorHotelesFragment.DatePickerFragment newFragment = BuscadorHotelesFragment.DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                setDateString(year, month, day);
                fechafin=dateString;
                fecha_fin.setText(dateString);
                try {
                    if ( !dateValidation() ){
                        error_inicio.setVisibility(View.VISIBLE);
                        error_fin.setVisibility(View.VISIBLE);
                        boton_buscar.setEnabled(false);
                        info = Toast.makeText(getActivity().getApplicationContext(),"La fecha inicial no puede ser mayor a la final",Toast.LENGTH_LONG);
                        info.show();
                    }else{
                        error_inicio.setVisibility(View.INVISIBLE);
                        error_fin.setVisibility(View.INVISIBLE);
                        boton_buscar.setEnabled(true);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        newFragment.show(getActivity().getFragmentManager(), "DatePicker");

    }

    private boolean dateValidation() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse(fechaini);
        Date date2 = simpleDateFormat.parse(fechafin);
        Log.i("FlyScan Abel","fecha 1: "+date1.toString());
        Log.i("FlyScan Abel","fecha 2: "+date2.toString());
        if (date1.before(date2)){ return true; }
        return false;
    }
    private void showDatePickerDialog() {
        //TODO - Create a Date Picker Dialog and show it
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getFragmentManager(), "datePicker");
    }
}