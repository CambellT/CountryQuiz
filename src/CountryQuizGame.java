import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CountryQuizGame {
    private static final String CSV_FILE_PATH = "countries.csv";
    private static final String DELIMITER = "\t";

    public static void main(String[] args) {
        List<Map<String, String>> countries = loadCountryData(CSV_FILE_PATH);

        System.out.println("Welcome to the Country Information Program!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==========================================");
            System.out.println("Enter a country name (or 'quit' to exit): ");
            System.out.println("------------------------------------------");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            String capital = getCapital(input, countries);
            if (capital != null) {
                System.out.println("==========================================");
                System.out.println("Country: " + input);
                System.out.println("Capital: " + capital);
            } else {
                System.out.println("==========================================");
                System.out.println("Country not found.");
            }
        }

        scanner.close();
    }

    private static List<Map<String, String>> loadCountryData(String filePath) {
        List<Map<String, String>> countries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headers = null;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(DELIMITER);

                if (headers == null) {
                    headers = data; // Assume first line as headers
                } else {
                    Map<String, String> country = new HashMap<>();

                    for (int i = 0; i < headers.length && i < data.length; i++) {
                        country.put(headers[i], data[i]);
                    }

                    countries.add(country);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the CSV file: " + e.getMessage());
        }

        return countries;
    }

    private static String getCapital(String countryName, List<Map<String, String>> countries) {
        for (Map<String, String> country : countries) {
            if (country.containsKey("country") && country.containsKey("capital")) {
                String name = country.get("country");
                if (name.equalsIgnoreCase(countryName)) {
                    return country.get("capital");
                }
            }
        }
        return null;
    }
}
