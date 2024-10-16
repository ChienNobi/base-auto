package helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter {
    XSSFWorkbook workbook;
    private static XSSFSheet sheet;

    private static XSSFRow row;
    public FileOutputStream outputStream;

    public ExcelWriter(String filePath) throws FileNotFoundException {
        workbook = new XSSFWorkbook();
        outputStream = new FileOutputStream(new File(filePath));
    }

    public ExcelWriter(String filePath, String sheetName) throws FileNotFoundException {
        workbook = new XSSFWorkbook();
        outputStream = new FileOutputStream(new File(filePath));
        createSheet(sheetName);
    }

    public void createSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        if(sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
    }

    public void setCellValue(int rowIndex, int cellIndex, String value) {
        if(sheet == null) {
            sheet = workbook.createSheet("Sheet1");
        }
        if(sheet.getRow(rowIndex) == null) {
            row = sheet.createRow(rowIndex);
        };
        row.createCell(cellIndex).setCellValue(value);
    }

    public void setCellValue(XSSFRow row, int cellIndex, String value) {
        if(sheet == null) {
            sheet = workbook.createSheet("Sheet1");
        }
        row.createCell(cellIndex).setCellValue(value);
    }

    public void writeFile() throws IOException {
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    // if open exist workbook -> can use this function to write data to excel
    public void writeByOtherData(XSSFWorkbook workbook) throws IOException {
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
