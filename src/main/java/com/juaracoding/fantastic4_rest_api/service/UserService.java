package com.juaracoding.fantastic4_rest_api.service;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.core.IService;
import com.juaracoding.fantastic4_rest_api.dto.report.RepFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.dto.report.RepUserDTO;
import com.juaracoding.fantastic4_rest_api.dto.response.ResUserDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValUserDTO;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import com.juaracoding.fantastic4_rest_api.model.Akses;
import com.juaracoding.fantastic4_rest_api.model.Fasilitas;
import com.juaracoding.fantastic4_rest_api.model.User;
import com.juaracoding.fantastic4_rest_api.repo.UserRepo;
import com.juaracoding.fantastic4_rest_api.security.BcryptImpl;
import com.juaracoding.fantastic4_rest_api.utils.GlobalFunction;
import com.juaracoding.fantastic4_rest_api.utils.GlobalResponse;
import com.juaracoding.fantastic4_rest_api.utils.SendMailOTP;
import com.juaracoding.fantastic4_rest_api.utils.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService implements IService<User> {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination tp;

    private Random random = new Random();


    @Override
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        Map<String,Object> m = GlobalFunction.extractToken(request);
        Map<String,Object> ms = new HashMap<>();
        try {
            if (user == null) {
                return new ResponseHandler().handleResponse("Object Null !!", HttpStatus.BAD_REQUEST, null, "OBJECT NULL", request
                );
            }

            int otp = random.nextInt(100000,999999);
            user.setOtp(BcryptImpl.hash(String.valueOf(otp)));
            user.setPassword(BcryptImpl.hash(user.getUsername()+user.getPassword()));
            /** Default Akses untuk New member */
            Akses akses = new Akses();
            akses.setId(2L);
            user.setAkses(akses);
            user.setRegistered(false);
//            user.setId(user.getNama().split("\\s+")[0]+123);
//            user.setDepartemen("Trainer");
//            user.setJabatan("Trainer");
            user.setCreatedBy(String.valueOf(user.getId()));
            user.setCreatedDate(LocalDateTime.now());

            userRepo.save(user);
            if(OtherConfig.getEnableAutomationTesting().equals("y")){
                ms.put("otp",otp);// ini untuk automation
            }
            SendMailOTP.verifyRegisOTP("OTP UNTUK REGISTRASI",
                    user.getNama(),user.getEmail(),String.valueOf(otp),"ver_regis.html");
            m.put("email",user.getEmail());
            ms.put("otp",otp);
            Thread.sleep(1000);
        } catch (Exception e) {
            return GlobalResponse.dataGagalDisimpan("AUT05FE001", request);
        }
        return new ResponseHandler().handleResponse("OTP Terkirim, Cek Email !!",HttpStatus.OK,ms,null,request);
    }

    @Override
    public ResponseEntity<Object> update(String id, User user, HttpServletRequest request) {
        try {
            if (id == null || user == null) {
                return GlobalResponse.objectIsNull("USR01FV011", request);
            }
            Optional<User> opUser = userRepo.findById(id);
            if (opUser.isEmpty()) {
                return GlobalResponse.dataTidakDitemukan("USR01FV012", request);
            }
            User userDB = opUser.get();
            userDB.setNama(user.getNama());
            userDB.setEmail(user.getEmail());
            userDB.setNoTelp(user.getNoTelp());
            userDB.setDepartemen(user.getDepartemen());
            userDB.setJabatan(user.getJabatan());
            userDB.setModifiedBy(user.getId());
            userDB.setModifiedDate(LocalDateTime.now());
            userRepo.save(userDB);
            return GlobalResponse.dataBerhasilDiubah(request);
        } catch (Exception e) {
            return GlobalResponse.dataGagalDiubah("USR01FE011", request);
        }
    }

    @Override
    public ResponseEntity<Object> delete(String id, HttpServletRequest request) {
        try {
            if (id == null) {
                return GlobalResponse.objectIsNull("USR01FV021", request);
            }
            Optional<User> opUser = userRepo.findById(id);
            if (opUser.isEmpty()) {
                return GlobalResponse.dataTidakDitemukan("USR01FV022", request);
            }
            userRepo.deleteById(id);
            return GlobalResponse.dataBerhasilDihapus(request);
        } catch (Exception e) {
            return GlobalResponse.dataGagalDihapus("USR01FE021", request);
        }
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<User> page = null;
        List<User> list = null;
        List<RepUserDTO> listDTO = null;
        Map<String, Object> data = null;
        try{
            page = userRepo.findAll(pageable);
            if (page.isEmpty()){
                return GlobalResponse.dataTidakDitemukan("FAC031", request);
            }
            listDTO = mapToDTO(page.getContent());
            data = tp.transformPagination(listDTO, page, "id", "");
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("FAC032", request);
        }
        return GlobalResponse.dataDitemukan(listDTO, request);
    }

    @Override
    public ResponseEntity<Object> findById(String id, HttpServletRequest request) {
        ResUserDTO resUserDTO = null;
        try {
            if (id == null) {
                return GlobalResponse.objectIsNull("USR01FV041", request);
            }
            Optional<User> opUser = userRepo.findById(id);
            if (opUser.isEmpty()) {
                return GlobalResponse.dataTidakDitemukan("USR01FV042", request);
            }
            ResUserDTO dto = mapToDTO(opUser.get());
            return GlobalResponse.dataDitemukan(dto, request);
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("USR01FE041", request);
        }
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        try {
            Page<User> page;
            switch (columnName) {
                case "nama":
                    page = userRepo.findByNamaContainsIgnoreCase(value, pageable);
                    break;
                case "email":
                    page = userRepo.findByEmailContainsIgnoreCase(value, pageable);
                    break;
                case "noTelp":
                    page = userRepo.findByNoTelpContainsIgnoreCase(value, pageable);
                    break;
                case "departemen":
                    page = userRepo.findByDepartemenContainsIgnoreCase(value, pageable);
                    break;
                case "jabatan":
                    page = userRepo.findByJabatanContainsIgnoreCase(value, pageable);
                    break;
                default:
                    page = userRepo.findAll(pageable);
                    break;
            }

            if (page.isEmpty()) {
                return GlobalResponse.dataTidakDitemukan("USR01FV051", request);
            }
            List<RepUserDTO> listDTO = mapToDTO(page.getContent());
            Map<String, Object> data = tp.transformPagination(listDTO, page, columnName, value);
            return GlobalResponse.dataDitemukan(data, request);
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("USR01FE051", request);
        }
    }

    // ------------------------ Mapping Methods ------------------------

    public User mapToUser(ValUserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    public List<RepUserDTO> mapToDTO(List<User> listUser) {
        return modelMapper.map(listUser, new TypeToken<List<RepUserDTO>>() {}.getType());
    }

    public ResUserDTO mapToDTO(User user) {
        return modelMapper.map(user, ResUserDTO.class);
    }
}
