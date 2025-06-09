package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValPesanDTO;
import com.juaracoding.fantastic4_rest_api.model.Pesan;
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

@RestController
@RequestMapping("pesan")
public class PesanController {

    @Autowired
    private PesanService pesanService;

    @PostMapping
    @PreAuthorize("hasAuthority('Pesan')")
    public ResponseEntity<Object> save(@Valid @RequestBody ValPesanDTO valPesanDTO,
                                       HttpServletRequest request){
        return pesanService.save(pesanService.mapToPesan(valPesanDTO), request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Pesan')")
    public ResponseEntity<Object> update(@Valid @RequestBody ValPesanDTO valPesanDTO,
                                         @PathVariable String id,
                                         HttpServletRequest request){
        return pesanService.update(id, pesanService.mapToPesan(valPesanDTO), request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Pesan')")
    public ResponseEntity<Object> delete(@PathVariable String id,
                                         HttpServletRequest request){
        return pesanService.delete(id, request);
    }

    /** defaultSearch
     * Ketika menu dibuka pertama kali, api yang di hit adalah api ini ....
     */
    @GetMapping
    @PreAuthorize("hasAuthority('Pesan')")
    public ResponseEntity<Object> findAll(HttpServletRequest request){
        Pageable pageable = PageRequest.of(0, OtherConfig.getDefaultPaginationSize(), Sort.by("id"));
        return pesanService.findAll(pageable, request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Pesan')")
    public ResponseEntity<Object> findById(
            @PathVariable String id,
            HttpServletRequest request) {
        return pesanService.findById(id, request);
    }

    @GetMapping("{sort}/{sort-by}/{page}")
    @PreAuthorize("hasAuthority('Pesan')")
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
