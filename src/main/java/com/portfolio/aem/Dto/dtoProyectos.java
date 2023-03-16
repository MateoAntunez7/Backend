/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.aem.Dto;

import javax.validation.constraints.NotBlank;

public class dtoProyectos {

    @NotBlank
    private String nombre;
    @NotBlank
    private String link;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String img;

    public dtoProyectos() {
    }

    public dtoProyectos(String nombre, String link, String descripcion, String img) {
        this.nombre = nombre;
        this.link = link;
        this.descripcion = descripcion;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
}
