package filesServices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ActionLogsService {
    private static ActionLogsService singleInstance = null;
    private File file;
    private Scanner myScanner;
    private FileWriter myPrinter;

    private ActionLogsService(String fileName){
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

    public static ActionLogsService getSingleInstance(String fileName){
        if (singleInstance == null)
            singleInstance = new ActionLogsService(fileName);
        return singleInstance;
    }

    public void addLogToFile(ArrayList<String> logInfos){
        String line = String.join(", ", logInfos);
        try {
            LocalTime date = LocalTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss"); // or DateTimeFormatter.ISO_LOCAL_TIME
            String dateFormatted = date.format(myFormatObj);
            myPrinter.write("Logged at: " + dateFormatted + ", " + line + '\n');
            myPrinter.flush();
        }catch (IOException e){
            System.out.println("An error occurred while writing on " + file.getName());
        }
    }

    public ArrayList<String> getLogsFromFile(){
        ArrayList<String> lines = new ArrayList<>();
        while (myScanner.hasNextLine()) {
            String line = myScanner.nextLine();
            ArrayList<String> lineValues = new ArrayList<>(Arrays.asList(line.split(", ")));
            line = "";
            for(String value:lineValues){
                line += value + '\n';
            }
            lines.add(line);
        }
        return lines;
    }
}