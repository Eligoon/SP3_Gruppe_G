import utility.TextUI;
import java.util.List;

public class Series extends Media implements Playable {
    int season;
    int episode;
    int endYear;
    List<String> seasons;   // for summary style
    String yearRange;       // for summary style

    // Detailed constructor
    Series(String name, int releaseYear, double rating, String category, int season, int episode, int endYear) {
        super(name, releaseYear, rating, category);
        this.season = season;
        this.episode = episode;
        this.endYear = endYear;
    }

    // Summary constructor for CSV loader
    Series(String name, String yearRange, double rating, String category, List<String> seasons) {
        super(name, 0, rating, category); // 0 placeholder for releaseYear
        this.yearRange = yearRange;
        this.seasons = seasons;
    }

    public void playMedia(User u) {
        TextUI ui = new TextUI();
        ui.displayMsg("1. Play " + getName());
        ui.displayMsg("2. Save " + getName() + " to your list");

        boolean continueLoop = true;
        while (continueLoop) {
            int choice = ui.promptNumeric("Type 1 or 2");
            if (choice == 1) {
                ui.displayMsg("Now playing: " + getName());
                // Only add if not already in the list
                if (!u.getSeenMedia().contains(this)) {
                    u.getSeenMedia().add(this);
                }
                continueLoop = false;
            } else if (choice == 2) {
                ui.displayMsg(getName() + " has been added to your list");
                // Only add if not already in the list
                if (!u.getWantsToSee().contains(this)) {
                    u.getWantsToSee().add(this);
                }
            }
        }
    }
}
