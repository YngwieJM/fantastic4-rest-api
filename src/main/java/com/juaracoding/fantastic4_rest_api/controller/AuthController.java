package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.dto.validation.ChangePasswordDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.LoginDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.RegisDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.VerifyRegisDTO;
import com.juaracoding.fantastic4_rest_api.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("auth")
public class AuthController {
    /**
     * 1. Registrasi
     * 2. Login
     * 3. Lupa Password
     */
    @Autowired
    AuthService authService;


    @PostMapping("/regis")
    public ResponseEntity<Object> registration(@Valid @RequestBody RegisDTO regisDTO
            , HttpServletRequest request

    ){
        return authService.regis(authService.mapToUser(regisDTO),request);
    }

    @PostMapping("/verify-regis")
    public ResponseEntity<Object> verifyRegis(@Valid @RequestBody VerifyRegisDTO verifyRegisDTO
            , HttpServletRequest request){
        return authService.verifyRegis(authService.mapToUser(verifyRegisDTO), verifyRegisDTO.getNewPassword(), request);
    }

    @PostMapping("login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginDTO
            , HttpServletRequest request){
        return authService.login(authService.mapToUser(loginDTO),request);
    }

    @PutMapping("change-password")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO,
                                                 HttpServletRequest request,
                                                 Principal principal) {
        String username = principal.getName(); // authenticated username
        return authService.changePassword(
                username,
                changePasswordDTO.getCurrentPassword(),
                changePasswordDTO.getNewPassword(),
                request
        );
    }
}