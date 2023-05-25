package com.biodb.genomes.service;

import com.biodb.genomes.entity.Sample;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

public interface SampleService {
    List<Sample> listSample();

    List<Sample> listSampleByCondition(String sex, String populationCode, String SuperpopulationCode);

    List<Map<String, Integer>> countPopulation();

    List<Map<String, Integer>> countSuperPopulation();

    List<Map<String, Integer>> countSex();
}
