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
import java.text.Normalizer;
import java.util.regex.Pattern;

public class CountryQuizGame { 
    private static final String DELIMITER = ",";
    private static final String SEPARATOR = "------------------------------------------"; 

    /**  
     * Loads the countries from the csv file into the set of all countries
     */
    public static List<Country> loadCountries(){
        List<Country> allCountries = new ArrayList<Country>(); 
        try {
            String fileName = "countries.csv";
            String filePath = getFilePath(fileName);
            List<String> lines = Files.readAllLines(Path.of(filePath));
            for (String line : lines) {
                String[] values = line.split(DELIMITER);
                String name = values[0].replace("\"", "");
                String capital = values[1].replace("\"", "");
                Country c = new Country(name, capital);
                allCountries.add(c);
            }
            System.out.printf("Loaded %d countries.%n", allCountries.size());
        } catch(IOException e){
            System.out.println("File reading failed");
        }
        for (Country c : allCountries){
            //System.out.println(c.toString());
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

        List<Country> allCountries = loadCountries();
        Scanner scanner = new Scanner(System.in);
        int correctCount = 0;

        System.out.println("==========================================");
        System.out.println("Welcome to the Country Capitals Quiz!");
        System.out.println("Enter the Capital City of each Country,");
        System.out.println("or type 'quit' to exit.");
        System.out.println(SEPARATOR); 
        System.out.println("How many rounds would you like to play?");

        int numRounds = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i < numRounds + 1; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(allCountries.size());
            Country currentCountry = allCountries.get(randomIndex);
            String currentCity = currentCountry.capital();
            System.out.println(SEPARATOR);
            System.out.println("Round "+ i); 
            System.out.println("Enter the Capital City for "+ currentCountry.name() +": ");
            System.out.println(SEPARATOR); 

            String input = scanner.nextLine();

            // Type quit 
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (preprocessString(input).equalsIgnoreCase(currentCity)) { 
                System.out.println(SEPARATOR); 
                System.out.println("Well done, "+ currentCity +" is Correct!");
                System.out.println(input);
                correctCount++;
            }
            else{
                System.out.println(SEPARATOR); 
                System.out.println("Incorrect, the answer was "+currentCountry.capital()+".");
            }
        }

        System.out.println(SEPARATOR); 
        if (correctCount == numRounds) {
            System.out.println("Well done! You got every question correct!");
        }
        else if (correctCount > 1) {
            System.out.println("You got "+correctCount+" questions correct out of "+numRounds+".");
        }
        else if (correctCount > 0) {
            System.out.println("You got "+correctCount+" question correct out of "+numRounds+".");
        }
        else {
            System.out.println("Sorry, you got no questions correct.");
        }
        System.out.println(SEPARATOR); 

    }

    public static String preprocessString(String s) {
        // Remove punctuation
        String noPunctuation = s.replaceAll("\\p{Punct}", "");

        // Remove spaces
        String noSpaces = noPunctuation.replaceAll("\\s", "");

        return noSpaces.toLowerCase();
    }

}
