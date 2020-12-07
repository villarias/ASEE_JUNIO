package com.example.asee_project;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.model.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisHotelesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisHotelesFragment extends Fragment implements AdaptadorMisHoteles.OnHotelClickListener, AdaptadorMisHoteles.OnBorrarlClickListener,AdaptadorMisHoteles.OnEditarClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Hotel> hoteles;


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorMisHoteles mAdapter;

    public MisHotelesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisHotelesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisHotelesFragment newInstance(String param1, String param2) {
        MisHotelesFragment fragment = new MisHotelesFragment();
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
        View v = inflater.inflate(R.layout.fragment_mis_hoteles, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdaptadorMisHoteles(new ArrayList<>(), this,this,this);
        recyclerView.setAdapter(mAdapter);

        //Todo ListadoHoteles


        return v;
    }





    @Override
    public void onItemHotelClick(Hotel datum) {
        DetalleHotelFragment fragment = new DetalleHotelFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DetalleHotelFragment.ARG_NOMBRE,datum.getNombre());
        bundle.putString(DetalleHotelFragment.ARG_PUNTUACION,datum.getPuntuacion());
        bundle.putString(DetalleHotelFragment.ARG_ID,datum.getID());
        bundle.putString(DetalleHotelFragment.ARG_PRECIO,datum.getPrecio());
        bundle.putString(DetalleHotelFragment.ARG_DIRECCION,datum.getCalle());
        bundle.putString(DetalleHotelFragment.ARG_CONTACTO,datum.getContacto());
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onEditaItemClick(Hotel datum) {
        EditarHotelFragment fragment = new EditarHotelFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DetalleHotelFragment.ARG_NOMBRE,datum.getNombre());
        bundle.putString(DetalleHotelFragment.ARG_PUNTUACION,datum.getPuntuacion());
        bundle.putString(DetalleHotelFragment.ARG_ID,datum.getID());
        bundle.putString(DetalleHotelFragment.ARG_PRECIO,datum.getPrecio());
        bundle.putString(DetalleHotelFragment.ARG_DIRECCION,datum.getCalle());
        bundle.putString(DetalleHotelFragment.ARG_CONTACTO,datum.getContacto());
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBorrarItemClick(Hotel datum) {

  //Todo BorrarHotel

    }
}