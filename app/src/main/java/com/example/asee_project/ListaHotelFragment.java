package com.example.asee_project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.modelo.Datum;
import com.example.asee_project.modelo.Hotel;
import com.example.asee_project.modelo.Medium;
import com.example.asee_project.modelo.Offer;
import com.example.asee_project.modelo.Price;
import com.example.asee_project.modelo.Token;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaHotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaHotelFragment extends Fragment  implements OnTokenLoadedListener, MyAdapter.OnItemClickListener{

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
        AppExecutors.getInstance().networkIO().execute(new TokenRunnable(this));
        //  Log.d("FlyScann Abel", k.getAccessToken());
        return v;
    }

    @Override
    public void onItemClick(Datum datum) {
        DetalleHotelFragment fragment = new DetalleHotelFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DetalleHotelFragment.ARG_NOMBRE,datum.getHotel().getName());
        bundle.putString(DetalleHotelFragment.ARG_PUNTUACION,datum.getHotel().getRating());
        bundle.putString(DetalleHotelFragment.ARG_ID,datum.getHotel().getHotelId());

        Log.d("FlyScan","Se ejecuta correctamente");
        Log.d("Calle",datum.getHotel().getAddress().getLines().get(0));
        ArrayList<String>imagenes = new ArrayList<>();
        if (datum.getHotel().getMedia().size()>0) {
            for (Medium media : datum.getHotel().getMedia()) {
                imagenes.add(media.getUri());
            }
        }
        bundle.putStringArrayList(String.valueOf(DetalleHotelFragment.ARG_IMAGENES),imagenes);

        if (datum.getOffers().size()>0) {
            Price min = datum.getOffers().get(0).getPrice();
            for(Offer f : datum.getOffers()){
                if (Double.parseDouble(f.getPrice().getTotal()) < Double.parseDouble(min.getTotal())) {
                    min = f.getPrice();
                }
            }
            bundle.putString(DetalleHotelFragment.ARG_PRECIO,min.getTotal()+ " "+min.getCurrency());
        }else{
            bundle.putString(DetalleHotelFragment.ARG_PRECIO,"");
        }

        if (datum.getHotel().getAddress().getLines().size()>0){
            bundle.putString(DetalleHotelFragment.ARG_DIRECCION,datum.getHotel().getAddress().getLines().get(0));
        }else{
            bundle.putString(DetalleHotelFragment.ARG_DIRECCION," ");
        }
        bundle.putString(DetalleHotelFragment.ARG_CONTACTO,datum.getHotel().getContact().getPhone());

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

        AppExecutors.getInstance().networkIO().execute(new HotelRunnable(k.getTokenType()+" " + k.getAccessToken(), "LON", fecha_ini, fecha_fin, numPersonas, p,new OnHotelLoadedListener() {
            @Override
            public void onHotelLoaded(Hotel data) {
                if(data!=null){
                    mAdapter.swap(data.getData());
                    if (data.getData().size()==0){
                        lista_vacia.setVisibility(View.VISIBLE);
                    }
                }else{
                    lista_vacia.setVisibility(View.VISIBLE);
                }
            }
        } ));
    }
}
