package com.biodb.genomes.controller;

import com.biodb.genomes.entity.Variant;
import com.biodb.genomes.service.VariantService;
import com.biodb.genomes.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import com.biodb.genomes.util.JsonUtils;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
@RequestMapping(value="/1000genomes/variant")
public class VariantController {
    @Autowired
    VariantService variantService;

    /**
     * 根据染色体变异起始终止位点位置查询变异信息
     * @param chr
     * @param startpos
     * @param endpos
     * @return
     */
    @PostMapping(value = "/searchByPos",produces = {"application/json;charset=UTF-8"})
    public String searchPos(@RequestParam("chr")Integer chr, @RequestParam("startpos")Integer startpos, @RequestParam("endpos")Integer endpos){
        Map<String,Object> map = new HashMap<>();
        map.put("result",variantService.listVariantByPos(chr,startpos,endpos));
        return JsonUtils.objectToJson(map);
//        return variantService.listVariantByPos(chr,startpos,endpos);
    }




}
