package com.example.asee_project.vistaVuelos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.AppExecutors;
import com.example.asee_project.R;
import com.example.asee_project.database.VueloDataBase;
import com.example.asee_project.model.Vuelo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisVuelosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisVuelosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdaptadorVuelos mAdapter;

    public MisVuelosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VuelosGuardadosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisVuelosFragment newInstance(String param1, String param2) {
        MisVuelosFragment fragment = new MisVuelosFragment();
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
          //  mParam1 = getArguments().getString(ARG_PARAM1);
          //  mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_vuelos_guardados, container, false);
        mAdapter = new AdaptadorVuelos(new ArrayList<>(), new AdaptadorVuelos.OnVueloClickListener() {
            @Override
            public void onItemClick(Vuelo v) {
                EdicionVueloFragment fragment = new EdicionVueloFragment();
                Bundle bundle= new Bundle();
                bundle.putBoolean(EdicionVueloFragment.ARG_BORRADO,false);
                bundle.putString(EdicionVueloFragment.ARG_ORIGEN,v.getOrigen());
                bundle.putString(EdicionVueloFragment.ARG_LLEGADA,v.getLlegada());
                bundle.putString(EdicionVueloFragment.ARG_DESTINO,v.getDestino());
                bundle.putString(EdicionVueloFragment.ARG_SALIDA,v.getSalida());
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        mRecyclerView = v.findViewById(R.id.recycler_resultado_vuelos);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //TODO ListadoVuelos
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Vuelo> items = VueloDataBase.getInstance(getActivity()).getDao().getAll();
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.swap(items);
                    }
                });
            }
        });
        Button button = v.findViewById(R.id.borrar_todos_boton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        VueloDataBase.getInstance(getActivity()).getDao().deleteAll();

                    }
                });
                MisVuelosFragment fragment = new MisVuelosFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .addToBackStack(null)
                        .commit();

            }
        });
        return v;
    }
}