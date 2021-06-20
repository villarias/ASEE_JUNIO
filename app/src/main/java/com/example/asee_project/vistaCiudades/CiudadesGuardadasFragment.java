package com.example.asee_project.vistaCiudades;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.AppExecutors;
import com.example.asee_project.R;
import com.example.asee_project.database.CiudadDatabase;
import com.example.asee_project.model.Ciudad;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CiudadesGuardadasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CiudadesGuardadasFragment extends Fragment implements AdapterCiudad.OnCiudadClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Ciudad> list;
    private AdapterCiudad mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CiudadesGuardadasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CiudadesGuardadasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CiudadesGuardadasFragment newInstance(String param1, String param2) {
        CiudadesGuardadasFragment fragment = new CiudadesGuardadasFragment();
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
        View v = inflater.inflate(R.layout.fragment_ciudades_guardadas, container, false);
        mRecyclerView = v.findViewById(R.id.recycler_ciudades);
        mAdapter = new AdapterCiudad(new ArrayList<>(), this);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Cogemos la instancia
        //Mostramos la lista de las ciudades favoritas en una RecyclerView
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                list = CiudadDatabase.getInstance(getActivity()).getCiudadDao().getAllFav();
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.swap(list);
                    }
                });
            }

        });
        return v;
    }

    @Override
    public void onItemClick(Ciudad v) {
        DetalleCiudadFragment fragment = new DetalleCiudadFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DetalleCiudadFragment.ARG_ORIGEN,v.getNombre());
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }
}