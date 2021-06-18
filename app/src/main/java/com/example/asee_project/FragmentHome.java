package com.example.asee_project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.apiVuelos.VueloNetworkDataSource;
import com.example.asee_project.database.CiudadDatabase;
import com.example.asee_project.database.VueloDataBase;
import com.example.asee_project.model.Ciudad;
import com.example.asee_project.model.Vuelo;
import com.example.asee_project.vistaCiudades.BuscadorCiudadFragment;

import com.example.asee_project.vistaVuelos.AdaptadorVuelos;
import com.example.asee_project.vistaVuelos.BuscadorVuelosFragment;
import com.example.asee_project.vistaVuelos.DetalleVueloFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment implements AdaptadorVuelos.OnVueloClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private AdaptadorVuelos mAdapter;
    private VueloRepository mRepository;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Ciudad> destinos = new ArrayList<Ciudad>();
    private List<Vuelo> vuelos = new ArrayList<Vuelo>();
    private Ciudad Random = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructo
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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
        View v =inflater.inflate(R.layout.fragment_home, container, false);
        Button buscadorVuelo = (Button) v.findViewById(R.id.home_buscador_vuelos);
        mAdapter = new AdaptadorVuelos(new ArrayList<>(),this);
        mRecyclerView = v.findViewById(R.id.recyclerHome);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRepository = VueloRepository.getInstance(VueloDataBase.getInstance(getActivity()).getDao(), VueloNetworkDataSource.getInstance());

        FactoryHome factory = new  FactoryHome(mRepository);
        ViewModelHome mViewModel = new ViewModelProvider(this, factory).get(ViewModelHome.class);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                destinos = CiudadDatabase.getInstance(getActivity()).getCiudadDao().getAllFav();
                vuelos = VueloDataBase.getInstance(getActivity()).getDao().getCache();
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        int numeroAleatorio = (int) (Math.random()*destinos.size());
                        Ciudad CiudadRandom = destinos.get(numeroAleatorio);
                        mViewModel.setCiudad(CiudadRandom);
                    }
                });
            }
        });
        mViewModel.getVuelosFavs().observe(getActivity(), ciudad -> {
                    mAdapter.swap(vuelos);
                });

        buscadorVuelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscadorVuelosFragment fragment = new BuscadorVuelosFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });



        Button buscadorCiudad = (Button) v.findViewById(R.id.buscador_Ciudades);
        buscadorCiudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscadorCiudadFragment fragment = new BuscadorCiudadFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return v;
    }

    @Override
    public void onItemClick(Vuelo v) {
            DetalleVueloFragment fragment = new DetalleVueloFragment();
            Bundle bundle  = new Bundle();
            bundle.putString(DetalleVueloFragment.ARG_CODORIGEN,v.getCodOrigen());
            bundle.putString(DetalleVueloFragment.ARG_CODDESTINO,v.getCodOrigen());
            bundle.putString(DetalleVueloFragment.ARG_DESTINO,v.getDestino());
            bundle.putString(DetalleVueloFragment.ARG_ORIGEN,v.getOrigen());
            bundle.putString(DetalleVueloFragment.ARG_SALIDA,v.getSalida());
            bundle.putString(DetalleVueloFragment.ARG_LLEGADA,v.getLlegada());
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .addToBackStack(null)
                    .commit();
        }

}
