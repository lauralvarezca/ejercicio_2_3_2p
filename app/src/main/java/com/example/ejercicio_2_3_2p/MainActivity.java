package com.example.ejercicio_2_3_2p;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ejercicio_2_3_2p.Procesos.DatabaseHandler;
import com.example.ejercicio_2_3_2p.Procesos.Modelo;

public class MainActivity extends AppCompatActivity {

    EditText txtdescripcion;
    ImageView imagen;
    Bitmap imagenToStore;
    Uri imagenFilePath;

    private static final int PICK_IMAGE_REQUEST = 100;
    DatabaseHandler objectDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            txtdescripcion = (EditText) findViewById(R.id.txtdescripcion);
            imagen = (ImageView) findViewById(R.id.imagen);

            objectDatabaseHandler = new DatabaseHandler(this);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    public void chooseImage(View objectView) {
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent, PICK_IMAGE_REQUEST);


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imagenFilePath = data.getData();
                imagenToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imagenFilePath);
                imagen.setImageBitmap(imagenToStore);
            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    public void storeImage(View view) {
        try {
            if (!txtdescripcion.getText().toString().isEmpty() && imagen.getDrawable() != null && imagenToStore != null) {
                objectDatabaseHandler.storeImage(new Modelo(txtdescripcion.getText().toString(), imagenToStore));
            } else {
                Toast.makeText(this, "Por favor seleccione un nombre y una Imagen", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    public void moveToShowActivity(View view) {
        startActivity(new Intent( this, ActivityMostrar.class));
    }

}