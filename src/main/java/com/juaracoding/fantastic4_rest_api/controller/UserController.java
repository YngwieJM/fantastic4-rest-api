package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.dto.validation.RegisDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValUserDTO;
import com.juaracoding.fantastic4_rest_api.service.AuthService;
import com.juaracoding.fantastic4_rest_api.service.UserService;
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
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourcehandlerMapping;


    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValUserDTO valUserDTO,
                                       HttpServletRequest request) {
        return userService.save(userService.mapToUser(valUserDTO), request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> update(@Valid @RequestBody ValUserDTO valUserDTO,
                                         @PathVariable String id,
                                         HttpServletRequest request) {
        return userService.update(id, userService.mapToUser(valUserDTO), request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> delete(@PathVariable String id,
                                         HttpServletRequest request) {
        return userService.delete(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        Pageable pageable = PageRequest.of(0, OtherConfig.getDefaultPaginationSize(), Sort.by("id"));
        return userService.findAll(pageable, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id,
                                           HttpServletRequest request) {
        return userService.findById(id, request);
    }

    @GetMapping("/search/{sort}/{sort-by}/{page}")
    public ResponseEntity<Object> findByParam(@PathVariable String sort,
                                              @PathVariable("sort-by") String sortBy,
                                              @PathVariable Integer page,
                                              @RequestParam Integer size,
                                              @RequestParam String column,
                                              @RequestParam String value,
                                              HttpServletRequest request) {
        Pageable pageable;
        sortBy = sortColumn(sortBy);
        switch (sort.toLowerCase()) {
            case "desc":
                pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
                break;
            default:
                pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
                break;
        }
        return userService.findByParam(pageable, column, value, request);
    }

    private String sortColumn(String column) {
        switch (column) {
            case "nama":
            case "email":
            case "noTelp":
            case "departemen":
            case "jabatan":
                return column;
            default:
                return "id";
        }
    }
}
