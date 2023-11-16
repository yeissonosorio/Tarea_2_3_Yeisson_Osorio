package com.example.tarea_2_3_yeisson_osorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Person;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tarea_2_3_yeisson_osorio.Conecxion.Conecxion;
import com.example.tarea_2_3_yeisson_osorio.Conecxion.Photograp;
import com.example.tarea_2_3_yeisson_osorio.Modelo.Foto;
import com.example.tarea_2_3_yeisson_osorio.Modelo.Fotografia;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class Listafoto extends AppCompatActivity {
    Conecxion conecxion;

    ArrayList<Fotografia> listfoto;
    List<Foto> data = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listafoto);

        conecxion = new Conecxion(this,Photograp.NameDatabase,null,1);

        recyclerView = findViewById(R.id.listr);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GetFoto();

        MyAdapter adp = new MyAdapter(data);
        recyclerView.setAdapter(adp);
    }

    private void GetFoto()
    {
        SQLiteDatabase db = conecxion.getReadableDatabase();
        Fotografia fot = null;
        listfoto = new ArrayList<Fotografia>();

        Cursor cursor = db.rawQuery(Photograp.Selectfoto,null);

        while(cursor.moveToNext())
        {
            fot = new Fotografia();
            fot.setId(cursor.getInt(0));
            fot.setDescripcion(cursor.getString(1));
            byte[] blob = cursor.getBlob(2);
            fot.setImage(blob);
            listfoto.add(fot);
        }

        cursor.close();
        FillList();
    }
    private void FillList()
    {

        for(int i = 0; i < listfoto.size(); i++)
        {
            data.add(new Foto(listfoto.get(i).getId(),listfoto.get(i).getDescripcion(),revelar(listfoto.get(i).getImage())));
        }
    }

    public Bitmap revelar(byte[] blob){
        Bitmap bitmap = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(blob);
        bitmap = BitmapFactory.decodeStream(bais);
        return bitmap;
    }

}