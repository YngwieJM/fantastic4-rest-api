package com.juaracoding.fantastic4_rest_api.repo;

import com.juaracoding.fantastic4_rest_api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByNoTelp(String noTelp);
    Optional<User> findByUsernameOrEmailOrNoTelp(String username, String email, String noTelp);
    Optional<User> findByUsernameAndIsRegistered(String username, Boolean valid);
    @Query("SELECT p FROM User p WHERE p.akses.id = ?1")
    Optional<User> cariAkses(String id);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmailOrNoTelpAndIsRegistered(String username, String email, String noTelp, Boolean valid);

    public Page<User> findByNamaContainsIgnoreCase(String nama, Pageable pageable);
    public Page<User> findByEmailContainsIgnoreCase(String email, Pageable pageable);
    public Page<User> findByNoTelpContainsIgnoreCase(String noTelp, Pageable pageable);
    public Page<User> findByDepartemenContainsIgnoreCase(String departemen, Pageable pageable);
    public Page<User> findByJabatanContainsIgnoreCase(String jabatan, Pageable pageable);

//    public List<User> findByUsername(String username);
    public List<User> findByNamaContainsIgnoreCase(String nama);
    public List<User> findByEmailContainsIgnoreCase(String email);
    public List<User> findByNoTelpContainsIgnoreCase(String noTelp);
    public List<User> findByDepartemenContainsIgnoreCase(String departemen);
    public List<User> findByJabatanContainsIgnoreCase(String jabatan);


    Optional<User> findTop1ByOrderByIdDesc();
}