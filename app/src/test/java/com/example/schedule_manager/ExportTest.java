 package com.example.schedule_manager;

 import com.example.schedule_manager.Ergazomenoi;
 import com.example.schedule_manager.Export;

 import org.junit.After;
import org.junit.Before;
 import org.junit.Rule;
 import org.junit.Test;
 import org.junit.rules.ExpectedException;

 import java.io.IOException;
 import java.lang.reflect.Array;
 import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;

 import static com.example.schedule_manager.Schedule.createSchedule;
 import static org.junit.Assert.*;

public class ExportTest {
    public ArrayList<Ergazomenoi> testErgazomenoi,testErgazomenoiNull;
    public ArrayList<Schedule> testSchedule,testScheduleNull;
    public List<Vardies> testvardiesList,vardiesListNull ;
    public int testdiarkeia,diarkeiaNull;
    public HashMap<String,String> testshiftsMap,shiftsMapNull;
    public Matrix testmatrix[],matrixNull[];

    @Before
    public void SetUp(){
        //SetUp with data
        testErgazomenoi = new ArrayList<>();
        testErgazomenoi.add(new Ergazomenoi(1,"Basil1is", "Kalogirou","Katharistria", 80, "den kserw", false));
        testErgazomenoi.add(new Ergazomenoi(2,"Apostolis", "Baliakos","Deliveras", 80, "den kserw", false));
        testErgazomenoi.add(new Ergazomenoi(3,"Spyros", "Gianniler","Asfalitis", 80, "den kserw", false));
        testErgazomenoi.add(new Ergazomenoi(1,"Basil1is22", "Kalogirou22","Katharistria", 80, "den kserw", false));
        testErgazomenoi.add(new Ergazomenoi(2,"Apostolis22", "Baliakos22","Deliveras", 80, "den kserw", false));
        testErgazomenoi.add(new Ergazomenoi(3,"Spyros22", "Gianniler22","Asfalitis", 80, "den kserw", false));
        testErgazomenoi.add(new Ergazomenoi(1,"Basil1is33", "Kalogirou33","Katharistria", 80, "den kserw", false));
        testErgazomenoi.add(new Ergazomenoi(2,"Apostolis33", "Baliakos33","Deliveras", 80, "den kserw", false));
        testErgazomenoi.add(new Ergazomenoi(3,"Spyros33", "Gianniler33","Asfalitis", 80, "den kserw", false));
        testshiftsMap=new HashMap<>();
        testshiftsMap.put("1","prwi");
        testshiftsMap.put("2","apogeuma");
        testshiftsMap.put("3","vradu");
        testvardiesList=new ArrayList<>();
        testvardiesList.add(new Vardies("1","Katharistria",1));
        testvardiesList.add(new Vardies("1","Deliveras",1));
        testvardiesList.add(new Vardies("1","Asfalitis",1));
        testvardiesList.add(new Vardies("2","Katharistria",1));
        testvardiesList.add(new Vardies("2","Deliveras",1));
        testvardiesList.add(new Vardies("2","Asfalitis",1));
        testvardiesList.add(new Vardies("3","Katharistria",1));
        testvardiesList.add(new Vardies("3","Deliveras",1));
        testvardiesList.add(new Vardies("3","Asfalitis",1));
        testmatrix = new Matrix[testErgazomenoi.size()];
        testdiarkeia=5;
        int i = 0;

        for (Ergazomenoi erg : testErgazomenoi) {
            testmatrix[i] = new Matrix(erg);
            i++;
        }
        for(int meres =0; meres <testdiarkeia; meres++) {

            testSchedule=(ArrayList)createSchedule(testmatrix, testvardiesList, testshiftsMap);
        }

        //SetUp with Nulls
        testErgazomenoiNull=new ArrayList<>();
        shiftsMapNull=new HashMap<>();
        vardiesListNull=new ArrayList<>();
        matrixNull = new Matrix[testErgazomenoi.size()];
        diarkeiaNull=0;
    }
    @Test
    public void checkThatErgazomenoiFileExists() throws IOException {

       Export.export_erg(testErgazomenoi);
        assertTrue((Files.exists(Paths.get("Εργαζόμενοι.xls"))));
    }
    @Test
    public void checkThatErgazomenoiFileExistsWithoutData() throws IOException {

        Export.export_erg(testErgazomenoiNull);
        assertTrue((Files.exists(Paths.get("Εργαζόμενοι.xls"))));
    }
    @Test
    public void checkThatBardiesFileExists() throws IOException {

        Export.export_vardia(testSchedule,"11/1/2000",testErgazomenoi.size());
        assertTrue((Files.exists(Paths.get("Βάρδιες.xls"))));
    }
    @Test
    public void checkThatBardiesWithoutDataThrowsNullPointer() {

        assertThrows(NullPointerException.class,() -> {
            Export.export_vardia(testScheduleNull,"11/1/2000",testErgazomenoiNull.size());
        });

    }
    @Test
    public void checkThatWresFileExists() throws IOException {
        Export.export_Wres(testmatrix,testshiftsMap);
        assertTrue((Files.exists(Paths.get("Ωρες Εργαζομένων.xls"))));
    }
    @Test
    public void checkThatWresWithoutDataThrowsNullPointer(){

        assertThrows(NullPointerException.class,() -> {
            Export.export_Wres(matrixNull, shiftsMapNull);
        });
    }
   @Test
    public void checkThatWholeFileExists() throws IOException {
        Export.ExportWhole(testErgazomenoi, testmatrix, testshiftsMap, testSchedule,"11/1/2000", testErgazomenoi.size());
        assertTrue((Files.exists(Paths.get("Πρόγραμμα.xls"))));
    }
    @Test
    public void checkThatWholeWithoutDataThrowsNullPointer()  {

        assertThrows(NullPointerException.class,() -> {
            Export.ExportWhole(testErgazomenoiNull, matrixNull, shiftsMapNull, testScheduleNull,"11/1/2000", testErgazomenoiNull.size());
        });
    }
    @After
    public void TearDown() throws IOException {
        testErgazomenoi =null;
        testErgazomenoiNull=null;
        testSchedule=null;
        testScheduleNull=null;
        matrixNull=null;
        testmatrix=null;
        shiftsMapNull=null;
        testshiftsMap=null;
        vardiesListNull=null;
        testvardiesList=null;

        if(Files.exists(Paths.get("Εργαζόμενοι.xls")))
         Files.delete(Paths.get("Εργαζόμενοι.xls"));
        if(Files.exists(Paths.get("Βάρδιες.xls")))
            Files.delete(Paths.get("Βάρδιες.xls"));
        if(Files.exists(Paths.get("Ωρες Εργαζομένων.xls")))
            Files.delete(Paths.get("Ωρες Εργαζομένων.xls"));
        if(Files.exists(Paths.get("Πρόγραμμα.xls")))
            Files.delete(Paths.get("Πρόγραμμα.xls"));

    }
}