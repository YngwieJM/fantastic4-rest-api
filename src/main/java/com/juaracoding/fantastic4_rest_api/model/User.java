package com.juaracoding.fantastic4_rest_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MstUser")
public class User {

    @Id
    @Column(name = "UserID", length = 50, nullable = false, unique = true)
    private String id;

    @Column(name = "Nama", length = 50, nullable = false)
    private String nama;

    @Column(name = "Email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "NoTelp ", length = 50, nullable = false, unique = true)
    private String noTelp;

    @Column(name = "Password", length = 64, nullable = false)
    private String password;

    @Column(name = "Departemen", length = 100, nullable = false)
    private String departemen;

    @Column(name = "Jabatan", length = 100, nullable = false)
    private String jabatan;

    public String getUserID() {
        return id;
    }

    public void setUserID(String id) {
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

    public String getDepartemen(){
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen ;
    }

    public String getJabatan(){
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public void setPassword(String password) {

        this.password = password;
    }

}