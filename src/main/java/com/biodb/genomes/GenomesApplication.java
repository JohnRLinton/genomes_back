package com.biodb.genomes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.biodb.genomes.dao")
public class GenomesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenomesApplication.class, args);
    }

}
