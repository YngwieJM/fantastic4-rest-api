package com.juaracoding.fantastic4_rest_api.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.juarcoding.pcmspringboot3.dto.rel.RelMenuDTO;

import java.util.List;

public class ResAksesDTO {

    private String nama;

    private String deskripsi;

    @JsonProperty("list-menu")
    private List<RelMenuDTO> listMenu;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public List<RelMenuDTO> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<RelMenuDTO> listMenu) {
        this.listMenu = listMenu;
    }
}
