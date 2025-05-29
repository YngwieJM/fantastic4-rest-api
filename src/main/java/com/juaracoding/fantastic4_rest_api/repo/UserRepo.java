package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByNoTelp(String noTelp);
    Optional<User> findByidOrEmailOrNoTelp(String id, String email, String noTelp);

    public Page<User> findByNamaContainsIgnoreCase(String nama, Pageable pageable);
    public Page<User> findByEmailContainsIgnoreCase(String email, Pageable pageable);
    public Page<User> findByNoTelpContainsIgnoreCase(String noTelp, Pageable pageable);
    public Page<User> findByDepartemenContainsIgnoreCase(String departemen, Pageable pageable);
    public Page<User> findByJabatanContainsIgnoreCase(String jabatan, Pageable pageable);

    public List<User> findByNamaContainsIgnoreCase(String nama);
    public List<User> findByEmailContainsIgnoreCase(String email);
    public List<User> findByNoTelpContainsIgnoreCase(String noTelp);
    public List<User> findByDepartemenContainsIgnoreCase(String departemen);
    public List<User> findByJabatanContainsIgnoreCase(String jabatan);


}