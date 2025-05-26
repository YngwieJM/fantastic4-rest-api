package com.juaracoding.fantastic4_rest_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @Column(name = "OTP",length = 64)
    private String otp;

    @Column(name = "IsRegistered")
    private Boolean isRegistered;

    @Column(name = "CreatedBy",nullable = false,updatable = false)
    @NotEmpty
    private Long createdBy=1L;

    @Column(name = "CreatedDate",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "ModifiedBy",insertable = false)
    private Long modifiedBy;

    @Column(name = "ModifiedDate",insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @Column(name = "TokenEstafet",length = 64)
    private String tokenEstafet;

    @ManyToOne
    @JoinColumn(name = "IDAkses",foreignKey = @ForeignKey(name = "fk-user-to-akses"))
    private Akses akses;

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

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getTokenEstafet() {
        return tokenEstafet;
    }

    public void setTokenEstafet(String tokenEstafet) {
        this.tokenEstafet = tokenEstafet;
    }

    public Akses getAkses() {
        return akses;
    }

    public void setAkses(Akses akses) {
        this.akses = akses;
    }
}