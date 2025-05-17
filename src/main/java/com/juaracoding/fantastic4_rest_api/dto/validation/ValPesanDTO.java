package com.juaracoding.fantastic4_rest_api.dto.validation;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ValPesanDTO {
    @NotNull
    @Pattern(regexp = "^([A-Z0-9]{5,50})$",
            message = "UserID harus terdiri dari huruf kapital dan angka, panjang 5–50 karakter.")
    private String userID;

    @NotNull
    @Pattern(regexp = "^([A-Z0-9]{5,50})$",
            message = "RuanganID harus terdiri dari huruf kapital dan angka, panjang 5–50 karakter.")
    private String ruanganID;

    @NotNull(message = "Tanggal tidak boleh kosong.")
    private LocalDate tanggal; // Gunakan format ISO: yyyy-MM-dd

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9\\s]{3,100}$",
            message = "Nama pertemuan hanya boleh huruf, angka, spasi. Panjang 3–100 karakter.")
    private String namaPertemuan;

    @NotNull(message = "Waktu mulai tidak boleh kosong.")
    private LocalDateTime mulai; // Format ISO: yyyy-MM-dd'T'HH:mm:ss

    @NotNull(message = "Waktu berakhir tidak boleh kosong.")
    private LocalDateTime berakhir;

    @NotNull
    @Pattern(regexp = "^(0\\\\.5|1(\\\\.0)?|1\\\\.5|2(\\\\.0)?|2\\\\.5|3(\\\\.0)?|3\\\\.5|4(\\\\.0)?)$",
            message = "Durasi hanya boleh antara 0.5 hingga 4.0 jam, dalam kelipatan 0.5 atau 1.")
    private String durasi;

    @NotNull
    @Pattern(regexp = "^(pending|approved|cancelled)$",
            message = "Status harus bernilai: pending, approved, atau cancelled.")
    private String status;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRuanganID() {
        return ruanganID;
    }

    public void setRuanganID(String ruanganID) {
        this.ruanganID = ruanganID;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaPertemuan() {
        return namaPertemuan;
    }

    public void setNamaPertemuan(String namaPertemuan) {
        this.namaPertemuan = namaPertemuan;
    }

    public LocalDateTime getMulai() {
        return mulai;
    }

    public void setMulai(LocalDateTime mulai) {
        this.mulai = mulai;
    }

    public LocalDateTime getBerakhir() {
        return berakhir;
    }

    public void setBerakhir(LocalDateTime berakhir) {
        this.berakhir = berakhir;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
