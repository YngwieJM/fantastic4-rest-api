package com.juaracoding.fantastic4_rest_api.dto.report;


public class RepMenuDTO {

    private Long id;

    private String nama;

    private String path;

    private String deskripsi;

//    private Long idGroupMenu;

    private String namaGroupMenu;

//    private String deskripsiGroupMenu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

//    public Long getIdGroupMenu() {
//        return idGroupMenu;
//    }

//    public void setIdGroupMenu(Long idGroupMenu) {
//        this.idGroupMenu = idGroupMenu;
//    }

    public String getNamaGroupMenu() {
        return namaGroupMenu;
    }

    public void setNamaGroupMenu(String namaGroupMenu) {
        this.namaGroupMenu = namaGroupMenu;
    }

//    public String getDeskripsiGroupMenu() {
//        return deskripsiGroupMenu;
//    }
//
//    public void setDeskripsiGroupMenu(String deskripsiGroupMenu) {
//        this.deskripsiGroupMenu = deskripsiGroupMenu;
//    }
}