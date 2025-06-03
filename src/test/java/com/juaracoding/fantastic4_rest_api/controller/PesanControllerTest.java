package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelRuanganDTO;
import com.juaracoding.fantastic4_rest_api.dto.rel.RelUserDTO;
import com.juaracoding.fantastic4_rest_api.model.Pesan;
import com.juaracoding.fantastic4_rest_api.model.Ruangan;
import com.juaracoding.fantastic4_rest_api.model.User;
import com.juaracoding.fantastic4_rest_api.repo.PesanRepo;
import com.juaracoding.fantastic4_rest_api.utils.DataGenerator;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.bytebuddy.asm.Advice;
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
    private User user;
    private Ruangan ruangan;
    private Random rand;
    private DataGenerator dataGenerator;
    private Long idPesanTest;

    @BeforeClass
    private void init() {
//            token = new TokenGenerator(AuthControllerTest.authorization).getToken();
        rand = new Random();
        req = new JSONObject();
        pesan = new Pesan();
        dataGenerator = new DataGenerator();
        idPesanTest = Long.valueOf(String.valueOf(dataGenerator.dataIdPesan()));
        Optional<Pesan> op = pesanRepo.findTop1ByOrderByIdDesc();
        pesan = op.get();
    }

    @BeforeTest
    private void setup() {
        /** sifatnya optional */
    }

    @Test(priority = 0)
    void save() {
        Response response;
        idPesanTest = dataGenerator.dataIdPesan();
        Long id = dataGenerator.dataIdPesan();
        String mulai = dataGenerator.dataMulai();
        String berakhir = dataGenerator.dataBerakhir(mulai);
        LocalDate tanggalPemesanan = LocalDate.parse(dataGenerator.dataTanggalPemesanan());
        LocalDate tanggalPertemuan = LocalDate.parse(dataGenerator.dataTanggalPertemuan(tanggalPemesanan.toString()));
        String nama = dataGenerator.dataNamaTim();
        String path = "/" + nama.toLowerCase().replace(" ", "-");
        try {
            req.put("id", idPesanTest);
            req.put("tanggal-pemesanan", tanggalPemesanan);
            req.put("namaPertemuan", dataGenerator.dataNamaPertemuan());
            req.put("tanggal-pertemuan", tanggalPertemuan);
            req.put("mulai", mulai);
            req.put("berakhir", berakhir);
            req.put("durasi", dataGenerator.dataDurasi());
            req.put("status", dataGenerator.dataStatus());
            RelUserDTO relUserDTO = new RelUserDTO();
            relUserDTO.setId(pesan.getUser().getId());
            req.put("user", relUserDTO);
            RelRuanganDTO relRuanganDTO = new RelRuanganDTO();
            relRuanganDTO.setId(pesan.getRuangan().getId());
            req.put("ruangan", relRuanganDTO);

            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
            body(req).
                    request(Method.POST, "pesan");

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
        Response response;
        req.clear();
        try {
            String reqId = String.valueOf(idPesanTest);
            LocalDate tanggalPemesanan = LocalDate.parse(dataGenerator.dataTanggalPemesanan());
            String reqNamaPertemuan = dataGenerator.dataNamaPertemuan();
            LocalDate tanggalPertemuan = LocalDate.parse(dataGenerator.dataTanggalPertemuan(tanggalPemesanan.toString()));
            String mulai = dataGenerator.dataMulai();
            String berakhir = dataGenerator.dataBerakhir(mulai);
            BigDecimal reqDurasi = pesan.getDurasi();
            String reqStatus = pesan.getStatus();

            pesan.setPesanID(Long.valueOf(reqId));
            pesan.setTanggalPemesanan(tanggalPemesanan);
            pesan.setNamaPertemuan(reqNamaPertemuan);
            pesan.setTanggalPertemuan(tanggalPertemuan);
            pesan.setMulai(Time.valueOf(mulai));
            pesan.setBerakhir(Time.valueOf(berakhir));
            pesan.setDurasi(reqDurasi);
            pesan.setStatus(reqStatus);
            RelUserDTO relUserDTO = new RelUserDTO();
            relUserDTO.setId(pesan.getUser().getId());
            RelRuanganDTO relRuanganDTO = new RelRuanganDTO();
            relRuanganDTO.setId(pesan.getRuangan().getId());

            req.put("id", idPesanTest);
            req.put("tanggal-pemesanan", tanggalPemesanan);
            req.put("namaPertemuan", reqNamaPertemuan);
            req.put("tanggal-pertemuan", tanggalPemesanan);
            req.put("mulai", mulai);
            req.put("berakhir", berakhir);
            req.put("durasi", reqDurasi);
            req.put("status", reqStatus);
            req.put("user", relUserDTO);
            req.put("ruangan", relRuanganDTO);

            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
            body(req).
                    request(Method.PUT, "pesan/" + idPesanTest);

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("message"),"DATA BERHASIL DIUBAH");
            Assert.assertNotNull(jsonPath.getString("data"));
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));

            System.out.println(response.getBody().prettyPrint());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 20)
    void findById() {
        Response response;
        if (idPesanTest == null) {
            idPesanTest = pesan.getPesanID();
        }

        try {
            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
            request(Method.GET,"pesan/"+idPesanTest);

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();
            System.out.println(response.getBody().prettyPrint());
            Assert.assertEquals(intResponse,200);
            Assert.assertEquals(jsonPath.getString("message"), "DATA DITEMUKAN");

            Assert.assertEquals(jsonPath.getString("data.id"), String.valueOf(pesan.getPesanID()));
            Assert.assertEquals(jsonPath.getString("data.['tanggal-pemesanan']"), pesan.getTanggalPemesanan().toString());
            Assert.assertEquals(jsonPath.getString("data.['nama-pertemuan']"), pesan.getNamaPertemuan());
            Assert.assertEquals(jsonPath.getString("data.['tanggal-pertemuan']"), pesan.getTanggalPertemuan().toString());
            Assert.assertEquals(jsonPath.getString("data.mulai"), pesan.getMulai().toString());
            Assert.assertEquals(jsonPath.getString("data.berakhir"), pesan.getBerakhir().toString());
            Assert.assertEquals(jsonPath.getString("data.durasi"), pesan.getDurasi().toString());
            Assert.assertEquals(jsonPath.getString("data.status"), pesan.getStatus());
            Assert.assertEquals(jsonPath.getString("data.user.id"), pesan.getUser().getId());
            Assert.assertEquals(jsonPath.getString("data.ruangan.id"), pesan.getRuangan().getId());
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 30)
    void findByAll() {
        Response response;
        if (idPesanTest == null) {
            idPesanTest = pesan.getPesanID();
        }
        try {
            response = given().
                    header("Content-Type","application/json").
                    header("accept","*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
            request(Method.GET,"/pesan/"+idPesanTest);

            int intResponse = response.getStatusCode();
            JsonPath jsonPath = response.jsonPath();

            System.out.println(response.getBody().prettyPrint());

            List ltData = jsonPath.getList("data");
            int intData = ltData.size();

            Assert.assertEquals(intResponse, 200);
            Assert.assertEquals(jsonPath.getString("message"), "DATA DITEMUKAN");
            Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
            Assert.assertNotNull(jsonPath.getString("timestamp"));

            //======================================================================================================================================================

            Assert.assertEquals(jsonPath.getString("data.sort-by"), "id");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.current-page")), 0);
            Assert.assertEquals(jsonPath.getString("data.column-name"), "id");
            Assert.assertNotNull(jsonPath.getString("data.total-pages"));
            Assert.assertEquals(jsonPath.getString("data.sort"), "asc");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.size-per-page")), OtherConfig.getDefaultPaginationSize());
            Assert.assertEquals(jsonPath.getString("data.value"), "");
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.total-data")), intData);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 40)
    void findByParam() {
        Response response;
        String pathVariable = "/pesan/asc/id/0";
        String strValue = String.valueOf(pesan.getTanggalPemesanan());

        try {
            response = given().
                    header("Content-Type", "application/json").
                    header("accept", "*/*").
                    params("size", 10).
                    params("column", "nama").
                    params("value", strValue).
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
            Assert.assertEquals(Integer.parseInt(jsonPath.getString("data.total-data")), intData);

            Assert.assertEquals(map.get("id").toString(), pesan.getPesanID());
            Assert.assertEquals(jsonPath.getString("data.tanggal-pemesanan"), pesan.getTanggalPemesanan().toString());
            Assert.assertEquals(jsonPath.getString("data.namaPertemuan"), pesan.getNamaPertemuan());
            Assert.assertEquals(jsonPath.getString("data.tanggal-pertemuan"), pesan.getTanggalPertemuan().toString());
            Assert.assertEquals(jsonPath.getString("data.mulai"), pesan.getMulai().toString());
            Assert.assertEquals(jsonPath.getString("data.berakhir"), pesan.getBerakhir().toString());
            Assert.assertEquals(jsonPath.getString("data.durasi"), pesan.getDurasi().toString());
            Assert.assertEquals(jsonPath.getString("data.status"), pesan.getStatus());
            Assert.assertEquals(jsonPath.getString("data.user.id"), pesan.getUser().getId());
            Assert.assertEquals(jsonPath.getString("data.ruangan.id"), pesan.getRuangan().getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

        @Test(priority = 50)
        void delete () {
            Response response;
            try {
                response = given().
                        header("Content-Type", "application/json").
                        header("accept", "*/*").
//                        header(AuthControllerTest.AUTH_HEADER,token).
                request(Method.DELETE, "/pesan/" + pesan.getPesanID());

                int intResponse = response.getStatusCode();
                JsonPath jsonPath = response.jsonPath();

                System.out.println(response.getBody().prettyPrint());
                Assert.assertEquals(intResponse, 200);
                Assert.assertEquals(jsonPath.getString("message"), "DATA BERHASIL DIHAPUS");
                Assert.assertNotNull(jsonPath.getString("data"));
                Assert.assertTrue(Boolean.parseBoolean(jsonPath.getString("success")));
                Assert.assertNotNull(jsonPath.getString("timestamp"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }