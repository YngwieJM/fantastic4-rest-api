package com.juaracoding.fantastic4_rest_api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TxnPesan")
public class Pesan {
    @Id
    @Column(name = "PesanID", length = 50, nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk-pesan-to-user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "RuanganID", foreignKey = @ForeignKey(name = "fk-pesan-to-ruangan"))
    private Ruangan ruangan;

    @Column(name = "TanggalPemesanan", nullable = false, updatable = false)
    private LocalDate tanggalPemesanan;

    @Column(name = "NamaPertemuan", length = 100, nullable = false)
    private String namaPertemuan;

    @Column(name = "TanggalPertemuan", nullable = false)
    private LocalDate tanggalPertemuan;

    @Column(name = "Mulai", nullable = false)
    private Time mulai;

//    private Time mulaiX;

    @Column(name = "Berakhir", nullable = false)
    private Time berakhir;

    @Column(name = "Durasi", precision = 18, scale = 1, nullable = false)
    private BigDecimal durasi;

    @Column(name = "Status", length = 50, nullable = false)
    private String status;

    @Column(name = "CreatedBy",nullable = false,updatable = false)
    private Long createdBy=1L;

    @Column(name = "ModifiedBy",insertable = false)
    private Long modifiedBy;

    @Column(name = "ModifiedDate",insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;


    public Long getPesanID() {
        return id;
    }

    public void setPesanID(Long pesanID) {
        this.id = pesanID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ruangan getRuangan() {
        return ruangan;
    }

    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }

    public LocalDate getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public void setTanggalPemesanan(LocalDate tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }

    public LocalDate getTanggalPertemuan() {
        return tanggalPertemuan;
    }

    public void setTanggalPertemuan(LocalDate tanggalPertemuan) {
        this.tanggalPertemuan = tanggalPertemuan;
    }

    public String getNamaPertemuan() {
        return namaPertemuan;
    }

    public void setNamaPertemuan(String namaPertemuan) {
        this.namaPertemuan = namaPertemuan;
    }

    public Time getMulai() {
        return mulai;
    }

    public void setMulai(Time mulai) {
        this.mulai = mulai;
    }

    public Time getBerakhir() {
        return berakhir;
    }

    public void setBerakhir(Time berakhir) {
        this.berakhir = berakhir;
    }

    public BigDecimal getDurasi() {
        return durasi;
    }

    public void setDurasi(BigDecimal durasi) {
        this.durasi = durasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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
}