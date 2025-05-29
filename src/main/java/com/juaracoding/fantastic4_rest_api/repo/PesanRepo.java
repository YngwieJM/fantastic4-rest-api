package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.Pesan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PesanRepo extends  JpaRepository<Pesan, Long> {

    @Query("SELECT p FROM Pesan p WHERE p.userID.id = ?1")
    Optional<Pesan> cariUser(String id);
    @Query("SELECT p FROM Pesan p WHERE p.userID.id = ?1")
    Page<Pesan> cariUser(String id, Pageable pageable);
    @Query("SELECT p FROM Pesan p WHERE p.ruanganID.id = ?1")
    Optional<Pesan> cariRuangan(String id);
    @Query("SELECT p FROM Pesan p WHERE p.ruanganID.id = ?1")
    Page<Pesan> cariRuangan(String id, Pageable pageable);


    public Page<Pesan> findByNamaPertemuanContainsIgnoreCase(String nama, Pageable pageable);
    public Page<Pesan> findByStatusIgnoreCase(String status, Pageable pageable);
    public Page<Pesan> findByTanggalPertemuan(LocalDate tanggal, Pageable pageable);

    public List<Pesan> findByTanggalPertemuan(LocalDate tanggal);
}
