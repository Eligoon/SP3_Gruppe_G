import utility.TextUI;

import java.util.*;

public class StreamingService {
    private Scanner scanner;
    private List<Movie> movies;
    private List<Series> series;
    private ArrayList<User> users = new ArrayList<>();

    TextUI ui = new TextUI();

    public StreamingService() {
        this.scanner = new Scanner(System.in);
        this.movies = new ArrayList<>();
        this.series = new ArrayList<>();
    }

    private void mainMenu()
    {
// Define menu options
        ArrayList<String> menuOptions = new ArrayList<>();
        menuOptions.add("Search for media by name");
        menuOptions.add("Search for media by category");
        menuOptions.add("Get list of your saved movies");
        menuOptions.add("Get list of movies you have already seen");
        menuOptions.add("Exit streaming service");

        while (true)
        {
            // Prompt user for input using TextUI
            int choice = ui.promptMenu("Main Menu", menuOptions);

            // Handle the menu choice
            switch (choice)
            {
                case 1:
                   searchByName();
                    break;
                case 2:
                    searchByCategory();
                    break;
                case 3:
                    getListOfSaved();
                    break;
                case 4:
                    getListOfWatched();
                    break;
                case 5:
                    // Exit the program safely
                    System.out.println("Exiting streaming service.");

                    System.exit(0);
                default:
                    // If the input doesn't match a valid option
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    // Getters and setters
    public Scanner getScanner() {
        return scanner;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Series> getSeries() {
        return series;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}