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

    private Media searchByName()
    {
        String searchFor = ui.promptText("Enter the name of the media to search for: ").toLowerCase();

        ArrayList<Media> foundMedia = new ArrayList<>();

        // Search through all movies
        for (Movie m : movies)
        {
            if (m.getName().toLowerCase().contains(searchFor))
            {
                foundMedia.add(m);
            }
        }

        // Search through all series
        for (Series s : series)
        {
            if (s.getName().toLowerCase().contains(searchFor))
            {
                foundMedia.add(s);
            }
        }

        if (foundMedia.isEmpty())
        {
            System.out.println("No media found with that name.");
            return null;
        }

        // Create a list of media names for the menu
        ArrayList<String> mediaNames = new ArrayList<>();
        for (Media media : foundMedia)
        {
            mediaNames.add(media.getName());
        }

        // Prompt the user to select a media item
        int choice = ui.promptMenu("Select a media from the results:", mediaNames);

        Media selected = foundMedia.get(choice - 1);
        System.out.println("You selected: " + selected.getName());

        return selected;
    }

    public void getListOfSaved() {
        ArrayList<String> list = new ArrayList<>();
        list = u.getWantsToSee(); // u er den bruger som er logget ind
        int choice = ui.promptMenu("Select media", list);
        list.get(choice).playMedia();
    }

    public void getListOfWatched() {
        ArrayList<String> list = new ArrayList<>();
        list = u.getSeenMedia(); // u er den bruger som er logget ind
        int choice = ui.promptMenu("Select media", list);
        list.get(choice).playMedia();
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