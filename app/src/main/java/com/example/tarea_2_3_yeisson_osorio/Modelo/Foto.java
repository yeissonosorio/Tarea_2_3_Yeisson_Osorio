package com.example.tarea_2_3_yeisson_osorio.Modelo;

import android.graphics.Bitmap;

public class Foto {

    private int id;
    private String Descripcion;

    private Bitmap fot;

    public Foto(int id, String descripcion, Bitmap fot) {
        this.id = id;
        Descripcion = descripcion;
        this.fot = fot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Bitmap getFot() {
        return fot;
    }

    public void setFot(Bitmap fot) {
        this.fot = fot;
    }
}
