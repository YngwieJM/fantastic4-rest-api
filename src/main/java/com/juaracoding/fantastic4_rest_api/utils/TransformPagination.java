package com.juaracoding.fantastic4_rest_api.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransformPagination {

    private String sortByColumn;// disortir berdasarkan kolom apa?
    private String sort;// disortir berdasarkan apa ? Desc atau Asc

    String [] sortArr = new String [2];

    public Map<String,Object> transformPagination(
            List ls,
            Page page,
            String column,
            String value
    ){
        Sort s = page.getSort();
        sortArr = s.toString().split(":");
        sortByColumn = sortArr[0];
        Boolean isSorted = sortByColumn.equals("UNSORTED");
        sortByColumn = isSorted ? "id" : sortByColumn;
        sort = isSorted ? "asc" : sortArr[1];

        Map<String,Object> m = new HashMap<String,Object>();
        m.put("content",ls);
        m.put("total-data",page.getTotalElements());
        m.put("total-pages",page.getTotalPages());
        m.put("current-page",page.getNumber());
        m.put("size-per-page",page.getSize());
        m.put("sort-by",sortByColumn);
        m.put("sort",sort.trim().toLowerCase());
        m.put("column-name",column);
        m.put("value",value==null?"":value);
        return m;
    }

}
