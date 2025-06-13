package com.juaracoding.fantastic4_rest_api.controller;


import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.service.PesanService;
import com.juaracoding.fantastic4_rest_api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("list-booking-room")
public class ListBookingRoomController {


    @Autowired
    private PesanService pesanService;

    @GetMapping
    @PreAuthorize("hasAuthority('List Booking Room')")
    public ResponseEntity<Object> findAll(HttpServletRequest request){
        Pageable pageable = PageRequest.of(0, OtherConfig.getDefaultPaginationSize(), Sort.by("id"));
        return pesanService.findAll(pageable, request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('List Booking Room')")
    public ResponseEntity<Object> findById(
            @PathVariable String id,
            HttpServletRequest request) {
        return pesanService.findById(id, request);
    }

    @GetMapping("/download-excel")
    @PreAuthorize("hasAuthority('List Booking Room')")
    public void downloadExcel(@RequestParam String column,
                              @RequestParam String value,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        pesanService.downloadReportExcel(column, value, request, response);
    }

}
