package com.hector.mycryptotracker.entities;

import com.hector.mycryptotracker.R;

public class Activity {

    private String type;
    private String name;
    private float value;
    private String date;
    private float amount;

    private int imgTipo;
    private String titulo;
    private String descripcion;

    public Activity(String type, String name, float value, String date, float amount) {

        this.type = type;
        this.name = name;
        this.value = value;
        this.date = date;
        this.amount = amount;

        if (type == null){
            this.imgTipo = R.drawable.img_vacio;
            this.titulo = "Vaya, parece que aún no tienes ninguna transacción";
            this.descripcion = "Puedes añadir una transacción pulsando el botón 'Añadir nueva transacción'";
        } else {

            this.imgTipo = type.toLowerCase().equals("compra") ? R.drawable.img_compra : R.drawable.img_venta;
            this.titulo = type +  " de " + String.valueOf(amount) + "€ de " + name;
            this.descripcion = "Valor de " + name + ": " + value + "€. Fecha de emisión: " + date;
        }

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getImgTipo() {
        return imgTipo;
    }

    public void setImgTipo(int imgTipo) {
        this.imgTipo = imgTipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
