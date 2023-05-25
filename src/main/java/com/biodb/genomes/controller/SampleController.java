package com.biodb.genomes.controller;

import com.biodb.genomes.entity.Sample;
import com.biodb.genomes.service.SampleService;
import com.biodb.genomes.util.ExcelText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.*;

import com.biodb.genomes.util.JsonUtils;

@RestController
@CrossOrigin
@RequestMapping(value = "/1000genomes/sample")
public class SampleController {
    @Autowired
    SampleService sampleService;

    /**
     * 基因数据浏览页面
     * 获取2504个individuals信息
     * @return
     */
    @GetMapping(value = "/getAllSample",produces = {"application/json;charset=UTF-8"})
    public String getAllSample(){
        Map<String, Object> map = new HashMap<>();
        map.put("result",sampleService.listSample());
        map.put("NumPopulation",sampleService.countPopulation());
        map.put("NumSuperPopulation",sampleService.countSuperPopulation());
        map.put("NumSex",sampleService.countSex());
        return JsonUtils.objectToJson(map);
    }

    /**
     * 基因数据浏览页面
     * 有条件筛选individuals
     * @param sex
     * @param populationCode
     * @param SuperpopulationCode
     * @return
     */
    @GetMapping(value="/getAllSampleFiltrate",produces = {"application/json;charset=UTF-8"})
    public List<Sample> getAllSampleFiltrate(@RequestParam("sex")String sex,@RequestParam("populationCode")String populationCode,@RequestParam("SuperpopulationCode")String SuperpopulationCode ){
        return sampleService.listSampleByCondition(sex,populationCode,SuperpopulationCode);
    }


    /**
     * 运行shell并获得结果，注意：如果sh中含有awk,一定要按new String[]{"/bin/sh","-c",shStr}写,才可以获得流
     *
     * @param chrpath
     * @param picpath
     * @param region 需要执行的shell
     * @return
     */
    public static List<String[]> runShell(String chrpath,String picpath,String region) throws IOException {
        List<String[]> strList = new ArrayList<>();
        InputStreamReader ir = null;
        LineNumberReader input = null;
        Process process = null;
        try {
//            process = Runtime.getRuntime().exec(shStr);
            process = Runtime.getRuntime().exec(new String[]{"LDBlockShow", "-InVCF", chrpath,"-OutPut",picpath,"-Region",region}, null, null);

            //执行脚本
//            process = Runtime.getRuntime().exec(new String[]{"sh", "/home/luoyang/1000Genomes/LDBlock/test/ldblock.sh", chrpath,picpath,region}, null, null);
            ir = new InputStreamReader(process.getInputStream());
            input = new LineNumberReader(ir);
            String line;
            while ((line = input.readLine()) != null) {
                strList.add(line.split("\t"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ir != null) {
                ir.close();
            }
            if (input != null) {
                input.close();
            }
            if (process != null) {
                process.destroy();
            }
        }
        return strList;
    }


    /**
     * LDBlock图页面
     * @param chr
     * @param startpos
     * @param endpos
     * @return
     */
    @GetMapping(value="/getldblock",produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    public String getldblock(@RequestParam("chr")String chr,@RequestParam("startpos")String startpos,@RequestParam("endpos")String endpos){
        Map<String,Object> map = new HashMap<>();
        try{
            String chrpath = "/data/luoyang/1000genomes/release_20130502/20130502_gz/ALL.chr"+chr+".phase3_shapeit2_mvncall_integrated_v5b.20130502.genotypes.vcf.gz";
            String picpath = "/home/luoyang/1000Genomes/LDBlock/chr"+chr+"_LDBlock";
            String region = chr+":"+startpos+"-"+endpos;
            //String order = "LDBlockShow -InVCF /data/luoyang/1000genomes/release_20130502/20130502_gz/ALL.chr"+chr+".phase3_shapeit2_mvncall_integrated_v5b.20130502.genotypes.vcf.gz -OutPut chr"+chr+"_LDBlock1 -Region "+chr+":"+startpos+"-"+endpos+" -OutPng";
            runShell(chrpath,picpath,region);

            //先解压gunzip,否则乱码！！！

            File file = new File("/home/luoyang/1000Genomes/LDBlock/chr"+chr+"_LDBlock.blocks.gz");
            if(file.exists()){
                Map<String, Object> map1 = ExcelText.readSpecifyColumns(file, 6, 0);
                ArrayList arrayLists = (ArrayList) map1.get("title"); // 标题列表
                List<String[]> lists = (List<String[]>) map1.get("result"); // 数据list
                int rows = (int) map1.get("rows"); // 一共多少条数据
                String[][] data = new String[lists.get(0).length][arrayLists.size()];
//                Map<String, Object> filtrate = new HashMap<>();
                if (arrayLists.size() == lists.size()) {
                    for (int i = 0; i < arrayLists.size(); i++) {
                        String array = (String) arrayLists.get(i);
                        Map<String, Integer> integerMap = new HashMap<>();
                        String[] arrayList = lists.get(i);
                        for (int j = 0; j < lists.get(i).length; j++) {
                            data[j][i] = lists.get(i)[j];
                        }
                    }
                }
                List<String[]> list = new ArrayList<>(Arrays.asList(data));

                map.put("SNPnumber",list);
                map.put("rows",rows);
                map.put("title",arrayLists.toArray());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //文件路径必须在gongyue tomcat webapp下，未解决！！！
        map.put("LDBlock","https://ptr.nefu.edu.cn/LDBlock/chr"+chr+"_LDBlock.png");
        return JsonUtils.objectToJson(map);
    }


}
