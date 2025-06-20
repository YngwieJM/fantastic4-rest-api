package com.juaracoding.fantastic4_rest_api.dto.validation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ValRuanganDTO {

    @NotNull
    @Pattern(regexp = "^([A-Z0-9]{4,50})$",
            message = "Masukan ID ruangan, ex: R001")
    private String id;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9\\s]{5,50})$",
            message = "Masukan nama ruangan, ex : Ruang Meeting 1")
    private String namaRuangan;

    @NotNull
    @Pattern(regexp = "^([0-9]{1,4})$",
            message = "Hanya boleh memasukan angka max 4 digit")
    private String minKapasitas;


    @NotNull
    @Pattern(regexp = "^([0-9]{1,4})$",
            message = "Hanya boleh memasukan angka max 4 digit")
    private String maxKapasitas;


    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9\\s]{2,50})$",
            message = "Masukan lokasi, ex : Lantai 1")
    private String lokasi;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public String getMinKapasitas() {
        return minKapasitas;
    }

    public void setMinKapasitas(String minKapasitas) {
        this.minKapasitas = minKapasitas;
    }

    public String getMaxKapasitas() {
        return maxKapasitas;
    }

    public void setMaxKapasitas(String maxKapasitas) {
        this.maxKapasitas = maxKapasitas;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}