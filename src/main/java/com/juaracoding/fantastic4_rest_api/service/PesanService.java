package com.juaracoding.fantastic4_rest_api.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.repo.PesanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PesanService {
    private final PesanRepo pesanRepo;

    @Autowired
    public PesanService(PesanRepo pesanRepo) {
        this.pesanRepo = pesanRepo;
    }

    public Pesan save(Pesan pesan) {
        return pesanRepo.save(pesan);
    }

    public List<Pesan> findAll() {
        return pesanRepo.findAll();
    }

    public Optional<Pesan> findById(Long id) {
        return pesanRepo.findById(id);
    }

    public void deleteById(Long id) {
        pesanRepo.deleteById(id);
    }

    public Page<Pesan> findByNamaPertemuan(String nama, Pageable pageable) {
        return pesanRepo.findByNamaPertemuanContainsIgnoreCase(nama, pageable);
    }

    public Page<Pesan> findByStatus(String status, Pageable pageable) {
        return pesanRepo.findByStatusIgnoreCase(status, pageable);
    }

    public List<Pesan> findByTanggal(LocalDate tanggal) {
        return pesanRepo.findByTanggal(tanggal);
    }

    public List<Pesan> findByUser(Long userId) {
        return pesanRepo.findByUserIDUserID(userId);
    }

    public List<Pesan> findByRuangan(Long ruanganId) {
        return pesanRepo.findByRuanganIDRuanganID(ruanganId);
    }
}
