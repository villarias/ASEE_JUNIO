package com.example.asee_project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.POJO.ResponseRoute;
import com.example.asee_project.POJO.ScheduledFlight;
import com.example.asee_project.model.Vuelo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultadoVuelosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadoVuelosFragment extends Fragment implements AdaptadorVuelos.OnVueloClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_ORIGEN = "param1";
    public static final String ARG_DESTINO = "param2";
    public static final String ARG_DIA = "param3";
    public static final String ARG_MES = "param4";
    public static final String ARG_ANIO = "param5";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdaptadorVuelos mAdapter;


    // TODO: Rename and change types of parameters
    private String mParamOrigen;
    private String mParamDestino;
    private String mParamDia;
    private String mParamMes;
    private String mParamAnio;

    public ResultadoVuelosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultadoVuelosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultadoVuelosFragment newInstance(String param1, String param2) {
        ResultadoVuelosFragment fragment = new ResultadoVuelosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ORIGEN, param1);
       // args.putString(ARG_PARAM, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamOrigen = getArguments().getString(ARG_ORIGEN);
            mParamDestino = getArguments().getString(ARG_DESTINO);
            mParamDia = getArguments().getString(ARG_DIA);
            mParamMes = getArguments().getString(ARG_MES);
            mParamAnio = getArguments().getString(ARG_ANIO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_resultado_busqueda_vuelos, container, false);
        mAdapter = new AdaptadorVuelos(new ArrayList<>(),this);
        mRecyclerView = v.findViewById(R.id.recycler_resultado_vuelos);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        final String origen,destino,dia,mes,anio;
        origen =  getArguments().getString(ARG_ORIGEN);
        destino = getArguments().getString(ARG_DESTINO);
        dia = getArguments().getString(ARG_DIA);
        mes = getArguments().getString(ARG_MES);
        anio = getArguments().getString(ARG_ANIO);
        ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
        AppExecutors.getInstance().networkIO().execute(new FlightsNetworkLoaderRunnable(new onFlightsLoadedListener() {
            @Override
            public void onFlightsRouteLoaded(ResponseRoute ruta) {
                if(ruta != null){
                    int numVuelos =ruta.getScheduledFlights().size();
                    ArrayList<ScheduledFlight> scheduledFlights = (ArrayList<ScheduledFlight>) ruta.getScheduledFlights();
                    ScheduledFlight sf;
                    String llegada,salida;

                    boolean repetido=false;
                    for(int i=0;i<numVuelos;i++){
                        repetido = false;
                        sf =scheduledFlights.get(i);
                        llegada = sf.getArrivalTime();
                        salida = sf.getDepartureTime();
                        for(Vuelo v : vuelos) {
                            if (salida.equals(v.getSalida()))
                                repetido = true;
                        }
                        if(repetido==false)
                            vuelos.add(new Vuelo(origen,destino,salida,llegada));
                    }
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {

                            mAdapter.swap(vuelos);

                        }
                    });
                    Log.i("vuelosSize",vuelos.size()+"");
                }else{
                    Log.i("mensaje","ruta == null");
                }
            }
        },origen,destino,dia,mes,anio));


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        // use a linear layout manager
        // - Set a Linear Layout Manager to the RecyclerView


        return v;
    }

    @Override
    public void onItemClick(Vuelo v) {
        DetalleVueloFragment fragment = new DetalleVueloFragment();
        Bundle bundle  = new Bundle();
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