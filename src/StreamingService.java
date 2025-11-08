import java.util.*;

public class StreamingService {
    private Scanner scanner;
    private List<Movie> movies;
    private List<Series> series;
    private User currentUser;

    public StreamingService() {
        this.scanner = new Scanner(System.in);
        this.movies = new ArrayList<>();
        this.series = new ArrayList<>();
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}