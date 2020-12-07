package com.example.asee_project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.asee_project.model.Hotel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditarHotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarHotelFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_NOMBRE = "param1";
    public static final String ARG_PUNTUACION = "param2";
    public static final String ARG_PRECIO = "param3";
    public static final String ARG_DIRECCION = "param4";
    public static final String ARG_CONTACTO = "param5";
    public static final String ARG_ID = "param6";

    // TODO: Rename and change types of parameters
    private  String nombre ;
    private  String puntuacion ;
    private  String precio ;
    private  String direccion;
    private  String contacto;
    private String id;
    private ImageView[] estrellas = new ImageView[5];
    private TextView rate_nd;
    private TextView nombre_h;
    private TextView precio_hotel;
    private TextView direccion_hotel;
    private EditText contacto_hotel;
    private Button guardar;
    private Toast info;
    private Hotel actualizado;

    public EditarHotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditarHotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditarHotelFragment newInstance(String param1, String param2) {
        EditarHotelFragment fragment = new EditarHotelFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_NOMBRE);
            puntuacion = getArguments().getString(ARG_PUNTUACION);
            precio = getArguments().getString(ARG_PRECIO);
            direccion = getArguments().getString(ARG_DIRECCION);
            contacto = getArguments().getString(ARG_CONTACTO);
            id = getArguments().getString(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_editar_hotel, container, false);
        guardar= (Button) v.findViewById(R.id.boton_guardar);
        nombre_h = (TextView) v.findViewById(R.id.nombre_hotel);
        precio_hotel = (TextView) v.findViewById(R.id.precio_hotel);
        direccion_hotel = (TextView) v.findViewById(R.id.calle_hotel);
        contacto_hotel = (EditText) v.findViewById(R.id.telefono_contacto);
        rate_nd = (TextView) v.findViewById(R.id.rate_nd);
        estrellas[0] = (ImageView) v.findViewById(R.id.estrella1);
        estrellas[1] = (ImageView) v.findViewById(R.id.estrella2);
        estrellas[2] = (ImageView) v.findViewById(R.id.estrella3);
        estrellas[3] = (ImageView) v.findViewById(R.id.estrella4);
        estrellas[4] = (ImageView) v.findViewById(R.id.estrella5);
        for (int i = 0; i < 5; i++) {
            estrellas[i].setVisibility(View.INVISIBLE);
        }
        rate_nd.setVisibility(View.INVISIBLE);

        nombre_h.setText(nombre);
        precio_hotel.setText(precio);
        rate_nd.setText("No disponible");
        direccion_hotel.setText(direccion);
        contacto_hotel.setText(contacto);

        Log.i("FlyScann", "Puntuacion: "+puntuacion );

        if (puntuacion != null){
            if (!puntuacion.equals("No disponible")) {
                for (int i = 0; i < Integer.parseInt(puntuacion); i++) {
                    estrellas[i].setVisibility(View.VISIBLE);
                }
            } else {
                rate_nd.setVisibility(View.VISIBLE);
            }
            Log.i("MESSAGE","puntuacion != null");
        }
    //Todo Guardar


        return v;
    }
}