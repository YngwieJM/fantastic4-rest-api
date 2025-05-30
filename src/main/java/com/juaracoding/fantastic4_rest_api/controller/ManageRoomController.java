package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.dto.validation.ValFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValRuanganDTO;
import com.juaracoding.fantastic4_rest_api.service.FasilitasService;
import com.juaracoding.fantastic4_rest_api.service.RuanganService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

@RestController
@RequestMapping ("manage-room")
public class ManageRoomController {

    @Autowired
    private RuanganService ruanganService;

    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourcehandlerMapping;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValRuanganDTO valRuanganDTO,
                                       HttpServletRequest request){
        return ruanganService.save(ruanganService.mapToRuangan(valRuanganDTO), request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody ValRuanganDTO valRuanganDTO,
                                         @PathVariable String id,
                                         HttpServletRequest request){
        return ruanganService.update(id, ruanganService.mapToRuangan(valRuanganDTO), request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id,
                                         HttpServletRequest request) {
        return ruanganService.delete(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        return ruanganService.findAll(pageable, request);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id,
                                           HttpServletRequest request) {
        return ruanganService.findById(id, request);
    }

    @GetMapping("{sort}/{sort-by}/{page}")
    public ResponseEntity<Object> findByParam(
            @PathVariable String sort,
            @PathVariable(value = "sort-by") String sortBy,
            @PathVariable Integer page,
            @RequestParam Integer size,
            @RequestParam String column,
            @RequestParam String value,
            HttpServletRequest request) {
        Pageable pageable = null;
        sortBy = sortColumn(sortBy);
        switch (sort) {
            case "desc":
                pageable = PageRequest.of(page, size, Sort.by("id").descending());
                break;
            default:
                pageable = PageRequest.of(page, size, Sort.by("id"));
                break;
        }
        return ruanganService.findByParam(pageable, column, value, request);
    }

    private String sortColumn(String column) {
        switch (column) {
            case "namaRuangan":
                column = "namaruangan";
                break;
            case "minKapasitas":
                column = "minkapasitas";
                break;
            case "maxKapasitas":
                column = "maxkapasitas";
                break;
            case "lokasi":
                column = "lokasi";
                break;
            default:
                column = "id";
                break;
        }
        return column;
    }
}

