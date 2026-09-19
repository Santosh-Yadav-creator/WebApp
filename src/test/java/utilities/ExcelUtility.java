package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;

    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;

    String path;

    // Constructor
    public ExcelUtility(String path) {
        this.path = path;
    }

    // ============================================================
    // Get Row Count
    // ============================================================

    public int getRowCount(String sheetName) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();

        workbook.close();
        fi.close();

        return rowCount;
    }

    // ============================================================
    // Get Cell Count
    // ============================================================

    public int getCellCount(String sheetName, int rowNum) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);

        int cellCount = row.getLastCellNum();

        workbook.close();
        fi.close();

        return cellCount;
    }

    // ============================================================
    // Get Cell Data
    // ============================================================

    public String getCellData(String sheetName, int rowNum, int colNum)
            throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);

        if (row == null) {
            workbook.close();
            fi.close();
            return "";
        }

        cell = row.getCell(colNum);

        if (cell == null) {
            workbook.close();
            fi.close();
            return "";
        }

        DataFormatter formatter = new DataFormatter();

        String data = formatter.formatCellValue(cell);

        workbook.close();
        fi.close();

        return data;
    }

    // ============================================================
    // Set Cell Data
    // ============================================================

    public void setCellData(
            String sheetName,
            int rowNum,
            int colNum,
            String data) throws IOException {

        File file = new File(path);

        // Open existing workbook
        fi = new FileInputStream(file);

        workbook = new XSSFWorkbook(fi);

        fi.close();

        sheet = workbook.getSheet(sheetName);

        // Create sheet if it doesn't exist
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        // Create row if it doesn't exist
        row = sheet.getRow(rowNum);

        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        // Create cell if it doesn't exist
        cell = row.getCell(colNum);

        if (cell == null) {
            cell = row.createCell(colNum);
        }

        cell.setCellValue(data);

        // Write changes to Excel
        fo = new FileOutputStream(file);

        workbook.write(fo);

        workbook.close();
        fo.close();
    }

    // ============================================================
    // Fill Cell Green - PASS
    // ============================================================

    public void fillGreenColor(
            String sheetName,
            int rowNum,
            int colNum) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        fi.close();

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);

        cell = row.getCell(colNum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(
                IndexedColors.GREEN.getIndex()
        );

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);

        workbook.write(fo);

        workbook.close();
        fo.close();
    }

    // ============================================================
    // Fill Cell Red - FAIL
    // ============================================================

    public void fillRedColor(
            String sheetName,
            int rowNum,
            int colNum) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        fi.close();

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);

        cell = row.getCell(colNum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(
                IndexedColors.RED.getIndex()
        );

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);

        workbook.write(fo);

        workbook.close();
        fo.close();
    }
}