package com.juaracoding.fantastic4_rest_api.service;


import com.juaracoding.fantastic4_rest_api.core.IService;
import com.juaracoding.fantastic4_rest_api.dto.report.RepRuanganDTO;
import com.juaracoding.fantastic4_rest_api.dto.response.ResRuanganDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValRuanganDTO;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import com.juaracoding.fantastic4_rest_api.repo.RuanganRepo;
import com.juaracoding.fantastic4_rest_api.utils.*;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kode Platform / Aplikasi : AUT
 * Kode Modul : 05
 * Kode Validation / Error  : FV - FE
 */

@Service
@Transactional
public class RuanganService implements IService<Ruangan>{
    private static final Logger log = LoggerFactory.getLogger(RuanganService.class);

    @Autowired
    private RuanganRepo ruanganRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination tp;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private PdfGenerator pdfGenerator;

    private StringBuilder sBuilder = new StringBuilder();

    @Override
    public ResponseEntity<Object> save(Ruangan ruangan, HttpServletRequest request) {
        try {
            if (ruangan == null) {
                return new ResponseHandler().handleResponse("Object Null !!", HttpStatus.BAD_REQUEST, null, "OBJECT NULL", request
                );
            }ruangan.setCreatedBy(1L);
            ruanganRepo.save(ruangan);

        } catch (Exception e) {
            return GlobalResponse.dataGagalDisimpan("AUT05FE001", request);
        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }


    @Override
    public ResponseEntity<Object> update(String id, Ruangan ruangan, HttpServletRequest request) {
        try {
            if (id == null) {
                return new ResponseHandler().handleResponse("Id NULL !!", HttpStatus.BAD_REQUEST,null,"ID_NULL",request);
            }
            if (ruangan == null) {
                return new ResponseHandler().handleResponse("Object Null !!", HttpStatus.BAD_REQUEST,null,"OBJECT NULL",request);
            }
            Optional<Ruangan> opRuangan = ruanganRepo.findById(id);
            if(!opRuangan.isPresent()){
                return GlobalResponse.dataTidakDitemukan("AUT05FV011",request);
        }
        Ruangan ruanganDB = opRuangan.get();
        ruanganDB.setNamaRuangan(ruangan.getNamaRuangan());
        ruanganDB.setMinKapasitas(ruangan.getMinKapasitas());
        ruanganDB.setMaxKapasitas(ruangan.getMaxKapasitas());
        ruanganDB.setLokasi(ruangan.getLokasi());
        ruanganDB.setModifiedBy(1L);
    } catch (Exception e){
            return GlobalResponse.dataGagalDiubah("AUT05FE012",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
}


    @Override
    public ResponseEntity<Object> delete(String id, HttpServletRequest request) {
        try {
            if (id==null) {
                return GlobalResponse.objectIsNull("AUT05FV021", request);
            }
            Optional<Ruangan> opRuangan = ruanganRepo.findById(String.valueOf(id));
            if(!opRuangan.isPresent()){
                    return GlobalResponse.dataTidakDitemukan("AUT05FV022",request);
                }
            ruanganRepo.findById(id);
            }catch (Exception e){
                return GlobalResponse.dataGagalDihapus("AUT05FE021",request);
            }
                return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<Ruangan> page = null;
        List<Ruangan> list = null;
        List<RepRuanganDTO> listDTO = null;
        Map<String,Object> data = null;

        try {
            page = ruanganRepo.findAll(pageable);
            if(page.isEmpty()){
                return GlobalResponse.dataTidakDitemukan("AUT05FV031",request);
            }
            listDTO = mapToDTO(page.getContent());
            data = tp.transformPagination(listDTO,page,"id","");
            } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("AUT05FE031", request);
        }
        return GlobalResponse.dataDitemukan(listDTO, request);
        }

    @Override
    public ResponseEntity<Object> findById(String id, HttpServletRequest request) {
        ResRuanganDTO resRuanganDTO = null;
        try {
            if (id == null) {
                return GlobalResponse.objectIsNull("AUT05FV041", request);
            }
            Optional<Ruangan> opRuangan = ruanganRepo.findById(String.valueOf(id));
            if (!opRuangan.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("AUT05FV042", request);
            }
            Ruangan ruanganDB = opRuangan.get();
            resRuanganDTO = mapToDTO(ruanganDB);
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("AUT05wFE041", request);
        }
        return GlobalResponse.dataDitemukan(resRuanganDTO, request);
    }


    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<Ruangan> page = null;
        List<RepRuanganDTO> listDTO = null;
        Map<String, Object> data = null;
        try {
            switch (columnName) {
                case "namaRuangan":
                    page = ruanganRepo.findByNamaRuanganContainsIgnoreCase(value, pageable);
                    break;
                case "minKapasitas":
                    page = ruanganRepo.findByMinKapasitasAndMaxKapasitas(Short.valueOf(value),Short.valueOf(value), pageable);
                    break;
                case "lokasi":
                    page = ruanganRepo.findByLokasiContainsIgnoreCase(value, pageable);
                    break;
                default:
                    page = ruanganRepo.findAll(pageable);
                    break;
            }
            if (page.isEmpty()) {
                return GlobalResponse.dataTidakDitemukan("AUT04FV051", request);
            }
            listDTO = mapToDTO(page.getContent());
            data = tp.transformPagination(listDTO, page, columnName, value);
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("AUT04FE051", request);
        }
        return GlobalResponse.dataDitemukan(listDTO, request);
    }


    /** additional function */

    public Ruangan mapToRuangan(ValRuanganDTO valRuanganDTO){
        return modelMapper.map(valRuanganDTO, Ruangan.class);
    }

    public List<RepRuanganDTO> mapToDTO(List<Ruangan> listRuangan){
        return modelMapper.map(listRuangan,new TypeToken<List<RepRuanganDTO>>(){}.getType());
    }

    public ResRuanganDTO mapToDTO(Ruangan ruangan){
        return modelMapper.map(ruangan,ResRuanganDTO.class);}
    }