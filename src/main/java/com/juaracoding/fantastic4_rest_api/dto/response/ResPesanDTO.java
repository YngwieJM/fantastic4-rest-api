package com.juaracoding.fantastic4_rest_api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelRuanganDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ResPesanDTO {

    private Long id;

    @JsonProperty("ruangan")
    private RelRuanganDTO ruangan;

    @JsonProperty("tanggal-pemesanan")
    private LocalDate tanggalPemesanan;

    @JsonProperty("nama-pertemuan")
    private String namaPertemuan;

    @JsonProperty("tanggal-pertemuan")
    private LocalDate tanggalPertemuan;

    private String mulai;

    private String berakhir;

    private BigDecimal durasi;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RelRuanganDTO getRuangan() {
        return ruangan;
    }

    public void setRuangan(RelRuanganDTO ruangan) {
        this.ruangan = ruangan;
    }

    public LocalDate getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public void setTanggalPemesanan(LocalDate tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }

    public String getNamaPertemuan() {
        return namaPertemuan;
    }

    public void setNamaPertemuan(String namaPertemuan) {
        this.namaPertemuan = namaPertemuan;
    }

    public LocalDate getTanggalPertemuan() {
        return tanggalPertemuan;
    }

    public void setTanggalPertemuan(LocalDate tanggalPertemuan) {
        this.tanggalPertemuan = tanggalPertemuan;
    }

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai;
    }

    public String getBerakhir() {
        return berakhir;
    }

    public void setBerakhir(String berakhir) {
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
