package com.example.asee_project.vistaCiudades;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.AppExecutors;
import com.example.asee_project.R;
import com.example.asee_project.database.AppDatabase;
import com.example.asee_project.model.Ciudad;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaCiudadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaCiudadFragment extends Fragment implements AdapterCiudad.OnCiudadClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Ciudad> list;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SelectionListener mCallback;

    public ListaCiudadFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaCiudadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaCiudadFragment newInstance(String param1, String param2) {
        ListaCiudadFragment fragment = new ListaCiudadFragment();
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
        View v =inflater.inflate(R.layout.list_ciudades, container, false);
         View recyclerView = v.findViewById(R.id.item_list);
        assert recyclerView != null;
        //Cogemos la instancia
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                list = AppDatabase.getInstance(getActivity()).getCiudadDao().getAll();
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
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, this.list));
    }

    @Override
    public void onItemClick(Ciudad v) {
        DetalleCiudadFragment fragment = new DetalleCiudadFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DetalleCiudadFragment.ARG_ORIGEN,v.getNombre());
        Log.i("FlyScan", "Nombre ciudad: "+v.getNombre());
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }


    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ListaCiudadFragment mParentFragment;
        private final List<Ciudad> mValues;
        public String pasando = "";
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ciudad item = (Ciudad) view.getTag();
                mParentFragment.mCallback.onListItemSelected(item);

                String a = item.getCod_ciudad();
                pasando = item.getNombre();
                //Toast.makeText(view.getContext(),"Men"+a,Toast.LENGTH_LONG).show();

            }
        };

        SimpleItemRecyclerViewAdapter(ListaCiudadFragment parent,
                                      List<Ciudad> items) {
            mValues = items;
            mParentFragment = parent;


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_ciudades, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
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
            mCallback = (SelectionListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement SelectionListener");
        }
    }
    public interface SelectionListener{
        public void onListItemSelected(Ciudad item);
    }



}