import utility.TextUI;

public class Movie extends Media implements Playable {

    private double lengthInMinutes;

    Movie(String name, int releaseYear, double rating, String category, double lengthInMinutes) {
        super(name, releaseYear, rating, category);
        this.lengthInMinutes = lengthInMinutes;
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
                u.getSeenMedia().add(this); // mark as seen
                continueLoop = false;
            } else if (choice == 2) {
                ui.displayMsg(getName() + " has been added to your list");
                u.getWantsToSee().add(this); // save to watch later
                continueLoop = false;
            } else {
                ui.displayMsg("Invalid number");
            }
        }
    }

}