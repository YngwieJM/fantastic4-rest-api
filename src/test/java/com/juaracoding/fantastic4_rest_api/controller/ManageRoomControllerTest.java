package com.juaracoding.fantastic4_rest_api.controller;


import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import com.juaracoding.fantastic4_rest_api.repo.RuanganRepo;
import com.juaracoding.fantastic4_rest_api.utils.*;
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


import java.util.Optional;
import java.util.Random;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ManageRoomControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RuanganRepo ruanganRepo;
    private JSONObject req;
    private Ruangan ruangan;
    private Random rand;
    private DataGenerator dataGenerator;
    private String token;
    private String idRuanganTest;

    @BeforeClass
    private void init() {
//            token = new TokenGenerator(AuthControllerTest.authorization).getToken();
        rand = new Random();
        req = new JSONObject();
        ruangan = new Ruangan();
        dataGenerator = new DataGenerator();
        Optional<Ruangan> op = ruanganRepo.findTop1ByOrderByIdDesc();
        ruangan = op.get();
    }

    @BeforeTest
    private void setup() {
        /** sifatnya optional */
    }

    @Test(priority = 0)
    void save() {
        Response response;
        idRuanganTest = dataGenerator.dataIdRuangan();
        short[] kapasitas = dataGenerator.dataKapasitas();
        short minKapasitas = kapasitas[0];
        short maxKapasitas = kapasitas[1];
//        String nama = dataGenerator.dataNamaRuangan();
//        String path = "/"+nama.toLowerCase().replace(" ","-");
        try {
            req.put("id", idRuanganTest);
            req.put("namaRuangan", dataGenerator.dataNamaRuangan());
            req.put("minKapasitas",minKapasitas);
            req.put("maxKapasitas", maxKapasitas);
            req.put("lokasi", dataGenerator.dataLokasi());

            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        body(req).
                    request(Method.POST, "manage-room");

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse, 201);
            Assert.assertEquals(jsonPath.getString("message"), "SAVE SUCCESS !!");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 10)
    void update() {
        if (idRuanganTest == null) {
            idRuanganTest = dataGenerator.dataIdRuangan();
        }
        short[] kapasitas = dataGenerator.dataKapasitas();
        short minKapasitas = kapasitas[0];
        short maxKapasitas = kapasitas[1];
        Response response;
        try {
            req.put("id", idRuanganTest);
            req.put("namaRuangan", dataGenerator.dataNamaRuangan());
            req.put("minKapasitas", minKapasitas);
            req.put("maxKapasitas", maxKapasitas);
            req.put("lokasi", dataGenerator.dataLokasi());

            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        body(req).
                    request(Method.PUT, "manage-room/" + idRuanganTest);

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("message"), "DATA BERHASIL DIUBAH");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 20)
    void findAll() {
        Response response;
        try {
            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                    request(Method.GET, "manage-room");

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("message"), "DATA DITEMUKAN");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 30)
    void findById() {
        Response response;
        try {
            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                    request(Method.GET, "manage-room/" + ruangan.getId());

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("message"), "DATA DITEMUKAN");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 40)
    void findByParam() {
        Response response;
        String pathVariable = "/manage-room/asc/id/0";
        String strValue = ruangan.getNamaRuangan();
        try {
            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                    params("size", 10).
                    params("column", "namaRuangan").
                    params("value", strValue).
                    request(Method.GET, pathVariable);

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("message"), "DATA DITEMUKAN");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));

            Assert.assertEquals(jsonPath.getString("data.sort-by"), "id");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.current-page")), 0);
            Assert.assertEquals(jsonPath.getString("data.column-name"), "namaRuangan");
            Assert.assertNotNull(jsonPath.getString("data.total-pages"));
            Assert.assertEquals(jsonPath.getString("data.sort"), "asc");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.size-per-page")), 10);
            Assert.assertEquals(jsonPath.getString("data.value"), strValue);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 50)
    void delete() {
        Response response;
        try {
            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                    request(Method.DELETE, "manage-room/" + ruangan.getId());

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("message"), "DATA BERHASIL DIHAPUS");
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


