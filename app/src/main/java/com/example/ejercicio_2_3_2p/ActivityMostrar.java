package com.example.ejercicio_2_3_2p;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio_2_3_2p.Procesos.Adapt;
import com.example.ejercicio_2_3_2p.Procesos.DatabaseHandler;

public class ActivityMostrar extends AppCompatActivity {

    DatabaseHandler objectDatabaseHandler;
    RecyclerView objectRecycleView;
    Adapt objectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        try {
            objectRecycleView=findViewById(R.id.listimagen);
            objectDatabaseHandler=new DatabaseHandler(this);

        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void getData(View view) {
        try {
            objectAdapter=new Adapt(objectDatabaseHandler.getAllImagesData()) {
            };
            objectRecycleView.setHasFixedSize(true);

            objectRecycleView.setLayoutManager(new LinearLayoutManager(this));
            objectRecycleView.setAdapter((RecyclerView.Adapter) objectAdapter);
        }
        catch (Exception e) {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}

