package filesServices;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CategoriesFileService {
    private File file;
    private Scanner myScanner;
    private FileWriter myPrinter;
    private static CategoriesFileService service = null;

    private CategoriesFileService(String fileName){
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

    public static CategoriesFileService getInstance(String fileName){
        if(service == null) {
            service = new CategoriesFileService(fileName);
        }
        return service;
    }

    public ArrayList<String> getCategoriesFromFile(){
        ArrayList<String> categories = new ArrayList<>();
        while (myScanner.hasNextLine()) {
            String data = myScanner.nextLine();
            List<String> c = new ArrayList<String>(Arrays.asList(data.split(", ")));
            categories.addAll(c);
        }
        return categories;
    }

    public void addCategoriesToFile(String category){
        try {
            myPrinter.write(", " + category);
            myPrinter.flush();
        }catch (IOException e){
            System.out.println("An error occurred while writing on " + file.getName());
        }
    }
}
