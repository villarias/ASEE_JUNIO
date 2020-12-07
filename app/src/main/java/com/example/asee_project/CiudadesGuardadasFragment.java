package com.example.asee_project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.database.AppDatabase;
import com.example.asee_project.model.Ciudad;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CiudadesGuardadasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CiudadesGuardadasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Ciudad> list;
    private CiudadesGuardadasFragment.SelectionListener mCallback;

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
        View recyclerView = v.findViewById(R.id.recycler_ciudades);

            assert recyclerView != null;
            //Cogemos la instancia
        //Todo ListadoCiudades
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                list = AppDatabase.getInstance(getActivity()).getCiudadDao().getAllFav();
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        setupRecyclerView((RecyclerView) recyclerView);
                    }
                });
            }
        });
        return v;
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new CiudadesGuardadasFragment.SimpleItemRecyclerViewAdapter(this, this.list));
    }



    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<CiudadesGuardadasFragment.SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final CiudadesGuardadasFragment mParentFragment;
        private final List<Ciudad> mValues;

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ciudad item = (Ciudad) view.getTag();
                mParentFragment.mCallback.onListItemSelected(item);

                String a = item.getCod_ciudad();
                //Toast.makeText(view.getContext(),"Men"+a,Toast.LENGTH_LONG).show();

            }
        };

        SimpleItemRecyclerViewAdapter(CiudadesGuardadasFragment parent,
                                      List<Ciudad> items) {
            mValues = items;
            mParentFragment = parent;


        }

        @Override
        public CiudadesGuardadasFragment.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_ciudades, parent, false);
            return new CiudadesGuardadasFragment.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final CiudadesGuardadasFragment.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.id_vuelo.setText(mValues.get(position).getCod_ciudad());
            holder.nombre_ciudad.setText(mValues.get(position).getNombre());
            //holder.nombre_ciudad.setText(mValues.get(position).content);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);

        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView id_vuelo;
            final TextView nombre_ciudad;

            ViewHolder(View view) {
                super(view);
                id_vuelo = (TextView) view.findViewById(R.id.c_codigo_ciudad);
                nombre_ciudad = (TextView) view.findViewById(R.id.c_nombre_ciudad);
            }
        }

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mCallback = (CiudadesGuardadasFragment.SelectionListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement SelectionListener");
        }
    }
    public interface SelectionListener{
        public void onListItemSelected(Ciudad item);
    }

    public void onListItemSelected(Ciudad item){
        DetalleCiudadFragment fragment = new DetalleCiudadFragment();

        Bundle bundle = new Bundle();
        bundle.putString(CiudadesGuardadasFragment.ARG_PARAM1,item.getCod_ciudad());
        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }


}