package com.biodb.genomes.service.impl;

import com.biodb.genomes.dao.VariantDao;
import com.biodb.genomes.entity.Variant;
import com.biodb.genomes.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServiceImpl implements VariantService {
    @Autowired
    VariantDao variantDao;

    @Override
    public List<Variant> listVariantByPos(Integer chr, Integer startpos, Integer endpos) {
        return variantDao.findVariantByPos(chr,startpos,endpos);
    }
}
