package com.example.tarea_2_3_yeisson_osorio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tarea_2_3_yeisson_osorio.Conecxion.Conecxion;
import com.example.tarea_2_3_yeisson_osorio.Conecxion.Photograp;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    static final int peticion_acceso_camara = 101;
    static final int peticion_toma_fotografica = 102;

    EditText Descripsion;
    ImageView imagen;
    Button salvar,tomar,listab;

    Bitmap imagenb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Descripsion=(EditText) findViewById(R.id.txtDescripcion);
        imagen=(ImageView) findViewById(R.id.imageView);
        salvar = (Button) findViewById(R.id.btnSalvar);
        tomar = (Button) findViewById(R.id.btntomar);
        listab = (Button) findViewById(R.id.btnlista);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Descripsion.getText().toString().replaceAll("\\s","").isEmpty()){
                    Descripsion.setError("Este campo es obligatorio");
                }else {
                    addFoto(imagenb);

                }
            }
        });

        tomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos();
            }
        });

        listab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Listafoto.class);
                startActivity(intent);
            }
        });

    }

    private void permisos() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},peticion_acceso_camara);
        }
        else
        {
            TomarFoto();
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == peticion_acceso_camara)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                TomarFoto();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Permiso denegado", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void TomarFoto()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!= null)
        {
            startActivityForResult(intent, peticion_toma_fotografica);
        }
    }

    protected void onActivityResult(int requescode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requescode, resultCode, data);

        if (requescode==peticion_toma_fotografica && resultCode== RESULT_OK){
            Bundle extras = data.getExtras();
            imagenb = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imagenb);
        } else if (resultCode==RESULT_OK) {
            Uri path=data.getData();
            imagen.setImageURI(path);
        }
    }
    private void addFoto(Bitmap bitmap) {
        try {
            Conecxion con = new Conecxion(this, Photograp.NameDatabase,null,1);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            SQLiteDatabase db =  con.getWritableDatabase();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            byte[] ArrayFoto =stream.toByteArray();

            ContentValues valores = new ContentValues();
            valores.put(Photograp.Descripcion, Descripsion.getText().toString());
            valores.put(String.valueOf(Photograp.foto),ArrayFoto);

            Long Result = db.insert(Photograp.tablacontactos,Photograp.id, valores);

            Toast.makeText(this,"Foto Guarda", Toast.LENGTH_SHORT).show();
            db.close();
        }
        catch (Exception exception)
        {
            Toast.makeText(this,"Necesita tomar una foto", Toast.LENGTH_SHORT).show();
        }

    }
}