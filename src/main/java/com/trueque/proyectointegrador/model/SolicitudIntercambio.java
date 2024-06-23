package com.trueque.proyectointegrador.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "api_solicitudintercambio")
public class SolicitudIntercambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_solicitado_id", nullable = false)
    private Producto productoSolicitado;

    @ManyToOne
    @JoinColumn(name = "producto_ofrecido_id", nullable = false)
    private Producto productoOfrecido;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "propietario_id", nullable = false)
    private Usuario propietario;

    private Boolean aceptada = false;
    private Boolean respondida = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProductoSolicitado() {
        return productoSolicitado;
    }

    public void setProductoSolicitado(Producto productoSolicitado) {
        this.productoSolicitado = productoSolicitado;
    }

    public Producto getProductoOfrecido() {
        return productoOfrecido;
    }

    public void setProductoOfrecido(Producto productoOfrecido) {
        this.productoOfrecido = productoOfrecido;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public Boolean getAceptada() {
        return aceptada;
    }

    public void setAceptada(Boolean aceptada) {
        this.aceptada = aceptada;
    }

    public Boolean getRespondida() {
        return respondida;
    }

    public void setRespondida(Boolean respondida) {
        this.respondida = respondida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
