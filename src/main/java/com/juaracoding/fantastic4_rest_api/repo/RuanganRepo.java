package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RuanganRepo extends JpaRepository<Ruangan, String> {

    public Page<Ruangan> findByNamaRuanganContainsIgnoreCase(String namaRuangan, Pageable pageable);
    public Page<Ruangan> findByMinKapasitasAndMaxKapasitas(Short minKapasitas,Short maxKapasitas, Pageable pageable);
    public Page<Ruangan> findByLokasiContainsIgnoreCase(String lokasi, Pageable pageable);
    public Page<Ruangan> findByMinKapasitas(Short minKapasitas, Pageable pageable);
    public Page<Ruangan> findByMaxKapasitas(Short maxKapasitas, Pageable pageable);

    public List<Ruangan> findByNamaRuanganContainsIgnoreCase(String namaRuangan);
    public List<Ruangan> findByMinKapasitasAndMaxKapasitas(Short minKapasitas,Short maxKapasitas);
    public List<Ruangan> findByLokasiContainsIgnoreCase(String lokasi);
    public List<Ruangan> findByMinKapasitas(Short minKapasitas);
    public List<Ruangan> findByMaxKapasitas(Short maxKapasitas);
    @Query("SELECT r FROM Ruangan r WHERE r.id = ?1")
    List<Ruangan> findByIdRuangan(String id);

    // In RuanganRepo.java
    @Query(value = "SELECT * FROM corez.MstRuangan r WHERE r.MinKapasitas <= :kapasitas AND r.MaxKapasitas >= :kapasitas " +
            "AND NOT EXISTS (SELECT 1 FROM corez.TxnPesan p WHERE p.RuanganID = r.RuanganID " +
            "AND (:mulai < p.Berakhir AND :berakhir > p.Mulai))", nativeQuery = true)
    List<Ruangan> findAvailableRooms(@Param("kapasitas") Short kapasitas,
                                     @Param("mulai") Time mulai,
                                     @Param("berakhir") Time berakhir);
    public Optional<Ruangan> findTop1ByOrderByIdDesc();
}