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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("change-password")
public class ChangePasswordController {
    @Autowired
    AuthService authService;

    @PutMapping
    @PreAuthorize("Change Password")
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