/*
 * Author: Cambell Tanner
 * Date: June 2023
 * Country Quiz Game personal project
 * 
 */

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class CountryQuizGame { 
    private static final String DELIMITER = ",";
    private static final String SEPARATOR = "------------------------------------------"; 

    /**  
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

    public static String getFilePath(String fileName) {
        try {
            Path filePath = Paths.get(".", "src", fileName);
            return filePath.toAbsolutePath().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Process the string to ignore spaces and punctuation
    public static String preprocessString(String s) {
        // Remove punctuation
        String noPunctuation = s.replaceAll("\\p{Punct}", "");
        // Remove spaces
        String noSpaces = noPunctuation.replaceAll("\\s", "");
        return noSpaces.toLowerCase();
    }

    public static void main(String[] args) {
        List<Country> allCountries = loadCountries();
        Scanner sc = new Scanner(System.in);
        int correctCount;

        System.out.println("==========================================");
        System.out.println("Welcome to the Country Capitals Quiz!");
        System.out.println("How many rounds would you like to play?");
        int numRounds = sc.nextInt();
        sc.nextLine();
        System.out.println(SEPARATOR);

        System.out.println("Would you like to guess Countries(A) or Capitals(B)?");
        String gameType = sc.next();
        sc.nextLine();

        if (gameType.equalsIgnoreCase("A")||gameType.equalsIgnoreCase("Countries")) {
            correctCount = playCountryGame(numRounds, allCountries);
        }
        else if (gameType.equalsIgnoreCase("B")||gameType.equalsIgnoreCase("Capitals")) {
            correctCount = playCapitalGame(numRounds, allCountries);
        }
        else {
            System.out.println("Invalid game type entered.");
            return;
        }

        // Tells the user how many questions they got correct
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

    /* The version of the game where the user guesses the Capital City of a given Country */
    public static int playCapitalGame(int numRounds, List<Country> allCountries){
        Scanner scanner = new Scanner(System.in);
        int correctCount = 0;
        System.out.println(SEPARATOR);  
        System.out.println("Enter the Capital City of each Country,");
        System.out.println("or type 'quit' to exit.");

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
            System.out.println(SEPARATOR);

            // Type quit 
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Quitting...");
                break;
            }

            // Guess was correct
            if (preprocessString(input).equalsIgnoreCase(preprocessString(currentCity))) { 
                System.out.println("Well done, "+ currentCity +" is Correct!");
                correctCount++;
            }
            // Guess was incorrect
            else{ 
                System.out.println("Incorrect, the answer was "+currentCity+".");
            }
        }
        // Return the number of questions answered correctly
        return correctCount;
    }

    /* The version of the game where the user guesses the Country of a given Capital City */
    public static int playCountryGame(int numRounds, List<Country> allCountries){
        Scanner scanner = new Scanner(System.in);
        int correctCount = 0;
        System.out.println(SEPARATOR);
        System.out.println("Enter the Country that corresponds to the");
        System.out.println("Capital City, or type 'quit' to exit."); 

        for (int i = 1; i < numRounds + 1; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(allCountries.size());
            Country currentCountry = allCountries.get(randomIndex);
            String countryName = currentCountry.name();
            String currentCity = currentCountry.capital();
            System.out.println(SEPARATOR);
            System.out.println("Round "+ i); 
            System.out.println("Enter the Country that "+ currentCity +" is the Capital of: ");
            System.out.println(SEPARATOR); 

            String input = scanner.nextLine();
            System.out.println(SEPARATOR);

            // Type quit 
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Quitting...");
                break;
            }

            // Guess was correct
            if (preprocessString(input).equalsIgnoreCase(preprocessString(countryName))) {  
                System.out.println("Well done, "+ countryName +" is Correct!");
                correctCount++;
            }
            // Guess was incorrect
            else {
                System.out.println("Incorrect, the answer was "+countryName+".");
            }
        }
        // Return the number of questions answered correctly
        return correctCount;
    }
}
