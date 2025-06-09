package com.juaracoding.fantastic4_rest_api.dto.validation;

import com.juaracoding.fantastic4_rest_api.dto.rel.RelAksesDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ValUserDTO {
    /*UserID dari user, wajib diisi, kombinasi huruf/angka 5-50 karakter*/

    @NotNull
    @Pattern(regexp = "^([A-Z a-z 0-9]{5,50})$",
            message = "User ID tidak boleh kosong. Contoh yang benar : LWF1997")
    private String id;

    @Pattern(regexp = "^([a-z0-9\\.]{8,16})$",
            message = "Format Huruf kecil ,numeric dan titik saja min 8 max 16 karakter, ex : paulch.1234")
    private String username;

    /*Nama lengkap user, hanya huruf dan spasi, 4-50 karakter*/
    @NotNull
    @Pattern(regexp ="^[a-zA-Z\\s]{4,50}$",
            message = "Nama tidak boleh kosong. Contoh yang benar: Luthfan Wafi Fadhliyan")
    private String nama;

    /*Email user, wajib format email valid*/
    @NotNull
    @Pattern(regexp = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?:(?![.])[a-zA-Z0-9._%+-]+(?:(?<!\\\\)[.][a-zA-Z0-9-]+)*?)@[a-zA-Z0-9.-]+(?:\\.[a-zA-Z]{2,50})+$",
            message = "Email tidak boleh kosong. Contoh yang benar: lwfjms@fantastic4.com")
    private String email;

    /*Nomor telepon user, format harus +628xxxxxxxxxx*/
    @NotNull
    @Pattern(
            regexp = "^\\+628[1-9][0-9]{7,12}$",
            message = "Nomor telepon harus diawali +628 dan diikuti 8-13 digit. Contoh: +6281234567890"
    )
    private String noTelp;

    /*Password user, wajib memenuhi syarat kombinasi karakter*/
//    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_#\\-$])[\\w@#\\-\\$]{9,16}$",
            message = "Password harus terdiri dari 9-16 karakter dan minimal mengandung 1 huruf besar, 1 huruf kecil, 1 angka, dan 1 spesial karakter (_ - # $ @). Contoh: aB4$12345")
    private String password;

    /*Departemen user, wajib diisi, min 3 max 100 karakter*/
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Departemen hanya boleh berisi huruf, angka, dan spasi. Contoh: Information Technology, IT Support")
    /*@Size(min = 3, max = 100, message = "Departemen harus terdiri dari 3-100 karakter. Contoh: Information Technology")*/
    private String departemen;

    /*Jabatan user, wajib diisi, min 3 max 100 karakter*/
    @NotNull
    /*@Size(min = 3, max = 100, message = "Jabatan harus terdiri dari 3-100 karakter. Contoh: Manager")*/
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Jabatan hanya boleh berisi huruf, angka, dan spasi. Contoh: Manager, Supervisor IT, Staff 1")
    private String jabatan;

    @NotNull(message = "Akses Wajib Dipilih")
    private RelAksesDTO akses;

    /*Getter dan Setter UserID*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*Getter dan Setter Nama*/
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    /*Getter dan Setter Email*/
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /*Getter dan Setter noTelp*/
    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    /*Getter dan Setter Password*/
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /*Getter dan Setter Departemen*/
    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }
    /*Getter dan Setter Jabatan*/
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
