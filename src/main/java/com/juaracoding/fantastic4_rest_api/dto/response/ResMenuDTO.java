package com.juaracoding.fantastic4_rest_api.dto.response;


public class ResMenuDTO {

    private Long id;

    private String nama;

    private String path;

    private String deskripsi;

//    private ResGroupMenuDTO groupMenu;

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

//    public ResGroupMenuDTO getGroupMenu() {
//        return groupMenu;
//    }
//
//    public void setGroupMenu(ResGroupMenuDTO groupMenu) {
//        this.groupMenu = groupMenu;
//    }
}