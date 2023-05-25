package com.biodb.genomes.service;

import com.biodb.genomes.entity.Variant;

import java.util.List;

public interface VariantService {
    List<Variant> listVariantByPos(Integer chr, Integer startpos, Integer endpos);

}
