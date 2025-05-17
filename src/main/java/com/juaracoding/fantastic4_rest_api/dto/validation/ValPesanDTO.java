package com.juaracoding.fantastic4_rest_api.dto.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import com.juaracoding.fantastic4_rest_api.model.User;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class ValPesanDTO {


    @NotNull(message = "Relasi Tidak Boleh Kosong")
    @JsonProperty("user")
    private User userID;

    @NotNull(message = "Relasi Tidak Boleh Kosong")
    @JsonProperty("ruangan")
    private Ruangan ruanganID;

    @NotNull(message = "Tanggal tidak boleh kosong.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty("tanggal")
    private LocalDate tanggal; // Gunakan format ISO: yyyy-MM-dd

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9\\s]{3,100}$",
            message = "Nama pertemuan hanya boleh huruf, angka, spasi. Panjang 3–100 karakter.")
    private String namaPertemuan;

    @NotNull(message = "Waktu mulai tidak boleh kosong.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("mulai")
    private LocalDateTime mulai; // Format ISO: yyyy-MM-dd'T'HH:mm:ss

    @NotNull(message = "Waktu berakhir tidak boleh kosong.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("berakhir")
    private LocalDateTime berakhir;

    @NotNull
    @Pattern(regexp = "^(0\\.5|1(\\.0)?|1\\.5|2(\\.0)?|2\\.5|3(\\.0)?|3\\.5|4(\\.0)?)$",
            message = "Durasi hanya boleh antara 0.5 hingga 4.0 jam, dalam kelipatan 0.5 atau 1.")
    private String durasi;

    @NotNull
    @Pattern(regexp = "^(pending|approved|cancelled)$",
            message = "Status harus bernilai: pending, approved, atau cancelled.")
    private String status;

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Ruangan getRuanganID() {
        return ruanganID;
    }

    public void setRuanganID(Ruangan ruanganID) {
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