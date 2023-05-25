package com.biodb.genomes.service.impl;

import com.biodb.genomes.dao.SampleDao;
import com.biodb.genomes.entity.Sample;

import com.biodb.genomes.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@Service
public class SampleServiceImpl implements SampleService {
    @Autowired
    SampleDao sampleDao;

    @Override
    public List<Sample> listSample(){
        return sampleDao.findAll();
    }

    @Override
    public List<Sample> listSampleByCondition(String sex, String populationCode, String SuperpopulationCode) {
        return sampleDao.findSampleByCondition(sex,populationCode,SuperpopulationCode);
    }

    @Override
    public List<Map<String, Integer>> countPopulation() {
        return sampleDao.countPopulation();
    }

    @Override
    public List<Map<String, Integer>> countSuperPopulation() {
        return sampleDao.countSuperPopulation();
    }

    @Override
    public List<Map<String, Integer>> countSex() {
        return sampleDao.countSex();
    }


}
