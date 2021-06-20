package com.example.asee_project.vistaCiudades;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.asee_project.AppExecutors;
import com.example.asee_project.R;
import com.example.asee_project.database.CiudadDatabase;
import com.example.asee_project.model.Ciudad;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleCiudadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleCiudadFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String ARG_ORIGEN = "param1";

    // TODO: Rename and change types of parameters

    private String item;
   // public List<Ciudad> my_city ;
    public Ciudad my;
    private Button add;
    private Button del;
    private TextView tw_notificacion;


    public DetalleCiudadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleCiudadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleCiudadFragment newInstance(String param1, String param2) {
        DetalleCiudadFragment fragment = new DetalleCiudadFragment();
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
            item  = getArguments().getString(ARG_ORIGEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_detalle_ciudad, container, false);


        // Show item content
        final ImageView image = v.findViewById(R.id.imagenDetalleCiudad);
        final TextView name_city = v.findViewById(R.id.details_code);
        tw_notificacion = v.findViewById(R.id.notificacion);
        del = (Button) v.findViewById(R.id.borrar_ciudad);
        Resources res = getResources();

        CiudadDatabase.getInstance(getContext());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                CiudadDatabase database = CiudadDatabase.getInstance(getContext());
                my = CiudadDatabase.getInstance(getContext()).getCiudadDao().findByName(item);
                Bitmap bmp = BitmapFactory.decodeResource(res,my.getImage());
                try {
                    if (my.getNombre() == null) {
                        Log.i("Error Detalle Ciudad","Ciudad no encontrada");
                    } else {
                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                if (my.getNombre() != null) {
                                    name_city.setText(my.getNombre());
                                    if (my.getFavorite().equals("1")) {
                                        tw_notificacion.setText("Añadida a favoritos.");
                                        tw_notificacion.setVisibility(View.VISIBLE);
                                        add.setVisibility(View.INVISIBLE);
                                        del.setVisibility(View.VISIBLE);
                                    } else {
                                        tw_notificacion.setText("¿Desea añadir?");
                                        tw_notificacion.setVisibility(View.VISIBLE);
                                        add.setVisibility(View.VISIBLE);
                                        del.setVisibility(View.INVISIBLE);
                                    }
                                }
                                image.setImageBitmap(bmp);
                            }
                        });
                    }
                }catch (Exception e){
                    tw_notificacion.setText("La ciudad buscada no se encentra en nuestra base de datos");
                }
            }
        });

/*        int i = 5;
        if (my.getNombre()==null){
            tw_notificacion.setText("mieeeees.");
        }*/


        add = (Button) v.findViewById(R.id.fav);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AppDatabase
                if(my.getFavorite().equals("0")){
                    my.setFavorite("1");
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            CiudadDatabase database = CiudadDatabase.getInstance(getContext());
                            database.getCiudadDao().update(my);
                        }
                    });
                    add.setVisibility(View.INVISIBLE);
                    del.setVisibility(View.VISIBLE);
                    tw_notificacion.setText("Añadida a favoritos.");
                    tw_notificacion.setVisibility(View.VISIBLE);

                }
            }
        });

        //Todo deleteCiudad
        del = (Button) v.findViewById(R.id.borrar_ciudad);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AppDatabase
                if(my.getFavorite().equals("1")){
                    my.setFavorite("0");
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            CiudadDatabase database = CiudadDatabase.getInstance(getContext());
                            database.getCiudadDao().update(my);
                        }
                    });
                    //TODO Añadir notificaciones
                    del.setVisibility(View.INVISIBLE);
                    add.setVisibility(View.VISIBLE);
                    tw_notificacion.setText("¿Desea añadir esta ciudad a favoritos?");
                    tw_notificacion.setVisibility(View.VISIBLE);
                }
            }
        });

        return v;
    }
}