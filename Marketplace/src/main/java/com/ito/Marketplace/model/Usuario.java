package com.ito.Marketplace.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @Getter
    @Column(name = "correo_institucional",nullable = false, unique = true)
    private String correoInstitucional;

    @Column(name = "contrasena",nullable = false)
    private String contrasena;

    @Column(name = "rol", nullable = false)
    private String rol; // ya sea comprador, vendedor o supervisor, etc xd

    @Column(name = "telefono")
    private String telefono;


    public Usuario() {
    }
    public Usuario(String nombreCompleto, String matricula, String correo, String contrasena, String rol, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.matricula = matricula;
        this.correoInstitucional = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.telefono = telefono;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
