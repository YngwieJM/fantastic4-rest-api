package com.juaracoding.fantastic4_rest_api.core;

import com.juaracoding.fantastic4_rest_api.model.Admin;
import com.juaracoding.fantastic4_rest_api.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface IAuth<T> {
    public ResponseEntity<Object> login(User user, HttpServletRequest request);//181-190
    public ResponseEntity<Object> login(Admin admin, HttpServletRequest request);//191-200
}
