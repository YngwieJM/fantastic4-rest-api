package com.juaracoding.fantastic4_rest_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MstRuangan")
public class Ruangan {

    @Id
    @Column(name = "RuanganID", length = 50, nullable = false, unique = true)
    private String ruanganID;

    @Column(name = "NamaRuangan", length = 50, nullable = false, unique = true)
    private String namaRuangan;

    @Column(name = "MinKapasitas", nullable = false)
    private Long minKapasitas;

    @Column(name = "MaxKapasitas", nullable = false)
    private Long maxKapasitas;

    @Column(name = "Lokasi", length = 50, nullable = false, unique = true)
    private String lokasi;

    public String getRuanganID() {
        return ruanganID;
    }

    public void setRuanganID(String ruanganID) {
        this.ruanganID = ruanganID;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public Long getMinKapasitas() {
        return minKapasitas;
    }

    public void setMinKapasitas(Long minKapasitas) {
        this.minKapasitas = minKapasitas;
    }

    public Long getMaxKapasitas() {
        return maxKapasitas;
    }

    public void setMaxKapasitas(Long maxKapasitas) {
        this.maxKapasitas = maxKapasitas;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}