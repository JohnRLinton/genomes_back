package com.biodb.genomes.entity;

import lombok.Data;

@Data
public class Variant {
    private Integer CHROM;
    private Integer POS;
    private String ID;
    private String REF;
    private String ALT;
//    private Integer QUAL;
//    private String FILTER;
    private String INFO;
//    private String FORMAT;
}
