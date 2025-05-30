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

    @BeforeClass
    private void init(){
//            token = new TokenGenerator(AuthControllerTest.authorization).getToken();
        rand  = new Random();
        req = new JSONObject();
        ruangan = new Ruangan();
        dataGenerator = new DataGenerator();
        Optional<Ruangan> op = ruanganRepo.findTop1ByOrderByIdDesc();
        ruangan = op.get();
    }

    @BeforeTest
    private void setup(){
        /** sifatnya optional */
    }

    @Test(priority = 0)
    void save(){
        Response response ;
        String nama = dataGenerator.dataNamaRuangan();
        String path = "/"+nama.toLowerCase().replace(" ","-");
        try{
            req.put("namaRuangan", dataGenerator.dataNamaRuangan());
            req.put("minKapasitas", ruangan.getMinKapasitas());
            req.put("maxKapasitas", ruangan.getMaxKapasitas());
            req.put("lokasi", dataGenerator.dataLokasi());

            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
        body(req).
                    request(Method.POST,"ruangan");

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
