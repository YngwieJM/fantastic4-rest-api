package com.juaracoding.fantastic4_rest_api.service;

import com.juaracoding.fantastic4_rest_api.core.IService;
import com.juaracoding.fantastic4_rest_api.dto.report.RepAksesDTO;
import com.juaracoding.fantastic4_rest_api.dto.response.ResAksesDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValAksesDTO;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import com.juaracoding.fantastic4_rest_api.model.Akses;
import com.juaracoding.fantastic4_rest_api.repo.AksesRepo;
import com.juaracoding.fantastic4_rest_api.utils.GlobalResponse;
import com.juaracoding.fantastic4_rest_api.utils.PdfGenerator;
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
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
public class AksesService implements IService<Akses> {

    @Autowired
    private AksesRepo aksesRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination tp;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private PdfGenerator pdfGenerator;

    private StringBuilder sBuild = new StringBuilder();

    @Override
    public ResponseEntity<Object> save(Akses akses, HttpServletRequest request) {//001-010

        try{
            if(akses == null){
                return new ResponseHandler().handleResponse("Object Null !!", HttpStatus.BAD_REQUEST,null,"AUT03FV001",request);
            }
            akses.setCreatedBy(1L);
            aksesRepo.save(akses);
        }catch (Exception e){
            return GlobalResponse.dataGagalDisimpan("AUT03FE001",request);
        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    public ResponseEntity<Object> update(String id, Akses akses, HttpServletRequest request) {//011-020
        try{
            if(id == null){
                return GlobalResponse.objectIsNull("AUT03FV011",request);
            }
            if(akses == null){
                return GlobalResponse.objectIsNull("AUT03FV012",request);
            }
            Optional<Akses> opAkses = aksesRepo.findById(Long.valueOf(id));
            if(!opAkses.isPresent()){
                return GlobalResponse.dataTidakDitemukan("AUT03FV013",request);
            }
            Akses aksesDB = opAkses.get();
            aksesDB.setNama(akses.getNama());
            aksesDB.setDeskripsi(akses.getDeskripsi());
            aksesDB.setListMenu(akses.getListMenu());
            aksesDB.setModifiedBy(1L);

        }catch (Exception e){
            return GlobalResponse.dataGagalDiubah("AUT03FE011",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    public ResponseEntity<Object> delete(String id, HttpServletRequest request) {//021-030
        try{
            if(id==null){
                return GlobalResponse.objectIsNull("AUT03FV021",request);
            }
            Optional<Akses> opAkses = aksesRepo.findById(Long.valueOf(id));
            if(!opAkses.isPresent()){
                return GlobalResponse.dataTidakDitemukan("AUT03FV022",request);
            }
            aksesRepo.deleteById(Long.valueOf(id));

        }catch (Exception e){
            return GlobalResponse.dataGagalDihapus("AUT03FE021",request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {//031-040
        Page<Akses> page = null;
        List<Akses> list = null;
        List<RepAksesDTO> listDTO = null;
        Map<String,Object> data = null;
        try {
            page = aksesRepo.findAll(pageable);
            if(page.isEmpty()){
                return GlobalResponse.dataTidakDitemukan("AUT03FV031",request);
            }
            listDTO = mapToDTO(page.getContent());
            data = tp.transformPagination(listDTO,page,"id","");
        }catch (Exception e){
            return GlobalResponse.terjadiKesalahan("AUT03FE031",request);
        }
        return GlobalResponse.dataDitemukan(listDTO,request);
    }
    @Override
    public ResponseEntity<Object> findById(String id, HttpServletRequest request) {//041-050
        ResAksesDTO resAksesDTO = null;
        try{
            if(id==null){
                return GlobalResponse.objectIsNull("AUT03FV041",request);

            }
            Optional<Akses> opAkses = aksesRepo.findById(Long.valueOf(id));
            if(!opAkses.isPresent()){
                return GlobalResponse.dataTidakDitemukan("AUT03FV042",request);
            }
            Akses aksesDB = opAkses.get();
            resAksesDTO = mapToDTO(aksesDB);
        }catch (Exception e){
            return GlobalResponse.terjadiKesalahan("AUT03FE041",request);
        }

        return GlobalResponse.dataDitemukan(resAksesDTO,request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {//051-060
        Page<Akses> page = null;
        List<RepAksesDTO> listDTO = null;
        Map<String,Object> data = null;
        try {
            switch (columnName){
                case "nama":page = aksesRepo.findByNamaContainsIgnoreCase(value,pageable);break;
                case "deskripsi":page = aksesRepo.findByDeskripsiContainsIgnoreCase(value,pageable);break;
                default:page = aksesRepo.findAll(pageable);
            }
            if(page.isEmpty()){
                return GlobalResponse.dataTidakDitemukan("AUT03FV051",request);
            }
            listDTO = mapToDTO(page.getContent());
            data = tp.transformPagination(listDTO,page,columnName,value);
        }catch (Exception e){
            return GlobalResponse.terjadiKesalahan("AUT03FE051",request);
        }
        return GlobalResponse.dataDitemukan(listDTO,request);
    }

    /** additional function */

    public Akses mapToAkses(ValAksesDTO valAksesDTO){
        return modelMapper.map(valAksesDTO, Akses.class);
    }

    public List<RepAksesDTO> mapToDTO(List<Akses> listAkses){
        return modelMapper.map(listAkses,new TypeToken<List<RepAksesDTO>>(){}.getType());
    }

    public ResAksesDTO mapToDTO(Akses akses){
        return modelMapper.map(akses,ResAksesDTO.class);
    }

}
