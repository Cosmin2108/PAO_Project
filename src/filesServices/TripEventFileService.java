package filesServices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TripEventFileService {
    private File file;
    private Scanner myScanner;
    private FileWriter myPrinter;
    private static TripEventFileService singleInstance = null;

    private TripEventFileService(String fileName){
        try {
            file = new File(fileName);
            myScanner = new Scanner(file);
            myPrinter = new FileWriter(file, true);
        }catch (FileNotFoundException e){
            System.out.println("An error occurred while opening file " + fileName);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TripEventFileService getInstance(String fileName){
        if(singleInstance == null) {
            singleInstance = new TripEventFileService(fileName);
        }
        return singleInstance;
    }

    public ArrayList<ArrayList<String>> getTripEventFromFile(){
        ArrayList<ArrayList<String>> categories = new ArrayList<ArrayList<String>>();
        while (myScanner.hasNextLine()) {
            String data = myScanner.nextLine();
            ArrayList<String> event = new ArrayList<String>(Arrays.asList(data.split(", ")));
            categories.add(event);
        }
        return categories;
    }

    public void addTripEventToFile(ArrayList<String> logInfos){
        String line = String.join(", ", logInfos);
        try {
            myPrinter.write(line + '\n');
            myPrinter.flush();
        }catch (IOException e){
            System.out.println("An error occurred while writing on " + file.getName());
        }
    }

}
