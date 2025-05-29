package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.dto.validation.ValUserDTO;
import com.juaracoding.fantastic4_rest_api.model.User;
import com.juaracoding.fantastic4_rest_api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourceHandlerMapping;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValUserDTO valUserDTO,
                                       HttpServletRequest request){
        return userService.save(userService.mapToUser(valUserDTO),request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody ValUserDTO valUserDTO,
                                               @PathVariable String id,
                                               HttpServletRequest request) {
        return userService.update(id, userService.mapToUser(valUserDTO), request);
    }

    private ResponseEntity<Object> delete(@PathVariable String id,
                                          HttpServletRequest request) {
        return userService.delete(id, request);
    }

    /** default page for user */

    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        return userService.findAll(pageable, request);
    }

    public ResponseEntity<Object> findById(@PathVariable String id,
                                           HttpServletRequest request) {
        return userService.findById(id, request);
    }

    public ResponseEntity<Object> findByParam(@PathVariable String sort,
                                              @PathVariable(value = "sort-by") String sortBy,
                                              @PathVariable Integer page,
                                              @RequestParam Integer size,
                                              @RequestParam String column,
                                              @RequestParam String value,
                                              HttpServletRequest request) {
        Pageable pageable = null;
        sortBy = sortColumn(sortBy);
        switch (sort){
            case"desc":pageable = PageRequest.of(page, size, Sort.by("id").descending()); break;
            default:pageable = PageRequest.of(page, size, Sort.by("id").ascending()); break;
        }
        return userService.findByParam(pageable, column, value, request);
    }

    private String sortColumn(String column) {
        switch (column){
            case "nama": column = "nama"; break;
            case "email": column = "email"; break;
            case "noTelp": column = "noTelp"; break;
            case "departemen": column = "departemen"; break;
            case "jabatan": column = "jabatan"; break;
            default:column= "id"; break;
        }
        return column;
    }

}
