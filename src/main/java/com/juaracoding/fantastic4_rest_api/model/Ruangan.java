package com.juaracoding.fantastic4_rest_api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "MstRuangan")
public class Ruangan {

    @Id
    @Column(name = "RuanganID", length = 50, nullable = false, unique = true)
    private String id;

    @Column(name = "NamaRuangan", length = 50, nullable = false, unique = true)
    private String namaRuangan;

    @Column(name = "MinKapasitas", nullable = false)
    private Short minKapasitas;

    @Column(name = "MaxKapasitas", nullable = false)
    private Short maxKapasitas;

    @Column(name = "Lokasi", length = 50, nullable = false)
    private String lokasi;

    @Column(name = "CreatedBy",nullable = false,updatable = false)
    private Long createdBy=1L;

    @Column(name = "CreatedDate",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "ModifiedBy",insertable = false)
    private Long modifiedBy;

    @Column(name = "ModifiedDate",insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public Short getMinKapasitas() {
        return minKapasitas;
    }

    public void setMinKapasitas(Short minKapasitas) {
        this.minKapasitas = minKapasitas;
    }

    public Short getMaxKapasitas() {
        return maxKapasitas;
    }

    public void setMaxKapasitas(Short maxKapasitas) {
        this.maxKapasitas = maxKapasitas;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}