package com.biodb.genomes.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
@CrossOrigin
@RequestMapping(value = "/1000genomes/download")
public class DownloadController {

    /**
     * download页面，
     * chr.dbsnp.vcf.gz、chr.LD
     * @param request
     * @param response
     */
    @GetMapping(value = "/downloadData",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @CrossOrigin
    public void downloadData(HttpServletRequest request, HttpServletResponse response) {
        int type = Integer.parseInt(request.getParameter("type")); //获取type
        int typeFlag = Integer.parseInt(request.getParameter("typeFlag")); //获取exam_record_id
        String chr = request.getParameter("chr"); //获取exam_record_id
        File file = null;
        if (type == 0) { //type == 0代表下载dbsnp数据
            file = new File("/data/luoyang/1000genomes/dbsnpGZ/" + chr + ".dbsnp.vcf.gz");
        } else if (type == 1) { // type == 1代表下载LD数据
            file = new File("//data/luoyang/1000genomes/LD/"+ chr +".LD.hap.ld");
        }  else if (type == 2) {
            if (typeFlag == 0) {
                file = new File("/home/luoyang/1000Genomes/population_data/result/AllGT.gz");
            }
        }
        if (file != null) {
            if (file.exists()) {
                // 读去Object内容  返回
                try {
                    InputStream inputStream = new FileInputStream(file);
                    BufferedInputStream in = new BufferedInputStream(inputStream);
                    BufferedOutputStream out = null;
                    try {
                        out = new BufferedOutputStream(response.getOutputStream());
                        //通知浏览器以附件形式下载
                        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "utf-8"));

                        byte[] car = new byte[1024];
                        int L = 0;
                        while ((L = in.read(car)) != -1) {
                            out.write(car, 0, L);
                        }
                        out.flush();
                        out.close();
                        in.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
