package com.juaracoding.fantastic4_rest_api.service;


import com.juaracoding.fantastic4_rest_api.core.IService;
import com.juaracoding.fantastic4_rest_api.dto.report.RepFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.dto.report.RepPesanDTO;
import com.juaracoding.fantastic4_rest_api.dto.response.ResFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.dto.response.ResPesanDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValPesanDTO;
import com.juaracoding.fantastic4_rest_api.handler.ResponseHandler;
import com.juaracoding.fantastic4_rest_api.model.Fasilitas;
import com.juaracoding.fantastic4_rest_api.model.Menu;
import com.juaracoding.fantastic4_rest_api.utils.GlobalResponse;
import com.juaracoding.fantastic4_rest_api.utils.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.repo.PesanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class PesanService implements IService<Pesan> {

    @Autowired
    private PesanRepo pesanRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination tp;

    private StringBuilder sBuild = new StringBuilder();

    @Override
    public ResponseEntity<Object> save(Pesan pesan, HttpServletRequest request) {// 001-010
        try{
            if(pesan==null){
                return new ResponseHandler().handleResponse("Object Null !!", HttpStatus.BAD_REQUEST, null, "PES01", request);
            }
            pesan.setCreatedBy(1L);
            pesan.setStatus("pending");
            pesanRepo.save(pesan);// Assuming 1L is the ID of the user creating the record
        } catch (Exception e) {
            return GlobalResponse.dataGagalDisimpan("PES002", request);
        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    public ResponseEntity<Object> update(String id, Pesan pesan, HttpServletRequest request) {// 011-020
        try {
            if (id == null) {
                return new ResponseHandler().handleResponse("ID Null !!", HttpStatus.BAD_REQUEST, null, "PES11", request);

            }if (pesan == null) {
                return new ResponseHandler().handleResponse("Object Null !!", HttpStatus.BAD_REQUEST, null, "PES12", request);
            }
            Optional<Pesan> opPesan = pesanRepo.findById(Long.valueOf(id));
            if (!opPesan.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PES13", request);
            }
            Pesan pesanDB = opPesan.get();
            pesanDB.setNamaPertemuan(pesan.getNamaPertemuan());
            pesanDB.setTanggalPertemuan(pesan.getTanggalPertemuan());
            pesanDB.setMulai(pesan.getMulai());
            pesanDB.setBerakhir(pesan.getBerakhir());
            pesanDB.setStatus(pesan.getStatus());
            pesanDB.setModifiedBy(1L); // Assuming 1L is the ID of the user updating the record
            pesanDB.setModifiedDate(LocalDateTime.now());
        } catch (Exception e) {
            return GlobalResponse.dataGagalDiubah("PES14", request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    public ResponseEntity<Object> delete(String id, HttpServletRequest request) {// 021-030
        try{
            if(id == null || id.isEmpty()) {
                return new ResponseHandler().handleResponse("ID Null !!", HttpStatus.BAD_REQUEST, null, "PES21", request);
            }
            Optional<Pesan> opPesan = pesanRepo.findById(Long.valueOf(id));
            if (!opPesan.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PES22", request);
            }
            pesanRepo.deleteById(Long.valueOf(id));

        } catch (Exception e) {
            return GlobalResponse.dataGagalDihapus("PES23", request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {// 031-040
        Page<Pesan> page = null;
        List<Pesan> list = null;
        List<RepPesanDTO> listDTO = null;
        Map<String, Object> data = null;

        try{
            page = pesanRepo.findAll(pageable);
            if (page.isEmpty()){
                return GlobalResponse.dataTidakDitemukan("PES31", request);
            }
            listDTO = mapToDTO(page.getContent());
            data = tp.transformPagination(listDTO, page, "id", "");
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("PES32", request);
        }
        return GlobalResponse.dataDitemukan(listDTO, request);
    }



    @Override
    public ResponseEntity<Object> findById(String id, HttpServletRequest request) {// 041-050
        ResPesanDTO resPesanDTO = null;
        try{
            if (id == null){
                return GlobalResponse.objectIsNull("PES41", request);
            }
            Optional<Pesan> opPesan = pesanRepo.findById(Long.valueOf(id));
            if (!opPesan.isPresent()){
                return GlobalResponse.dataTidakDitemukan("PES42", request);
            }
            Pesan pesanDB = opPesan.get();
            resPesanDTO = mapToDTO(pesanDB);
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("PES43", request);
        }
        return GlobalResponse.dataDitemukan(resPesanDTO, request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {// 051-060
        Page<Pesan> page = null;
        List<RepPesanDTO> listDTO = null;
        Map<String, Object> data = null;

        try{
            switch (columnName){
                case "namaPertemuan":page = pesanRepo.findByNamaPertemuanContainsIgnoreCase(value, pageable);break;
                case "idUser":page = pesanRepo.cariUser(value, pageable);break;
                case "idRuangan":page = pesanRepo.cariRuangan(value, pageable);break;
                case "status":page = pesanRepo.findByStatusIgnoreCase(value, pageable);break;
                case "tanggalPertemuan":
                try {
                    LocalDate tanggal = LocalDate.parse(value); // Format: yyyy-MM-dd
                    page = pesanRepo.findByTanggalPertemuan(tanggal, pageable);
                } catch (Exception e) {
                    return new ResponseHandler().handleResponse(
                            "Format tanggal salah. Gunakan format yyyy-MM-dd",
                            HttpStatus.BAD_REQUEST,
                            null,
                            "PES53",
                            request
                    );
                }
                break;

                default:
                    return new ResponseHandler().handleResponse(
                            "Kolom tidak dikenali.",
                            HttpStatus.BAD_REQUEST,
                            null,
                            "PES54",
                            request
                    );
            }

            if (page.isEmpty()){
                return GlobalResponse.dataTidakDitemukan("PES51", request);
            }
            listDTO = mapToDTO(page.getContent());
            data = tp.transformPagination(listDTO, page, columnName, value);
        } catch (Exception e) {
            GlobalResponse.terjadiKesalahan("PES52", request);
        }
        return GlobalResponse.dataDitemukan(listDTO, request);
    }

    public ResponseEntity<Object> findPesananUser(String idUser, HttpServletRequest request){
        ResPesanDTO resPesanDTO = null;
        try{
            if (idUser == null){
                return GlobalResponse.objectIsNull("PES61", request);
            }
            Optional<Pesan> opPesan = pesanRepo.cariUser(idUser);
            if (!opPesan.isPresent()){
                return GlobalResponse.dataTidakDitemukan("PES62", request);
            }
            Pesan pesanDB = opPesan.get();
            resPesanDTO = mapToDTO(pesanDB);
        } catch (Exception e) {
            return GlobalResponse.terjadiKesalahan("PES63", request);
        }
        return GlobalResponse.dataDitemukan(resPesanDTO, request);
    }

    /** aditonal function */

    public Pesan mapToPesan(ValPesanDTO valPesanDTO){
        return modelMapper.map(valPesanDTO, Pesan.class);
    }

    public List<RepPesanDTO> mapToDTO(List<Pesan> listPesan){
        return modelMapper.map(listPesan, new org.modelmapper.TypeToken<List<RepPesanDTO>>(){}.getType());

    }
    public ResPesanDTO mapToDTO(Pesan pesan){
        return modelMapper.map(pesan, ResPesanDTO.class);
    }

}
