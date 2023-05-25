package com.biodb.genomes.dao;

import com.biodb.genomes.entity.Population;
import com.biodb.genomes.entity.Umap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmapDao {

    List<Umap> findAllUmap();

    List<Umap> findEASUmap();

    List<Umap> findSASUmap();

    List<Umap> findEURUmap();

    List<Umap> findAFRUmap();

    List<Umap> findAMRUmap();

    @Select("select Population,PopulationInfo,Count,`Superpopulation code` as SuperpopulationCode from umappopulationinfo")
    List<Population> findPopulationInfo();
}
