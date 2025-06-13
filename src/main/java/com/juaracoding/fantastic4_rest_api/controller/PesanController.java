package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValPesanDTO;
import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.model.User;
import com.juaracoding.fantastic4_rest_api.repo.UserRepo;
import com.juaracoding.fantastic4_rest_api.service.PesanService;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("pesan")
public class PesanController {

    @Autowired
    private PesanService pesanService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    @PreAuthorize("hasAuthority('Booking Room')")
    public ResponseEntity<Object> save(@Valid @RequestBody ValPesanDTO valPesanDTO,
                                       HttpServletRequest request,
                                       Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Pesan pesan = pesanService.mapToPesan(valPesanDTO);
        pesan.setUser(user); // Set the logged-in user
        return pesanService.save(pesan, request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Booking Room')")
    public ResponseEntity<Object> update(@Valid @RequestBody ValPesanDTO valPesanDTO,
                                         @PathVariable String id,
                                         HttpServletRequest request){
        return pesanService.update(id, pesanService.mapToPesan(valPesanDTO), request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Booking Room')")
    public ResponseEntity<Object> delete(@PathVariable String id,
                                         HttpServletRequest request){
        return pesanService.delete(id, request);
    }

    /** defaultSearch
     * Ketika menu dibuka pertama kali, api yang di hit adalah api ini ....
     */
    @GetMapping
    @PreAuthorize("hasAuthority('Booking Room')")
    public ResponseEntity<Object> findAll(HttpServletRequest request){
        Pageable pageable = PageRequest.of(0, OtherConfig.getDefaultPaginationSize(), Sort.by("id"));
        return pesanService.findAll(pageable, request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Booking Room')")
    public ResponseEntity<Object> findById(
            @PathVariable String id,
            HttpServletRequest request) {
        return pesanService.findById(id, request);
    }

    @GetMapping("{sort}/{sort-by}/{page}")
    @PreAuthorize("hasAuthority('Booking Room')")
    public ResponseEntity<Object> findByParam(
            @PathVariable String sort,
            @PathVariable(value = "sort-by") String sortBy,
            @PathVariable Integer page,
            @RequestParam Integer size,
            @RequestParam String column,
            @RequestParam String value,
            HttpServletRequest request){
        Pageable pageable = null;
        sortBy = sortColumn(sortBy);
        switch (sort){
            case "desc":pageable = PageRequest.of(page, size, Sort.by("id").descending());break;
            default:pageable = PageRequest.of(page, size, Sort.by("id"));break;
        }
        return pesanService.findByParam(pageable, column, value, request);
    }

    private String sortColumn(String column){
        switch (column){
            case "namaPertemuan":column="namaPertemuan";break;
            case "ruanganID":column="nuanganID";break;
            default:column="id";break;
        }
        return column;
    }
}
