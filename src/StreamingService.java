import utility.FileIO;
import utility.TextUI;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class StreamingService {
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private User currentUser = null;
    private final List<Media> mediaLibrary = new ArrayList<>();


    TextUI ui = new TextUI();
    FileIO IO = new FileIO();

    public void start()
    {
        loadMedia();
        loadUsers();
        startMenu();
        loadMedia();
        mainMenu();
    }

    private void loadUsers() {
        List<String> lines = IO.readData("Data/userLogin.csv");
        for (String line : lines) {
            if (line.startsWith("username")) continue; // skip header
            String[] parts = line.split(";");
            if (parts.length == 2) {
                users.add(new User(parts[0], parts[1]));
            }
        }
    }

    private void loadMedia() {
        // Clear existing data so it always loads fresh from CSV
        movies.clear();
        series.clear();

        // Load Movies
        List<String> movieLines = IO.readData("Data/movies.csv");
        for (String line : movieLines) {
            if (line.startsWith("name")) continue; // skip header
            String[] parts = line.split(";");
            // Check for malformed/wrong lines
            if (parts.length < 5) continue;
            try {
                String name = parts[0];
                int year = Integer.parseInt(parts[1]);
                double rating = Double.parseDouble(parts[2]);
                String category = parts[3];
                double lenght = Double.parseDouble(parts[4]);

                movies.add(new Movie(name, year, rating, category, lenght));
            } catch (NumberFormatException e) {
                ui.displayMsg("Skipping invalid movie line: " + line);
            }
        }

        // Load Series
        List<String> seriesLines = IO.readData("Data/series.csv");
        for (String line : seriesLines) {
            if (line.startsWith("name")) continue;
            String[] parts = line.split(";");
            if (parts.length < 8) continue;
            try {
                String name = parts[0];
                int year = Integer.parseInt(parts[1]);
                double rating = Double.parseDouble(parts[2]);
                String category = parts[3];
                double lenght = Double.parseDouble(parts[4]);
                int season = Integer.parseInt(parts[5]);
                int episode = Integer.parseInt(parts[6]);
                int endYear = Integer.parseInt(parts[7]);

                series.add(new Series(name, year, rating, category, season, episode, endYear));
            } catch (NumberFormatException e) {
                ui.displayMsg("Skipping invalid series line: " + line);
            }
        }

        // Combine into mediaLibrary
        if (mediaLibrary != null) {
            mediaLibrary.clear();
            mediaLibrary.addAll(movies);
            mediaLibrary.addAll(series);
        }

        ui.displayMsg("Loaded " + movies.size() + " movies and " + series.size() + " series into library.");
    }


    private boolean validateUser(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void createNewUser() {
        // Prompt for username and password
        String username = ui.promptText("Create a Netflix login. \nPlease Type your Username");

        // Check if username already exists
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                ui.displayMsg("Username already exists. Try another one.");
                return; // exits the method, user must try again
            }
        }

        String password = ui.promptText("Type your Password");

        // Create new User instance
        User newUser = new User(username, password);

        // Add new user to the list
        users.add(newUser);

        // Prepare data to save all users
        ArrayList<String> establish = new ArrayList<>();
        for (User u : users) {
            establish.add(u.getUsername() + ";" + u.getPassword());
        }

        // Save all users to CSV
        String path = "Data/userLogin.csv";
        String header = "username;password";
        IO.saveData(establish, path, header);

        ui.displayMsg("User created successfully!");

        ui.displayMsg("Now log in with your new account.");
        // Optionally, automatically start login process:
        // User currentUser = logIn();
    }

    private void startMenu() {
        ArrayList<String> menuOptions = new ArrayList<>();
        menuOptions.add("Create new user");
        menuOptions.add("Log in");

        boolean continueLoop = true;
        while (continueLoop) {
            int choice = ui.promptMenu("Start menu", menuOptions);

            if (choice == 1) {
                createNewUser();
                continueLoop = false;
            } else if (choice == 2) {
                currentUser = logIn();
                continueLoop = false;
            } else {
                ui.displayMsg("Type a valid number");
            }
        }
    }

    private void mainMenu() {
        // Define menu options
        ArrayList<String> menuOptions = new ArrayList<>();
        menuOptions.add("Search for media by name");
        menuOptions.add("Search for media by category");
        menuOptions.add("Get list of your saved movies");
        menuOptions.add("Get list of movies you have already seen");
        menuOptions.add("Exit streaming service");


        while (true) {
            // Prompt user for input using TextUI
            int choice = ui.promptMenu("Main Menu", menuOptions);

            // Handle the menu choice
            switch (choice) {
                case 1:
                    searchByName();
                    break;
                case 2:
                    String category = ui.promptText("Enter a category to search for: ");
                    List<Media> results = searchByCategory(category);
                    break;
                case 3:
                    getListOfSaved();
                    break;
                case 4:
                    getListOfWatched();
                    break;
                case 5:
                    // Exit the program safely
                    ui.displayMsg("Exiting streaming service.");

                    System.exit(0);
                default:
                    // If the input doesn't match a valid option
                    ui.displayMsg("Invalid choice, try again.");
            }
        }
    }

    private Media searchByName() {
        String searchFor = ui.promptText("Enter the name of the media to search for: ").toLowerCase();

        ArrayList<Media> foundMedia = new ArrayList<>();

        // Search through all movies
        for (Movie m : movies) {
            if (m.getName().toLowerCase().contains(searchFor)) {
                foundMedia.add(m);
            }
        }

        // Search through all series
        for (Series s : series) {
            if (s.getName().toLowerCase().contains(searchFor)) {
                foundMedia.add(s);
            }
        }

        if (foundMedia.isEmpty()) {
            ui.displayMsg("No media found with that name.");
            return null;
        }

        // Create a list of media names for the menu
        ArrayList<String> mediaNames = new ArrayList<>();
        for (Media media : foundMedia) {
            mediaNames.add(media.getName());
        }

        // Prompt the user to select a media item
        int choice = ui.promptMenu("Select a media from the results:", mediaNames);

        Media selected = foundMedia.get(choice - 1);
        ui.displayMsg("You selected: " + selected.getName());

        return selected;
    }


    public ArrayList<Media> searchByCategory(String category) {
        ArrayList<Media> result = new ArrayList<>();

        if (category == null || category.isBlank()) {
            return result; // empty ArrayList
        }

        // Check if the category exists in Category.All
        boolean validCategory = false;
        for (String c : Category.All) {
            if (c.equalsIgnoreCase(category)) {
                validCategory = true;
                break;
            }
        }

        if (!validCategory) {
            return result; // return empty if invalid category
        }

        // Filter mediaLibrary and add matching items to result
        for (Media m : mediaLibrary) {
            if (m.getCategory() != null && m.getCategory().equalsIgnoreCase(category)) {
                result.add(m);
            }
        }

        return result;
    }


    private void getListOfSaved() {

        ArrayList<Media> list = currentUser.getWantsToSee();
        if (list == null || list.isEmpty()) {
            ui.displayMsg("You have no saved media.");
            return;
        }

        ArrayList<String> mediaNames = new ArrayList<>();
        for(Media media : list) {
            mediaNames.add(media.getName());
        }
        int choice = ui.promptMenu("Select media", mediaNames);
        list.get(choice -1).playMedia(currentUser);
    }

    private void getListOfWatched() {
        ArrayList<Media> list = currentUser.getWantsToSee();
        if (list == null || list.isEmpty()) {
            ui.displayMsg("You have no saved media.");
            return;
        }

        ArrayList<String> mediaNames = new ArrayList<>();
        for(Media media : list) {
            mediaNames.add(media.getName());
        }
        int choice = ui.promptMenu("Select media", mediaNames);
        list.get(choice -1).playMedia(currentUser);
    }

    private User logIn() {
        boolean loggedIn = false;
        User foundUser = null;

        while (!loggedIn) {
            String username = ui.promptText("Type your username");
            String password = ui.promptText("Type your password");

            if (validateUser(username, password)) {
                for (User u : users) {
                    if (u.getUsername().equals(username)) {
                        foundUser = u;
                        break;
                    }
                }

                ui.displayMsg("Logged in as " + username);
                loggedIn = true;
            } else {
                ui.displayMsg("Invalid username or password. Try again.");
            }
        }

        currentUser = foundUser;
        return currentUser;
    }


    // Getters and setters
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