package com.juaracoding.fantastic4_rest_api.dto.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelRuanganDTO;
import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ValFasilitasDTO {

    @NotNull
    @Pattern(regexp = "^([A-Za-z0-9]{2,20})$",
            message = "Masukan ID fasilitas yang benar ex: LCD1")
    private String id;

    @NotNull(message = "Relasi Tidak Boleh Kosong")
    @JsonProperty("ruangan")
    private RelRuanganDTO ruanganID;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s]{3,50}$",message = "Nama Tidak Valid hanya Alfabet dan spasi Max 50 , ex : LCD, Meja Bundar")
    private String namaFasilitas;

    @NotNull
    @Pattern(regexp = "^([0-9]{1,4})$",
            message = "Hanya boleh memasukan angka max 4 digit")
    private String jumlah;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RelRuanganDTO getRuanganID() {
        return ruanganID;
    }

    public void setRuanganID(RelRuanganDTO ruanganID) {
        this.ruanganID = ruanganID;
    }

    public String getNamaFasilitas() {
        return namaFasilitas;
    }

    public void setNamaFasilitas(String namaFasilitas) {
        this.namaFasilitas = namaFasilitas;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
