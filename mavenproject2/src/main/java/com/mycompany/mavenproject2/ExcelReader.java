package com.mycompany.mavenproject2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Import excel files to the database
 * @author Stan van Weringh 500771870 (95 lines)
 */
public class ExcelReader {

    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private Iterator<Row> rowIterator = null;

    public ExcelReader(String fileName) {
        try (FileInputStream file = new FileInputStream(new File(fileName))) {

            // Create Workbook instance holding reference to .xlsx file
            this.workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            setSelectedSheet(0);
        } catch (IOException ex) {
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }

    public int getNumberOfSheets() {
        return this.workbook.getNumberOfSheets();
    }
        
    public final void setSelectedSheet(int sheetNr) {
        // Get desired sheet from the workbook
        this.sheet = this.workbook.getSheetAt(sheetNr);

        // initialise the row iterator to the first row in the sheet
        this.rowIterator = this.sheet.iterator();
    }

    public List<String> getNextRow() {
        if (this.rowIterator.hasNext()) {
            return parseRow(this.rowIterator.next());
        } else {
            return null;
        }
    }

    private List<String> parseRow(Row row) {

        // a string list will capture the values
        List<String> sList = new ArrayList<>();

        for (int cn = 0; cn < row.getLastCellNum(); cn++) {
            Cell cell = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell == null) {
                sList.add("");
            } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                String str = NumberToTextConverter.toText(cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell)) {
                    str = cell.toString();
                    // if the cell contained a time value, the apache library will not properly convert to string
                    if (str.equals("31-Dec-1899")) {
                        // compute time in the day in seconds
                        int secsInDay = (int) ((cell.getDateCellValue().getTime() / 1000) % 86400);
                        if (secsInDay < 0) {
                            secsInDay += 86400;
                        }
                        // compute hours, minutes and format the string
                        int hours = secsInDay / 3600;
                        int minutes = (secsInDay % 3600) / 60;
                        str = String.format("%02d:%02d", hours, minutes);
                    }
                }
                sList.add(str);
            } else {
                sList.add(cell.toString());
            }
        }

        return sList;
    }
}
