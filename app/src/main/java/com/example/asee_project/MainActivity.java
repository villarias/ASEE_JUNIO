package com.example.asee_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.database.CiudadDao;
import com.example.asee_project.database.CiudadDatabase;
import com.example.asee_project.database.Dataset_Ciudades;
import com.example.asee_project.database.VueloDataBase;
import com.example.asee_project.database.VueloItemDao;
import com.example.asee_project.model.Ciudad;
import com.example.asee_project.vistaCiudades.BuscadorCiudadFragment;
import com.example.asee_project.vistaCiudades.CiudadesGuardadasFragment;
import com.example.asee_project.vistaCiudades.DetalleCiudadFragment;
import com.example.asee_project.vistaVuelos.BuscadorVuelosFragment;


public class MainActivity extends AppCompatActivity {
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
                VueloDataBase databaseVuelos = VueloDataBase.getInstance(getApplicationContext());
                CiudadDao ciudadDdao = database.getCiudadDao();
                VueloItemDao vueloDao = databaseVuelos.getDao();
                if(ciudadDdao.size() ==0)
                    ciudadDdao.insertList(data.getDataSet());
                    /*Ciudad venecia = ciudadDdao.findByName("Venecia");
                    Ciudad barcelona = ciudadDdao.findByName("Barcelona");
                    Ciudad Amsterdam = ciudadDdao.findByName("Amsterdam");
                    Ciudad Bilbao = ciudadDdao.findByName("Bilbao");
                    Ciudad Lisboa = ciudadDdao.findByName("Lisboa");
                    database.getCiudadDao().setFavourite(venecia.getCod_ciudad());
                    database.getCiudadDao().setFavourite(barcelona.getCod_ciudad());
                    database.getCiudadDao().setFavourite(Amsterdam.getCod_ciudad());
                    database.getCiudadDao().setFavourite(Bilbao.getCod_ciudad());
                    database.getCiudadDao().setFavourite(Lisboa.getCod_ciudad());*/
                    //vueloDao.fav("Madrid","Dublin","2021-06-20 18:05:00.0","2021-06-20 16:50:00.0",0);
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (savedInstanceState == null){
                            FragmentHome fragment = new FragmentHome();
                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.frameLayout, fragment)
                                    .commit();
                        }
                    }
                });
            }
        });

        ImageButton buttonPerfil = findViewById(R.id.iconoAvión);
        buttonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscadorVuelosFragment fragment = new BuscadorVuelosFragment();
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
    }
}
