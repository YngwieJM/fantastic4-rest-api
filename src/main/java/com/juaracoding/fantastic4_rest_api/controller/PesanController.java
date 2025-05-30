package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.dto.validation.ValPesanDTO;
import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.service.PesanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public ResponseEntity<Object> savePesan(@Valid @RequestBody ValPesanDTO valPesanDTO, HttpServletRequest request) {
        Pesan pesan = pesanService.mapToPesan(valPesanDTO); // Assuming mapToPesan is a method to map DTO to model
        return pesanService.save(pesan, request);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePesan(@Valid @RequestBody ValPesanDTO valPesanDTO,
                                              @PathVariable("id") String id, HttpServletRequest request) {
        Pesan pesan = pesanService.mapToPesan(valPesanDTO);
        return pesanService.update(id, pesan, request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePesan(@PathVariable("id") String id, HttpServletRequest request) {
        return pesanService.delete(id, request);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> findAllPesan(Pageable pageable, HttpServletRequest request) {
        return pesanService.findAll(pageable, request);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findPesanById(@PathVariable("id") String id, HttpServletRequest request) {
        return pesanService.findById(id, request);
    }

    @GetMapping("/find")
    public ResponseEntity<Object> findPesanByParam(@RequestParam String columnName, @RequestParam String value,
                                                   Pageable pageable, HttpServletRequest request) {
        return pesanService.findByParam(pageable, columnName, value, request);
    }
}
