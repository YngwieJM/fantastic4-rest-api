package com.juaracoding.fantastic4_rest_api.dto.report;

import com.juaracoding.fantastic4_rest_api.dto.rel.RelRuanganDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class RepPesanDTO {

    private Long id;

    private RelRuanganDTO namaRuangan;

    private String namaPertemuan;

    private LocalDate tanggalPertemuan;

    private LocalTime mulai;

    private LocalTime berakhir;

    private BigDecimal durasi;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RelRuanganDTO getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(RelRuanganDTO namaRuangan) {
        this.namaRuangan = namaRuangan;
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

    public LocalTime getMulai() {
        return mulai;
    }

    public void setMulai(LocalTime mulai) {
        this.mulai = mulai;
    }

    public LocalTime getBerakhir() {
        return berakhir;
    }

    public void setBerakhir(LocalTime berakhir) {
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
