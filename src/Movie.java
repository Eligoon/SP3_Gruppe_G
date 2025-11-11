import utility.TextUI;

public class Movie extends Media implements Playable {

    private double lengthInMinutes;

    Movie(String name, int releaseYear, double rating, String category, double lengthInMinutes) {
        super(name, releaseYear, rating, category);
        this.lengthInMinutes = lengthInMinutes;
    }
    public void playMedia() {
        TextUI ui = new TextUI();
        ui.displayMsg("1. Play " + this);
        ui.displayMsg("2. Save " + this + " to your list");
        boolean continueLoop = true;
        while(continueLoop) {
            int choice = ui.promptNumeric("Type 1 or 2");
            if(choice == 1) {
                ui.displayMsg("Now playing: " + this);
                //to do: tilføj film til brugerens seenMedia liste
                continueLoop = false;
            } else if(choice == 2) {
                ui.displayMsg(this + "has been added to your list");
                //to do: tilføj film til brugerens wantsToSee liste
                continueLoop = false;
            } else {
                ui.displayMsg("Invalid number");
            }
            //to do: håndter en numberFormatException hvis brugeren ikke taster et tal
            //skift "this" til "this.name" hvis vi ændrer toString
        }
    }



    public String toString() {
        return name;
    }
}