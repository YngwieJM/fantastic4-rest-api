package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.dto.rel.RelAksesDTO;
import com.juaracoding.fantastic4_rest_api.model.User;
import com.juaracoding.fantastic4_rest_api.repo.UserRepo;
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

import java.util.Optional;
import java.util.Random;

import static io.restassured.RestAssured.given;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepo userRepo;

        private JSONObject req;
        private User user;
        private Random rand ;
        private String token;
        private DataGenerator dataGenerator;

        @BeforeClass
        private void init(){
//            token = new TokenGenerator(AuthControllerTest.authorization).getToken();
            rand  = new Random();
            req = new JSONObject();
            user = new User();
            dataGenerator = new DataGenerator();
            Optional<User> op = userRepo.findTop1ByOrderByIdDesc();
            user = op.get();
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
                req.put("email", dataGenerator.dataEmail());
                req.put("noTelp", dataGenerator.dataNoTelp());
                req.put("nama", dataGenerator.dataNamaLengkap());
                req.put("departemen",dataGenerator.dataDepartement());
                req.put("jabatan",dataGenerator.dataJabatan());
                req.put("password",dataGenerator.dataPassword());
                RelAksesDTO relAksesDTO = new RelAksesDTO();
                relAksesDTO.setId(user.getAkses().getId());
                req.put("akses",relAksesDTO);

                response = given().
                        header("Content-Type","application/json").
                        header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                        body(req).
                        request(Method.POST,"user");

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
