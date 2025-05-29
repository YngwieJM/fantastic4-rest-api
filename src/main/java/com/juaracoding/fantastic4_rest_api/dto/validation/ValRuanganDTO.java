package com.juaracoding.fantastic4_rest_api.dto.validation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ValRuanganDTO {

    @NotNull
    @Pattern(regexp = "^([A-Z0-9]{5,50})$",
            message = "Masukan ID ruangan, ex: R1001")
    private String id;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9\\s]{5,50})$",
            message = "Masukan nama ruangan, ex : Ruang Meeting 1")
    private String namaRuangan;

    @NotNull
    @Min(value = 1, message = "Minimal kapasitas 1")
    @Max(value = 9999, message = "Maksimal kapasitas 9999")
    private Short minKapasitas;


    @NotNull
    @Min(value = 1, message = "Minimal kapasitas 1")
    @Max(value = 9999, message = "Maksimal kapasitas 9999")
    private Short maxKapasitas;

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

    public Short getMinKapasitas() {
        return minKapasitas;
    }

    public void setMinKapasitas(Short minKapasitas) {
        this.minKapasitas = minKapasitas;
    }

    public Short getMaxKapasitas() {
        return maxKapasitas;
    }

    public void setMaxKapasitas(Short maxKapasitas) {
        this.maxKapasitas = maxKapasitas;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}