package com.esteban.buscar.pojo;

public class Producto {
    private String uid;
    private String nombre;
    private Integer precio;
    private Integer cantidad;
    private String categoria;

    public Producto(){}

        public Producto(String uid, String nombre, Integer precio, Integer cantidad, String categoria) {
      //public Producto(String nombre, Integer precio, Integer cantidad, String categoria) {
        this.uid = uid;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }


    public String getUid() {return uid;}
    public void setUid(String uid) {this.uid = uid;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public Integer getPrecio() {return precio;}
    public void setPrecio(Integer precio) {this.precio = precio;}

    public Integer getCantidad() {return cantidad;}
    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}

    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria) {this.categoria = categoria;}

}