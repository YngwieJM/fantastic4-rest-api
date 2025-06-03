package com.juaracoding.fantastic4_rest_api.controller;

import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import com.juaracoding.fantastic4_rest_api.dto.validation.ValFasilitasDTO;
import com.juaracoding.fantastic4_rest_api.service.FasilitasService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

@RestController
@RequestMapping("facility")
public class FacilityController {

    @Autowired
    private FasilitasService fasilitasService;
    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourcehandlerMapping;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValFasilitasDTO valFasilitasDTO,
                                       HttpServletRequest request){
        return fasilitasService.save(fasilitasService.mapToFasilitas(valFasilitasDTO), request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody ValFasilitasDTO valFasilitasDTO,
                                       @PathVariable String id,
                                         HttpServletRequest request){
        return fasilitasService.update(id, fasilitasService.mapToFasilitas(valFasilitasDTO), request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id,
                                         HttpServletRequest request){
        return fasilitasService.delete(id, request);
    }

    /** defaultSearch
     * Ketika menu dibuka pertama kali, api yang di hit adalah api ini ....
     */
    @GetMapping
    public ResponseEntity<Object> findAll(HttpServletRequest request){
        Pageable pageable = PageRequest.of(0, OtherConfig.getDefaultPaginationSize(), Sort.by("id"));
        return fasilitasService.findAll(pageable, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(
            @PathVariable String id,
            HttpServletRequest request) {
        return fasilitasService.findById(id, request);
    }

    @GetMapping("{sort}/{sort-by}/{page}")
    public ResponseEntity<Object> findByParam(
            @PathVariable String sort,
            @PathVariable(value = "sort-by") String sortBy,
            @PathVariable Integer page,
            @RequestParam Integer size,
            @RequestParam String column,
            @RequestParam String value,
            HttpServletRequest request){
        Pageable pageable = null;
        sortBy = sortColumn(sortBy);
        switch (sort){
            case "desc":pageable = PageRequest.of(page, size, Sort.by("id").descending());break;
            default:pageable = PageRequest.of(page, size, Sort.by("id"));break;
        }
        return fasilitasService.findByParam(pageable, column, value, request);
    }

    private String sortColumn(String column){
        switch (column){
            case "namaFasilitas":column="namaFasilitas";break;
            case "ruanganID":column="nuanganID";break;
            default:column="id";break;
        }
        return column;
    }

}
