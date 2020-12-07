package com.example.asee_project;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.asee_project.database.HotelDatabase;
import com.example.asee_project.model.Hotel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleHotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleHotelFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_NOMBRE = "param1";
    public static final String ARG_PUNTUACION = "param2";
    public static final String ARG_PRECIO = "param3";
    public static final String ARG_DIRECCION = "param4";
    public static final String ARG_CONTACTO = "param5";
    public static final String ARG_ID = "param6";
    public static final ArrayList<String> ARG_IMAGENES = new ArrayList<String>();
    private  String nombre ;
    private  String puntuacion ;
    private  String precio ;
    private  ArrayList<String> imagenes =new ArrayList<String>();
    private  String direccion;
    private  String contacto;
    private String id;
    private boolean favorito;

    private TextView nombre_h;
    private TextView precio_hotel;
    private TextView direccion_hotel;
    private TextView contacto_hotel;
    private ImageView[] estrellas = new ImageView[5];
    private TextView rate_nd;
    private ImageButton imagen_hotel;
    private ImageButton cerrar_zoom;
    private ImageView zoom_hotel;
    private ImageView correcto;
    private TextView aniadido;
    private Bitmap bmp;
    private Button fav;
    private long insertado;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleHotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleHotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleHotelFragment newInstance(String param1, String param2) {
        DetalleHotelFragment fragment = new DetalleHotelFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
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
            imagenes = getArguments().getStringArrayList(String.valueOf(ARG_IMAGENES));
            direccion = getArguments().getString(ARG_DIRECCION);
            contacto = getArguments().getString(ARG_CONTACTO);
            id = getArguments().getString(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.detalles_hotel, container, false);
        Log.d("FlyScan", "Se ejecuta correctamente detalles 1");


        Log.d("FlyScan", "Se ejecuta correctamente detalles 2");
        correcto = (ImageView) v.findViewById(R.id.correcto);
        aniadido = (TextView) v.findViewById(R.id.aniadido);
        fav = (Button) v.findViewById(R.id.boton_guardar);
        nombre_h = (TextView) v.findViewById(R.id.nombre_hotel);
        precio_hotel = (TextView) v.findViewById(R.id.precio_hotel);
        direccion_hotel = (TextView) v.findViewById(R.id.calle_hotel);
        contacto_hotel = (TextView) v.findViewById(R.id.telefono_contacto);
        rate_nd = (TextView) v.findViewById(R.id.rate_nd);
        estrellas[0] = (ImageView) v.findViewById(R.id.estrella1);
        estrellas[1] = (ImageView) v.findViewById(R.id.estrella2);
        estrellas[2] = (ImageView) v.findViewById(R.id.estrella3);
        estrellas[3] = (ImageView) v.findViewById(R.id.estrella4);
        estrellas[4] = (ImageView) v.findViewById(R.id.estrella5);
        imagen_hotel = (ImageButton) v.findViewById(R.id.boton_imagen_hotel);
        zoom_hotel = (ImageView) v.findViewById(R.id.zoom_imagen_hotel);
        cerrar_zoom = (ImageButton) v.findViewById(R.id.cerrar_zoom);
        zoom_hotel.setVisibility(View.INVISIBLE);
        cerrar_zoom.setVisibility(View.INVISIBLE);
        correcto.setVisibility(View.INVISIBLE);
        aniadido.setVisibility(View.INVISIBLE);
        fav.setVisibility(View.INVISIBLE);


        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final String idh;
                idh = HotelDatabase.getInstance(getActivity()).getDao().getHotel(id);
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (idh == null){
                            fav.setVisibility(View.VISIBLE);
                        }else{
                            correcto.setVisibility(View.VISIBLE);
                            aniadido.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });


        //Todo Añdir Hotel
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertado=HotelDatabase.getInstance(getActivity()).getDao().insert(new Hotel(id,nombre,puntuacion,precio,direccion,contacto));
                      //Todo Añadir Notificaciones Hotel
                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                if (insertado != 0){
                                    fav.setVisibility(View.INVISIBLE);
                                    correcto.setVisibility(View.VISIBLE);
                                    aniadido.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }
                });
            }
        });


        for (int i = 0; i < 5; i++) {
            estrellas[i].setVisibility(View.INVISIBLE);
        }

        rate_nd.setVisibility(View.INVISIBLE);

        if (imagenes != null) {
            if (imagenes.size() > 0) {

                AppExecutors.getInstance().networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(imagenes.get(0));
                            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imagen_hotel.setImageBitmap(bmp);
                                }
                            });
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                imagen_hotel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        zoom_hotel.setImageBitmap(bmp);
                        zoom_hotel.setVisibility(View.VISIBLE);
                        cerrar_zoom.setVisibility(View.VISIBLE);

                    }
                });

                cerrar_zoom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        zoom_hotel.setVisibility(View.INVISIBLE);
                        cerrar_zoom.setVisibility(View.INVISIBLE);
                    }
                });

            }
        }

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
        Log.d("FlyScan","Se ejecuta correctamente detalles 4");
        nombre_h.setText(nombre);
        if(precio != null) {
            if (!precio.equals(""))
                precio_hotel.setText(precio);
            Log.i("MESSAGE","precio != null");
        }
        if(direccion != null) {
            if (!direccion.equals(""))
                direccion_hotel.setText(direccion);
            Log.i("MESSAGE","direccion != null");
        }
        if(contacto != null) {
            if (!contacto.equals(""))
                contacto_hotel.setText(contacto);
            Log.i("MESSAGE","contacto != null");
        }
        Log.d("FlyScan","Se ejecuta correctamente detalles 5");

        return v;
    }

}