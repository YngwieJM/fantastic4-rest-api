package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepo extends JpaRepository<Menu, Long> {

    public Page<Menu> findByNamaContainsIgnoreCase(String nama, Pageable pageable);
    public Page<Menu> findByPathContainsIgnoreCase(String nama, Pageable pageable);
    public Page<Menu> findByDeskripsiContainsIgnoreCase(String nama, Pageable pageable);

    public List<Menu> findByNamaContainsIgnoreCase(String nama);
    public List<Menu> findByPathContainsIgnoreCase(String nama);
    public List<Menu> findByDeskripsiContainsIgnoreCase(String nama);
}

