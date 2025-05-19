package com.juaracoding.fantastic4_rest_api.dto.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ValRuanganDTO {

    @NotNull
    @Pattern(regexp = "^([A-Z0-9]{5,50})$",
            message = "Masukan ID ruangan, ex: R1001")
    private String ruanganID;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9\\s]{5,50})$",
            message = "Masukan nama ruangan, ex : Ruang Meeting 1")
    private String namaRuangan;

    @NotNull
    @Pattern(regexp = "^([1-9][0-9]{1,3})$",
            message = "Hanya boleh angka tidak diawali angka 0")
    private Long minKapasitas;

    @NotNull
    @Pattern(regexp = "^([1-9][0-9]{1,3})$",
            message = "Hanya boleh angka tidak diawali angka 0")
    private Long maxKapasitas;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9\\s]{2,50})$",
            message = "Masukan lokasi, ex : Lantai 1")
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