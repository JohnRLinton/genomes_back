package com.biodb.genomes.dao;

import com.biodb.genomes.entity.Variant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantDao {
    List<Variant> findVariantByPos(Integer chr, Integer startpos, Integer endpos);
}
