package com.juaracoding.fantastic4_rest_api.service;

import com.juaracoding.fantastic4_rest_api.core.IService;
import com.juaracoding.fantastic4_rest_api.dto.report.RepMenuDTO;
import com.juaracoding.fantastic4_rest_api.dto.response.ResMenuDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValMenuDTO;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import com.juaracoding.fantastic4_rest_api.model.Menu;
import com.juaracoding.fantastic4_rest_api.repo.MenuRepo;
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
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Kode Platform / Aplikasi : AUT
 * Kode Modul : 02
 * Kode Validation / Error  : FV - FE
 */
@Service
@Transactional
public class MenuService implements IService<Menu> {
    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination tp;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

//    @Autowired
//    private PdfGenerator pdfGenerator;

    private StringBuilder sBuild = new StringBuilder();

    @Override
    public ResponseEntity<Object> save(Menu menu, HttpServletRequest request) {//001-010
        try{
            if(menu == null){
                return new ResponseHandler().handleResponse("Object Null !!", HttpStatus.BAD_REQUEST,null,"AUT02FV001",request);
            }
            menu.setCreatedBy(1L);
            menuRepo.save(menu);
        }catch (Exception e){
            return GlobalResponse.dataGagalDisimpan("AUT02FE001",request);
        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    public ResponseEntity<Object> update(String id, Menu menu, HttpServletRequest request) {//011-020
        try{
            if(id == null){
                return GlobalResponse.objectIsNull("AUT02FV011",request);
            }
            if(menu == null){
                return GlobalResponse.objectIsNull("AUT02FV012",request);
            }
            Optional<Menu> opMenu = menuRepo.findById(Long.valueOf(id));
            if(!opMenu.isPresent()){
                return GlobalResponse.dataTidakDitemukan("AUT02FV013",request);
            }
            Menu menuDB = opMenu.get();
            menuDB.setNama(menu.getNama());
            menuDB.setDeskripsi(menu.getDeskripsi());
            menuDB.setPath(menu.getPath());
            menuDB.setModifiedBy(1L);

        }catch (Exception e){
            return GlobalResponse.dataGagalDiubah("AUT02FE011",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    public ResponseEntity<Object> delete(String id, HttpServletRequest request) {//021-030
        try{
            if(id==null){
                return GlobalResponse.objectIsNull("AUT02FV021",request);
            }
            Optional<Menu> opMenu = menuRepo.findById(Long.valueOf(id));
            if(!opMenu.isPresent()){
                return GlobalResponse.dataTidakDitemukan("AUT02FV022",request);
            }
            menuRepo.deleteById(Long.valueOf(id));

        }catch (Exception e){
            return GlobalResponse.dataGagalDihapus("AUT02FE021",request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {//031-040
        Page<Menu> page = null;
        List<Menu> list = null;
        List<RepMenuDTO> listDTO = null;
        Map<String,Object> data = null;
        try {
            page = menuRepo.findAll(pageable);
            if(page.isEmpty()){
                return GlobalResponse.dataTidakDitemukan("AUT02FV031",request);
            }
            listDTO = mapToDTO(page.getContent());
            data = tp.transformPagination(listDTO,page,"id","");
        }catch (Exception e){
            return GlobalResponse.terjadiKesalahan("AUT02FE031",request);
        }
        return GlobalResponse.dataDitemukan(listDTO,request);
    }

    @Override
    public ResponseEntity<Object> findById(String id, HttpServletRequest request) {//041-050
        ResMenuDTO resMenuDTO = null;
        try{
            if(id==null){
                return GlobalResponse.objectIsNull("AUT02FV041",request);

            }
            Optional<Menu> opMenu = menuRepo.findById(Long.valueOf(id));
            if(!opMenu.isPresent()){
                return GlobalResponse.dataTidakDitemukan("AUT02FV042",request);
            }
            Menu menuDB = opMenu.get();
            resMenuDTO = mapToDTO(menuDB);
        }catch (Exception e){
            return GlobalResponse.terjadiKesalahan("AUT02FE041",request);
        }

        return GlobalResponse.dataDitemukan(resMenuDTO,request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {//051-060
        Page<Menu> page = null;
        List<RepMenuDTO> listDTO = null;
        Map<String,Object> data = null;
        try {
            switch (columnName){
                case "nama":page = menuRepo.findByNamaContainsIgnoreCase(value,pageable);break;
                case "deskripsi":page = menuRepo.findByDeskripsiContainsIgnoreCase(value,pageable);break;
                case "path":page = menuRepo.findByPathContainsIgnoreCase(value,pageable);break;
                default:page = menuRepo.findAll(pageable);
            }
            if(page.isEmpty()){
                return GlobalResponse.dataTidakDitemukan("AUT02FV051",request);
            }
            listDTO = mapToDTO(page.getContent());
            data = tp.transformPagination(listDTO,page,columnName,value);
        }catch (Exception e){
            return GlobalResponse.terjadiKesalahan("AUT02FE051",request);
        }
        return GlobalResponse.dataDitemukan(listDTO,request);
    }

    /** additional function */

    public Menu mapToMenu(ValMenuDTO valMenuDTO){
        return modelMapper.map(valMenuDTO, Menu.class);
    }

    public List<RepMenuDTO> mapToDTO(List<Menu> listMenu){
        return modelMapper.map(listMenu,new TypeToken<List<RepMenuDTO>>(){}.getType());
    }

    public ResMenuDTO mapToDTO(Menu menu){
        return modelMapper.map(menu,ResMenuDTO.class);
    }



}
