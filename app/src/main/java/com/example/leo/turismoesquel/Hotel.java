package com.example.leo.turismoesquel;

/**
 * Created by julio on 08/09/2017.
 */

public class Hotel {

    private int id;
    private String nombre;
    private String descripcion_breve;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String mail;
    private String imagen_portada;
    private String imagen1;
    private String imagen2;

    public Hotel(){
    }

    public Hotel(int id,
                 String nombre,
                 String direccion,
                 String telefono,
                 String mail,
                 String imagen_portada,
                 String imagen1,
                 String imagen2){

        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mail = mail;
        this.imagen_portada = imagen_portada;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getMail() {
        return mail;
    }

    public String getImagen_portada() {
        return imagen_portada;
    }

    public String getImagen1() {
        return imagen1;
    }

    public String getDescripcion_breve() {
        return descripcion_breve;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion_breve(String descripcion_breve) {
        this.descripcion_breve = descripcion_breve;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setImagen_portada(String imagen_portada) {
        this.imagen_portada = imagen_portada;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }
}
