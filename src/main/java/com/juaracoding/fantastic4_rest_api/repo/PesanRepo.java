package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.Pesan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface PesanRepo extends  JpaRepository<Pesan, Long> {

    public Page<Pesan> findByNamaPertemuanContainsIgnoreCase(String nama, Pageable pageable);
    public Page<Pesan> findByStatusIgnoreCase(String status, Pageable pageable);

    public List<Pesan> findByTanggalPertemuan(LocalDate tanggal);
//    public List<Pesan> findByUserIDUserID(String userID);
//    public List<Pesan> findByRuanganIDRuanganID(String ruanganID);
}
