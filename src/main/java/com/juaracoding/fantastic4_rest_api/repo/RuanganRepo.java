package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RuanganRepo extends JpaRepository<Ruangan, String> {

    public Page<Ruangan> findByNamaRuanganContainsIgnoreCase(String nama, Pageable pageable);
    public Page<Ruangan> findByMinKapasitas(Short minKapasitas, Pageable pageable); //min max kapasitas dipisah dulu Hesti
    public Page<Ruangan> findByMaxKapasitas(Short maxKapasitas, Pageable pageable);  //min max kapasitas dipisah dulu Hesti
    public Page<Ruangan> findByLokasiContainsIgnoreCase(String lokasi, Pageable pageable);

    public List<Ruangan> findByNamaRuanganContainsIgnoreCase(String nama);
    public List<Ruangan> findByMinKapasitas(Short minKapasitas);  //min max kapasitas dipisah dulu Hesti
    public List<Ruangan> findByMaxKapasitas(Short maxKapasitas);  //min max kapasitas dipisah dulu Hesti
    public List<Ruangan> findByLokasiContainsIgnoreCase(String lokasi);

    public Optional<Ruangan> findTop1ByOrderByIdDesc();
}