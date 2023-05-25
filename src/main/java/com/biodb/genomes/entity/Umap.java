package com.biodb.genomes.entity;

import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

@Data
public class Umap {
    private String IndividualID;
    private Double UMAP0;
    private Double UMAP1;
    private String Population;
    private String Super;
}
