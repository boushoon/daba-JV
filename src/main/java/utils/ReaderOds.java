package utils;

import com.github.miachm.sods.Range;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ReaderOds{
    public static void read(String path){
        try {
            SpreadSheet spread = new SpreadSheet(new File(path));
            //System.out.println("Number of sheets: " + spread.getNumSheets());
            //List<Sheet> sheets = spread.getSheets();

            Sheet studentTable = spread.getSheet("Студенты");
            System.out.println(Arrays.deepToString(studentTable.getDataRange().getValues()));

//            for (Sheet sheet : sheets) {
//                System.out.println("In sheet " + sheet.getName());
//
//                Range range = sheet.getDataRange();
//                System.out.println(range.toString());
//            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
