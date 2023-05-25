package com.biodb.genomes.service.impl;

import com.biodb.genomes.dao.UmapDao;
import com.biodb.genomes.entity.Population;
import com.biodb.genomes.entity.Umap;
import com.biodb.genomes.service.UmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmapServiceImpl implements UmapService {
    @Autowired
    UmapDao umapDao;

    @Override
    public List<Umap> listUmap(){
        return umapDao.findAllUmap();
    }

    @Override
    public List<Umap> listEASUmap() {
        return umapDao.findEASUmap();
    }

    @Override
    public List<Umap> listSASUmap() {
        return umapDao.findSASUmap();
    }

    @Override
    public List<Umap> listEURUmap() {
        return umapDao.findEURUmap();
    }

    @Override
    public List<Umap> listAFRUmap() {
        return umapDao.findAFRUmap();
    }

    @Override
    public List<Umap> listAMRUmap() {
        return umapDao.findAMRUmap();
    }

    @Override
    public List<Population> listPopulationInfo() {
        return umapDao.findPopulationInfo();
    }

}
