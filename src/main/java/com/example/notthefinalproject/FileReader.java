package com.example.notthefinalproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class FileReader {



    public ArrayList<Competition> competitions;


    FileReader(){
        try {
            this.competitions = getDataList();
        }catch (IOException e){
            System.out.println("The file dose not exist!");
        }

    }


    public static ArrayList<Competition> getDataList() throws IOException {

        ArrayList<Competition> compList = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File("/Users/nawafo/IdeaProjects/zg/src/Competitions Participations.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        Iterator<Sheet> ii = workbook.iterator();
        int sheetNumber = 0;
        while(ii.hasNext()){
            XSSFSheet sheet = (XSSFSheet) ii.next();
            boolean team = sheet.getRow(4).getPhysicalNumberOfCells() == 7; //this line will check if it's team or not.
            if (team){
                System.out.println("They are a team\n\n\n");
            }else {
                System.out.println("single\n\n\n");
            }
            Iterator<Row> itr = sheet.iterator();
            //iterating over excel file
            String compName = (itr.next()).getCell(1).getStringCellValue();
            String compUrl =  itr.next().getCell(1).getStringCellValue();
            String compDate =  itr.next().getCell(1).getNumericCellValue()+" ";
            compList.add(new Competition(compName, compDate, compUrl, !team));
            itr.next();
            double laseTeamAdded = 0;
            while (itr.hasNext())
            {
                Row row = itr.next();

                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                if(!team){
                    cellIterator.next();
                    double pId = cellIterator.next().getNumericCellValue();
                    String pName = cellIterator.next().getStringCellValue();
                    String pMajor = cellIterator.next().getStringCellValue();
                    Cell rankc =  cellIterator.next();
                    String rank = rankc.getCellType() == CellType.STRING ? rankc.getStringCellValue() : rankc.getNumericCellValue()+" ";
                    compList.get(compList.size()-1).addPartecipant(new SinglePartecipant(new Student(
                            pName,
                            pId,
                            pMajor
                    ), rank));


                }
                else{
                    cellIterator.next();
                    double pId = cellIterator.next().getNumericCellValue();
                    String pName = cellIterator.next().getStringCellValue();
                    String pMajor = cellIterator.next().getStringCellValue();
                    double teamNumber = cellIterator.next().getNumericCellValue();
                    String teamName = cellIterator.next().getStringCellValue();

                    Cell rankc =  cellIterator.next();
                    String rank = rankc.getCellType() == CellType.STRING ? rankc.getStringCellValue() : rankc.getNumericCellValue()+" ";
                    Student st = new Student(pName, pId, pMajor);
                    if(teamNumber == laseTeamAdded){
                        ((TeamPartecipant) (compList.get(compList.size()-1).partecipants.get(compList.get(compList.size()-1).partecipants.size()-1))).addMumber(st);
                    }else{
                        TeamPartecipant tp = new TeamPartecipant(teamName, rank);
                        tp.addMumber(st);
                        compList.get(compList.size()-1).addPartecipant(tp);
                    }
                    laseTeamAdded = teamNumber;



                }
            }
        }
        return compList;
    }


}
