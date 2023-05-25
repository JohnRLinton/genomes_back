package com.biodb.genomes.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelText {
    public static void main(String[] args) {
        String path = "E:\\ATAC-seq\\xlsPeak\\Sample_00_001_peak.xls";
        File file = new File(path);

    }

    public static Map<String, Object> readTxT(File file) {
        Map<String, Object> map = new HashMap<>();
        List<String[]> result = new ArrayList<>();
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));

            String line = "";
            int lines = -1; // 总行数
            while ((line = br.readLine()) != null) // 读取到的内容给line变量
            {
                if (lines == -1) {
                    String[] title = line.split("\t"); // 标题行
                    map.put("title", title);
                } else {
                    String[] res = line.split("\t"); // 数据行
                    result.add(res);
                }
                lines++;
            }

            map.put("rows", lines);
            map.put("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 读取指定列
     *
     * @param file
     * @throws Exception
     */
    public static Map<String, Object> readSpecifyColumns(File file, int column, int sheetNum) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<String[]> result = new ArrayList<>();

        List<String> title = new ArrayList<>();

        if (file.getName().substring(file.getName().lastIndexOf(".")).equals(".xls")) {
            org.apache.poi.ss.usermodel.Workbook book = null;
            try {
                book = new HSSFWorkbook(new FileInputStream(file));
            } catch (Exception ex) {
                try {
                    book = new XSSFWorkbook(new FileInputStream(file));
                } catch (Exception e) {
                    return readTxT(file);
                }
            }
            org.apache.poi.ss.usermodel.Sheet sheet = book.getSheetAt(sheetNum);
            // 获取到Excel文件中的所有行数
            int rows = sheet.getPhysicalNumberOfRows();
            map.put("rows", rows - 1);
            for (int i = 0; i < column; i++) {
                result.add(new String[rows - 1]);
            }
            gain(sheet, rows, column, result);
            Row row = sheet.getRow(0);
            for (int i = 0; i < column; i++) {
                org.apache.poi.ss.usermodel.Cell cell = (org.apache.poi.ss.usermodel.Cell) row.getCell(i);
                String content = getCellContent(cell);
                if (!content.equals("")) {
                    title.add(content);
                }
            }
            // 遍历行
        } else {
            // 创建对Excel工作簿文件
            org.apache.poi.ss.usermodel.Workbook book = null;
            try {
                book = new XSSFWorkbook(new FileInputStream(file));
            } catch (Exception ex) {
                try {
                    book = new HSSFWorkbook(new FileInputStream(file));
                } catch (Exception se) {
                    return readTxT(file); //返回的是行的list
                }
            }

            org.apache.poi.ss.usermodel.Sheet sheet = book.getSheetAt(sheetNum);
            // 获取到Excel文件中的所有行数
            int rows = sheet.getPhysicalNumberOfRows();
            // 遍历行
            for (int i = 0; i < column; i++) {
                result.add(new String[rows - 1]);
            }
            gain(sheet, rows, column, result);
            map.put("rows", rows - 1);
            Row row = sheet.getRow(0);
            for (int i = 0; i < column; i++) {
                org.apache.poi.ss.usermodel.Cell cell = (org.apache.poi.ss.usermodel.Cell) row.getCell(i);
                String content = getCellContent(cell);
                if (!content.equals("")) {
                    title.add(content);
                }
            }
        }

        map.put("result", result);
        map.put("title", title);
        return map;
    }

    /**
     * 返回的是列的list
     * @param sheet
     * @param rows
     * @param column
     * @param lists
     */
    private static void gain(org.apache.poi.ss.usermodel.Sheet sheet, int rows, int column, List<String[]> lists) {
        for (int i = 1; i < rows; i++) {
            // 读取左上端单元格
            Row row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
                // 遍历列
                // 获取到列的值
                for (int j = 0; j < column; j++) {
                    org.apache.poi.ss.usermodel.Cell cell = (org.apache.poi.ss.usermodel.Cell) row.getCell(j);
                    if (cell != null) {
                        String content = getCellContent(cell);
                        if (!content.equals("")) {
                            lists.get(j)[i - 1] = content;
                        }
                    }
                }
            }
        }
    }

    private static final DataFormatter FORMATTER = new DataFormatter();

    /**
     * 获取单元格内容
     *
     * @param cell 单元格对象
     * @return 转化为字符串的单元格内容
     */
    private static String getCellContent(org.apache.poi.ss.usermodel.Cell cell) {
        return FORMATTER.formatCellValue(cell);
    }
}
