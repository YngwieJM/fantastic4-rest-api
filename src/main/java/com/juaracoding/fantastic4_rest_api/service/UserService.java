package com.juaracoding.fantastic4_rest_api.service;

import com.juaracoding.fantastic4_rest_api.dto.response.ResUserDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValUserDTO;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import com.juaracoding.fantastic4_rest_api.model.User;
import com.juaracoding.fantastic4_rest_api.repo.UserRepo;
import com.juaracoding.fantastic4_rest_api.utils.GlobalResponse;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination tp;

    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        try {
            if (user == null) {
                return new ResponseHandler().handleResponse("Object Null !!", HttpStatus.BAD_REQUEST, null, "USR01FV001", request);
            }
            user.setCreatedBy("1");
            userRepo.save(user);
            return GlobalResponse.dataBerhasilDisimpan(request);
        } catch (Exception e) {
            return GlobalResponse.dataGagalDisimpan("USR01FE001", request);
        }
    }

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
            userDB.setModifiedBy("1");
            userRepo.save(userDB);
            return GlobalResponse.dataBerhasilDiubah(request);
        } catch (Exception e) {
            return GlobalResponse.dataGagalDiubah("USR01FE011", request);
        }
    }

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

    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        try {
            Page<User> page = userRepo.findAll(pageable);
            if (page.isEmpty()) {
                return GlobalResponse.dataTidakDitemukan("USR01FV031", request);
            }
            List<ResUserDTO> listDTO = mapToDTO(page.getContent());
            Map<String, Object> data = tp.transformPagination(listDTO, page, "id", "");
            return GlobalResponse.dataDitemukan(data, request);
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("USR01FE031", request);
        }
    }

    public ResponseEntity<Object> findById(String id, HttpServletRequest request) {
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
            List<ResUserDTO> listDTO = mapToDTO(page.getContent());
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

    public List<ResUserDTO> mapToDTO(List<User> listUser) {
        return modelMapper.map(listUser, new TypeToken<List<ResUserDTO>>() {}.getType());
    }

    public ResUserDTO mapToDTO(User user) {
        return modelMapper.map(user, ResUserDTO.class);
    }
}
