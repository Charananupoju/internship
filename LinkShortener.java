import java.io.*;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinkShortener {

    private final Map<String, String> shortToLongMap;
    private final Map<String, String> longToShortMap;
    private static final String DATA_FILE = "link_data.txt"; // File to persist data

    public LinkShortener() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
        loadPersistedData(); // Load data from the file on initialization
    }

    // Function to generate a secure short URL
    private String generateShortURL(String longURL) {
        int hash = longURL.hashCode();
        return Integer.toString(hash, Character.MAX_RADIX);
    }

    // Function to shorten a long URL
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return longToShortMap.get(longURL);
        }

        String shortURL = generateShortURL(longURL);
        shortToLongMap.put(shortURL, longURL);
        longToShortMap.put(longURL, shortURL);
        persistData(); // Save data to the file

        return shortURL;
    }

    // Function to expand a short URL
    public String expandURL(String shortURL) {
        return shortToLongMap.getOrDefault(shortURL, "URL not found");
    }

    // Helper function to load data from a persisted file
    private void loadPersistedData() {
        try (Scanner scanner = new Scanner(new File(DATA_FILE))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 2) {
                    shortToLongMap.put(parts[0], parts[1]);
                    longToShortMap.put(parts[1], parts[0]);
                }
            }
        } catch (FileNotFoundException e) {
            // File not found, it's okay for the first run
        }
    }

    // Helper function to persist data to a file
    private void persistData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Map.Entry<String, String> entry : shortToLongMap.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL: ");
                    String longURL = scanner.nextLine();
                    String shortURL = linkShortener.shortenURL(longURL);
                    System.out.println("Shortened URL: " + shortURL);
                    break;

                case 2:
                    System.out.print("Enter the short URL: ");
                    String inputShortURL = scanner.nextLine();
                    String expandedURL = linkShortener.expandURL(inputShortURL);
                    System.out.println("Expanded URL: " + expandedURL);
                    break;

                case 3:
                    System.out.println("Exiting the program. Thank you!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }
}
