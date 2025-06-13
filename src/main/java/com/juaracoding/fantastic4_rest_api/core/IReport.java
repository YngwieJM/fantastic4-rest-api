package com.juaracoding.fantastic4_rest_api.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IReport<G> {

    public List<G> convertListWorkBookToListEntity(List<Map<String, String>> workBookData, String userId);//111-120
    public void downloadReportExcel(String column, String value, HttpServletRequest request, HttpServletResponse response);//121-130
//    public void generateToPDF(String column, String value, HttpServletRequest request, HttpServletResponse response);//131-140
}