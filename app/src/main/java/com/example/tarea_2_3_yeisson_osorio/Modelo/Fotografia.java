package com.example.tarea_2_3_yeisson_osorio.Modelo;

import android.graphics.Bitmap;

import java.sql.Blob;

public class Fotografia {

    private int id;
    private String Descripcion;
    private byte[] image;

    public Fotografia(int id, String descripcion, byte[] image) {
        this.id = id;
        Descripcion = descripcion;
        this.image = image;
    }

    public Fotografia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }
}
