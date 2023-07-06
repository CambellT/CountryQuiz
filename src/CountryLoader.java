/*
 * Author: Cambell Tanner
 * Date: July 2023
 * Country Quiz Game personal project
 * 
 */

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/* 
 * Class to load countries and create a country object from each line
 */
public class CountryLoader { 
    private static final String DELIMITER = ","; 
    private CountryLoader() {}
    /*  
     * Loads the countries from the csv file into the set of all countries
     */
    public static List<Country> loadCountries(){
        List<Country> allCountries = new ArrayList<>(); 
        try {
            String fileName = "countries.csv";
            String filePath = getFilePath(fileName);
            List<String> lines = Files.readAllLines(Path.of(filePath));
            boolean isFirstLine = true;
            for (String line : lines) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(DELIMITER);
                String id = values[0].replace("\"", "");
                String name = values[1].replace("\"", "");
                String capital = values[2].replace("\"", "");
                String abr = values[3].replace("\"", "");
                String continent = values[4].replace("\"", "");
                Country c = new Country(id, name, capital, abr, continent);
                allCountries.add(c);
            }
            System.out.printf("Loaded %d countries.%n", allCountries.size());
        } catch(IOException e){
            System.out.println("File reading failed");
        }
        return allCountries;
    }

    /* 
     * Gets the file path of a file or image to ensure everything is loaded correctly 
     */
    public static String getFilePath(String fileName) {
        try {
            Path filePath = Paths.get(".", "src", fileName);
            return filePath.toAbsolutePath().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* 
     * Process the string to ignore spaces and punctuation 
     */
    public static String preprocessString(String s) {
        // Remove punctuation
        String noPunctuation = s.replaceAll("\\p{Punct}", "");
        // Remove spaces
        String noSpaces = noPunctuation.replaceAll("\\s", "");
        return noSpaces.toLowerCase();
    }
}
