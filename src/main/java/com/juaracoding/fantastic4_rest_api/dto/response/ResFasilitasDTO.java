package com.juaracoding.fantastic4_rest_api.dto.response;

import com.juaracoding.fantastic4_rest_api.dto.rel.RelRuanganDTO;

public class ResFasilitasDTO {

    private String id;

    private String namaFasilitas;

    private Short jumlah;

    private RelRuanganDTO ruangan;

    public RelRuanganDTO getRuangan() {
        return ruangan;
    }

    public void setRuangan(RelRuanganDTO ruangan) {
        this.ruangan = ruangan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaFasilitas() {
        return namaFasilitas;
    }

    public void setNamaFasilitas(String namaFasilitas) {
        this.namaFasilitas = namaFasilitas;
    }

    public Short getJumlah() {
        return jumlah;
    }

    public void setJumlah(Short jumlah) {
        this.jumlah = jumlah;
    }
}
