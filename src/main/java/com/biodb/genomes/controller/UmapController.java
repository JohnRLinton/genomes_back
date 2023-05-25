package com.biodb.genomes.controller;

import com.biodb.genomes.service.UmapService;
import com.biodb.genomes.util.JsonUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/1000genomes/umap")
public class UmapController {
    @Autowired
    UmapService umapService;


    /**
     * 降维分类页面
     * 获取2504个个体的降维数据，画图，5 Super、26 population、EAS、SAS、EUR、AMR、AFR
     */
    @GetMapping(value = "/getUmap",produces = {"application/json;charset=UTF-8"})
    public String getUmap(){
        Map<String,Object> map = new HashMap<>();
        map.put("AllUmap",umapService.listUmap());
        map.put("EASUmap",umapService.listEASUmap());
        map.put("SASUmap",umapService.listSASUmap());
        map.put("EURUmap",umapService.listEURUmap());
        map.put("AFRUmap",umapService.listAFRUmap());
        map.put("AMRUmap",umapService.listAMRUmap());
        map.put("PopulationInfo",umapService.listPopulationInfo());
        return JsonUtils.objectToJson(map);
    }

}
