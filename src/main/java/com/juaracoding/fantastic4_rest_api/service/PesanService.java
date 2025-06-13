package com.juaracoding.fantastic4_rest_api.service;


import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.core.IReport;
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
import com.juaracoding.fantastic4_rest_api.utils.ExcelWriter;
import com.juaracoding.fantastic4_rest_api.utils.GlobalFunction;
import com.juaracoding.fantastic4_rest_api.utils.GlobalResponse;
import com.juaracoding.fantastic4_rest_api.utils.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class PesanService implements IService<Pesan>, IReport<Pesan> {

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
//            pesan.setCreatedBy(1L); // Assuming 1L is the ID of the user creating the record
            if(OtherConfig.getEnableAutomationTesting().isEmpty()) pesan.setTanggalPemesanan(LocalDate.now());
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
            pesanDB.setModifiedBy(String.valueOf(1L)); // Assuming 1L is the ID of the user updating the record
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
        if (OtherConfig.getEnableAutomationTesting().equalsIgnoreCase("y")){
            sBuild.append("PesanService.findAll: Berhasil mendapatkan data Pesan");
            System.out.println(sBuild);
            return GlobalResponse.dataDitemukan(data, request);
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
        if (OtherConfig.getEnableAutomationTesting().equalsIgnoreCase("y")){
            sBuild.append("PesanService.findByParam.");
            System.out.println(sBuild);
            return GlobalResponse.dataDitemukan(data, request);
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


    @Override
    public List<Pesan> convertListWorkBookToListEntity(List<Map<String, String>> workbookData,
                                     String userId) {
        List<Pesan> list = new ArrayList<>();
        for (Map<String, String> row : workbookData) {
            Pesan pesan = new Pesan();
            pesan.setNamaPertemuan(row.get("namaPertemuan"));
            pesan.setTanggalPertemuan(LocalDate.parse(row.get("tanggalPertemuan")));
            pesan.setMulai(Time.valueOf(row.get("mulai").toString()));
            pesan.setBerakhir(Time.valueOf(row.get("berakhir").toString()));
            pesan.setStatus(row.get("status"));
            pesan.setCreatedBy(userId); // Assuming userId is passed correctly
            list.add(pesan);
        }
        return list;
    }

    public void downloadReportExcel(String column, String value,
                                    HttpServletRequest request, HttpServletResponse response) {
        List<Pesan> listPesan = null;
        try{
            switch (column) {
                case "namaPertemuan":
                    listPesan = pesanRepo.findByNamaPertemuanContainsIgnoreCase(value);
                    break;
                case "idUser":
                    listPesan = pesanRepo.findByUserId(value);
                    break;
                case "idRuangan":
                    listPesan = pesanRepo.findByRuanganId(value);
                    break;
                case "status":
                    listPesan = pesanRepo.findByStatusIgnoreCase(value);
                    break;
                case "tanggalPertemuan":
                    LocalDate tanggal = LocalDate.parse(value); // Format: yyyy-MM-dd
                    listPesan = pesanRepo.findByTanggalPertemuan(tanggal);
                    break;
                default:listPesan = pesanRepo.findAll();
                    break;

            }
            if (listPesan.isEmpty()) {
                GlobalResponse.manualResponse(response,GlobalResponse.dataTidakDitemukan("PES71", request));
                return;
            }
            List<RepPesanDTO> listDTO = mapToDTO(listPesan);

            String headerKey = "conetent-disposition";
            sBuild.setLength(0);
            String headerValue = sBuild.append("attachment; filename=pesan.xlsx").
                    append(new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date())).
                    append(".xlsx").toString();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader(headerKey, headerValue);

            Map<String, Object> map = GlobalFunction.convertClassToMap(new RepPesanDTO());
            List<String> listTemp = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()){
                listTemp.add(entry.getKey());
            }
            int intListTemp = listTemp.size();
            String [] headerArr = new String[intListTemp];
            String [] loppDataArr = new String[intListTemp];

            for (int i = 0 ; i < intListTemp; i++){
                headerArr[i] = GlobalFunction.camelToStandard(listTemp.get(i));
                loppDataArr[i] = listTemp.get(i);
            }
            int intListDTOSize = listDTO.size();
            String [][] strBody = new String[intListDTOSize][intListTemp];
            for(int i = 0; i < intListDTOSize; i++){
                map = GlobalFunction.convertClassToMap(listDTO.get(i));
                for (int j = 0; j < intListTemp; j++){
                    strBody[i][j] = String.valueOf(map.get(loppDataArr[j]));
                }
            }
            new ExcelWriter(strBody,headerArr,"sheet-1",response);
        } catch (Exception e) {
            GlobalResponse.
                    manualResponse(response, GlobalResponse.terjadiKesalahan("PES72", request));
            return;
        }
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
