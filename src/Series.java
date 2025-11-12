import utility.TextUI;

public class Series extends Media implements  Playable{
    int season;
    int episode;
    int endYear;

    Series(String name, int releaseYear, double rating, String category, int season, int episode, int endYear) {
        super(name, releaseYear, rating, category);
        this.season = season;
        this.episode = episode;
        this.endYear = endYear;
    }

    public void playMedia(User u) {
        TextUI ui = new TextUI();
        ui.displayMsg("1. Play " + this);
        ui.displayMsg("2. Save " + this + " to your list");
        boolean continueLoop = true;
        while(continueLoop) {
            int choice = ui.promptNumeric("Type 1 or 2");
            if(choice == 1) {
                ui.displayMsg("Now playing: " + this);
                // tilføjer medie til brugerens seenMedia liste
                u.getSeenMedia().add(this);
                continueLoop = false;
            } else if(choice == 2) {
                ui.displayMsg(this + "has been added to your list");
                // tilføjer medie til brugerens wantsToSee liste
                u.getWantsToSee().add(this);
                continueLoop = false;
            } else {
                ui.displayMsg("Invalid number");
            }
            //skift "this" til "this.name" hvis vi ændrer toString
        }
    }

    public String toString() {
        return name;
    }
}