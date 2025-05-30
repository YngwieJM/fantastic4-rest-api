package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.dto.rel.RelRuanganDTO;
import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import com.juaracoding.fantastic4_rest_api.repo.RuanganRepo;
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
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
    public void init() {
        req = new JSONObject();
        ruangan = new Ruangan();
        rand = new Random();
        dataGenerator = new DataGenerator();
        Optional<Ruangan> op = ruanganRepo.findTop1ByOrderByIdDesc();
        if (op.isPresent()) {
            ruangan = op.get();
        } else {
            System.out.println("Tidak ada ruangan yang tersedia untuk diambil");
            ruangan = new Ruangan(); // atau log/buat ruangan dummy
        }
    }

    @Test(priority = 0)
    void save(){
        Response response ;
        String nama = dataGenerator.dataNamaRuangan();
        String path = "/"+nama.toLowerCase().replace(" ","-");
        try{
            int min = rand.nextInt(50) + 1; // 1 - 50
            int max = min + rand.nextInt(50); // min+1 to min+50

            req.put("namaRuangan", dataGenerator.dataNamaRuangan());
            req.put("minKapasitas", min);
            req.put("maxKapasitas", max);
            req.put("lokasi", dataGenerator.dataLokasi());


            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
                    body(req).
                    request(Method.POST,"room");

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
