package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelAksesDTO;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelRuanganDTO;
import com.juaracoding.fantastic4_rest_api.model.Fasilitas;
import com.juaracoding.fantastic4_rest_api.repo.FasilitasRepo;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FacilityControllerTest extends AbstractTestNGSpringContextTests {

        @Autowired
        private FasilitasRepo fasilitasRepo;
        private JSONObject req;
        private Fasilitas fasilitas;
        private Random rand;
        private DataGenerator dataGenerator;
        private String idFasilitasTest;

        @BeforeClass
        private void init(){
//            token = new TokenGenerator(AuthControllerTest.authorization).getToken();
        rand  = new Random();
        req = new JSONObject();
        fasilitas = new Fasilitas();
        dataGenerator = new DataGenerator();
        Optional<Fasilitas> op = fasilitasRepo.findTop1ByOrderByIdDesc();
        fasilitas = op.get();
    }

    @BeforeTest
    private void setup(){
        /** sifatnya optional */
    }

    @Test(priority = 0)
    void save(){
        for (int i = 0; i<10 ; i++){
            Response response ;
            idFasilitasTest = dataGenerator.dataIdFasilitas();
            String nama = dataGenerator.dataNamaFasilitas();
            String path = "/"+nama.toLowerCase().replace(" ","-");
            try{
                req.put("id", idFasilitasTest);
                req.put("namaFasilitas", dataGenerator.dataNamaFasilitas());
                req.put("jumlah", fasilitas.getJumlah());
                RelRuanganDTO relRuanganDTO = new RelRuanganDTO();
                relRuanganDTO.setId(fasilitas.getRuanganID().getId());
                req.put("ruangan", relRuanganDTO);
//            RelAksesDTO relAksesDTO = new RelAksesDTO();
//            req.put("akses",relAksesDTO);

                response = given().
                        header("Content-Type","application/json").
                        header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        body(req).
                        request(Method.POST,"facility");

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
    }

    @Test(priority = 10)
    void update(){
        Response response;
        req.clear();
        try{
//            String resId = fasilitas.getId();
            String reqId = dataGenerator.dataIdFasilitas();
            String reqNamaFasilitas = dataGenerator.dataNamaFasilitas();
            Short reqJumlah = dataGenerator.dataJumlahFasilitas();

            fasilitas.setId(reqId);
            fasilitas.setNamaFasilitas(reqNamaFasilitas);
            fasilitas.setJumlah(reqJumlah);
            RelRuanganDTO relRuanganDTO = new RelRuanganDTO();
            relRuanganDTO.setId(fasilitas.getRuanganID().getId());


            req.put("id", reqId);
            req.put("namaFasilitas", reqNamaFasilitas);
            req.put("jumlah", reqJumlah);
            req.put("ruangan", relRuanganDTO);


            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        body(req).
                    request(Method.PUT,"facility/"+ idFasilitasTest);

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            Assert.assertEquals(intResponse,200);
            Assert.assertEquals(jsonPath.getString("message"),"DATA BERHASIL DIUBAH");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
            System.out.println(response.getBody().prettyPrint());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test(priority = 20)
    void findById(){
        Response response;
        try{
            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        request(Method.GET,"facility/"+idFasilitasTest);

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            Assert.assertEquals(intResponse,200);
            Assert.assertEquals(jsonPath.getString("message"),"DATA DITEMUKAN");
            Assert.assertEquals(jsonPath.getString("data.id"), idFasilitasTest);
//            Assert.assertEquals(jsonPath.getString("data.ruangan"),fasilitas.getRuanganID().getId());
            Assert.assertEquals(jsonPath.getString("data.namaFasilitas"),fasilitas.getNamaFasilitas());
            Assert.assertEquals(jsonPath.getString("data.jumlah"),fasilitas.getJumlah().toString());
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
            System.out.println(response.getBody().prettyPrint());
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
            request(Method.GET,"facility");

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            System.out.println(response.getBody().prettyPrint());
            List ltData = jsonPath.getList("data.content");
            int intData = (ltData != null) ? ltData.size() : 0;

            Assert.assertEquals(intResponse,200);
            Assert.assertEquals(jsonPath.getString("message"),"DATA DITEMUKAN");
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
// ======================================================================================================================================================
            System.out.println("data.sort-by = " + jsonPath.getString("data.sort-by"));
            Assert.assertEquals(jsonPath.getString("data.sort-by"),"id");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.current-page")),0);
            System.out.println("data.column-name = " + jsonPath.getString("data.column-name"));
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
        String pathVariable = "/facility/asc/id/0";
        String strValue = fasilitas.getNamaFasilitas();

        try {
            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
                    params("size", 10).
                    params("column", "namaFasilitas").
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
            Assert.assertEquals(jsonPath.getString("data.column-name"), "namaFasilitas");
            Assert.assertNotNull(jsonPath.getString("data.total-pages"));
            Assert.assertEquals(jsonPath.getString("data.sort"), "asc");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.size-per-page")), 10);
            Assert.assertEquals(jsonPath.getString("data.value"), strValue);

//            Assert.assertEquals(jsonPath.getString("data.content.id"), fasilitas.getId());
//            Assert.assertEquals(jsonPath.getString("data.ruangan"), fasilitas.getRuanganID().getId());
//            Assert.assertEquals(jsonPath.getString("data.namaFasilitas"), fasilitas.getNamaFasilitas());
//            Assert.assertEquals(jsonPath.getString("data.jumlah"), fasilitas.getJumlah());
//            Assert.assertEquals(map.get("id").toString(), idFasilitasTest);
            Assert.assertEquals(map.get("ruangan").toString(), fasilitas.getRuanganID().getId());
            Assert.assertEquals(map.get("namaFasilitas").toString(), fasilitas.getNamaFasilitas());
            Assert.assertEquals(map.get("jumlah").toString(), fasilitas.getJumlah().toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test (priority = 50)
    void delete(){
        Response response;
//        String resId = fasilitas.getId();
        try {

            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        request(Method.DELETE, "facility/" + idFasilitasTest);

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