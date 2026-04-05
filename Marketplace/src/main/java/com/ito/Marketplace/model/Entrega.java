package com.ito.Marketplace.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private Long idEntrega;

    @OneToOne
    @JoinColumn(name = "id_transaccion", nullable = false, unique = true)
    private Transaccion transaccion;

    private String puntoEncuentro;

    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private Boolean confirmacionVendedor = false;

    @Column(nullable = false)
    private Boolean confirmacionComprador = false;

    public Entrega() {
    }

    public Entrega(Transaccion transaccion, String puntoEncuentro, LocalDateTime fechaHora,
            Boolean confirmacionVendedor, Boolean confirmacionComprador) {
        this.transaccion = transaccion;
        this.puntoEncuentro = puntoEncuentro;
        this.fechaHora = fechaHora;
        this.confirmacionVendedor = confirmacionVendedor;
        this.confirmacionComprador = confirmacionComprador;
    }

    // getters y setters

    public Long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public String getPuntoEncuentro() {
        return puntoEncuentro;
    }

    public void setPuntoEncuentro(String puntoEncuentro) {
        this.puntoEncuentro = puntoEncuentro;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Boolean getConfirmacionVendedor() {
        return confirmacionVendedor;
    }

    public void setConfirmacionVendedor(Boolean confirmacionVendedor) {
        this.confirmacionVendedor = confirmacionVendedor;
    }

    public Boolean getConfirmacionComprador() {
        return confirmacionComprador;
    }

    public void setConfirmacionComprador(Boolean confirmacionComprador) {
        this.confirmacionComprador = confirmacionComprador;
    }
}