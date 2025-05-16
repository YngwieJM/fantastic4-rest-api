package com.juaracoding.fantastic4_rest_api.model;


import jakarta.persistence.*;

@Entity
@Table(name = "MstAdmin")
public class Admin {

    @Id
    @Column(name = "AdminID", length = 50, nullable = false, unique = true)
    private String id;

    @Column(name = "Nama", length = 50, nullable = false)
    private String nama;

    @Column(name = "Email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "NoTelp", length = 50, nullable = false, unique = true)
    private String noTelp;

    @Column(name = "Password", length = 64, nullable = false)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
