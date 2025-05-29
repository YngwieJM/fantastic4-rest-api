package com.juaracoding.fantastic4_rest_api.service;

import com.juaracoding.fantastic4_rest_api.core.IService;
import com.juaracoding.fantastic4_rest_api.dto.report.RepFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.dto.response.ResFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import com.juaracoding.fantastic4_rest_api.model.Fasilitas;
import com.juaracoding.fantastic4_rest_api.repo.FasilitasRepo;
import com.juaracoding.fantastic4_rest_api.utils.GlobalResponse;
import com.juaracoding.fantastic4_rest_api.utils.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class FasilitasService implements IService<Fasilitas> {

    @Autowired
    private FasilitasRepo fasilitasRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination tp;

    private StringBuilder sBuild = new StringBuilder();


    @Override
    public ResponseEntity<Object> save(Fasilitas fasilitas, HttpServletRequest request){ //01-10
        try{
            if(fasilitas == null){
                return new ResponseHandler().handleResponse("OBJECT NULL !!", HttpStatus.BAD_REQUEST,null,"OBJECT_NULL",request);
            }
            fasilitas.setCreatedBy(1L); // Assuming 1L is the ID of the user creating the record
            fasilitasRepo.save(fasilitas);

        } catch (Exception e) {
            return GlobalResponse.dataGagalDisimpan("FAC001", request);
        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }


    public ResponseEntity<Object> update(String id, Fasilitas fasilitas, HttpServletRequest request){//11 - 20
        try {
            if (id == null || id.isEmpty()) {
                return new ResponseHandler().handleResponse("ID NULL !!", HttpStatus.BAD_REQUEST, null, "ID_NULL", request);
            }
            if (fasilitas == null){
                return new ResponseHandler().handleResponse("OBJECT NULL !!", HttpStatus.BAD_REQUEST,null,"OBJECT_NULL",request);
            }
            Optional<Fasilitas> opFasilitas = fasilitasRepo.findById(id);
            if (!opFasilitas.isPresent()){
                return GlobalResponse .dataTidakDitemukan("FAC011", request);
            }
            Fasilitas fasilitasDB = opFasilitas.get();
            fasilitasDB.setRuanganID(fasilitas.getRuanganID());
            fasilitasDB.setNamaFasilitas(fasilitas.getNamaFasilitas());
            fasilitasDB.setJumlah(fasilitas.getJumlah());
            fasilitasDB.setModifiedBy(1L); // Assuming 1L is the ID of the user updating the record

        } catch (Exception e) {
            return GlobalResponse.dataGagalDiubah("FAC012", request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }


    public ResponseEntity<Object> delete(String id, HttpServletRequest request){//21 - 30
        try {
            if (id == null) {
                return new ResponseHandler().handleResponse("ID NULL !!", HttpStatus.BAD_REQUEST, null, "ID_NULL", request);
            }
            Optional<Fasilitas> opFasilitas = fasilitasRepo.findById(id);
            if (!opFasilitas.isPresent()){
                return GlobalResponse.dataTidakDitemukan("FAC021", request);
            }
            fasilitasRepo.deleteById(id);

        } catch (Exception e) {
            return GlobalResponse.dataGagalDihapus("FAC022", request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {//31 - 40
        Page<Fasilitas> page = null;
        List<Fasilitas> list = null;
        List<RepFasilitasDTO> listDTO = null;
        Map<String, Object> data = null;
        try{
            page = fasilitasRepo.findAll(pageable);
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
    public ResponseEntity<Object> findById(String id, HttpServletRequest request){//41-50
        ResFasilitasDTO resFasilitasDTO = null;
        try {
            if(id == null){
                return GlobalResponse.objectIsNull("FAC041", request);
            }
            Optional<Fasilitas> opFasilitas = fasilitasRepo.findById(id);
            if (!opFasilitas.isPresent()){
                return GlobalResponse.dataTidakDitemukan("FAC042", request);
            }
            Fasilitas fasilitasDB = opFasilitas.get();
            resFasilitasDTO = mapToDTO(fasilitasDB);
        } catch (Exception e) {
            return  GlobalResponse.terjadiKesalahan("FAC043", request);
        }
        return GlobalResponse.dataDitemukan(resFasilitasDTO, request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request){//51-60
        Page<Fasilitas> page = null;
        List<RepFasilitasDTO> listDTO = null;
        Map<String, Object> data = null;
        try {
            switch (columnName){
                case"namaFasilitas": page = fasilitasRepo.findByNamaFasilitasContainsIgnoreCase(value, pageable);break;
                case "ruanganID": page = fasilitasRepo.cariRuangan(value, pageable);break;
                default:page = fasilitasRepo.findAll(pageable);break;
            }
            if (page.isEmpty()){
                return GlobalResponse.dataTidakDitemukan("FAC051", request);
            }
            listDTO = mapToDTO(page.getContent());
            data = tp.transformPagination(listDTO, page, columnName, value);
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("FAC042", request);
        }
        return GlobalResponse.dataDitemukan(listDTO, request);
    }


    /** aditonal function */

    public Fasilitas mapToFasilitas(ValFasilitasDTO valFasilitasDTO){
        return modelMapper.map(valFasilitasDTO, Fasilitas.class);
    }

    public List<RepFasilitasDTO> mapToDTO(List<Fasilitas> listFasilitas){
        return modelMapper.map(listFasilitas, new org.modelmapper.TypeToken<List<RepFasilitasDTO>>(){}.getType());

    }
    public ResFasilitasDTO mapToDTO(Fasilitas fasilitas){
        return modelMapper.map(fasilitas, ResFasilitasDTO.class);
    }

}
