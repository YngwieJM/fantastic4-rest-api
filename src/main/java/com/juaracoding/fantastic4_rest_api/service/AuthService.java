package com.juaracoding.fantastic4_rest_api.service;


import com.juaracoding.fantastic4_rest_api.config.JwtConfig;
import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.dto.MenuLoginDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ChangePasswordDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.LoginDTO;
//import com.juarcoding.pcmspringboot3.dto.validation.VerifyRegisDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.RegisDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.VerifyRegisDTO;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import com.juaracoding.fantastic4_rest_api.model.Akses;
import com.juaracoding.fantastic4_rest_api.model.Menu;
import com.juaracoding.fantastic4_rest_api.model.User;
import com.juaracoding.fantastic4_rest_api.repo.UserRepo;
import com.juaracoding.fantastic4_rest_api.security.BcryptImpl;
//import com.juarcoding.pcmspringboot3.utils.SendMailOTP;
import com.juaracoding.fantastic4_rest_api.security.Crypto;
import com.juaracoding.fantastic4_rest_api.utils.LoggingFile;
import com.juaracoding.fantastic4_rest_api.utils.RequestCapture;
import com.juaracoding.fantastic4_rest_api.utils.SendMailOTP;
import com.juaracoding.fantastic4_rest_api.security.JwtUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Kode Platform / Aplikasi : 001 atau AUT
 * Kode Modul : 00
 * Kode Validation / Error  : FV - FE
 */
@Service
@Transactional
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    private Random random = new Random();

    @Autowired
    private JwtUtility jwtUtility;

    /** 001-010 */
    public ResponseEntity<Object> regis(User user, HttpServletRequest request) {
        if(user==null){
            return new ResponseHandler().handleResponse("Email Tidak Ditemukan !!",HttpStatus.BAD_REQUEST,null,"AUT00FV001",request);
        }
        if(user.getEmail()==null){
            return new ResponseHandler().handleResponse("Email Tidak Ditemukan !!",HttpStatus.BAD_REQUEST,null,"AUT00FV002",request);
        }
        Map<String,Object> m = new HashMap<>();
        try{
            int otp = random.nextInt(100000,999999);
            user.setOtp(BcryptImpl.hash(String.valueOf(otp)));
            user.setPassword(BcryptImpl.hash(user.getUsername()+user.getPassword()));
            /** Default Akses untuk New member */
            Akses akses = new Akses();
            akses.setId(2L);
            user.setAkses(akses);
            user.setRegistered(false);
            user.setId(user.getNama().split("\\s+")[0]+123);
//            user.setDepartemen("Trainer");
//            user.setJabatan("Trainer");
            user.setCreatedBy(String.valueOf(user.getId()));
            user.setCreatedDate(LocalDateTime.now());

            userRepo.save(user);
            if(OtherConfig.getEnableAutomationTesting().equals("y")){
                m.put("otp",otp);// ini untuk automation
            }
            SendMailOTP.verifyRegisOTP("OTP UNTUK REGISTRASI",
                    user.getNama(),user.getEmail(),String.valueOf(otp),"ver_regis.html");
            m.put("email",user.getEmail());
            Thread.sleep(1000);
        }catch (Exception e){
//            String strArr [] = {"poll.chihuy@gmail.com","alfin@gmail.com",""};
            LoggingFile.logException("AuthService","regis(User user, HttpServletRequest request)"+ RequestCapture.allRequest(request),e);
//            new SMTPCore().sendMailWithAttachment(strArr,
//                    "Error ",
//                    "AuthService, "+"regis(User user, HttpServletRequest request)"+ RequestCapture.allRequest(request)+"    ----> "+e.getMessage(),
//                    "TLS",null);
            return new ResponseHandler().handleResponse("Server Tidak Dapat Memproses !!",HttpStatus.INTERNAL_SERVER_ERROR,null,"AUT00FE001",request);
        }
        return new ResponseHandler().handleResponse("OTP Terkirim, Cek Email !!",HttpStatus.OK,m,null,request);
    }

    /** 011-020 */
    public ResponseEntity<Object> verifyRegis(User user,String newPassword, HttpServletRequest request) {
        try {
            int otp = random.nextInt(100000,999999);
            Optional<User> opUser = userRepo.findByEmail(user.getEmail());
            if(!opUser.isPresent()) {
                return new ResponseHandler().handleResponse("Email Tidak Ditemukan !!",HttpStatus.BAD_REQUEST,null,"AUT00FV011",request);
            }
            User userNext = opUser.get();//ini dari database
            if(!BcryptImpl.verifyHash(user.getOtp(),userNext.getOtp())) {
                return new ResponseHandler().handleResponse("OTP Salah !!",HttpStatus.BAD_REQUEST,null,"AUT00FV012",request);
            }
            userNext.setRegistered(true);
            userNext.setModifiedBy(userNext.getId());
            userNext.setOtp(BcryptImpl.hash(String.valueOf(otp)));
            userNext.setPassword(BcryptImpl.hash(userNext.getUsername() + newPassword));
            userNext.setModifiedDate(LocalDateTime.now());
            userRepo.save(userNext);
        }catch (Exception e){
            LoggingFile.logException("AuthService","verifyRegis(User user, HttpServletRequest request)"+ RequestCapture.allRequest(request),e);

            return new ResponseHandler().handleResponse("Terjadi Kesalahan Pada Server",HttpStatus.INTERNAL_SERVER_ERROR,null,
                    "AUT00FE011",request);
        }
        return new ResponseHandler().handleResponse("Registrasi Berhasil !!",HttpStatus.OK,null,null,request);
    }

    /** 021-030 */
    public ResponseEntity<Object> login(User user, HttpServletRequest request) {
        Map<String,Object> m = new HashMap<>();
        User userNext = null;
        try{
            String username = user.getUsername();
            Optional<User> opUser = userRepo.findByIdOrUsernameOrEmailOrNoTelp(username, username, username, username);
            if(!opUser.isPresent()) {
                return new ResponseHandler().handleResponse("User Tidak Ditemukan",HttpStatus.BAD_REQUEST,null,"AUT00FV021",request);
            }
            userNext = opUser.get();//diambil dari DB

            if (userNext.getRegistered() == null || !userNext.getRegistered()) {
                return new ResponseHandler().handleResponse("User belum registrasi, registrasi lebih dahulu", HttpStatus.BAD_REQUEST, null, "AUT00FV023", request);
            }

            String pwdDB = userNext.getUsername()+user.getPassword();
            if(!BcryptImpl.verifyHash(pwdDB,userNext.getPassword())) {
                return new ResponseHandler().handleResponse("Username atau Password Salah !!",HttpStatus.BAD_REQUEST,null,"AUT00FV022",request);
            }
        }catch (Exception e){
            LoggingFile.logException("AuthService","login(User user, HttpServletRequest request)"+ RequestCapture.allRequest(request),e);

            return new ResponseHandler().handleResponse("Terjadi Kesalahan Pada Server",HttpStatus.INTERNAL_SERVER_ERROR,null,
                    "AUT00FE021",request);
        }

        Map<String,Object> mapData = new HashMap<>();
        mapData.put("em",userNext.getEmail());
        mapData.put("id",userNext.getId());
        mapData.put("hp",userNext.getNoTelp());
        mapData.put("naleng",userNext.getNama());
        List<MenuLoginDTO> menu = mapToMenuLoginDTO(userNext.getAkses().getListMenu());
        String token = jwtUtility.doGenerateToken(mapData,userNext.getUsername());

        m.put("menu",menu);
        if(JwtConfig.getTokenEncryptEnable().equals("y")){
            token = Crypto.performEncrypt(token);
        }
        m.put("token", token);

        return new ResponseHandler().handleResponse("Login Berhasil !!",HttpStatus.OK,m,null,request);
    }

    public ResponseEntity<Object> refreshToken(User user, HttpServletRequest request) {
        Map<String,Object> m = new HashMap<>();
        User userNext = null;
        try{
            String username = user.getUsername();
            Optional<User> opUser = userRepo.findByIdOrUsernameOrEmailOrNoTelpAndIsRegistered(username, username, username, username, true);
            if(!opUser.isPresent()) {
                return new ResponseHandler().handleResponse("User Tidak Ditemukan",HttpStatus.BAD_REQUEST,null,"AUT00FV021",request);
            }
            userNext = opUser.get();//diambil dari DB

            String pwdDB = userNext.getUsername()+user.getPassword();
            if(!BcryptImpl.verifyHash(pwdDB,userNext.getPassword())) {
                return new ResponseHandler().handleResponse("Username atau Password Salah !!",HttpStatus.BAD_REQUEST,null,"AUT00FV022",request);
            }
        }catch (Exception e){
            LoggingFile.logException("AuthService","login(User user, HttpServletRequest request)"+ RequestCapture.allRequest(request),e);

            return new ResponseHandler().handleResponse("Terjadi Kesalahan Pada Server",HttpStatus.INTERNAL_SERVER_ERROR,null,
                    "AUT00FE021",request);
        }

        Map<String,Object> mapData = new HashMap<>();
        mapData.put("em",userNext.getEmail());
        mapData.put("id",userNext.getId());
        mapData.put("hp",userNext.getNoTelp());
        String token = jwtUtility.doGenerateToken(mapData,userNext.getUsername());
        if(JwtConfig.getTokenEncryptEnable().equals("y")){
            token = Crypto.performEncrypt(token);
        }
        m.put("token", token);

        return new ResponseHandler().handleResponse("Login Berhasil !!",HttpStatus.OK,m,null,request);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opUser = userRepo.findByUsernameAndIsRegistered(username,true);
        if(!opUser.isPresent()) {
            throw new UsernameNotFoundException("Username atau Password Salah !!!");
        }
        User user = opUser.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getAuthorities());
    }

    // In AuthService.java
    public ResponseEntity<Object> changePassword(String username, String currentPassword, String newPassword, HttpServletRequest request) {
        Optional<User> opUser = userRepo.findByUsername(username);
        if (!opUser.isPresent()) {
            return new ResponseHandler().handleResponse("User not found", HttpStatus.NOT_FOUND, null, "AUT00FV031", request);
        }
        User user = opUser.get();
        String pwdDB = user.getUsername() + currentPassword;
        if (!BcryptImpl.verifyHash(pwdDB, user.getPassword())) {
            return new ResponseHandler().handleResponse("Current password is incorrect", HttpStatus.UNAUTHORIZED, null, "AUT00FV032", request);
        }
        user.setPassword(BcryptImpl.hash(user.getUsername() + newPassword));
        user.setModifiedBy(user.getId());
        user.setModifiedDate(LocalDateTime.now());
        userRepo.save(user);
        return new ResponseHandler().handleResponse("Password changed successfully", HttpStatus.OK, null, null, request);
    }


//    public User mapToUser(RegisDTO regisDTO) {
//        User user = new User();
//        user.setEmail(regisDTO.getEmail());
//        user.setNoTelp(regisDTO.getNoTelp());
//        user.setAlamat(regisDTO.getAlamat());
//        user.setNama(regisDTO.getNama());
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

    public User mapToUser(VerifyRegisDTO verifyRegisDTO) {
        return  modelMapper.map(verifyRegisDTO, User.class);
    }

    public User mapToUser(RegisDTO regisDTO) {
        return  modelMapper.map(regisDTO, User.class);
    }

    public User mapToUser(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, User.class);
    }

    public User mapToUser(ChangePasswordDTO changePasswordDTO, String username) {
        User user = new User();
        user.setUsername(username); // set from authenticated principal
        user.setPassword(changePasswordDTO.getCurrentPassword());
        return user;
    }

    public List<MenuLoginDTO> mapToMenuLoginDTO(List<Menu> ltMenu){
        List<MenuLoginDTO> ltMenuDTO = new ArrayList<>();
        for (Menu menu : ltMenu) {
            MenuLoginDTO menuDTO = new MenuLoginDTO();
            menuDTO.setNama(menu.getNama());
            menuDTO.setPath(menu.getPath());
            ltMenuDTO.add(menuDTO);
        }

        return ltMenuDTO;
    }


}
