package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RuanganRepo extends JpaRepository<Ruangan, String> {

    public Page<Ruangan> findByNamaRuanganContainsIgnoreCase(String nama, Pageable pageable);
    public Page<Ruangan> findByMinKapasitasAndMaxKapasitas(Short minKapasitas,Short maxKapasitas, Pageable pageable);
    public Page<Ruangan> findByLokasiContainsIgnoreCase(String lokasi, Pageable pageable);

    public List<Ruangan> findByNamaRuanganContainsIgnoreCase(String nama);
    public List<Ruangan> findByMinKapasitasAndMaxKapasitas(Short minKapasitas,Short maxKapasitas);
    public List<Ruangan> findByLokasiContainsIgnoreCase(String lokasi);

    public Optional<Ruangan> findTop1ByOrderByIdDesc();
}