package com.example.asee_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.database.AppDatabase;
import com.example.asee_project.model.Ciudad;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements ListaCiudadFragment.SelectionListener, CiudadesGuardadasFragment.SelectionListener{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        AppDatabase.getInstance(this);
        //Cogemos la instancia
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                // Inserción en la base de datos
                //Todo: Change context database or data set.
                AppDatabase database = AppDatabase.getInstance(getApplicationContext());
                //database.getCiudadDao().deleteAll();

                //database.getCiudadDao().insertList(getDataSet());
                if (database.getCiudadDao().getAll().size() <= 0) {
                    database.getCiudadDao().insertList(getDataSet());
                }
            }
        });
        ImageButton buttonPerfil = findViewById(R.id.perfil_button);
        buttonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerfilFragment fragment = new PerfilFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        ImageButton buttonHome = findViewById(R.id.inicioButton);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHome fragment = new FragmentHome();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        if (savedInstanceState == null){
            FragmentHome fragment = new FragmentHome();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, fragment)
                    .commit();
        }
    }
    private List<Ciudad> getDataSet() {
        List<Ciudad> l = new ArrayList<Ciudad>() {{
            add(new Ciudad("ABC","Albacete"));
            add(new Ciudad("CDT","Castellon"));
            add(new Ciudad("GRS","Granada"));
            add(new Ciudad("SVQ", "Sevilla"));
            add(new Ciudad("MAD", "Madrid"));
            add(new Ciudad("TFN", "Tenerife Norte"));
            add(new Ciudad("MUC", "Munich"));
            add(new Ciudad("MXP", "Milan"));
            add(new Ciudad("TRN", "Turin"));
            add(new Ciudad("LAS", "Las Vegas"));
            add(new Ciudad("ORD", "Chicago"));
        }};
        /*for (int i = 0 ; i < l.size(); i++) {
            l.get(i).setFavorite(false);
        }*/
        return l;

    }

    @Override


        public void onListItemSelected(Ciudad item) {
            DetalleCiudadFragment fragment = new DetalleCiudadFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ListaCiudadFragment.ARG_PARAM1,item.getCod_ciudad());
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .addToBackStack(null)
                    .commit();


    }
}
