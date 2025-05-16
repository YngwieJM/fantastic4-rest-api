package com.juaracoding.fantastic4_rest_api.dto.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ValAdminDTO {

    @NotNull
    @Pattern(regexp = "^([A-Z a-z 0-9]{5,20})$",
            message = "Masukan NIP yang benar ex: YNG1998")
    private String id;

    @NotNull
    @Pattern(regexp ="^[a-zA-Z\\s]{4,50}$",
            message = "Masukan Nama yang benar! ex: Yngwie Mantiri")
    private String nama;

    @NotNull
    @Pattern(regexp = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?:(?![.])[a-zA-Z0-9._%+-]+(?:(?<!\\\\)[.][a-zA-Z0-9-]+)*?)@[a-zA-Z0-9.-]+(?:\\.[a-zA-Z]{2,50})+$",
            message = "Format tidak valid ex : yngwiejms@fantastic4.com")
    private String email;

    @NotNull
    @Pattern(regexp = "^(\\+62)8[0-9]{9,13}$",
            message = "Format No HP Tidak Valid, contoh : +6281111111")
    private String noTelp;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_#\\-$])[\\w].{8,15}$",
            message = "Format minimal 1 angka, 1 huruf kecil, 1 huruf besar, 1 spesial karakter (_ \"Underscore\", - \"Hyphen\", # \"Hash\", atau $ \"Dollar\" atau @ \"At\") setelah 4 kondisi min 9 max 16 alfanumerik, contoh : aB4$12345")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
}
