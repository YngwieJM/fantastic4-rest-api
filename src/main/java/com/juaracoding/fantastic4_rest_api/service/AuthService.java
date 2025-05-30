package com.juaracoding.fantastic4_rest_api.service;


import com.juaracoding.fantastic4_rest_api.dto.validation.LoginDTO;
//import com.juarcoding.pcmspringboot3.dto.validation.VerifyRegisDTO;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import com.juaracoding.fantastic4_rest_api.model.User;
import com.juaracoding.fantastic4_rest_api.repo.UserRepo;
import com.juaracoding.fantastic4_rest_api.security.BcryptImpl;
//import com.juarcoding.pcmspringboot3.utils.SendMailOTP;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * Kode Platform / Aplikasi : 001 atau AUT
 * Kode Modul : 00
 * Kode Validation / Error  : FV - FE
 */
@Service
@Transactional
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    private Random random = new Random();

    /** 001-010 */


    /** 011-020 */
//    public ResponseEntity<Object> verifyRegis(User user, HttpServletRequest request) {
//        try {
//            int otp = random.nextInt(100000,999999);
//            Optional<User> opUser = userRepo.findByEmail(user.getEmail());
//            if(!opUser.isPresent()) {
//                return new ResponseHandler().handleResponse("Email Tidak Ditemukan !!",HttpStatus.BAD_REQUEST,null,"AUT00FV011",request);
//            }
//            User userNext = opUser.get();//ini dari database
//            if(!BcryptImpl.verifyHash(user.getOtp(),userNext.getOtp())) {
//                return new ResponseHandler().handleResponse("OTP Salah !!",HttpStatus.BAD_REQUEST,null,"AUT00FV012",request);
//            }
//            userNext.setRegistered(true);
//            userNext.setModifiedBy(userNext.getId());
//            userNext.setOtp(String.valueOf(otp));
//        }catch (Exception e){
//            return new ResponseHandler().handleResponse("Terjadi Kesalahan Pada Server",HttpStatus.INTERNAL_SERVER_ERROR,null,
//                    "AUT00FE011",request);
//        }
//        return new ResponseHandler().handleResponse("Registrasi Berhasil !!",HttpStatus.OK,null,null,request);
//    }
    /** 021-030 */
    public ResponseEntity<Object> login(User user, HttpServletRequest request) {
        Map<String,Object> m = new HashMap<>();
        User userNext = null;
        try{
            String username = user.getId();
            Optional<User> opUser = userRepo.findByidOrEmailOrNoTelp(username, username, username);
            if(!opUser.isPresent()) {
                return new ResponseHandler().handleResponse("User Tidak Ditemukan",HttpStatus.BAD_REQUEST,null,"AUT00FV021",request);
            }
            userNext = opUser.get();//diambil dari DB

            String pwdDB = user.getPassword();
            if(!BcryptImpl.verifyHash(pwdDB,userNext.getPassword())) {
                return new ResponseHandler().handleResponse("Username atau Password Salah !!",HttpStatus.BAD_REQUEST,null,"AUT00FV022",request);
            }
        }catch (Exception e){
            return new ResponseHandler().handleResponse("Terjadi Kesalahan Pada Server",HttpStatus.INTERNAL_SERVER_ERROR,null,
                    "AUT00FE021",request);
        }
        m.put("menu","sama aja nanti di security");
        m.put("token","Nanti di security");
        return new ResponseHandler().handleResponse("Login Berhasil !!",HttpStatus.OK,m,null,request);
    }

//    public User mapToUser(RegisDTO regisDTO) {
//        User user = new User();
//        user.setEmail(regisDTO.getEmail());
//        user.setNoHp(regisDTO.getNoHp());
//        user.setAlamat(regisDTO.getAlamat());
//        user.setNamaLengkap(regisDTO.getNamaLengkap());
//        user.setPassword(regisDTO.getPassword());
//        user.setTanggalLahir(regisDTO.getTanggalLahir());
//        user.setUsername(regisDTO.getUsername());
//
//        return user;
//    }

//    public User mapToUser(VerifyRegisDTO verifyRegisDTO) {
//        User user = new User();
//        user.setEmail(verifyRegisDTO.getEmail());
//        user.setOtp(verifyRegisDTO.getOtp());
//
//        return user;
//    }

//    public User mapToUser(LoginDTO loginDTO) {
//        User user = new User();
//        user.setUsername(loginDTO.getUsername());
//        user.setPassword(loginDTO.getPassword());
//
//        return user;
//    }


//    public User mapToUser(VerifyRegisDTO verifyRegisDTO) {
//            return  modelMapper.map(verifyRegisDTO, User.class);
//    }
//
//    public User mapToUser(RegisDTO regisDTO) {
//        return  modelMapper.map(regisDTO, User.class);
//    }

    public User mapToUser(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, User.class);
    }
}
