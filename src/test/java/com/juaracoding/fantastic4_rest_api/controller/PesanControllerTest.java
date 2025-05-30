package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
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
        String nama = dataGenerator.dataNamaTim();
        String path = "/"+nama.toLowerCase().replace(" ","-");
        try{
            req.put("user", pesan.getUser().getId());
            req.put("ruangan", pesan.getRuangan().getId());
            req.put("tanggalPemesanan",dataGenerator.dataTanggalPemesanan());
            req.put("namaPertemuan", dataGenerator.dataNamaPertemuan());
            req.put("tanggalPertemuan",dataGenerator.dataTanggalPertemuan());
            req.put("mulai",pesan.getMulai());
            req.put("berakhir",pesan.getBerakhir());
            req.put("durasi",pesan.getDurasi());
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
    }

    @Test(priority = 10)
    void update(){
        Response response;
        req.clear();
        try{
            String reqUserID = pesan.getUser().getId();
            String reqRuanganID = pesan.getRuangan().getId();
            LocalDate reqTanggalPemesanan = LocalDate.parse(dataGenerator.dataTanggalPemesanan());
            String reqNamaPertemuan = dataGenerator.dataNamaPertemuan();
            LocalDate reqTanggalPertemuan = LocalDate.parse(dataGenerator.dataTanggalPertemuan());
            Time reqMulai = pesan.getMulai();
            Time reqBerakhir = pesan.getBerakhir();
            BigDecimal reqDurasi = pesan.getDurasi();
            String reqStatus = pesan.getStatus();


            pesan.setTanggalPemesanan(reqTanggalPemesanan);
            pesan.setNamaPertemuan(reqNamaPertemuan);
            pesan.setTanggalPertemuan(reqTanggalPertemuan);
            pesan.setMulai(reqMulai);
            pesan.setBerakhir(reqBerakhir);
            pesan.setDurasi(reqDurasi);
            pesan.setStatus(reqStatus);

            req.put("user",reqUserID);
            req.put("ruangan",reqRuanganID);
            req.put("tanggalPemesanan",reqTanggalPemesanan);
            req.put("namaPertemuan",reqNamaPertemuan);
            req.put("tanggalPertemuan",reqTanggalPertemuan);
            req.put("mulai",reqMulai);
            req.put("berakhir",reqBerakhir);
            req.put("durasi",reqDurasi);
            req.put("status",reqStatus);

            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                    body(req).
                    request(Method.PUT,"pesan/"+pesan.getPesanID());

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

    @Test (priority = 20)
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

            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("messege"), "DATA BERHASIL DIHAPUS");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 30)
    void findById(){
        Response response;
        try{
            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        request(Method.GET,"pesan/"+pesan.getPesanID());

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            Assert.assertEquals(intResponse,200);
            Assert.assertEquals(jsonPath.getString("message"),"DATA BERHASIL DITEMUKAN");
            Assert.assertEquals(jsonPath.getString("data.id"),pesan.getPesanID());
            Assert.assertEquals(jsonPath.getString("data.user.id"),pesan.getUser().getId());
            Assert.assertEquals(jsonPath.getString("data.ruangan.id"),pesan.getRuangan().getId());
            Assert.assertEquals(jsonPath.getString("data.tanggalPemesanan"),pesan.getTanggalPemesanan());
            Assert.assertEquals(jsonPath.getString("data.namaPertemuan"),pesan.getNamaPertemuan());
            Assert.assertEquals(jsonPath.getString("data.tanggalPertemuan"),pesan.getTanggalPertemuan());
            Assert.assertEquals(jsonPath.getString("data.mulai"),pesan.getMulai());
            Assert.assertEquals(jsonPath.getString("data.berakhir"),pesan.getBerakhir());
            Assert.assertEquals(jsonPath.getString("data.durasi"),pesan.getDurasi());
            Assert.assertEquals(jsonPath.getString("data.status"),pesan.getStatus());
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 40)
    void findByAll(){
        Response response;
        try{
            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        request(Method.GET,"pesan/");

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            List ltData = jsonPath.getList("data.content");
            int intData = ltData.size();

            Assert.assertEquals(intResponse,200);
            Assert.assertEquals(jsonPath.getString("message"),"DATA BERHASIL DITEMUKAN");
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

    @Test(priority = 50)
    void findByParam() {
        Response response;
        String pathVariable = "/pesan/asc/id/0";
        String strValue = String.valueOf(pesan.getTanggalPemesanan());

        try {
            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
                    params("value", strValue).
                    params("size", 10).
                    request(Method.GET, pathVariable);
            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            List<Map<String, Object>> ltData = jsonPath.getList("data.content");
            int intData = ltData.size();
            Map<String, Object> map = ltData.get(0);

            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("message"), "DATA BERHASIL DITEMUKAN");
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));

            Assert.assertEquals(jsonPath.getString("data.sort-by"), "id");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.current-page")), 0);
            Assert.assertEquals(jsonPath.getString("data.column-name"), "id");
            Assert.assertNotNull(jsonPath.getString("data.total-pages"));
            Assert.assertEquals(jsonPath.getString("data.sort"), "asc");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.size-per-page")), 10);
            Assert.assertEquals(jsonPath.getString("data.value"), strValue);

            Assert.assertEquals(jsonPath.getString("data.user.id"),pesan.getUser().getId());
            Assert.assertEquals(jsonPath.getString("data.ruangan.id"),pesan.getRuangan().getId());
            Assert.assertEquals(jsonPath.getString("data.tanggalPemesanan"),pesan.getTanggalPemesanan());
            Assert.assertEquals(jsonPath.getString("data.namaPertemuan"),pesan.getNamaPertemuan());
            Assert.assertEquals(jsonPath.getString("data.tanggalPertemuan"),pesan.getTanggalPertemuan());
            Assert.assertEquals(jsonPath.getString("data.mulai"),pesan.getMulai());
            Assert.assertEquals(jsonPath.getString("data.berakhir"),pesan.getBerakhir());
            Assert.assertEquals(jsonPath.getString("data.durasi"),pesan.getDurasi());
            Assert.assertEquals(jsonPath.getString("data.status"),pesan.getStatus());
            Assert.assertEquals(map.get("id").toString(), pesan.getPesanID());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
