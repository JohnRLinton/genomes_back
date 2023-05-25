package com.biodb.genomes.service;

import com.biodb.genomes.entity.Population;
import com.biodb.genomes.entity.Sample;
import com.biodb.genomes.entity.Umap;

import java.util.List;

public interface UmapService {
    List<Umap> listUmap();

    List<Umap> listEASUmap();

    List<Umap> listSASUmap();

    List<Umap> listEURUmap();

    List<Umap> listAFRUmap();

    List<Umap> listAMRUmap();

    List<Population> listPopulationInfo();
}

