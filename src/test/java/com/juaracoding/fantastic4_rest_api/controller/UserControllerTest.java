package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
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

import java.util.List;
import java.util.Map;
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
                req.put("nama", dataGenerator.dataNama());
                req.put("email", dataGenerator.dataEmail());
                req.put("noTelp", dataGenerator.dataNoTelp());
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

        @Test(priority = 10)
        void update(){
            Response response;
            req.clear();
            try{
                String reqNama = dataGenerator.dataNama();
                String reqEmail = dataGenerator.dataEmail();
                String reqNoTelp = dataGenerator.dataNoTelp();
                String reqDepartemen = dataGenerator.dataDepartement();
                String reqJabatan = dataGenerator.dataJabatan();
                String reqPassword = dataGenerator.dataPassword();

                user.setNama(reqNama);
                user.setEmail(reqEmail);
                user.setNoTelp(reqNoTelp);
                user.setDepartemen(reqDepartemen);
                user.setJabatan(reqJabatan);
                user.setPassword(reqPassword);

                req.put("nama", reqNama);
                req.put("email", reqEmail);
                req.put("noTelp", reqNoTelp);
                req.put("departemen", reqDepartemen);
                req.put("jabatan", reqJabatan);
                req.put("password", reqPassword);
                RelAksesDTO relAksesDTO = new RelAksesDTO();
                relAksesDTO.setId(user.getAkses().getId());
                req.put("akses",relAksesDTO);

                response = given().
                        header("Content-Type","application/json").
                        header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                        body(req).
                        request(Method.PUT,"user/"+user.getId());

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
            try{
                response = given().
                        header("Content-Type","application/json").
                        header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                        request(Method.GET,"user/"+user.getId());

                int intResponse = response.getStatusCode();
                JsonPath jsonPath = response.jsonPath();
                Assert.assertEquals(intResponse,200);
                Assert.assertEquals(jsonPath.getString("message"),"DATA BERHASIL DITEMUKAN");
                Assert.assertEquals(jsonPath.getString("data.id"),user.getId());
                Assert.assertEquals(jsonPath.getString("data.nama"),user.getNama());
                Assert.assertEquals(jsonPath.getString("data.email"),user.getEmail());;
                Assert.assertEquals(jsonPath.getString("data.noTelp"),user.getNoTelp());
                Assert.assertEquals(jsonPath.getString("data.departemen"),user.getDepartemen());
                Assert.assertEquals(jsonPath.getString("data.jabatan"),user.getJabatan());
                Assert.assertEquals(jsonPath.getString("data.password"),user.getPassword());
                Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
                Assert.assertNotNull(jsonPath.getString("timestamp"));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    @Test(priority = 30)
    void findByAll(){
        Response response;
        try{
            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                    request(Method.GET,"user/");

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

    @Test(priority = 40)
    void findByParam() {
        Response response;
        String pathVariable = "/user/asc/id/0";
        String strValue = user.getNama();

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

            Assert.assertEquals(jsonPath.getString("data.id"), user.getId());
            Assert.assertEquals(jsonPath.getString("data.nama"), user.getNama());
            Assert.assertEquals(jsonPath.getString("data.email"), user.getEmail());
            ;
            Assert.assertEquals(jsonPath.getString("data.noTelp"), user.getNoTelp());
            Assert.assertEquals(jsonPath.getString("data.departemen"), user.getDepartemen());
            Assert.assertEquals(jsonPath.getString("data.jabatan"), user.getJabatan());
            Assert.assertEquals(jsonPath.getString("data.password"), user.getPassword());
            Assert.assertEquals(map.get("id").toString(), user.getId());
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
        request(Method.DELETE, "user/" + user.getId());

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
}