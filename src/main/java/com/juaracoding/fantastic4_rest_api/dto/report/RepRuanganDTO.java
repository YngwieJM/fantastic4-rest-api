package com.juaracoding.fantastic4_rest_api.dto.report;

public class RepRuanganDTO {

    private String id;
    private String namaRuangan;
    private Short minKapasitas;
    private Short maxKapasitas;
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
