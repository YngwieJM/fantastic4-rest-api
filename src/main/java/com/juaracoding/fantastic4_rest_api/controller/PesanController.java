package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.dto.validation.ValPesanDTO;
import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.service.PesanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("pesan")
public class PesanController {

    @Autowired
    private PesanService pesanService;

    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourceHandlerMapping;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@Valid @RequestBody ValPesanDTO valPesanDTO,
                                       HttpServletRequest request) {
        return pesanService.save(pesanService.mapToPesan(valPesanDTO), request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody ValPesanDTO valPesanDTO,
                                         @PathVariable String id,
                                         HttpServletRequest request) {
        return pesanService.update(id, pesanService.mapToPesan(valPesanDTO), request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id,
                                         HttpServletRequest request) {
        return pesanService.delete(id, request);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        return pesanService.findAll(pageable, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id,
                                           HttpServletRequest request) {
        return pesanService.findById(id, request);
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findByParam(@RequestParam String columnName, @RequestParam String value,
                                              Pageable pageable, HttpServletRequest request) {
        return pesanService.findByParam(pageable, columnName, value, request);
    }
}
