package com.correos.modelos;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String dni;
    private boolean isAdmin;

    public Usuario(int id, String nombre, String email, String dni, boolean isAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
