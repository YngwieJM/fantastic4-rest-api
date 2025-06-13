package com.juaracoding.fantastic4_rest_api.dto.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 {
 "username":"paul.321",
 "email":"paul321@gmail.com",
 "noTelp":"08121314151",
 "password":"Paul@1234",
 "nama-lengkap":"Paul Malau",
 "alamat":"Bogor Bogor Bogor Bogor Bogor ",
 "tanggal-lahir":"1995-12-12"
 }
 */
public class RegisDTO {

    @Pattern(regexp = "^([a-z0-9\\.]{8,16})$",
            message = "Format Huruf kecil ,numeric dan titik saja min 8 max 16 karakter, ex : paulch.1234")
    private String username;

    @Pattern(regexp = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?:(?![.])[a-zA-Z0-9._%+-]+(?:(?<!\\\\)[.][a-zA-Z0-9-]+)*?)@[a-zA-Z0-9.-]+(?:\\.[a-zA-Z]{2,50})+$",
            message = "Format tidak valid ex : user_name123@sub.domain.com")
    private String email;

    @Pattern(regexp = "^(62|\\+62|0)8[0-9]{9,13}$",
            message = "Format No HP Tidak Valid , min 9 max 13 setelah angka 8, contoh : (0/62/+62)81111111")
    @JsonProperty("no-telp")
    private String noTelp;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_#\\-$])[\\w].{8,15}$",
            message = "Format minimal 1 angka, 1 huruf kecil, 1 huruf besar, 1 spesial karakter (_ \"Underscore\", - \"Hyphen\", # \"Hash\", atau $ \"Dollar\" atau @ \"At\") setelah 4 kondisi min 9 max 16 alfanumerik, contoh : aB4$12345")
    private String password;

    @Pattern(regexp = "^[a-zA-Z\\s]{4,70}$",message = "Hanya Alfabet dan spasi Minimal 4 Maksimal 70")
    private String nama;

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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
}
