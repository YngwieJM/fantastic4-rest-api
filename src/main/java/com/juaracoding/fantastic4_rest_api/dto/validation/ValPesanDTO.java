package com.juaracoding.fantastic4_rest_api.dto.validation;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

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
    private String tanggal; // Gunakan format ISO: yyyy-MM-dd

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9\\s]{3,100}$",
            message = "Nama pertemuan hanya boleh huruf, angka, spasi. Panjang 3–100 karakter.")
    private String namaPertemuan;

    @NotNull(message = "Waktu mulai tidak boleh kosong.")
    private String mulai; // Format ISO: yyyy-MM-dd'T'HH:mm:ss

    @NotNull(message = "Waktu berakhir tidak boleh kosong.")
    private String berakhir;

    @NotNull
    @DecimalMin(value = "0.5", inclusive = true, message = "Durasi minimal 0.5 jam.")
    private BigDecimal durasi;

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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaPertemuan() {
        return namaPertemuan;
    }

    public void setNamaPertemuan(String namaPertemuan) {
        this.namaPertemuan = namaPertemuan;
    }

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai;
    }

    public String getBerakhir() {
        return berakhir;
    }

    public void setBerakhir(String berakhir) {
        this.berakhir = berakhir;
    }

    public BigDecimal getDurasi() {
        return durasi;
    }

    public void setDurasi(BigDecimal durasi) {
        this.durasi = durasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
