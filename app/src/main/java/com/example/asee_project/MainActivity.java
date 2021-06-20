package com.example.asee_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.database.CiudadDao;
import com.example.asee_project.database.CiudadDatabase;
import com.example.asee_project.database.Dataset_Ciudades;
import com.example.asee_project.model.Ciudad;
import com.example.asee_project.vistaCiudades.BuscadorCiudadFragment;
import com.example.asee_project.vistaCiudades.CiudadesGuardadasFragment;
import com.example.asee_project.vistaCiudades.DetalleCiudadFragment;


public class MainActivity extends AppCompatActivity implements  CiudadesGuardadasFragment.SelectionListener{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        CiudadDatabase.getInstance(this);
        Dataset_Ciudades data = new Dataset_Ciudades();

        //Cogemos la instancia/
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                // Inserción en la base de datos
                //Todo: Change context database or data set
                CiudadDatabase database = CiudadDatabase.getInstance(getApplicationContext());
                CiudadDao ciudadDdao = database.getCiudadDao();
                if(ciudadDdao.size() ==0)
                    ciudadDdao.insertList(data.getDataSet());
                    Ciudad venecia = ciudadDdao.findByName("Venecia");
                    Ciudad barcelona = ciudadDdao.findByName("Barcelona");
                    Ciudad Amsterdam = ciudadDdao.findByName("Amsterdam");
                    Ciudad Bilbao = ciudadDdao.findByName("Bilbao");
                    Ciudad Lisboa = ciudadDdao.findByName("Lisboa");
                    database.getCiudadDao().setFavourite(venecia.getCod_ciudad());
                    database.getCiudadDao().setFavourite(barcelona.getCod_ciudad());
                    database.getCiudadDao().setFavourite(Amsterdam.getCod_ciudad());
                    database.getCiudadDao().setFavourite(Bilbao.getCod_ciudad());
                    database.getCiudadDao().setFavourite(Lisboa.getCod_ciudad());
            }
        });

        ImageButton buttonPerfil = findViewById(R.id.iconoAvión);
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

        ImageButton buttonHome = findViewById(R.id.iconoHome);
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
        ImageButton buttonCiudad = findViewById(R.id.iconoCiudad);
        buttonCiudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscadorCiudadFragment fragment = new BuscadorCiudadFragment();
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

    @Override
        public void onListItemSelected(Ciudad item) {
            DetalleCiudadFragment fragment = new DetalleCiudadFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BuscadorCiudadFragment.ARG_PARAM1,item.getNombre());
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .addToBackStack(null)
                    .commit();


    }
}
