/*
 * Author: Cambell Tanner
 * Date: June 2023
 * Country Quiz Game personal project
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class CountryQuizGame { 
    private static final String DELIMITER = ",";

    /**  
     * Loads the countries from the csv file into the set of all countries
     */
    public static Set<Country> loadCountries(){
        Set<Country> allCountries = new HashSet<Country>(); 
        try {
            String fileName = "countries.csv";
            String filePath = getFilePath(fileName);
            List<String> lines = Files.readAllLines(Path.of(filePath));
            for (String line : lines) {
                //System.out.println(line);
                String[] values = line.split(",");
                String name = values[0].replace("\"", "");
                String capital = values[1].replace("\"", "");
                Country c = new Country(name, capital);
                allCountries.add(c);
            }
            System.out.printf("Loaded %d countries.", allCountries.size());
        } catch(IOException e){
            System.out.println("File reading failed");
        }
        for (Country c : allCountries){
            System.out.println(c.toString());
        }
        return allCountries;
    }

    public static String getFilePath(String fileName) {
        try {
            Path filePath = Paths.get(".", "src", fileName);
            return filePath.toAbsolutePath().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String fileName = "countries.csv";
        String filePath = getFilePath(fileName);

        if (filePath != null) {
            System.out.println("Good file");
        } else {
            System.out.println("File is not accessible.");
        }
        Set<Country> allCountries = loadCountries();

        System.out.println("Welcome to the Country Capitals Quiz!");

        while (true) {
            System.out.println("==========================================");
            System.out.println("Enter a country name (or 'quit' to exit): ");
            System.out.println("------------------------------------------");
            break;
        }
    }

}
