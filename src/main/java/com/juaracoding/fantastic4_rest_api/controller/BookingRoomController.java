package com.juaracoding.fantastic4_rest_api.controller;


import com.juaracoding.fantastic4_rest_api.dto.validation.ValPesanDTO;
import com.juaracoding.fantastic4_rest_api.service.PesanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

@RestController
@RequestMapping("booking-room")
public class BookingRoomController {

    @Autowired
    private PesanService pesanService;

    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourceHandlerMapping;


    @PostMapping
    @PreAuthorize("hasAuthroity('Booking Room')")
    public ResponseEntity<Object> save(@Valid @RequestBody ValPesanDTO valPesanDTO,
                                       HttpServletRequest request) {
        return pesanService.save(pesanService.mapToPesan(valPesanDTO), request);
    }

    @GetMapping
    @PreAuthorize("hasAuthroity('Booking Room')")
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        return pesanService.findAll(pageable, request);
    }

    @GetMapping("/find-by-param/{sort}/{sort-by}/{page}")
    @PreAuthorize("hasAuthroity('Booking Room')")
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
        switch(sort){
            case "desc":pageable = PageRequest.of(page, size, Sort.by("id").descending());break;
            default:pageable = PageRequest.of(page, size, Sort.by("id"));break;
        }
        return pesanService.findByParam(pageable, column, value, request);
    }

    private String sortColumn(String column){
        switch (column){
            case "namaPertemuan":column="NamaPertemuan";break;
            case "status":column="Status";break;
            default:column="id";break;
        }
        return column;
    }
}
