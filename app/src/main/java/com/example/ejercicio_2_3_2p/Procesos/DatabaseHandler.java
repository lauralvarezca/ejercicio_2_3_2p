package com.example.ejercicio_2_3_2p.Procesos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "tarea.db";
    private static int DATABASE_VERSION = 1;
    private static String createTableQuery = "CREATE TABLE imageninformacion (Nombre TEXT" + ",imagen BLOB)";

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;
    Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(createTableQuery);
            Toast.makeText(context, "Table creada Correctamente", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeImage(Modelo objectModelClass) {
        try {
            SQLiteDatabase objectSqLiteDatabase = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = objectModelClass.getImage();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);

            imageInBytes = objectByteArrayOutputStream.toByteArray();
            ContentValues objectContentValues = new ContentValues();
            objectContentValues.put("Nombre", objectModelClass.getNombre());
            objectContentValues.put("imagen", imageInBytes);

            long checkIfQueryRuns = objectSqLiteDatabase.insert("imageninformacion", null, objectContentValues);
            if (checkIfQueryRuns != -1) {
                Toast.makeText(context, "Datos ingresados Correctamente", Toast.LENGTH_SHORT).show();
                objectSqLiteDatabase.close();

            } else {
                Toast.makeText(context, "Error ingreso de Datos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Modelo> getAllImagesData() {
        try {
            SQLiteDatabase objectSqLiteDatabase = this.getReadableDatabase();
            ArrayList<Modelo> objectModeloList= new ArrayList<>();

            Cursor objectCursor = objectSqLiteDatabase.rawQuery("SELECT * FROM imageninformacion", null);
            if (objectCursor.getCount() != 0) {
                while (objectCursor.moveToNext()) {
                    String nameOfImage = objectCursor.getString(0);
                    byte[] imageBytes = objectCursor.getBlob(1);
                    Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    objectModeloList.add(new Modelo(nameOfImage, objectBitmap));
                }
                return objectModeloList;
            } else {
                Toast.makeText(context, "No hay datos", Toast.LENGTH_SHORT).show();
                return null;
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
