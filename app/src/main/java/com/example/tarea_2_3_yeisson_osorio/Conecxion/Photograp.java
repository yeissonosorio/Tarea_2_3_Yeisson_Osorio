package com.example.tarea_2_3_yeisson_osorio.Conecxion;

public class Photograp {
    public static final String NameDatabase = "PM01DB";

    public static final String tablacontactos = "Galeria";

    public static final String id = "id";
    public static final String Descripcion = "Descripcion";
    public static final String foto = "foto";


    public static final String createTableContact = "CREATE TABLE " + tablacontactos +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Descripcion TEXT, foto BLOB)";

    public  static  final String Selectfoto ="select id, Descripcion, foto from "+tablacontactos;

    public static final String dropTableContact = "DROP TABLE IF EXIST" + tablacontactos;
}
