package com.juaracoding.fantastic4_rest_api.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "MstFasilitas")
public class Fasilitas {

    @Id
    @Column(name = "FasilitasID", length = 50, nullable = false, unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "RuanganID", foreignKey = @ForeignKey(name = "fk-fasilitas-to-ruangan"))
    private Ruangan ruanganID;

    @Column(name = "NamaFasilitas", length = 50, nullable = false)
    private String namaFasilitas;

    @Column(name = "Jumlah", nullable = false)
    private Long jumlah;

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

    public Ruangan getRuanganID() {
        return ruanganID;
    }

    public void setRuanganID(Ruangan ruanganID) {
        this.ruanganID = ruanganID;
    }

    public String getNamaFasilitas() {
        return namaFasilitas;
    }

    public void setNamaFasilitas(String namaFasilitas) {
        this.namaFasilitas = namaFasilitas;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
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
