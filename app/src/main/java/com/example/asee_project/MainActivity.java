package com.example.asee_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asee_project.database.AppDatabase;
import com.example.asee_project.database.Dataset_Ciudades;
import com.example.asee_project.model.Ciudad;
import com.example.asee_project.vistaCiudades.CiudadesGuardadasFragment;
import com.example.asee_project.vistaCiudades.DetalleCiudadFragment;
import com.example.asee_project.vistaCiudades.ListaCiudadFragment;

public class MainActivity extends AppCompatActivity implements ListaCiudadFragment.SelectionListener, CiudadesGuardadasFragment.SelectionListener{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        AppDatabase.getInstance(this);
        Dataset_Ciudades data = new Dataset_Ciudades();

        //Cogemos la instancia
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                // Inserción en la base de datos
                //Todo: Change context database or data set.
                AppDatabase database = AppDatabase.getInstance(getApplicationContext());
                database.getCiudadDao().insertList(data.getDataSet());

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

    @Override


        public void onListItemSelected(Ciudad item) {
            DetalleCiudadFragment fragment = new DetalleCiudadFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ListaCiudadFragment.ARG_PARAM1,item.getNombre());
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .addToBackStack(null)
                    .commit();


    }
}
