/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 25/01/23, 2:06 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    private static final Logger logger = LogManager.getLogger(ExcelUtil.class);

    public static void readFromExcel(String filepath, String sheetName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(filepath);
             XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workBook.getSheetAt(0); // get sheet by index

            if (CodeHelp.notEmpty(sheetName)) {
                sheet = workBook.getSheet(sheetName); //get sheet by name
            }

            for (Row cells : sheet) {
                XSSFRow row = (XSSFRow) cells;

                for (Cell value : row) {
                    XSSFCell cell = (XSSFCell) value;
                    switch (cell.getCellType()) {
                        case STRING:
                            logger.info(" {} ", cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            logger.info(" {} ", cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            logger.info(" {} ", cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            logger.info(" {} ", cell.getCellFormula());
                            break;
                    }
                    logger.info(" | ");
                }
                logger.info("\n");
            }
        }
    }

    public static void writeToExcel(List<Object[]> data, String sheetName, String destinationPathWithFileName, String formula) throws IOException {
        try (XSSFWorkbook workBook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(destinationPathWithFileName)) {
            XSSFSheet sheet = workBook.createSheet(sheetName);

            //some logic using LOOP or ITERATOR
            int rowNum = 0;
            for (Object[] rowData : data) {
                XSSFRow row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Object colElem : rowData) {
                    XSSFCell cell = row.createCell(colNum++);
                    if (colElem instanceof String) {
                        cell.setCellValue((String) colElem);
                    }
                    if (colElem instanceof Boolean) {
                        cell.setCellValue((Boolean) colElem);
                    }
                    if (colElem instanceof Integer) {
                        cell.setCellValue((Integer) colElem);
                    }
                }
                if (CodeHelp.notEmpty(formula)) {
                    XSSFCell cell = row.createCell(colNum);
                    cell.setCellFormula(formula);
                }
            }
            workBook.write(outputStream);
            logger.info("File [ {} ] written successfully!!", destinationPathWithFileName);
        }
    }

    public static void applyFormulaToParticularCell(String filePath) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(filePath);
             XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
             FileOutputStream outputStream = new FileOutputStream(filePath)) {

            XSSFSheet sheet = workBook.getSheetAt(0);

            sheet.getRow(7).getCell(4).setCellFormula("SUM(E2:E5)");
            workBook.write(outputStream);
            logger.info("File [ {} ] written successfully!!", filePath);
        }
    }

    public static void templateBasedFormatting(String filePath, String sheetName, ExcelFormatterTemplateEnum template) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(filePath);
             XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
             InputStream is = new FileInputStream("C:\\code\\dot_workspace\\VLogo.png");
             FileOutputStream outputStream = new FileOutputStream(filePath)) {

            readFromExcel(filePath, sheetName);
            byte[] bytes = IOUtils.toByteArray(is);
            int pictureIdx = workBook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            logger.info("File [ {} ] read successfully!!", filePath);

            XSSFSheet sheet = workBook.getSheetAt(0);
            if (CodeHelp.notEmpty(sheetName)) {
                sheet = workBook.getSheet(sheetName);
            }

            CreationHelper helper = workBook.getCreationHelper();
            Drawing<XSSFShape> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();

            anchor.setCol1(1);
            anchor.setRow1(2);
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            pict.resize();

            workBook.write(outputStream);
        }
    }

    public static void csvToXLSX() {
        try (XSSFWorkbook workBook = new XSSFWorkbook();
             BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\code\\dot_workspace\\28_CSR.csv"));
             FileOutputStream fileOutputStream = new FileOutputStream("C:\\code\\dot_workspace\\fromCSV.xlsx")) {

            XSSFSheet sheet = workBook.createSheet("sheet1");
            String currentLine;
            int RowNum = 0;

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] str = currentLine.split(",");
                RowNum++;
                XSSFRow currentRow = sheet.createRow(RowNum);
                for (int i = 0; i < str.length; i++) {
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }

            workBook.write(fileOutputStream);
            logger.info("Done");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Exception in try");
        }
    }

    public XSSFWorkbook setHeaderRow(List<String> headers) {
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet();

        int cellCount = 0;
        int autoSizeCount = 0;


        for (String headerElem : headers) {
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(cellCount++);
            XSSFCellStyle style = workBook.createCellStyle();
            XSSFFont font = workBook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);

            cell.setCellValue(headerElem);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(autoSizeCount++);
        }
        return workBook;

    }

    public XSSFWorkbook setRowData(XSSFWorkbook workBook, List<List<String>> data) {
        XSSFSheet sheet = workBook.getSheetAt(0);
        int rowCount = 1;

        for (List<String> elemList : data) {
            XSSFRow row = sheet.createRow(rowCount++);
            for (String elem : elemList) {
                int cellCount = 0;
                XSSFCell cell = row.createCell(cellCount++);
                cell.setCellValue(elem);
            }
        }
        return workBook;
    }
}
