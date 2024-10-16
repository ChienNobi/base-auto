package helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static Cell cell;
    FileInputStream file;
    DataFormatter formatter = new DataFormatter();

    public  ExcelReader(String filePath) throws IOException {
        file = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook (file);
    }

    public ExcelReader(String filePath, String sheetName) throws IOException {
        this(filePath);
        initSheet(sheetName);
    }

    /**
     * Init sheet for TestCase
     * @param sheetName
     * @return
     */
    public ExcelReader initSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName); // get sheet by name
        return this;
    }

    public XSSFSheet getCurrentSheet() {
        return sheet;
    }

    public ArrayList getRowData(String sheetName, int rowIndex) {
        initSheet(sheetName);
        return getRowData(rowIndex);
    }

    public ArrayList getRowData(int rowIndex) {
        row = sheet.getRow(rowIndex);
        return getAllRowData();
    }

    public ArrayList getTestDataByKey(String key) {
        int count = 0;
        Cell cell;
        row = null;
        while(count < 20) {
            cell = sheet.getRow(count).getCell(0);
            if(formatter.formatCellValue(cell).equals(key)) {
                row = sheet.getRow(count);
                break;
            }
            count++;
        }

        return getAllRowData();
    }

    private ArrayList<String> getAllRowData() {
        ArrayList<String> cellData = new ArrayList<String>();

        for (Cell value : row) {
            cell = value;
            cellData.add(formatter.formatCellValue(cell));
        }
        return cellData;
    }

    public List<Map<String, String>> parseData(String[] columns, boolean ignoreHeader) {
        List<Map<String, String>> data = new ArrayList<>();

        for(Row row : sheet) {
            Map<String, String> rowData = new HashMap<>();
            for(int i = 0; i < columns.length; i++) {
                rowData.put(columns[i], formatter.formatCellValue(row.getCell(i)));
            }
            rowData.put("row_index", String.valueOf(row.getRowNum()));

            data.add(rowData);
        }
        if(ignoreHeader && !data.isEmpty()) {
            data.remove(0);
        }
        return data;
    }

    public void setCellValue(int rowIndex, int cellIndex, String value) {
        if(sheet == null) {
            throw new RuntimeException("Have no sheet");
        }
        Cell cell = sheet.getRow(rowIndex).createCell(cellIndex);
        cell.setCellValue(value);
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public int getRowCount() {
        return sheet.getLastRowNum() - sheet.getFirstRowNum();
    }

    public void closeFile() throws IOException {
        file.close();
    }
}
