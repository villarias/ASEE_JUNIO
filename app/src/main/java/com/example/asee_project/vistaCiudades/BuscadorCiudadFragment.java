package com.example.asee_project.vistaCiudades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.asee_project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuscadorCiudadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuscadorCiudadFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageButton boton_buscar;
    private EditText ciudad;

    public BuscadorCiudadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuscadorCiudadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuscadorCiudadFragment newInstance(String param1, String param2) {
        BuscadorCiudadFragment fragment = new BuscadorCiudadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View nueva = inflater.inflate(R.layout.fragment_detalle_ciudad, container, false);
        View v = inflater.inflate(R.layout.buscador_ciudades, container, false);
        ciudad = (EditText) v.findViewById(R.id.input_ciudad);
        boton_buscar = (ImageButton) v.findViewById(R.id.boton_Buscar);
        boton_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetalleCiudadFragment fragment = new DetalleCiudadFragment();
                String codigo;
                codigo = String.valueOf(ciudad.getText());

                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                bundle.putString(DetalleCiudadFragment.ARG_ORIGEN, codigo);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }
}