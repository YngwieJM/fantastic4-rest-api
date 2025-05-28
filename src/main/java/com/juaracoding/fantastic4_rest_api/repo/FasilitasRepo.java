package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.Fasilitas;
import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FasilitasRepo extends JpaRepository<Fasilitas, String> {

    // Define any additional query methods if needed
    // For example:
     Page<Fasilitas> findByNamaFasilitasContainsIgnoreCase(String namaFasilitas, Pageable pageable);
     List<Fasilitas> findByNamaFasilitasContainsIgnoreCase(String namaFasilitas);
//     Page<Fasilitas> findByRuanganIDContainsIgnorCase(String ruanganID, Pageable pageable);
//     List<Fasilitas> findByRuanganIDContainsIgnorCase(Ruangan ruanganID);

}
