package com.juaracoding.fantastic4_rest_api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelAksesDTO;

public class ResUserDTO {

//    private String id;

    private String email;

    @JsonProperty("no-telp")
    private String noTelp;

//    private String password;

    private String nama;

    private String departemen;

    private String jabatan;

    private RelAksesDTO akses;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public RelAksesDTO getAkses() {
        return akses;
    }

    public void setAkses(RelAksesDTO akses) {
        this.akses = akses;
    }
}
