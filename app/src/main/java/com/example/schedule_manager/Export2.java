package com.example.schedule_manager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Export2 {

        private static String[] columnsvard = {"Ημέρα", "Βάρδια", "Όνομα", "Επίθετο", "Πόστο"};
        private static String[] columnserg = {"Όνομα", "Επίθετο", "Ειδικότητα", "Συμβόλαιο", "Ώρες"};
        private static String[] columnshours = {"Όνομα", "Επίθετο", "Ειδικότητα", "Ώρες σύνολο"};

/*Creates excel file with employees.If param is empty creates an empty file.
         *
         * @param ergazomenoi
         * @throws IOException
*/


        public static void export_erg(ArrayList<Ergazomenoi> ergazomenoi) throws IOException {
            Workbook workbook = new HSSFWorkbook();

            Sheet sheetErg = workbook.createSheet("Εργαζόμενοι");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLUE_GREY.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setFont(headerFont);

            Row headerErgRow = sheetErg.createRow(0);

            for (int i = 0; i < columnserg.length; i++) {
                Cell cellErg = headerErgRow.createCell(i);
                cellErg.setCellValue(columnserg[i]);
                cellErg.setCellStyle(headerCellStyle);
            }

            CellStyle normCellStyle = workbook.createCellStyle();
            normCellStyle.setAlignment(HorizontalAlignment.CENTER);

            int rowNum = 1;
            for (Ergazomenoi erg : ergazomenoi) {
                Row rowErg = sheetErg.createRow(rowNum++);
                rowErg.createCell(0).setCellValue(erg.onoma);
                rowErg.createCell(1).setCellValue(erg.epitheto);
                rowErg.createCell(2).setCellValue(erg.eidikotita);
                rowErg.createCell(3).setCellValue(erg.contract);
                rowErg.createCell(4).setCellValue(erg.evWres);
                for (int j = 0; j < 5; j++)
                    rowErg.getCell(j).setCellStyle(normCellStyle);
            }
            for (int i = 0; i < columnserg.length; i++) {
                sheetErg.autoSizeColumn(i);
            }

            FileOutputStream fileOutputStream = new FileOutputStream("Εργαζόμενοι.xls");
            workbook.write(fileOutputStream);

            fileOutputStream.close();

            workbook.close();


        }

/**Create export file with shifts.If param is empty throw nullpointer.
     *
             * @param vardiaArrayList
     * @param date
     * @param arErgAnaMera
     * @throws IOException
*/

        public static void export_vardia(ArrayList<Schedule> vardiaArrayList, String date, int arErgAnaMera) throws IOException {
            Workbook workbook = new HSSFWorkbook();


            CreationHelper creationHelper = workbook.getCreationHelper();

            Sheet sheetVard = workbook.createSheet("Βάρδιες");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLUE_GREY.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setFont(headerFont);

            Row headerVardRow = sheetVard.createRow(0);


            for (int i = 0; i < columnsvard.length; i++) {
                Cell cellVard = headerVardRow.createCell(i);
                cellVard.setCellValue(columnsvard[i]);
                cellVard.setCellStyle(headerCellStyle);
            }

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            dateCellStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle normCellStyle = workbook.createCellStyle();
            normCellStyle.setAlignment(HorizontalAlignment.CENTER);

            int rowNum = 1;
            int counter = 0;
            for (Schedule var : vardiaArrayList) {
                if (counter % arErgAnaMera == 0 && counter != 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    try {
                        c.setTime(sdf.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    c.add(Calendar.DATE, 1);  // number of days to add
                    date = sdf.format(c.getTime());
                }
                counter++;
                Row rowVar = sheetVard.createRow(rowNum++);
                Cell dateVard = rowVar.createCell(0);
                dateVard.setCellValue(date);
                dateVard.setCellStyle(dateCellStyle);
                rowVar.createCell(1).setCellValue(var.getVardia());
                rowVar.createCell(2).setCellValue(var.getOnoma());
                rowVar.createCell(3).setCellValue(var.getEpitheto());
                rowVar.createCell(4).setCellValue(var.getEidikothta());
                for (int j = 1; j < 5; j++)
                    rowVar.getCell(j).setCellStyle(normCellStyle);
            }
            for (int i = 0; i < columnsvard.length; i++) {
                sheetVard.autoSizeColumn(i);
            }

            FileOutputStream fileOutputStream = new FileOutputStream("Βάρδιες.xls");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            workbook.close();





        }

/**Creates excel file with hours worked.If param empty throws nullpointer
     *
             * @param matrix
     * @param shiftsMap
     * @throws IOException
*/

        public static void export_Wres(Matrix matrix[], HashMap<String, String> shiftsMap) throws IOException {
            Workbook workbook = new HSSFWorkbook();



            Sheet sheetPer = workbook.createSheet("Ώρες Εργαζομένων");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLUE_GREY.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setFont(headerFont);

            Row headerPerRow = sheetPer.createRow(0);

            for (int i = 0; i < columnshours.length; i++) {
                Cell cellPer = headerPerRow.createCell(i);
                cellPer.setCellValue(columnshours[i]);
                cellPer.setCellStyle(headerCellStyle);
            }
            for (int i = columnshours.length; i < columnshours.length + shiftsMap.size(); i++) {
                Cell cellPer = headerPerRow.createCell(i);
                cellPer.setCellValue(shiftsMap.get(String.valueOf(i - columnshours.length + 1)));
                cellPer.setCellStyle(headerCellStyle);
            }
            CellStyle normCellStyle = workbook.createCellStyle();
            normCellStyle.setAlignment(HorizontalAlignment.CENTER);

            int rowNum = 1;
            int cellNum = shiftsMap.size() + 4;
            for (Matrix per : matrix) {
                Row rowPer = sheetPer.createRow(rowNum++);
                rowPer.createCell(0).setCellValue(per.getErgazomenos().getOnoma());
                rowPer.createCell(1).setCellValue(per.getErgazomenos().getEpitheto());
                rowPer.createCell(2).setCellValue(per.getErgazomenos().getEidikotita());
                rowPer.createCell(3).setCellValue(per.getTotalHours());
                if (shiftsMap.size() >= 1)
                    rowPer.createCell(4).setCellValue(per.getWres1());
                if (shiftsMap.size() >= 2)
                    rowPer.createCell(5).setCellValue(per.getWres2());
                if (shiftsMap.size() >= 3)
                    rowPer.createCell(6).setCellValue(per.getWres3());
                if (shiftsMap.size() >= 4)
                    rowPer.createCell(7).setCellValue(per.getWres4());
                if (shiftsMap.size() >= 5)
                    rowPer.createCell(8).setCellValue(per.getWres5());
                if (shiftsMap.size() >= 6)
                    rowPer.createCell(9).setCellValue(per.getWres6());

                for (int j = 0; j < cellNum; j++) {
                    rowPer.getCell(j).setCellStyle(normCellStyle);
                }
            }
            for (int i = 0; i < columnshours.length; i++) {
                sheetPer.autoSizeColumn(i);
            }

            FileOutputStream fileOutputStream = new FileOutputStream("Ωρες Εργαζομένων.xls");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            workbook.close();





        }

/**
 * Creates excel file with shifts,employees and hours worked.if params empty throws nullpointer
     *
             * @param ergazomenoi
     * @param matrix
     * @param shiftsMap
     * @param vardiaArrayList
     * @param date
     * @param arErgAnaMera
     * @throws IOException
     * @throws NullPointerException
*/

        public static void ExportWhole(ArrayList<Ergazomenoi> ergazomenoi, Matrix matrix[], HashMap<String, String> shiftsMap, ArrayList<Schedule> vardiaArrayList, String date, int arErgAnaMera) throws IOException,NullPointerException {
            Workbook workbook = new HSSFWorkbook();
            CreationHelper creationHelper = workbook.getCreationHelper();

            Sheet sheetVard = workbook.createSheet("Βάρδιες");
            Sheet sheetErg = workbook.createSheet("Εργαζόμενοι");
            Sheet sheetPer = workbook.createSheet("Ώρες Εργαζομένων");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLUE_GREY.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setFont(headerFont);

            Row headerVardRow = sheetVard.createRow(0);
            Row headerErgRow = sheetErg.createRow(0);
            Row headerPerRow = sheetPer.createRow(0);
            for (int i = 0; i < columnsvard.length; i++) {
                Cell cellVard = headerVardRow.createCell(i);
                cellVard.setCellValue(columnsvard[i]);
                cellVard.setCellStyle(headerCellStyle);
            }
            for (int i = 0; i < columnserg.length; i++) {
                Cell cellErg = headerErgRow.createCell(i);
                cellErg.setCellValue(columnserg[i]);
                cellErg.setCellStyle(headerCellStyle);
            }
            for (int i = 0; i < columnshours.length; i++) {
                Cell cellPer = headerPerRow.createCell(i);
                cellPer.setCellValue(columnshours[i]);
                cellPer.setCellStyle(headerCellStyle);
            }
            for (int i = columnshours.length; i < columnshours.length + shiftsMap.size(); i++) {
                Cell cellPer = headerPerRow.createCell(i);
                cellPer.setCellValue(shiftsMap.get(String.valueOf(i - columnshours.length + 1)));
                cellPer.setCellStyle(headerCellStyle);
            }

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            dateCellStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle normCellStyle = workbook.createCellStyle();
            normCellStyle.setAlignment(HorizontalAlignment.CENTER);

            int rowNum = 1;
            for (Ergazomenoi erg : ergazomenoi) {
                Row rowErg = sheetErg.createRow(rowNum++);
                rowErg.createCell(0).setCellValue(erg.onoma);
                rowErg.createCell(1).setCellValue(erg.epitheto);
                rowErg.createCell(2).setCellValue(erg.eidikotita);
                rowErg.createCell(3).setCellValue(erg.contract);
                rowErg.createCell(4).setCellValue(erg.evWres);
                for (int j = 0; j < 5; j++)
                    rowErg.getCell(j).setCellStyle(normCellStyle);
            }
            for (int i = 0; i < columnsvard.length; i++) {
                sheetVard.autoSizeColumn(i);
            }

            rowNum = 1;
            int counter = 0;
            for (Schedule var : vardiaArrayList) {
                if (counter % arErgAnaMera == 0 && counter != 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    try {
                        c.setTime(sdf.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    c.add(Calendar.DATE, 1);  // number of days to add
                    date = sdf.format(c.getTime());
                }
                counter++;
                Row rowVar = sheetVard.createRow(rowNum++);
                Cell dateVard = rowVar.createCell(0);
                dateVard.setCellValue(date);
                dateVard.setCellStyle(dateCellStyle);
                rowVar.createCell(1).setCellValue(var.getVardia());
                rowVar.createCell(2).setCellValue(var.getOnoma());
                rowVar.createCell(3).setCellValue(var.getEpitheto());
                rowVar.createCell(4).setCellValue(var.getEidikothta());
                for (int j = 1; j < 5; j++)
                    rowVar.getCell(j).setCellStyle(normCellStyle);
            }
            for (int i = 0; i < columnserg.length; i++) {
                sheetErg.autoSizeColumn(i);
            }

            rowNum = 1;
            int cellNum = shiftsMap.size() + 4;
            for (Matrix per : matrix) {
                Row rowPer = sheetPer.createRow(rowNum++);
                rowPer.createCell(0).setCellValue(per.getErgazomenos().getOnoma());
                rowPer.createCell(1).setCellValue(per.getErgazomenos().getEpitheto());
                rowPer.createCell(2).setCellValue(per.getErgazomenos().getEidikotita());
                rowPer.createCell(3).setCellValue(per.getTotalHours());
                if (shiftsMap.size() >= 1)
                    rowPer.createCell(4).setCellValue(per.getWres1());
                if (shiftsMap.size() >= 2)
                    rowPer.createCell(5).setCellValue(per.getWres2());
                if (shiftsMap.size() >= 3)
                    rowPer.createCell(6).setCellValue(per.getWres3());
                if (shiftsMap.size() >= 4)
                    rowPer.createCell(7).setCellValue(per.getWres4());
                if (shiftsMap.size() >= 5)
                    rowPer.createCell(8).setCellValue(per.getWres5());
                if (shiftsMap.size() >= 6)
                    rowPer.createCell(9).setCellValue(per.getWres6());

                for (int j = 0; j < cellNum; j++)
                    rowPer.getCell(j).setCellStyle(normCellStyle);
            }
            for (int i = 0; i < columnshours.length; i++) {
                sheetPer.autoSizeColumn(i);
            }

            FileOutputStream fileOutputStream = new FileOutputStream("Πρόγραμμα.xls");
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            workbook.close();
        }
    }

