package com.example.asee_project;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.asee_project.database.AppDatabase;
import com.example.asee_project.model.Ciudad;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleCiudadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleCiudadFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public List<Ciudad> my_city ;
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
        View v =inflater.inflate(R.layout.fragment_detalle_ciudad, container, false);
        final String item = getArguments() == null ? "1" : getArguments().getString(ARG_PARAM1);

        // Show item content
        final TextView code_city = v.findViewById(R.id.details_name);
        final TextView name_city = v.findViewById(R.id.details_code);
        tw_notificacion = v.findViewById(R.id.notificacion);
        del = (Button) v.findViewById(R.id.borrar_ciudad);

        AppDatabase.getInstance(getContext());

        //Cogemos la instancia
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase database = AppDatabase.getInstance(getContext());
                my_city = database.getCiudadDao().findByCod(item);
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        code_city.setText(my_city.get(0).getCod_ciudad());
                        name_city.setText(my_city.get(0).getNombre());
                    }
                });

                if (my_city.get(0).getFavorite().equals("1")){
                    tw_notificacion.setText("AÑADIDO");
                    tw_notificacion.setVisibility(View.VISIBLE);
                    add.setVisibility(View.INVISIBLE);
                    del.setVisibility(View.VISIBLE);
                }else {
                    tw_notificacion.setVisibility(View.INVISIBLE);
                    del.setVisibility(View.INVISIBLE);
                }
            }
        });
        //Todo addCiudad

        add = (Button) v.findViewById(R.id.fav);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AppDatabase
                if(my_city.get(0).getFavorite().equals("0")){
                    my_city.get(0).setFavorite("1");

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase database = AppDatabase.getInstance(getContext());
                            database.getCiudadDao().update(my_city.get(0));

                        }
                    });
                    //TODO Añadir notificaciones

                    //tw_notificacion.setText("AÑADIDO");
                }
            }
        });

        //Todo deleteCiudad


        return v;
    }
}