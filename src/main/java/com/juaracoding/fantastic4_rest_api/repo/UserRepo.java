package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    public Page<User> findByNamaContainsIgnoreCase(String nama, Pageable pageable);
    public Page<User> findByEmailContainIgnoreCase(String email, Pageable pageable);
    public Page<User> findByNoTelpContainIgnoreCase(String noTelp, Pageable pageable);
    public Page<User> findByDepartemenContainIgnoreCase(String departemen, Pageable pageable);
    public Page<User> findByJabatanContainIgnoreCase(String jabatan, Pageable pageable);

    public List<User> findByNamaContainsIgnoreCase(String nama);
    public List<User> findByEmailContainIgnoreCase(String email);
    public List<User> findByNoTelpContainIgnoreCase(String noTelp);
    public List<User> findByDepartemenContainIgnoreCase(String departemen);
    public List<User> findByJabatanContainIgnoreCase(String jabatan);

    public Optional<User> findTop1ByOrderByIdDesc();
}