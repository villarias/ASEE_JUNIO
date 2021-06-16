package com.example.asee_project.vistaHoteles;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.AppContainer;
import com.example.asee_project.AppExecutors;
import com.example.asee_project.HotelViewModel;
import com.example.asee_project.MyApplication;
import com.example.asee_project.apiHoteles.OnHotelLoadedListener;
import com.example.asee_project.apiHoteles.OnTokenLoadedListener;
import com.example.asee_project.R;
import com.example.asee_project.database.AppDatabase;
import com.example.asee_project.model.Ciudad;
import com.example.asee_project.model.HotelDB;
import com.example.asee_project.modeloApiHoteles.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaHotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaHotelFragment extends Fragment  implements OnTokenLoadedListener, MyAdapter.OnItemClickListener, OnHotelLoadedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_CIUDAD = "param1";
    public static final String ARG_FECHA_IN="param2";
    public static final String ARG_FECHA_FIN="param3";
    public static final String ARG_NUM_PERSONAS="param4";


    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Token k;
    private String ciudad;
    private String fecha_ini;
    private String fecha_fin;
    private String numPersonas;
    private ProgressBar p;
    private TextView lista_vacia;
    private HotelViewModel mViewModel;
    private Ciudad c;



    public ListaHotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaHotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaHotelFragment newInstance(String param1, String param2) {
        ListaHotelFragment fragment = new ListaHotelFragment();
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
            ciudad = getArguments().getString(ARG_CIUDAD);
            fecha_ini = getArguments().getString(ARG_FECHA_IN);
            fecha_fin = getArguments().getString(ARG_FECHA_FIN);
            numPersonas = getArguments().getString(ARG_NUM_PERSONAS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.lista_hoteles, container, false);
        Log.d("Debug FlyScann", "Se ejecuta correctamente");
        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        p = (ProgressBar) v.findViewById(R.id.spinner);
        p.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(new ArrayList<>(), this, R.layout.hotel);
        recyclerView.setAdapter(mAdapter);
        lista_vacia = (TextView) v.findViewById(R.id.lista_vacia);
        lista_vacia.setVisibility(View.INVISIBLE);
        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;

        mViewModel = new ViewModelProvider(this, appContainer.factory).get(HotelViewModel.class);
        mViewModel.getHotels().observe(this, repos -> onHotelLoaded(repos));
        loadHotelRepo();
        //  Log.d("FlyScann Abel", k.getAccessToken());
        return v;
    }

    private void loadHotelRepo() {
        HotelDB h = new HotelDB();
        h.setFecha_inicio(fecha_ini);
        h.setFecha_fin(fecha_fin);
        h.setNum_personas(numPersonas);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                c=AppDatabase.getInstance(getActivity()).getCiudadDao().findByName(ciudad);
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        h.setCiudad(c.getCod_ciudad());
                        mViewModel.setHotel(h,p);
                    }
                });
            }
        });





    }

    @Override
    public void onItemClick(HotelDB datum) {
        DetalleHotelFragment fragment = new DetalleHotelFragment();
       Bundle bundle = new Bundle();
        bundle.putString(DetalleHotelFragment.ARG_ID,datum.getID()+"");
        bundle.putString(DetalleHotelFragment.ARG_NOMBRE,datum.getNombre());
        bundle.putString(DetalleHotelFragment.ARG_PUNTUACION,datum.getPuntuacion());
        bundle.putString(DetalleHotelFragment.ARG_IMAGENES,datum.getImagen());
        bundle.putString(DetalleHotelFragment.ARG_PRECIO,datum.getPrecio());
        bundle.putString(DetalleHotelFragment.ARG_DIRECCION,datum.getCalle());
        bundle.putString(DetalleHotelFragment.ARG_CONTACTO,datum.getContacto());
        bundle.putString(DetalleHotelFragment.ARG_FECHAINI,datum.getFecha_inicio());
        bundle.putString(DetalleHotelFragment.ARG_FECHAFIN,datum.getFecha_fin());
        bundle.putString(DetalleHotelFragment.ARG_NUMPERSONAS,datum.getNum_personas());
        bundle.putString(DetalleHotelFragment.ARG_CIUDAD,datum.getCiudad());
        Log.d("FlyScan","Se ejecuta correctamente");
         fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onTokenLoaded(Token token) {
        this.k = token;


    }

    @Override
    public void onHotelLoaded(List<HotelDB> hoteles) {
     getActivity().runOnUiThread(() -> mAdapter.swap(hoteles));
     p.setVisibility(View.INVISIBLE);
    }
}
