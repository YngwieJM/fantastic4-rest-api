package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.model.Fasilitas;
import com.juaracoding.fantastic4_rest_api.repo.FasilitasRepo;
import com.juaracoding.fantastic4_rest_api.utils.DataGenerator;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FacilityControllerTest extends AbstractTestNGSpringContextTests {

        @Autowired
        private FasilitasRepo fasilitasRepo;
        private JSONObject req;
        private Fasilitas fasilitas;
        private Random rand;
        private DataGenerator dataGenerator;


    }
}
