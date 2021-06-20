package com.example.asee_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asee_project.database.CiudadDao;
import com.example.asee_project.database.CiudadDatabase;
import com.example.asee_project.database.Dataset_Ciudades;
import com.example.asee_project.vistaCiudades.BuscadorCiudadFragment;
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
                CiudadDao ciudadDdao = database.getCiudadDao();
                if (ciudadDdao.size() == 0)
                    ciudadDdao.insertList(data.getDataSet());
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
        if (savedInstanceState == null) {
            FragmentHome fragment = new FragmentHome();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, fragment)
                    .commit();
        }
    }
}
