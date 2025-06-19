package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PesanRepo extends  JpaRepository<Pesan, Long> {

    @Query("SELECT p FROM Pesan p WHERE p.user.id = ?1")
    Optional<Pesan> cariUser(String id);
    @Query("SELECT p FROM Pesan p WHERE p.user.id = ?1")
    Page<Pesan> cariUser(String id, Pageable pageable);
    @Query("SELECT p FROM Pesan p WHERE p.ruangan.id = ?1")
    Optional<Pesan> cariRuangan(String id);
    @Query("SELECT p FROM Pesan p WHERE p.ruangan.id = ?1")
    Page<Pesan> cariRuangan(String id, Pageable pageable);


    public Page<Pesan> findByNamaPertemuanContainsIgnoreCase(String nama, Pageable pageable);
    public Page<Pesan> findByStatusIgnoreCase(String status, Pageable pageable);
    public Page<Pesan> findByTanggalPertemuan(LocalDate tanggal, Pageable pageable);

    public List<Pesan> findByTanggalPertemuan(LocalDate tanggal);
    public List<Pesan> findByNamaPertemuanContainsIgnoreCase(String nama);
    public List<Pesan> findByStatusIgnoreCase(String status);
    @Query("SELECT p FROM Pesan p WHERE p.ruangan.id = ?1")
    public List<Pesan> findByRuanganId(String id);
    @Query("SELECT p FROM Pesan p WHERE p.user.id = ?1")
    public List<Pesan> findByUserId(String id);

    // Example: Find by room, date, and time
    @Query("SELECT p FROM Pesan p WHERE " +
            "(:room IS NULL OR p.ruangan.namaRuangan = :room) AND " +
            "(:date IS NULL OR p.tanggalPertemuan = :date) AND " +
            "(:time IS NULL OR :time BETWEEN p.mulai AND p.berakhir)")
    List<Pesan> search(@Param("room") String room,
                       @Param("date") LocalDate date,
                       @Param("time") Time time);

    List<Pesan> findByRuangan(Ruangan ruangan);

    Optional<Pesan> findTop1ByOrderByIdDesc();
    boolean existsByRuanganAndTanggalPertemuanAndMulaiAndBerakhir(
            Ruangan ruangan, LocalDate tanggalPertemuan, Time mulai, Time berakhir);
}
