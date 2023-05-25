package com.biodb.genomes.dao;

import com.biodb.genomes.entity.Sample;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SampleDao {

    List<Sample> findAll();

    @Select("select `Population code` as populationCode,count(*) from samples group by `Population code`")
    List<Map<String, Integer>> countPopulation();

    @Select("select `Superpopulation code` as SuperpopulationCode,count(*) from samples group by `Superpopulation code`")
    List<Map<String, Integer>> countSuperPopulation();

    @Select("select Sex,count(*) from samples group by Sex")
    List<Map<String, Integer>> countSex();

    List<Sample> findSampleByCondition(String sex, String populationCode, String SuperpopulationCode);


}
