package com.juaracoding.fantastic4_rest_api.controller;


import com.juaracoding.fantastic4_rest_api.service.PesanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("booking-room")
public class BookingRoomController {

    @Autowired
    private PesanService pesanService;


    @PostMapping
//    @PreAuthorize("hasAuthroity('Booking Room')") for security
    public ResponseEntity<Object> save(){
        return null;
    }
}
