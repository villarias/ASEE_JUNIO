package com.example.asee_project.vistaVuelos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.VueloViewModel;
import com.example.asee_project.R;
import com.example.asee_project.apiVuelos.VueloNetworkDataSource;
import com.example.asee_project.VueloRepository;
import com.example.asee_project.VueloViewModelFactory;
import com.example.asee_project.database.VueloDataBase;
import com.example.asee_project.model.Vuelo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaVuelosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaVuelosFragment extends Fragment implements AdaptadorVuelos.OnVueloClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_CODORIGEN = "param1";
    public static final String ARG_CODDESTINO = "param2";
    public static final String ARG_ORIGEN= "param3";
    public static final String ARG_DESTINO= "param4";
    public static final String ARG_DIA = "param5";
    public static final String ARG_MES = "param6";
    public static final String ARG_ANIO = "param7";


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdaptadorVuelos mAdapter;
    private VueloRepository mRepository;
    private ProgressBar p;



    // TODO: Rename and change types of parameters
    private String mParamCodOrigen;
    private String mParamCodDestino;
    private String mParamOrigen;
    private String mParamDestino;
    private String mParamDia;
    private String mParamMes;
    private String mParamAnio;


    public ListaVuelosFragment() {
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
    public static ListaVuelosFragment newInstance(String param1, String param2) {
        ListaVuelosFragment fragment = new ListaVuelosFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_CODORIGEN, param1);
       // args.putString(ARG_PARAM, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParamCodOrigen = getArguments().getString(ARG_CODORIGEN);
            mParamCodDestino = getArguments().getString(ARG_CODDESTINO);
            mParamDestino = getArguments().getString(ARG_DESTINO);
            mParamOrigen = getArguments().getString(ARG_ORIGEN);
            mParamDia = getArguments().getString(ARG_DIA);
            mParamMes = getArguments().getString(ARG_MES);
            mParamAnio = getArguments().getString(ARG_ANIO);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.resultado_busqueda_vuelo, container, false);
        TextView info = v.findViewById(R.id.infoBusqueda);
        TextView infoDia  = v.findViewById(R.id.infoBusqueda2);
        info.setText(mParamOrigen+ " - "+ mParamDestino);
        infoDia.setText(mParamDia + " de " + getNombreMes(mParamMes));
        mAdapter = new AdaptadorVuelos(new ArrayList<>(),this);
        mRecyclerView = v.findViewById(R.id.recycler_resultado_vuelos);
        mRecyclerView.setHasFixedSize(true);
        p = (ProgressBar) v.findViewById(R.id.spinner);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRepository = VueloRepository.getInstance(VueloDataBase.getInstance(getActivity()).getDao(), VueloNetworkDataSource.getInstance());

        VueloViewModelFactory factory = new VueloViewModelFactory(mRepository);

        VueloViewModel mViewModel = new ViewModelProvider(this, factory).get(VueloViewModel.class);

        mViewModel.setParams(mParamCodOrigen,mParamCodDestino,mParamOrigen,mParamDestino,mParamDia,mParamMes,mParamAnio);
        mViewModel.getVuelos().observe(this, vuelos -> {

            mAdapter.swap(vuelos);
            // Show the repo list or the loading screen based on whether the repos data exists and is loaded
            if (vuelos != null && vuelos.size() != 0) onVueloLoaded(vuelos);
            else showLoading();
        });

        //mViewModel.setParams(mParamCodOrigen,mParamCodDestino,mParamOrigen,mParamDestino,mParamDia,mParamMes,mParamAnio);
/*


        mRepository.getCurrentVuelo().observe(this,new Observer<List<Vuelo>>(){
            @Override
            public void onChanged(List<Vuelo> repos) {
                onVueloLoaded(repos);
            }
        });
        loadVueloRepo();
*/
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


    public void onVueloLoaded(List<Vuelo> vuelos) {
        p.setVisibility(View.INVISIBLE);
        getActivity().runOnUiThread(() -> mAdapter.swap(vuelos));
    }
    public void showLoading(){ p.setVisibility(View.VISIBLE); }

    private void loadVueloRepo() {
        mRepository.setVuelo(mParamCodOrigen,mParamCodDestino,mParamOrigen,mParamDestino,mParamDia,mParamMes,mParamAnio);
    }
    
    private String getNombreMes(String mes){
     String resultado = " ";
     switch (mes){
         case "01":
             resultado = "Enero";
          break;
         case "02":
             resultado = "Febrero";
             break;
         case "03":
             resultado = "Marzo";
             break;
         case "04":
             resultado = "Abril"; 
             break; 
         case "05":
            resultado = "Mayo";
            break;
         case "06":
             resultado = "Junio";
             break;
         case "07":
             resultado ="Julio";
             break;
         case "08":
             resultado ="Agosto";
             break;
         case "09":
             resultado ="Septiembre";
             break;
         case "10":
             resultado ="Octubre";
             break;
         case "11":
             resultado ="Noviembre";
             break;
         case "12":
             resultado = "Diciembre";
             break;
         default:
             break;
             
     }
     return resultado;
    }
}
