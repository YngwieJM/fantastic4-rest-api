package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.Fasilitas;
import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FasilitasRepo extends JpaRepository<Fasilitas, String> {

    // Define any additional query methods if needed
    // For example:
    @Query("SELECT p FROM Fasilitas p WHERE p.ruanganID.id = ?1")
    Optional<Fasilitas> cariRuangan(String id);

    @Query("SELECT p FROM Fasilitas p WHERE p.ruanganID.id = ?1")
    Page<Fasilitas> cariRuangan(String id, Pageable pageable);

     Page<Fasilitas> findByNamaFasilitasContainsIgnoreCase(String namaFasilitas, Pageable pageable);
     List<Fasilitas> findByNamaFasilitasContainsIgnoreCase(String namaFasilitas);

    Optional<Fasilitas> findTop1ByOrderByIdDesc();
//     Page<Fasilitas> findByRuanganID(Map<String, Object> ruanganID, Pageable pageable);
//     List<Fasilitas> findByRuanganID(Map<String, Object> ruanganID);

}
