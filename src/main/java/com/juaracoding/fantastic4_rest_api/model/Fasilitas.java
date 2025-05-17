package com.juaracoding.fantastic4_rest_api.model;


import jakarta.persistence.*;

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
}
