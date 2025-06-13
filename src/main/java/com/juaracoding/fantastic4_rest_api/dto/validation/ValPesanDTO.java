package com.juaracoding.fantastic4_rest_api.dto.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelRuanganDTO;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelUserDTO;
import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import com.juaracoding.fantastic4_rest_api.model.User;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ValPesanDTO {


    @NotNull
    @Min(value = 1, message = "ID must be at least 1")
    @Max(value = 99999, message = "ID must be at most 99999")
    private Long id;

//    @NotNull(message = "Relasi Tidak Boleh Kosong")
    @JsonProperty("user")
    private RelUserDTO user;

    @NotNull(message = "Relasi Tidak Boleh Kosong")
    @JsonProperty("ruangan")
    private RelRuanganDTO ruangan;

    @NotNull(message = "Tanggal tidak boleh kosong.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty("tanggal-pemesanan")
    private LocalDate tanggalPemesanan; // Gunakan format ISO: yyyy-MM-dd


    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9\\s]{3,100}$",
            message = "Nama pertemuan hanya boleh huruf, angka, spasi. Panjang 3–100 karakter.")
    private String namaPertemuan;

    @NotNull(message = "Tanggal tidak boleh kosong.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty("tanggal-pertemuan")
    private LocalDate tanggalPertemuan; // Gunakan format ISO: yyyy-MM-dd

    @NotNull(message = "Waktu mulai tidak boleh kosong.")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Format waktu harus HH:mm:ss (contoh: 09:00:00)")
    @JsonProperty("mulai")
    private String mulai;

    @NotNull(message = "Waktu berakhir tidak boleh kosong.")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Format waktu harus HH:mm:ss (contoh: 10:00:00)")
    @JsonProperty("berakhir")
    private String berakhir;

    @NotNull
    @Pattern(regexp = "^(0\\.5|1(\\.0)?|1\\.5|2(\\.0)?|2\\.5|3(\\.0)?|3\\.5|4(\\.0)?)$",
            message = "Durasi hanya boleh antara 0.5 hingga 4.0 jam, dalam kelipatan 0.5 atau 1.")
    private String durasi;

//    @NotNull
    @Pattern(regexp = "^(pending|approved|cancelled)$",
            message = "Status harus bernilai: pending, approved, atau cancelled.")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RelUserDTO getUser() {
        return user;
    }

    public void setUser(RelUserDTO user) {
        this.user = user;
    }

    public RelRuanganDTO getRuangan() {
        return ruangan;
    }

    public void setRuangan(RelRuanganDTO ruangan) {
        this.ruangan = ruangan;
    }

    public LocalDate getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public void setTanggalPemesanan(LocalDate tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }

    public String getNamaPertemuan() {
        return namaPertemuan;
    }

    public void setNamaPertemuan(String namaPertemuan) {
        this.namaPertemuan = namaPertemuan;
    }

    public LocalDate getTanggalPertemuan() {
        return tanggalPertemuan;
    }

    public void setTanggalPertemuan(LocalDate tanggalPertemuan) {
        this.tanggalPertemuan = tanggalPertemuan;
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