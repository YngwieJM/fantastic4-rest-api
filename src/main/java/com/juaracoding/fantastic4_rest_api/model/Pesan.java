package com.juaracoding.fantastic4_rest_api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TxnPesan")
public class Pesan {
    @Id
    @Column(name = "PesanID", length = 50, nullable = false, unique = true)
    private String pesanID;

    @ManyToOne
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk-to-user"))
    private User userID;

    @ManyToOne
    @JoinColumn(name = "RuanganID", foreignKey = @ForeignKey(name = "fk-to-ruangan"))
    private Ruangan ruanganID;

    @Column(name = "Tanggal", nullable = false)
    private LocalDate tanggal;

    @Column(name = "NamaPertemuan", length = 100, nullable = false)
    private String namaPertemuan;

    @Column(name = "Mulai", nullable = false)
    private LocalDateTime mulai;

    @Column(name = "Berakhir", nullable = false)
    private LocalDateTime berakhir;

    @Column(name = "Durasi", precision = 18, scale = 1, nullable = false)
    private BigDecimal durasi;

    @Column(name = "Status", length = 50, nullable = false)
    private String status;

    public String getPesanID() {
        return pesanID;
    }

    public void setPesanID(String pesanID) {
        this.pesanID = pesanID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRuanganID() {
        return ruanganID;
    }

    public void setRuanganID(String ruanganID) {
        this.ruanganID = ruanganID;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaPertemuan() {
        return namaPertemuan;
    }

    public void setNamaPertemuan(String namaPertemuan) {
        this.namaPertemuan = namaPertemuan;
    }

    public LocalDateTime getMulai() {
        return mulai;
    }

    public void setMulai(LocalDateTime mulai) {
        this.mulai = mulai;
    }

    public LocalDateTime getBerakhir() {
        return berakhir;
    }

    public void setBerakhir(LocalDateTime berakhir) {
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
}
