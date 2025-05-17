package com.juaracoding.fantastic4_rest_api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TxnPesan")
public class Pesan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PesanID")
    private Long pesanID;

    @ManyToOne
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk-pesan-to-user"))
    private User userID;

    @ManyToOne
    @JoinColumn(name = "RuanganID", foreignKey = @ForeignKey(name = "fk-pesan-to-ruangan"))
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

    public Long getPesanID() {
        return pesanID;
    }

    public void setPesanID(Long pesanID) {
        this.pesanID = pesanID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Ruangan getRuanganID() {
        return ruanganID;
    }

    public void setRuanganID(Ruangan ruanganID) {
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