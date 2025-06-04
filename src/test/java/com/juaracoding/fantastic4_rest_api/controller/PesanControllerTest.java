package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelRuanganDTO;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelUserDTO;
import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.repo.PesanRepo;
import com.juaracoding.fantastic4_rest_api.utils.DataGenerator;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PesanControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private PesanRepo pesanRepo;
    private JSONObject req;
    private Pesan pesan;
    private Random rand;
    private DataGenerator dataGenerator;

    @BeforeClass
    private void init(){
//            token = new TokenGenerator(AuthControllerTest.authorization).getToken();
        rand  = new Random();
        req = new JSONObject();
        pesan = new Pesan();
        dataGenerator = new DataGenerator();
        Optional<Pesan> op = pesanRepo.findTop1ByOrderByIdDesc();
        pesan = op.get();
    }

    @BeforeTest
    private void setup(){
        /** sifatnya optional */
    }

    @Test(priority = 0)
    void save(){
        Response response ;
//        String nama = dataGenerator.dataNamaTim();
//        String path = "/"+nama.toLowerCase().replace(" ","-");
          Integer it = 1;
          String waktuMulai = dataGenerator.dataWaktuMulai();
        String waktuBerakhir = dataGenerator.dataWaktuBerakhir(waktuMulai);
        try{
            req.put("id", pesan.getPesanID()+it);
            RelUserDTO relUserDTO = new RelUserDTO();
            relUserDTO.setId(pesan.getUser().getId());
            req.put("user", relUserDTO);
            RelRuanganDTO relRuanganDTO = new RelRuanganDTO();
            relRuanganDTO.setId(pesan.getRuangan().getId());
            req.put("ruangan", relRuanganDTO);
            req.put("tanggal-pemesanan",dataGenerator.dataTanggalPemesanan());
            req.put("namaPertemuan", dataGenerator.dataNamaPertemuan());
            req.put("tanggal-pertemuan", dataGenerator.dataTanggalPertemuan());
            req.put("mulai",waktuBerakhir);
            req.put("berakhir",waktuBerakhir);
            req.put("durasi",dataGenerator.dataDurasi(waktuMulai,waktuBerakhir));
            req.put("status",pesan.getStatus());

            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                    body(req).
                    request(Method.POST,"pesan");

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse,201);
            Assert.assertEquals(jsonPath.getString("message"),"SAVE SUCCESS !!");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        it++;
    }

    @Test(priority = 10)
    void update(){
        Response response;
        req.clear();
        try{
//            String reqUserID = pesan.getUser().getId();
//            String reqRuanganID = pesan.getRuangan().getId();
//            LocalDate reqTanggalPemesanan = LocalDate.parse(dataGenerator.dataTanggalPemesanan());
            String reqNamaPertemuan = dataGenerator.dataNamaPertemuan();
            LocalDate reqTanggalPertemuan = LocalDate.parse(dataGenerator.dataTanggalPertemuan());
            String reqMulai = dataGenerator.dataWaktuMulai();
            String reqBerakhir = dataGenerator.dataWaktuBerakhir(reqMulai);
//            BigDecimal reqDurasi = dataGenerator.dataDurasi(reqMulai,reqBerakhir);
            String reqStatus = pesan.getStatus();


            RelRuanganDTO relRuanganDTO = new RelRuanganDTO();
            relRuanganDTO.setId(pesan.getRuangan().getId());
            RelUserDTO relUserDTO = new RelUserDTO();
            relUserDTO.setId(pesan.getUser().getId());
            pesan.setTanggalPemesanan(pesan.getTanggalPemesanan());
            pesan.setNamaPertemuan(reqNamaPertemuan);
            pesan.setTanggalPertemuan(reqTanggalPertemuan);
            pesan.setMulai(Time.valueOf(reqMulai));
            pesan.setBerakhir(Time.valueOf(reqBerakhir));
//            pesan.setDurasi(reqDurasi);
            pesan.setStatus(reqStatus);

            req.put("id",pesan.getPesanID());
            req.put("user", relUserDTO);
            req.put("ruangan",relRuanganDTO);
            req.put("tanggal-pemesanan",pesan.getTanggalPemesanan());
            req.put("namaPertemuan",reqNamaPertemuan);
            req.put("tanggal-pertemuan",reqTanggalPertemuan);
            req.put("mulai",reqMulai);
            req.put("berakhir",reqBerakhir);
            req.put("durasi", dataGenerator.dataDurasi(reqMulai,reqBerakhir));
            req.put("status",reqStatus);

            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                    body(req).
                    request(Method.PUT,"pesan/"+pesan.getPesanID());

            System.out.println(response.getBody().prettyPrint());

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            Assert.assertEquals(intResponse,200);
            Assert.assertEquals(jsonPath.getString("message"),"DATA BERHASIL DIUBAH");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 20)
    void findById(){
        Response response;
        RelRuanganDTO relRuanganDTO = new RelRuanganDTO();
        relRuanganDTO.setId(pesan.getRuangan().getId());
        RelUserDTO relUserDTO = new RelUserDTO();
        relUserDTO.setId(pesan.getUser().getId());
        try{
            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        request(Method.GET,"pesan/"+pesan.getPesanID());

            System.out.println(response.getBody().prettyPrint());
            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            Assert.assertEquals(intResponse,200);
            Assert.assertEquals(jsonPath.getString("message"),"DATA DITEMUKAN");
            Assert.assertEquals(jsonPath.getString("data.id"),String.valueOf(pesan.getPesanID()));
//            Assert.assertEquals(jsonPath.getString("data.user.id"), relUserDTO.getId());
            Assert.assertEquals(jsonPath.getString("data.ruangan.id"),relRuanganDTO.getId());
            Assert.assertEquals(jsonPath.getString("data.tanggal-pemesanan"),String.valueOf(pesan.getTanggalPemesanan()));
            Assert.assertEquals(jsonPath.getString("data.nama-pertemuan"),pesan.getNamaPertemuan());
            Assert.assertEquals(jsonPath.getString("data.tanggal-pertemuan"),String.valueOf(pesan.getTanggalPertemuan()));
            Assert.assertEquals(jsonPath.getString("data.mulai"),String.valueOf(pesan.getMulai()));
            Assert.assertEquals(jsonPath.getString("data.berakhir"),String.valueOf(pesan.getBerakhir()));
            Assert.assertEquals(jsonPath.getString("data.durasi"),String.valueOf(pesan.getDurasi()));
            Assert.assertEquals(jsonPath.getString("data.status"),pesan.getStatus());
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 30)
    void findAll(){
        Response response;
        try{
            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        request(Method.GET,"pesan");

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            List ltData = jsonPath.getList("data.content");
            int intData = ltData.size();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse,200);
            Assert.assertEquals(jsonPath.getString("message"),"DATA DITEMUKAN");
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
// ======================================================================================================================================================
            Assert.assertEquals(jsonPath.getString("data.sort-by"),"id");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.current-page")),0);
            Assert.assertEquals(jsonPath.getString("data.column-name"),"id");
            Assert.assertNotNull(jsonPath.getString("data.total-pages"));
            Assert.assertEquals(jsonPath.getString("data.sort"),"asc");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.size-per-page")), OtherConfig.getDefaultPaginationSize());
            Assert.assertEquals(jsonPath.getString("data.value"),"");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.total-data")),intData);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 40)
    void findByParam() {
        Response response;
        String pathVariable = "/pesan/asc/id/0";
        String strValue = pesan.getNamaPertemuan();
        RelRuanganDTO relRuanganDTO = new RelRuanganDTO();
        relRuanganDTO.setId(pesan.getRuangan().getId());
        RelUserDTO relUserDTO = new RelUserDTO();
        relUserDTO.setId(pesan.getUser().getId());

        try {
            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
                    params("size", 10).
                    params("column", "namaPertemuan").
                    params("value", strValue).
                    request(Method.GET, pathVariable);
            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            List<Map<String, Object>> ltData = jsonPath.getList("data.content");
            int intData = ltData.size();
            Map<String, Object> map = ltData.get(0);

            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("message"), "DATA DITEMUKAN");
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));

            Assert.assertEquals(jsonPath.getString("data.sort-by"), "id");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.current-page")), 0);
            Assert.assertEquals(jsonPath.getString("data.column-name"), "namaPertemuan");
            Assert.assertNotNull(jsonPath.getString("data.total-pages"));
            Assert.assertEquals(jsonPath.getString("data.sort"), "asc");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.size-per-page")), 10);
            Assert.assertEquals(jsonPath.getString("data.value"), strValue);

//            Assert.assertEquals(jsonPath.getString("data.user.id"),pesan.getUser().getId());
//            Assert.assertEquals(jsonPath.getString("data.ruangan.id"),pesan.getRuangan().getId());
//            Assert.assertEquals(jsonPath.getString("data.tanggalPemesanan"),String.valueOf(pesan.getTanggalPemesanan()));
//            Assert.assertEquals(jsonPath.getString("data.nama-pertemuan"),pesan.getNamaPertemuan());
//            Assert.assertEquals(jsonPath.getString("data.tanggal-pertemuan"),String.valueOf(pesan.getTanggalPertemuan()));
//            Assert.assertEquals(jsonPath.getString("data.mulai"),String.valueOf(pesan.getMulai()));
//            Assert.assertEquals(jsonPath.getString("data.berakhir"),String.valueOf(pesan.getBerakhir()));
//            Assert.assertEquals(jsonPath.getString("data.durasi"),String.valueOf(pesan.getDurasi()));
//            Assert.assertEquals(jsonPath.getString("data.status"),pesan.getStatus());
            Assert.assertEquals(map.get("id").toString(), String.valueOf(pesan.getPesanID()));
            Assert.assertEquals(map.get("user.id").toString(), pesan.getUser().getId());
            Assert.assertEquals(map.get("ruangan.id").toString(), pesan.getRuangan().getId());
            Assert.assertEquals(map.get("tanggal-pemesanan").toString(), String.valueOf(pesan.getTanggalPemesanan()));
            Assert.assertEquals(map.get("nama-pertemuan").toString(), pesan.getNamaPertemuan());
            Assert.assertEquals(map.get("tanggal-pertemuan").toString(), String.valueOf(pesan.getTanggalPertemuan()));
            Assert.assertEquals(map.get("mulai").toString(), String.valueOf(pesan.getMulai()));
            Assert.assertEquals(map.get("berakhir").toString(), String.valueOf(pesan.getBerakhir()));
            Assert.assertEquals(map.get("durasi").toString(), String.valueOf(pesan.getDurasi()));
            Assert.assertEquals(map.get("status").toString(), pesan.getStatus());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test (priority = 50)
    void delete(){
        Response response;
        try {
            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        request(Method.DELETE, "pesan/" + pesan.getPesanID());

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();

            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("message"), "DATA BERHASIL DIHAPUS");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        findAll();
    }
}
