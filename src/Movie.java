public class Movie extends Media {

    private double lengthInMinutes;

    Movie(String name, int releaseYear, double rating, String category, double lengthInMinutes) {
        super(name, releaseYear, rating, category);
        this.lengthInMinutes = lengthInMinutes;
    }

    public String toString() {
        return name;
    }
}