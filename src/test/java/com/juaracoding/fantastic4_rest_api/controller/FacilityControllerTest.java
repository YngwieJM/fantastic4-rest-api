package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelAksesDTO;
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
        Response response ;
        String nama = dataGenerator.dataNamaFasilitas();
        String path = "/"+nama.toLowerCase().replace(" ","-");
        try{
            req.put("namaFasilitas", dataGenerator.dataNamaFasilitas());
            req.put("ruanganID",fasilitas.getRuanganID().getId());
            req.put("jumlah", fasilitas.getJumlah());
            RelAksesDTO relAksesDTO = new RelAksesDTO();
            req.put("akses",relAksesDTO);

            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
            body(req).
                    request(Method.POST,"fasilitas");

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
            String reqNamaFasilitas = dataGenerator.dataNamaFasilitas();
            String reqRuanganID = fasilitas.getRuanganID().getId();
            Short reqJumlah = fasilitas.getJumlah();

            fasilitas.setNamaFasilitas(reqNamaFasilitas);
            fasilitas.setJumlah(reqJumlah);
            fasilitas.setRuanganID(fasilitas.getRuanganID());

            req.put("namaFasilitas", reqNamaFasilitas);
            req.put("jumlah", reqJumlah);
            req.put("ruanganID",fasilitas.getRuanganID());

            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        body(req).
                    request(Method.PUT,"fasilitas/"+fasilitas.getId());

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
        request(Method.DELETE, "fasilitas/" + fasilitas.getId());

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
        request(Method.GET,"fasilitas/"+fasilitas.getId());

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            Assert.assertEquals(intResponse,200);
            Assert.assertEquals(jsonPath.getString("message"),"DATA BERHASIL DITEMUKAN");
            Assert.assertEquals(jsonPath.getString("data.id"),fasilitas.getId());
            Assert.assertEquals(jsonPath.getString("data.ruanganID"),fasilitas.getRuanganID().getId());
            Assert.assertEquals(jsonPath.getString("data.namaFasilitas"),fasilitas.getNamaFasilitas());
            Assert.assertEquals(jsonPath.getString("data.jumlah"),fasilitas.getJumlah());
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
            request(Method.GET,"fasilitas/");

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
        String pathVariable = "/fasilitas/asc/id/0";
        String strValue = fasilitas.getNamaFasilitas();

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

            Assert.assertEquals(jsonPath.getString("data.id"), fasilitas.getId());
            Assert.assertEquals(jsonPath.getString("data.ruangID"), fasilitas.getRuanganID().getId());
            Assert.assertEquals(jsonPath.getString("data.namaFasilitas"), fasilitas.getNamaFasilitas());
            Assert.assertEquals(jsonPath.getString("data.jumlah"), fasilitas.getJumlah());
            Assert.assertEquals(map.get("id").toString(), fasilitas.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}