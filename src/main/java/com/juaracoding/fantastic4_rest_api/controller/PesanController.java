package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.service.PesanService;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pesan")
public class PesanController {

    @Autowired
    private PesanService pesanService;

    @PostMapping("/save")
    public ResponseEntity<Object> savePesan(@RequestBody Pesan pesan, HttpServletRequest request) {
        return pesanService.save(pesan, request);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePesan(@PathVariable("id") String id, @RequestBody Pesan pesan, HttpServletRequest request) {
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
    public ResponseEntity<Object> findPesanByParam(@RequestParam String columnName, @RequestParam String value, Pageable pageable, HttpServletRequest request) {
        return pesanService.findByParam(pageable, columnName, value, request);
    }
}
