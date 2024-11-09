package com.correos.modelos;

import java.util.Date;

public class Correo {
    private int id;
    private int remitenteId;
    private int destinatarioId;
    private String asunto;
    private String mensaje;
    private Date fechaEnvio;
    private String estado;
    private boolean leido;

    public Correo(int id, int remitenteId, int destinatarioId, String asunto, String mensaje, Date fechaEnvio, String estado, boolean leido) {
        this.id = id;
        this.remitenteId = remitenteId;
        this.destinatarioId = destinatarioId;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.estado = estado;
        this.leido = leido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemitenteId() {
        return remitenteId;
    }

    public void setRemitenteId(int remitenteId) {
        this.remitenteId = remitenteId;
    }

    public int getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(int destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}

