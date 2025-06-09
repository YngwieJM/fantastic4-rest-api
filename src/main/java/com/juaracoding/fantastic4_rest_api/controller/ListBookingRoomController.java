package com.juaracoding.fantastic4_rest_api.controller;


import com.juaracoding.fantastic4_rest_api.service.PesanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("list-booking-room")
public class ListBookingRoomController {


    @Autowired
    private PesanService pesanService;

    @GetMapping
    @PreAuthorize("hasAuthroity('List Booking Room')")
    public ResponseEntity<Object> getList(){
        return null;
    }

}
