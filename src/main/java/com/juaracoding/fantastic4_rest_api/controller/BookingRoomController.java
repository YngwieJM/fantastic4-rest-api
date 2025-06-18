package com.juaracoding.fantastic4_rest_api.controller;


import com.juaracoding.fantastic4_rest_api.dto.report.RepSarchDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValPesanDTO;
import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import com.juaracoding.fantastic4_rest_api.model.User;
import com.juaracoding.fantastic4_rest_api.repo.UserRepo;
import com.juaracoding.fantastic4_rest_api.service.PesanService;
import com.juaracoding.fantastic4_rest_api.service.RuanganService;
import com.juaracoding.fantastic4_rest_api.utils.GlobalResponse;
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

import java.security.Principal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("booking-room")
public class BookingRoomController {

    @Autowired
    private PesanService pesanService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RuanganService ruanganService;

    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourceHandlerMapping;


    @PostMapping
    @PreAuthorize("hasAuthority('Booking Room')")
    public ResponseEntity<Object> save(@Valid @RequestBody ValPesanDTO valPesanDTO,
                                       HttpServletRequest request,
                                       Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Pesan pesan = pesanService.mapToPesan(valPesanDTO);
        pesan.setUser(user); // Set the logged-in user
        return pesanService.save(pesan, request);
    }



//    @GetMapping
//    @PreAuthorize("hasAuthroity('Booking Room')")
//    public ResponseEntity<Object> findAll(HttpServletRequest request) {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
//        return pesanService.findAll(pageable, request);
//    }

//    @GetMapping("/find-by-param/{sort}/{sort-by}/{page}")
//    @PreAuthorize("hasAuthroity('Booking Room')")
//    public ResponseEntity<Object> findByParam(
//            @PathVariable String sort,
//            @PathVariable(value = "sort-by") String sortBy,
//            @PathVariable Integer page,
//            @RequestParam Integer size,
//            @RequestParam String column,
//            @RequestParam String value,
//            HttpServletRequest request) {
//        Pageable pageable = null;
//        sortBy = sortColumn(sortBy);
//        switch(sort){
//            case "desc":pageable = PageRequest.of(page, size, Sort.by("id").descending());break;
//            default:pageable = PageRequest.of(page, size, Sort.by("id"));break;
//        }
//        return pesanService.findByParam(pageable, column, value, request);
//    }
//
//    private String sortColumn(String column){
//        switch (column){
//            case "namaPertemuan":column="NamaPertemuan";break;
//            case "status":column="Status";break;
//            default:column="id";break;
//        }
//        return column;
//    }
@GetMapping("/search")
@PreAuthorize("hasAuthority('Booking Room')")
public ResponseEntity<Object> searchAvailableRooms(
        @RequestParam("kapasitas") Short kapasitas,
        @RequestParam("durasi") Integer durasi,
        HttpServletRequest request) {

    // Default start time (jam buka kantor 08:00:00)
    LocalTime mulaiLocalTime = LocalTime.of(8, 0, 0);
    LocalTime berakhirLocalTime = mulaiLocalTime.plusMinutes(durasi);
    Time mulaiTime = Time.valueOf(mulaiLocalTime);
    Time berakhirTime = Time.valueOf(berakhirLocalTime);

    return pesanService.searchAvailableRooms(kapasitas, mulaiTime, berakhirTime, request);
}

    @GetMapping("/search-manual")
    @PreAuthorize("hasAuthority('Booking Room')")
    public ResponseEntity<Object> search(
            @RequestParam("nama-pertemuan") String namaPertemuan,
            @RequestParam("kapasitas") Short kapasitas,
            @RequestParam("durasi") Integer durasi,
            HttpServletRequest request) {

        List<RepSarchDTO> data = new java.util.ArrayList<>();
        if (kapasitas <= 10 && durasi == 1) {
            data1(data);
        } else if (kapasitas <= 20 && durasi == 1) {
            data2(data);
        } else if(kapasitas <= 10 && durasi == 2){
            data3(data);
        } else {
            return GlobalResponse.ruanganTidakDitemukan("Ruangan Tidak Ditemukan",request);
        }
        return ResponseEntity.ok(data);
    }

    @PostMapping("/save-manual")
    @PreAuthorize("hasAuthority('Booking Room')")
    public ResponseEntity<Object> saveSimplePesan(
            @RequestBody Map<String, Object> payload,
            HttpServletRequest request,
            Principal principal) {

        // Extract fields from payload
        String roomId = payload.get("roomId").toString();
        String tanggalStr = payload.get("tanggal").toString();
        String namaPertemuan = payload.get("namaPertemuan").toString();
        Map<String, String> schedule = (Map<String, String>) payload.get("schedule");

        LocalDate tanggalPertemuan = LocalDate.parse(tanggalStr);
        Time mulai = Time.valueOf(schedule.get("mulai"));
        Time berakhir = Time.valueOf(schedule.get("berakhir"));

        // Find user and ruangan
        String username = principal.getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // You need a helper to get Ruangan entity directly (not DTO)
        Ruangan ruangan = ruanganService.findByRuanganId(roomId)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Ruangan not found"));

        // Call the service method
        return pesanService.saveSimplePesan(
                user,
                ruangan,
                LocalDate.now(), // tanggalPemesanan
                tanggalPertemuan,
                mulai,
                berakhir,
                "Pending",
                username,
                namaPertemuan,
                request
        );
    }



    private void data1(List<RepSarchDTO> data){
//        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-14:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"14:00-15:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-16:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"16:00-17:00"));

        data.add(new RepSarchDTO("R002","Ruang Meeting 2", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-14:00"));
        data.add(new RepSarchDTO("R002","Ruang Meeting 2", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"14:00-15:00"));
        data.add(new RepSarchDTO("R002","Ruang Meeting 2", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-16:00"));
        data.add(new RepSarchDTO("R002","Ruang Meeting 2", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"16:00-17:00"));

        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"08:00-09:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"09:00-10:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"10:00-11:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"11:00-12:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-14:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"14:00-15:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-16:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"16:00-17:00"));

        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"08:00-09:00"));
        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"09:00-10:00"));
        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"10:00-11:00"));
        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"11:00-12:00"));
        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-14:00"));
        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"14:00-15:00"));
        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-16:00"));
        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"16:00-17:00"));
    }

    private void data2(List<RepSarchDTO> data){
        data.add(new RepSarchDTO("R003","Ruang Meeting 3", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-14:00"));
        data.add(new RepSarchDTO("R003","Ruang Meeting 3", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"14:00-15:00"));
        data.add(new RepSarchDTO("R003","Ruang Meeting 3", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-16:00"));
        data.add(new RepSarchDTO("R003","Ruang Meeting 3", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"16:00-17:00"));

        data.add(new RepSarchDTO("R004","Ruang Meeting 4", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-14:00"));
        data.add(new RepSarchDTO("R004","Ruang Meeting 4", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"14:00-15:00"));
        data.add(new RepSarchDTO("R004","Ruang Meeting 4", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-16:00"));
        data.add(new RepSarchDTO("R004","Ruang Meeting 4", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"16:00-17:00"));

        data.add(new RepSarchDTO("R003","Ruang Meeting 3", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"08:00-10:00"));
        data.add(new RepSarchDTO("R003","Ruang Meeting 3", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"10:00-12:00"));
        data.add(new RepSarchDTO("R003","Ruang Meeting 3", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-15:00"));
        data.add(new RepSarchDTO("R003","Ruang Meeting 3", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-17:00"));

        data.add(new RepSarchDTO("R004" ,"Ruang Meeting 4", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"08:00-10:00"));
        data.add(new RepSarchDTO("R004" ,"Ruang Meeting 4", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"10:00-12:00"));
        data.add(new RepSarchDTO("R004" ,"Ruang Meeting 4", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-15:00"));
        data.add(new RepSarchDTO("R004" ,"Ruang Meeting 4", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-17:00"));
    }

    private void data3(List<RepSarchDTO> data){
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-15:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-17:00"));

        data.add(new RepSarchDTO("R002","Ruang Meeting 2", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-15:00"));
        data.add(new RepSarchDTO("R002","Ruang Meeting 2", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-17:00"));

        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"08:00-10:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"10:00-12:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-15:00"));
        data.add(new RepSarchDTO("R001","Ruang Meeting 1", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-17:00"));

        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"08:00-10:00"));
        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"10:00-12:00"));
        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"13:00-15:00"));
        data.add(new RepSarchDTO("R002" ,"Ruang Meeting 2", LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd MMM YYYY")),"15:00-17:00"));
    }
}
