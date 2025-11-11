import utility.FileIO;
import utility.TextUI;

import java.io.File;
import java.util.*;

public class StreamingService {
    private Scanner scanner;
    private List<Movie> movies;
    private List<Series> series;
    private ArrayList<User> users = new ArrayList<>();
    private User currentUser = null;

    TextUI ui = new TextUI();
    FileIO IO = new FileIO();

    public boolean validateUser(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void createNewUser() {
        // Prompt for username and password
        ui.displayMsg("Creat a Netflix login. \nPlease Type your Username");
        String username = getScanner().nextLine();
        ui.displayMsg("Type your Password");
        String password = getScanner().nextLine();

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
            System.out.println("No media found with that name.");
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
        System.out.println("You selected: " + selected.getName());

        return selected;
    }

    public void getListOfSaved() {
        ArrayList<Media> list = new ArrayList<>();
        list = u.getWantsToSee(); // u er den bruger som er logget ind
        int choice = ui.promptMenu("Select media", list);
        list.get(choice).playMedia();
    }

    public void getListOfWatched() {
        ArrayList<Media> list = new ArrayList<>();
        list = u.getSeenMedia(); // u er den bruger som er logget ind
        int choice = ui.promptMenu("Select media", list);
        list.get(choice).playMedia();
    }

    public User logIn() {
        boolean continueLoop = true;
        User currentUser = null;
        while (continueLoop) {
            String usernameInput = ui.promptText("Type your username");
            for (int i = 0; i < users.size(); i++) {
                if (usernameInput.equals(users.get(i).getUsername())) {
                    currentUser = users.get(i);
                    break;
                }
            }
            if (currentUser == null) {
                ui.displayMsg("No username found, try again");
            } else {
                continueLoop = false;
            }
        }
        continueLoop = true;
        while (continueLoop) {
            String passwordInput = ui.promptText("Type password for " + currentUser.getUsername());
            if (passwordInput.equals(currentUser.getPassword())) {
                ui.displayMsg("Logged in as " + currentUser.getUsername());
                continueLoop = false;
            } else {
                ui.displayMsg("Wrong password, try again");
            }
        }
        return currentUser;
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