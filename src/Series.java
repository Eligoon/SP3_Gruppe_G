public class Series extends Media {
    int season;
    int episode;
    int endYear;

    Series(String name, int releaseYear, double rating, String category, int season, int episode, int endYear) {
        super(name, releaseYear, rating, category);
        this.season = season;
        this.episode = episode;
        this.endYear = endYear;
    }

    public String toString() {
        return name;
    }
}