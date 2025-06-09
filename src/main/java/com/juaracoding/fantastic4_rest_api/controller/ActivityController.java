package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.dto.validation.ValPesanDTO;
import com.juaracoding.fantastic4_rest_api.service.PesanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

@RestController
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private PesanService pesanService;

    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourceHandlerMapping;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Activity')")
    public ResponseEntity<Object> findById(
            @PathVariable String id,
            HttpServletRequest request) {
        return pesanService.findById(id, request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Activity')")
    public ResponseEntity<Object> update(@Valid @RequestBody ValPesanDTO valPesanDTO,
                                         @PathVariable String id,
                                         HttpServletRequest request){
        return pesanService.update(id, pesanService.mapToPesan(valPesanDTO), request);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> delete(@PathVariable String id,
//                                         HttpServletRequest request){
//        return pesanService.delete(id, request);
//    }

}
