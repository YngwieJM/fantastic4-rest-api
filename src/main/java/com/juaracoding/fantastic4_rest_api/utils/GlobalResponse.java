package com.juaracoding.fantastic4_rest_api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalResponse {

    public static ResponseEntity<Object> dataBerhasilDisimpan(HttpServletRequest request){
        return new ResponseHandler().handleResponse("SAVE SUCCESS !!", HttpStatus.CREATED,null,null,request);
    }
    public static ResponseEntity<Object> dataBerhasilDisimpan(String message, HttpServletRequest request){
        return new ResponseHandler().handleResponse(message, HttpStatus.CREATED,null,null,request);
    }
    public static ResponseEntity<Object> dataBerhasilDiubah( HttpServletRequest request){
        return new ResponseHandler().handleResponse("DATA BERHASIL DIUBAH", HttpStatus.OK,null,null,request);
    }
    public static ResponseEntity<Object> dataBerhasilDihapus(HttpServletRequest request){
        return new ResponseHandler().handleResponse("DATA BERHASIL DIHAPUS", HttpStatus.OK,null,null,request);
    }

    public static ResponseEntity<Object> dataGagalDisimpan(String errorCode , HttpServletRequest request){
        return new ResponseHandler().handleResponse("FAILED TO SAVE !!", HttpStatus.INTERNAL_SERVER_ERROR,null,errorCode,request);
    }

    public static ResponseEntity<Object> dataGagalDiubah(String errorCode , HttpServletRequest request){
        return new ResponseHandler().handleResponse("DATA GAGAL DIUBAH", HttpStatus.INTERNAL_SERVER_ERROR,null,errorCode,request);
    }

    public static ResponseEntity<Object> dataGagalDihapus(String errorCode , HttpServletRequest request){
        return new ResponseHandler().handleResponse("DATA GAGAL DIHAPUS", HttpStatus.INTERNAL_SERVER_ERROR,null,errorCode,request);
    }

    public static ResponseEntity<Object> terjadiKesalahan(String errorCode , HttpServletRequest request){
        return new ResponseHandler().handleResponse("TERJADI KESALAHAN", HttpStatus.INTERNAL_SERVER_ERROR,null,errorCode,request);
    }

    public static ResponseEntity<Object> dataDitemukan(Object data,HttpServletRequest request){
        return new ResponseHandler().handleResponse("DATA DITEMUKAN", HttpStatus.OK,data,null,request);
    }

    public static ResponseEntity<Object> dataTidakDitemukan(String errorCode , HttpServletRequest request){
        return new ResponseHandler().handleResponse("DATA TIDAK DITEMUKAN", HttpStatus.BAD_REQUEST,null,errorCode,request);
    }
    public static ResponseEntity<Object> objectIsNull(String errorCode , HttpServletRequest request){
        return new ResponseHandler().handleResponse("OBJECT NULL !!", HttpStatus.BAD_REQUEST,null,errorCode,request);
    }

    public static ResponseEntity<Object> formatHarusExcel(String errorCode , HttpServletRequest request){
        return new ResponseHandler().handleResponse("FORMAT HARUS EXCEL", HttpStatus.BAD_REQUEST,null,errorCode,request);
    }

    public static ResponseEntity<Object> fileExcelKosong(String errorCode , HttpServletRequest request){
        return new ResponseHandler().handleResponse("FILE EXCEL KOSONG", HttpStatus.BAD_REQUEST,null,errorCode,request);
    }
    public static ResponseEntity<Object> uploadFileExcelBerhasil(HttpServletRequest request){
        return new ResponseHandler().handleResponse("UPLOAD FILE EXCEL BERHASIL", HttpStatus.OK,null,null,request);
    }

    public static void manualResponse(HttpServletResponse response, ResponseEntity<Object> resObject){
        try{
            response.getWriter().write(convertObjectToJson(resObject.getBody()));
            response.setStatus(resObject.getStatusCodeValue());
        }catch (Exception e){
        }
    }

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if(object == null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
